package br.com.nfast.api.repo.nfe;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.Produto;
import br.com.nfast.api.model.nfe.ItemNFe;
import br.com.nfast.api.model.nfe.NFe;
import br.com.nfast.api.model.nfe.NFeResumo;
import br.com.nfast.api.model.nfe.ParcelaNFe;
import br.com.nfast.api.repo.crm.EmpresaRepo;
import br.com.nfast.api.repo.estoque.ProdutoNcmRepo;
import br.com.nfast.api.repo.fiscal.NcmRepo;
import br.com.nfast.api.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NFeRepo extends DataRepository<NFe, Long> {
    @Autowired
    private EmpresaRepo empresaRepo;
    @Autowired
    private NFeResumoRepo nfeResumoRepo;
    @Autowired
    private ProdutoNcmRepo produtoNcmRepo;
    @Autowired
    private NcmRepo ncmRepo;

    private String ultimaChaveNota;
    private LocalDateTime ultimaImportacao;

    private Produto produto;

    public NFeRepo() {
        super(NFe.class);
    }

    public NFeResumo gravaNFe(final NFe nfe) {
        if (ultimaImportacao != null) {
            if (ultimaImportacao.plusSeconds(5).isAfter(LocalDateTime.now())) {
                if (Strings.equals(nfe.getNumChaveNfe(), ultimaChaveNota))
                    throw new RuntimeException("Nota Fiscal Importada a Menos de 5s");
            }
        }

        if (!empresaRepo.validaDataLimite(nfe.getCodEmpresa(), nfe.getDtaEntrada()))
            throw new RuntimeException("Lançamento Retroativo não Permitido - Data de Entrada " + Dates.format(nfe.getDtaEntrada()));

        BigInteger seqNota = nativeFindValue(query -> {
            query.add("SELECT COALESCE(grid,0) ");
            query.add("FROM nfe a ");
            query.add("WHERE a.chave_acesso = '" + nfe.getNumChaveNfe() + "' ");
        });

        if (Numbers.isNonEmpty(seqNota))
            throw new RuntimeException("Nota Fiscal já Existe - Sequência " + seqNota);

        if (nfe.getItens() == null)
            nfe.setItens(new ArrayList<>());

        boolean notaDespesa = false;

        for (ItemNFe item : nfe.getItens()) {

            if (item.getCodItem() == null) {
                notaDespesa = true;
                break;
            }
        }


        BigInteger nfPessoaEmpresa = gravaNotaFiscalPessoaEmpresa(nfe);
        BigInteger nfPessoaFornecedor = gravaNotaFiscalPessoaFornecedor(nfe);
        BigInteger mlid = nativeFindValue("SELECT pgd_gxid_f(pgd_sid, NEXTVAL('pgd_rid_seq')) FROM pgd_lsite");
        BigInteger notaFiscal = gravaNotaFiscal(nfe, nfPessoaEmpresa, nfPessoaFornecedor, mlid, notaDespesa);
        BigInteger nfeId = gravaNfe(nfe, notaFiscal);
        gravaXmlNfe(nfe, nfeId.longValue());
        gravaProtocoloNfe(nfe, nfeId.longValue());
        gravaNotaFiscalProduto(nfe, notaFiscal, nfPessoaFornecedor, mlid);
        gravaMovtoFinanceiro(nfe, mlid);

        ultimaChaveNota = nfe.getNumChaveNfe();
        ultimaImportacao = LocalDateTime.now();

        return nfeResumoRepo.obtem(notaFiscal);
    }

    private void gravaXmlNfe(NFe nfe, Long nfeId) {
        Query q = em.createNativeQuery("INSERT INTO nfe_xml(nfe, fonte_xml) VALUES (:nfe, :fonte_xml)");
        q.setParameter("nfe", nfeId);
        q.setParameter("fonte_xml", GZip.decodeB64AndUngzip(nfe.getXmlNfe()));
        q.executeUpdate();
    }

    private void gravaProtocoloNfe(NFe nfe, Long nfeId) {
        Query q = em.createNativeQuery("INSERT INTO nfe_protocolo(nfe, ts_proc, numero, digest, versao_sefaz, _id, tipo_protocolo) VALUES(:nfe, :ts_proc, :numero, :digest, :versao_sefaz, :_id, 1)");
        q.setParameter("nfe", nfeId);
        q.setParameter("ts_proc", Dates.utcToDateTime(nfe.getDhRecbtoNfe()));
        q.setParameter("numero", nfe.getNumProtocoloNfe());
        q.setParameter("digest", nfe.getDigValNfe());
        q.setParameter("versao_sefaz", nfe.getNumVersaoApliNfe());
        q.setParameter("_id", nfe.getIdProtocoloNfe());
        q.executeUpdate();
    }

    public void excluiNFe(String numChaveNfe, Long codEmpresa) {
        List<NFeResumo> notas = nfeResumoRepo.listaChave(codEmpresa, numChaveNfe);
        if (notas.isEmpty())
            return;

        if (notas.size() > 1)
            throw new RuntimeException("Foram encontradas " + notas.size() + " notas com essa chave");

        NFeResumo nfeResumo = notas.get(0);
        executeNative("DELETE FROM nota_fiscal_pessoa a WHERE exists (SELECT 1 FROM  nota_fiscal b WHERE b.destinatario = a.grid AND grid = " + nfeResumo.getSeqNota() + ")");
        executeNative("DELETE FROM nota_fiscal_pessoa a WHERE exists (SELECT 1 FROM  nota_fiscal b WHERE b.emitente = a.grid AND grid = " + nfeResumo.getSeqNota() + ")");
        executeNative("DELETE FROM nfe a WHERE nota_fiscal = " + nfeResumo.getSeqNota());
        executeNative("DELETE FROM lancto a WHERE exists (SELECT 1 FROM nota_fiscal_produto b WHERE b.lancto = a.grid AND b.nota_fiscal = " + nfeResumo.getSeqNota() + ")");
        executeNative("DELETE FROM nota_fiscal_produto WHERE nota_fiscal = " + nfeResumo.getSeqNota());
        executeNative("DELETE FROM movto a WHERE exists (SELECT 1 FROM nota_fiscal b WHERE b.mlid = a.mlid AND grid = " + nfeResumo.getSeqNota() + ")");
        executeNative("DELETE FROM nota_fiscal WHERE grid = " + nfeResumo.getSeqNota());
    }

    public BigInteger gravaNotaFiscalPessoaEmpresa(final NFe nfe) {
        StringList sql = new StringList();
        sql.add("INSERT INTO nota_fiscal_pessoa ( ");
        sql.add("	 fone, ");
        sql.add("    complemento, ");
        sql.add("    nome, ");
        sql.add("    pessoa, ");
        sql.add("    inscr_municipal, ");
        sql.add("    bairro, ");
        sql.add("    logradouro, ");
        sql.add("    nome_reduzido, ");
        sql.add("    numero, ");
        sql.add("    cep, ");
        sql.add("    pais, ");
        sql.add("    inscr_est, ");
        sql.add("    municipio, ");
        sql.add("    ordem, ");
        sql.add("    crt, ");
        sql.add("    cpf, ");
        sql.add("    uf, ");
        sql.add("    cnae, ");
        sql.add("    paf_hash) ");
        sql.add("( SELECT ");
        sql.add("	fone, ");
        sql.add("    complemento, ");
        sql.add("    nome, ");
        sql.add("    grid AS pessoa, ");
        sql.add("    inscr_municipal, ");
        sql.add("    bairro, ");
        sql.add("    logradouro, ");
        sql.add("    nome_reduzido, ");
        sql.add("    numero, ");
        sql.add("    cep, ");
        sql.add("    pais, ");
        sql.add("    inscr_est, ");
        sql.add("    municipio, ");
        sql.add("    (select max(ordem) +1 from nota_fiscal_pessoa where pessoa = a.grid) AS ordem, ");
        sql.add("    regime_apuracao AS CRT, ");
        sql.add("    cpf, ");
        sql.add("    uf, ");
        sql.add("    cnae, ");
        sql.add("    '' ");
        sql.add("FROM empresa a ");
        sql.add("WHERE codigo = :cod_empresa ");
        sql.add(" ) RETURNING grid ");

        Query q = em.createNativeQuery(sql.toString());
        q.setParameter("cod_empresa", nfe.getCodEmpresa());
        BigInteger nf_pessoa_empresa = Cast.of(q.getSingleResult());

        return nf_pessoa_empresa;
    }

    public BigInteger gravaNotaFiscalPessoaFornecedor(final NFe nfe) {
        StringList sql = new StringList();
        sql.add("INSERT INTO nota_fiscal_pessoa ( ");
        sql.add("  fone, ");
        sql.add("  nome, ");
        sql.add("  pessoa, ");
        sql.add("  bairro, ");
        sql.add("  logradouro, ");
        sql.add("  numero, ");
        sql.add("  cep, ");
        sql.add("  pais, ");
        sql.add("  inscr_est, ");
        sql.add("  municipio, ");
        sql.add("  ordem, ");
        sql.add("  crt, ");
        sql.add("  cpf, ");
        sql.add("  uf, ");
        sql.add("  paf_hash) ");
        sql.add("(SELECT ");
        sql.add("  fone, ");
        sql.add("  nome, ");
        sql.add("  a.grid AS pessoa, ");
        sql.add("  bairro, ");
        sql.add("  logradouro, ");
        sql.add("  numero, ");
        sql.add("  cep, ");
        sql.add("  pais, ");
        sql.add("  inscr_est, ");
        sql.add("  municipio, ");
        sql.add("  (SELECT max(ordem) +1 FROM nota_fiscal_pessoa WHERE pessoa = a.grid), ");
        sql.add("  regime_apuracao, ");
        sql.add("  cpf, ");
        sql.add("  uf, ");
        sql.add("  '' AS paf_hash ");
        sql.add("FROM pessoa a ");
        sql.add("WHERE codigo = :cod_pessoa ");
        sql.add(") RETURNING grid ");

        Query q = em.createNativeQuery(sql.toString());
        q.setParameter("cod_pessoa", nfe.getCodPessoaFornecedor());
        BigInteger nf_pessoa_fornecedor = Cast.of(q.getSingleResult());

        return nf_pessoa_fornecedor;

    }

    public BigInteger gravaNotaFiscal(final NFe nfe, BigInteger nf_pessoa_empresa, BigInteger nf_pessoa_fornecedor, BigInteger mlid, boolean notaDespesa) {

        StringList sql = new StringList();
        sql.add("INSERT INTO nota_fiscal ( ");
        sql.add("  fone, ");
        sql.add("  valor_ipi, ");
        sql.add("  base_subst, ");
        sql.add("  cep, ");
        sql.add("  mlid, ");
        sql.add("  municipio, ");
        sql.add("  hora_saida, ");
        sql.add("  cfop, ");
        sql.add("  valor_outr_des, ");
        sql.add("  base_icms, ");
        sql.add("  data_emissao, ");
        sql.add("  valor_cofins, ");
        sql.add("  valor_nota, ");
        sql.add("  tipo_emitente, ");
        sql.add("  tipo, ");
        sql.add("  valor_frete_avulso, ");
        sql.add("  versao, ");
        sql.add("  trans_frete, ");
        sql.add("  cidade, ");
        sql.add("  nome, ");
        sql.add("  valor_desconto, ");
        sql.add("  obs,");
        sql.add("  empresa, ");
        sql.add("  valor_subst, ");
        sql.add("  data_saida, ");
        sql.add("  valor_frete, ");
        sql.add("  pessoa, ");
        sql.add("  tipo_frete, ");
        sql.add("  valor_st_fcp, ");
        sql.add("  serie, ");
        sql.add("  numero_nota, ");
        sql.add("  finalidade, ");
        sql.add("  valor_icms, ");
        sql.add("  valor_seguro, ");
        sql.add("  emitente, ");
        sql.add("  valor_pis, ");
        sql.add("  cpf, ");
        sql.add("  destinatario, ");
        sql.add("  total_produto, ");
        sql.add("  bairro, ");
        sql.add("  numero, ");
        sql.add("  modelo, ");
        sql.add("  inscr_est, ");
        sql.add("  estado, ");
        sql.add("  quantidade, ");
        sql.add("  endereco, ");
        sql.add("  modelo_doc ) ");
        sql.add(" ( SELECT ");
        sql.add("  a.fone, "); //fone
        sql.add(nfe.getValIpi() + ","); //valor_ipi
        sql.add(nfe.getValBaseIcmsSubstituicao() + ","); //base_subst
        sql.add("  a.cep, "); //cep
        sql.add(mlid + ","); //mlid
        sql.add("  a.municipio, "); //municipio
        sql.add("  CURRENT_TIMESTAMP, "); //hora_saida
        sql.add(" (SELECT cfop FROM cfop WHERE REPLACE(cfop,'.','') = '" + nfe.getCodNaturezaOperacao() + "' LIMIT 1),"); //cfop
        sql.add(nfe.getValDespesaAcessoria() + ","); //valor_outr_des
        sql.add(nfe.getValBaseIcms() + ","); //base_icms
        sql.add("'" + nfe.getDtaEntrada() + "',"); //data_emissao
        sql.add(nfe.getValCofins() + ","); //valor_cofins
        sql.add(nfe.getValTotalNota() + ","); //valor_nota
        sql.add("  'T', "); //tipo_emitente

        if (notaDespesa == false)
            sql.add("  'C', "); //tipo
        else sql.add("  'D', ");

        sql.add(nfe.getValFreteCif() + ",");
        sql.add("'" + nfe.getNumVersaoApliNfe() + "',");
        sql.add("  'CONTRATACAO PELO EMITENTE', ");
        sql.add("  a.cidade, ");
        sql.add("  a.nome, ");
        sql.add("  '0', ");
        sql.add("'" + nfe.getDesMensagem() + "',");
        sql.add("  (SELECT grid FROM pessoa WHERE codigo = " + nfe.getCodEmpresa() + "), ");
        sql.add(nfe.getValIcmsSubstituicao() + ",");
        sql.add("'" + nfe.getDtaEmissao() + "',");
        sql.add(nfe.getValFreteCif() + ",");
        sql.add("  a.grid, ");
        sql.add("  '1', ");
        sql.add("  0.000000, ");
        sql.add(nfe.getNumSerie() + ",");
        sql.add("  CAST(" + nfe.getNumNota() + " AS INTEGER), ");
        sql.add("'" + nfe.getIndFinalidadeNota() + "',");
        sql.add(nfe.getValIcms() + ",");
        sql.add(nfe.getValSeguro() + ",");
        sql.add(nf_pessoa_fornecedor + ",");
        sql.add(nfe.getValPis() + ",");
        sql.add("  a.cpf, ");
        sql.add(nf_pessoa_empresa + ",");
        sql.add(nfe.getValTotalNota() + ",");
        sql.add("  a.bairro, ");
        sql.add("  a.numero, ");
        sql.add("  '55', ");
        sql.add("  a.inscr_est, ");
        sql.add("  a.estado, ");
        sql.add("  1.000000, ");
        sql.add("  a.endereco, ");
        sql.add("  '55' ");
        sql.add("FROM pessoa a");
        sql.add("WHERE codigo = " + nfe.getCodPessoaFornecedor());
        sql.add(" ) RETURNING grid ");

        Query q = em.createNativeQuery(sql.toString());
        BigInteger nota_fiscal = Cast.of(q.getSingleResult());

        return nota_fiscal;

    }

    public BigInteger gravaNfe(final NFe nfe, BigInteger nota_fiscal) {
        StringList sql = new StringList();
        sql.add("INSERT INTO nfe ( ");
        sql.add("  forma_emissao, ");
        sql.add("  avulsa, ");
        sql.add("  versao, ");
        sql.add("  nota_fiscal, ");
        sql.add("  chave_acesso, ");
        sql.add("  retorno, ");
        sql.add("  tipo_ambiente ");
        sql.add(" ) (SELECT ");
        sql.add("  1, ");
        sql.add("  FALSE, ");
        sql.add("  '4.00', ");
        sql.add(nota_fiscal + ",");
        sql.add("'" + nfe.getNumChaveNfe() + "',");
        sql.add("'" + nfe.getCodSituacaoNfe() + "',");
        sql.add("  :tipo_ambiente");
        sql.add(") RETURNING grid ");

        Query q = em.createNativeQuery(sql.toString());
        q.setParameter("tipo_ambiente", Numbers.asInt(nfe.getIndTipoAmbienteNfe()));

        BigInteger nfE = Cast.of(q.getSingleResult());

        /* q.setParameter("nota_fiscal", nota_fiscal);
        q.setParameter("chave_nfe", nfe.getNumChaveNfe());
        q.setParameter("cod_situacao_nfe", nfe.getCodSituacaoNfe());
        q.setParameter("tipo_ambiente", nfe.getIndTipoAmbienteNfe());*/


        return nfE;
    }

    public BigInteger gravaBaixaEstoque(final NFe nfe, ItemNFe item, BigInteger nf_pessoa_fornecedor, BigInteger mlid) {
        StringList sql = new StringList();
        sql.add("INSERT INTO lancto ( ");
        sql.add("hora, ");
        sql.add("empresa, ");
        sql.add("seq, ");
        sql.add("data_doc, ");
        sql.add("cst_cofins, ");
        sql.add("estacao, ");
        sql.add("produto, ");
        sql.add("deposito, ");
        sql.add("valor, ");
        sql.add("pessoa, ");
        sql.add("documento, ");
        sql.add("operacao, ");
        sql.add("tributacao, ");
        sql.add("mlid, ");
        sql.add("preco_unit, ");
        sql.add("data, ");
        sql.add("natureza_receita, ");
        sql.add("preco_unit_fiscal, ");
        sql.add("quantidade, ");
        sql.add("usuario, ");
        sql.add("cst_pis, ");
        sql.add("turno ) ");
        sql.add("(SELECT ");
        sql.add(" '" + nfe.getDtaDigitacao() + "',"); //hora
        sql.add("(SELECT grid FROM pessoa WHERE codigo = '" + nfe.getCodEmpresa() + "'),"); //empresa
        sql.add(" row_number() OVER (PARTITION by 0), "); //seq
        sql.add(" '" + nfe.getDtaEmissao() + "',"); //data_doc
        sql.add("'99', "); //cst_cofins
        sql.add("'WELLINGTON.WORKGROUP', "); //estacao
        sql.add(" (SELECT grid FROM produto WHERE codigo = '" + item.getCodItem() + "'),"); //produto
        sql.add(" (SELECT grid FROM deposito WHERE codigo = '" + item.getCodAlmoxarifado() + "'),"); //deposito
        sql.add(item.getValTotalItem() + ","); //valor
        sql.add(nf_pessoa_fornecedor + ","); //pessoa
        sql.add(nfe.getNumNota() + ","); //documento
        sql.add(" 'E', "); //operacao
        sql.add(" (SELECT cst FROM tributacao a INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) WHERE b.tipo = 'ICMS' AND b.cod_tributacao = " + item.getCodTributacaoIcms() + "),"); //tributacao
        sql.add(mlid + ","); //mlid
        sql.add(item.getValUnitario() + ","); //preco_unit
        sql.add(" '" + nfe.getDtaEmissao() + "',"); //data
        sql.add("NULL, "); //natureza_receita
        sql.add(item.getValUnitario() + ","); //preco_unit_fiscal
        sql.add(item.getQtdItemConvertido() + ","); //quantidade
        sql.add(" '" + nfe.getNomUsuario() + "',"); //usuario
        sql.add(item.getCodTributacaoPis() + ","); //cst_pis
        sql.add("99 ) RETURNING grid "); //turno

        Query q = em.createNativeQuery(sql.toString());
        BigInteger lancto = Cast.of(q.getSingleResult());

        return lancto;
    }

    public void gravaNotaFiscalProduto(final NFe nfe, BigInteger nota_fiscal, BigInteger nf_pessoa_fornecedor, BigInteger mlid) {
        StringList sql = new StringList();
        for (ItemNFe item : nfe.getItens()) {

            BigInteger lancto = null;

            if (item.getCodItem() != null) {
                lancto = gravaBaixaEstoque(nfe, item, nf_pessoa_fornecedor, mlid);
            }

            sql.clear();
            sql.add("INSERT INTO nota_fiscal_produto ( ");
            sql.add("  codigo_ncm, ");
            sql.add("  cest, ");
            sql.add("  valor_ipi, ");
            sql.add("  lancto, ");
            sql.add("  base_subst, ");
            sql.add("  cst_ipi, ");
            sql.add("  valor, ");
            sql.add("  qtde_unid, ");
            sql.add("  cfop_origem, ");
            sql.add("  numero_item, ");
            sql.add("  aliquota_icms_efetivo, ");
            sql.add("  cfop, ");
            sql.add("  valor_outr_des, ");
            sql.add("  base_icms, ");
            sql.add("  cst_origem, ");
            sql.add("  codigo_ajuste, ");
            //sql.add("  tipo, ");
            sql.add("  valor_icms_efetivo, ");
            sql.add("  descricao, ");
            sql.add("  codigo_barra, ");
            sql.add("  preco_unit, ");
            sql.add("  cst_cofins, ");
            sql.add("  valor_desconto, ");
            sql.add("  cst_pis, ");
            sql.add("  empresa, ");
            sql.add("  valor_subst, ");
            sql.add("  soma_total_nota, ");
            sql.add("  unid_med, ");
            sql.add("  perc_dif, ");
            sql.add("  numero_nota, ");
            sql.add("  perc_red_base_icms, ");
            sql.add("  base_icms_efetivo, ");
            sql.add("  valor_icms, ");
            sql.add("  codigo, ");
            sql.add("  valor_acrescimo, ");
            sql.add("  csosn, ");
            sql.add("  produto, ");
            sql.add("  nota_fiscal, ");
            sql.add("  cst, ");
            sql.add("  cst_tributacao, ");
            sql.add("  aliquota_ipi, ");
            sql.add("  subtotal, ");
            sql.add("  quantidade, ");
            sql.add("  aliquota_icms ");
            sql.add(") ( SELECT ");
            sql.add(" (SELECT codigo FROM ncm WHERE ex = 0 AND replace(codigo,'.','') = '" + item.getNcm() + "' LIMIT 1),"); //codigo_ncm
            sql.add("  (SELECT cest FROM produto WHERE codigo = '" + item.getCodItem() + "'), "); //cest
            sql.add(item.getValIpi() + ","); //valor_ipi
            sql.add(lancto + ","); //lancto
            sql.add(item.getValBcSubstituto() + ","); //base_subst
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoIpi() + "),"); //cst_ipi
            sql.add(item.getValTotalItem() / item.getQtdItem() + ","); //valor
            sql.add(item.getQtdItemConvertido() + ","); //qtde_unid
            sql.add(item.getCfopXml() + ","); //cfop_origem
            sql.add(item.getSeqItem() + ","); //numero_item
            sql.add(item.getPerAliquotaIcms() + ","); //aliquota_icms_efetivo
            sql.add(" (SELECT cfop FROM cfop WHERE REPLACE(cfop,'.','') = '" + item.getCodNaturezaOperacao() + "' LIMIT 1),"); //cfop
            sql.add(item.getValIcmsOutros() + ","); //valor_outr_des
            sql.add(item.getValBaseIcms() + ","); //base_icms
            sql.add(" (SELECT SUBSTRING(codigo for 1) FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoIcms() + "),"); //cst_origem
            sql.add("'',"); //codigo_ajuste
            sql.add(item.getValIcms() + ","); //valor_icms_efetivo

            if (lancto == null)
                sql.add("'" + item.getDesItemXml() + "',"); //descricao
            else sql.add("  (SELECT nome FROM produto WHERE codigo = '" + item.getCodItem() + "'),"); //descricao

            sql.add("'" + item.getCodBarra() + "',"); //codigo_barra
            sql.add(item.getValUnitario() + ","); //preco_unit
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoCofins() + "),"); //cst_cofins
            sql.add(item.getValDesconto() + ","); //valor_desconto
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoPis() + "),"); //cst_pis
            sql.add("  (SELECT grid FROM pessoa WHERE codigo = " + nfe.getCodEmpresa() + "), "); //empresa
            sql.add(item.getValIcmsSubstituicao() + ","); //valor_subst
            sql.add("true,"); //soma_total_nota
            sql.add(" (SELECT sgl_unidade FROM nfast_unidade WHERE cod_unidade = " + item.getCodUnidadeCompra() + "),"); //unid_med
            sql.add("0,"); //perc_dif
            sql.add(nfe.getNumNota() + ","); //numero_nota
            sql.add(null + ","); //perc_red_base_icms
            sql.add(item.getValBaseIcms() + ","); //base_icms_efetivo
            sql.add(item.getValBaseIcms() + ","); //valor_icms
            sql.add(item.getCodItem() + ","); //codigo
            sql.add(item.getValAcrescimo() + ","); //valor_acrescimo
            sql.add("  null,"); //csosn
            sql.add("  (SELECT grid FROM produto WHERE codigo = '" + item.getCodItem() + "'),"); //produto
            sql.add(nota_fiscal + ","); //nota_fiscal
            sql.add(" (SELECT SUBSTRING(codigo for 3) FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoIcms() + "),"); //cst
            sql.add(" (SELECT SUBSTRING(codigo from 2 for 2 ) FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoIcms() + "),"); //cst_tributacao
            sql.add(item.getPerAliquotaIpi() + ","); //aliquota_ipi
            sql.add(item.getValTotalItem() + ","); //subtotal
            sql.add(item.getQtdItem() + ","); //quantidade
            sql.add(item.getPerAliquotaIcms()); //aliquota_icms
            sql.add("  ) RETURNING grid ");

            Query q = em.createNativeQuery(sql.toString());
            BigInteger nf_produto = Cast.of(q.getSingleResult());

        }

    }

    public void gravaMovtoFinanceiro(final NFe nfe, BigInteger mlid) {
        StringList sql = new StringList();
        for (ParcelaNFe parcela : nfe.getParcelas()) {
            sql.clear();
            sql.add("INSERT INTO movto ( ");
            sql.add(" empresa, ");
            sql.add(" seq, ");
            sql.add(" conta_creditar, ");
            sql.add(" data_doc, ");
            sql.add(" motivo, ");
            sql.add(" estacao, ");
            sql.add(" vencto, ");
            sql.add(" valor, ");
            sql.add(" pessoa, ");
            sql.add(" documento, ");
            sql.add(" mlid, ");
            sql.add(" data, ");
            sql.add(" conta_debitar, ");
            sql.add(" usuario, ");
            sql.add(" turno, ");
            sql.add(" obs) ");
            sql.add("(SELECT ");
            sql.add(" (SELECT grid FROM empresa WHERE codigo = '" + nfe.getCodEmpresa() + "'),"); //empresa
            sql.add(" row_number() OVER (PARTITION by 0), "); //seq
            sql.add(" CASE "); //conta_creditar
            sql.add("   WHEN (SELECT a.conta ");
            sql.add("         FROM empresa_conta a ");
            sql.add("         INNER JOIN empresa b ON (b.grid = a.empresa), ");
            sql.add("                    motivo_movto c ");
            sql.add("         WHERE b.codigo = " + nfe.getCodEmpresa() + "");
            sql.add("         AND c.codigo = '" + nfe.getCodTipoCobranca() + "' ");
            sql.add("         AND a.conta ILIKE (c.conta_creditar||'.%')) IS NULL ");
            sql.add("    THEN (SELECT conta_creditar FROM motivo_movto WHERE codigo = '" + nfe.getCodTipoCobranca() + "') ");
            sql.add("   ELSE (SELECT a.conta ");
            sql.add("         FROM empresa_conta a ");
            sql.add("         INNER JOIN empresa b ON (b.grid = a.empresa), ");
            sql.add("                     motivo_movto c ");
            sql.add("         WHERE b.codigo = " + nfe.getCodEmpresa() + "");
            sql.add("         AND c.codigo = '" + nfe.getCodTipoCobranca() + "' ");
            sql.add("         AND a.conta ILIKE (c.conta_creditar||'.%')) END,  "); //conta_creditar
            sql.add(" '" + nfe.getDtaEntrada() + "',"); //data_doc
            sql.add(" (SELECT grid FROM motivo_movto WHERE codigo = '" + nfe.getCodTipoCobranca() + "'),"); //motivo
            sql.add(" '" + nfe.getNomUsuario() + "',"); //estacao
            sql.add(" '" + parcela.getDtaVencimento() + "',"); //vencto
            sql.add(parcela.getValParcela() + ","); //valor
            sql.add("(SELECT grid FROM pessoa WHERE codigo = '" + nfe.getCodPessoaFornecedor() + "'), "); //pessoa
            sql.add(nfe.getNumNota() + ","); //documento
            sql.add(mlid + ","); //mlid
            sql.add(" '" + nfe.getDtaEmissao() + "',"); //data
            sql.add(" CASE "); //conta_debitar
            sql.add("   WHEN (SELECT a.conta ");
            sql.add("         FROM empresa_conta a ");
            sql.add("         INNER JOIN empresa b ON (b.grid = a.empresa), ");
            sql.add("                    motivo_movto c ");
            sql.add("         WHERE b.codigo = " + nfe.getCodEmpresa() + "");
            sql.add("         AND c.codigo = '" + nfe.getCodTipoCobranca() + "' ");
            sql.add("         AND a.conta ILIKE (c.conta_debitar||'.%')) IS NULL ");
            sql.add("    THEN (SELECT conta_debitar FROM motivo_movto WHERE codigo = '" + nfe.getCodTipoCobranca() + "') ");
            sql.add("   ELSE (SELECT a.conta ");
            sql.add("         FROM empresa_conta a ");
            sql.add("         INNER JOIN empresa b ON (b.grid = a.empresa), ");
            sql.add("                     motivo_movto c ");
            sql.add("         WHERE b.codigo = " + nfe.getCodEmpresa() + "");
            sql.add("         AND c.codigo = '" + nfe.getCodTipoCobranca() + "' ");
            sql.add("         AND a.conta ILIKE (c.conta_debitar||'.%')) END,  "); //conta_debitar
            sql.add(" '" + nfe.getNomUsuario() + "',");
            sql.add("99, ");
            sql.add("'' ");
            sql.add(") RETURNING grid ");

            Query q = em.createNativeQuery(sql.toString());
            BigInteger nf_parcelas = Cast.of(q.getSingleResult());
        }
    }

    public NFeResumo buscaNfe(BigInteger grid) {
        NFeResumo nf = new NFeResumo();
        return nf;
    }

}

