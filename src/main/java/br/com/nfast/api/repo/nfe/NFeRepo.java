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

        BigInteger nfPessoaEmpresa = gravaNotaFiscalPessoaEmpresa(nfe);
        BigInteger nfPessoaFornecedor = gravaNotaFiscalPessoaFornecedor(nfe);
        BigInteger mlid = nativeFindValue("SELECT pgd_gxid_f(pgd_sid, NEXTVAL('pgd_rid_seq')) FROM pgd_lsite");
        BigInteger notaFiscal = gravaNotaFiscal(nfe, nfPessoaEmpresa, nfPessoaFornecedor, mlid);
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
        Query q = em.createNativeQuery("INSERT INTO nfe_xml(nfe, fonte_xml) VALUE(:nfe, :fonte_xml)");
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

    public BigInteger gravaNotaFiscal(final NFe nfe, BigInteger nf_pessoa_empresa, BigInteger nf_pessoa_fornecedor, BigInteger mlid) {
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
        sql.add("  a.fone, ");
        sql.add(nfe.getValIpi() + ",");
        sql.add(nfe.getValBaseIcmsSubstituicao() + ",");
        sql.add("  a.cep, ");
        sql.add(mlid + ",");
        sql.add("  a.municipio, ");
        sql.add("  CURRENT_TIMESTAMP, ");
        sql.add(" (SELECT cfop FROM cfop WHERE REPLACE(cfop,'.','') = '" + nfe.getCodNaturezaOperacao() + "' LIMIT 1),");
        sql.add(nfe.getValDespesaAcessoria() + ",");
        sql.add(nfe.getValBaseIcms() + ",");
        sql.add("  CURRENT_DATE, ");
        sql.add(nfe.getValCofins() + ",");
        sql.add(nfe.getValTotalNota() + ",");
        sql.add("  'T', ");
        sql.add("  'C', ");
        sql.add(nfe.getValFreteCif() + ",");
        sql.add("'" + nfe.getNumVersaoApliNfe() + "',");
        sql.add("  'CONTRATACAO PELO EMITENTE', ");
        sql.add("  a.cidade, ");
        sql.add("  a.nome, ");
        sql.add("  '0', ");
        sql.add("  'Empresa optante do simples nacional. Credito de ICMS de R$21.20 ");
        sql.add("   aliquota 1.86% ");
        sql.add("  Base calc. R$1140.00 Art. 23 da LC 123/2006->Valor aproximado de tributos R$ 0.00 (fonte IBPT).->->', ");
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

        /*q.setParameter("val_ipi", nfe.getValIpi());
        q.setParameter("val_base_st", nfe.getValBaseIcmsSubstituicao());
        q.setParameter("mlid", mlid);
        q.setParameter("cfop", nfe.getCodNaturezaOperacao());
        q.setParameter("valor_outr_des", nfe.getValDespesaAcessoria());
        q.setParameter("base_icms", nfe.getValBaseIcms());
        q.setParameter("valor_cofins", nfe.getValCofins());
        q.setParameter("valor_nota", nfe.getValTotalNota());
        q.setParameter("valor_frete_avulso", nfe.getValFreteCif());
        q.setParameter("versao", nfe.getNumVersaoApliNfe());
        q.setParameter("valor_subst", nfe.getValIcmsSubstituicao());
        q.setParameter("data_saida", nfe.getDtaEmissao());
        q.setParameter("valor_frete", nfe.getValFreteCif());
        q.setParameter("serie", nfe.getNumSerie());
        q.setParameter("numero_nota", nfe.getNumNota());
        q.setParameter("finalidade", nfe.getIndFinalidadeNota());
        q.setParameter("valor_icms", nfe.getValIcms());
        q.setParameter("valor_seguro", nfe.getValSeguro());
        q.setParameter("emitente", nf_pessoa_fornecedor);
        q.setParameter("valor_pis", nfe.getValPis());
        q.setParameter("destinatario", nf_pessoa_empresa);
        q.setParameter("total_produto", nfe.getValTotalNota());
        q.setParameter("cod_fornecedor", nfe.getCodPessoaFornecedor());
        BigInteger nota_fiscal = Cast.of(q.getSingleResult());*/

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
        sql.add(item.getQtdItem() + ","); //quantidade
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
            BigInteger lancto = gravaBaixaEstoque(nfe, item, nf_pessoa_fornecedor, mlid);

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
            sql.add(item.getValTotalItem() + ","); //valor
            sql.add(item.getQtdItem() + ","); //qtde_unid
            sql.add(" (SELECT cfop FROM cfop WHERE REPLACE(cfop,'.','') = '" + item.getCodNaturezaOperacao() + "' LIMIT 1),"); //cfop_origem
            sql.add(item.getSeqItem() + ","); //numero_item
            sql.add(item.getPerAliquotaIcms() + ","); //aliquota_icms_efetivo
            sql.add(" (SELECT cfop FROM cfop WHERE REPLACE(cfop,'.','') = '" + item.getCodNaturezaOperacao() + "' LIMIT 1),"); //cfop
            sql.add(item.getValIcmsOutros() + ","); //valor_outr_des
            sql.add(item.getValBaseIcms() + ","); //base_icms
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoIcms() + "),"); //cst_origem
            sql.add("'',"); //codigo_ajuste
            sql.add(item.getValIcms() + ","); //valor_icms_efetivo
            sql.add("  (SELECT nome FROM produto WHERE codigo = '" + item.getCodItem() + "'),"); //descricao
            sql.add("'" + item.getCodBarra() + "',"); //codigo_barra
            sql.add(item.getValUnitario() + ","); //preco_unit
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoCofins() + "),"); //cst_cofins
            sql.add(item.getValDesconto() + ","); //valor_desconto
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoPis() + "),"); //cst_pis
            sql.add(nfe.getCodEmpresa() + ","); //empresa
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
            sql.add("  CASE WHEN (('" + item.getCodTributacaoIcms() + "' ILIKE '5%') OR ('" + item.getCodTributacaoIcms() + "' ILIKE '1%')) THEN '" + item.getCodTributacaoIcms() + "' ELSE null END ,"); //csosn
            sql.add("  (SELECT grid FROM produto WHERE codigo = '" + item.getCodItem() + "'),"); //produto
            sql.add(nota_fiscal + ","); //nota_fiscal
            sql.add(" (SELECT codigo FROM nfast_tributacao WHERE cod_tributacao = " + item.getCodTributacaoIcms() + "),"); //cst
            sql.add(item.getCodTributacaoIcms() + ","); //cst_tributacao
            sql.add(item.getPerAliquotaIpi() + ","); //aliquota_ipi
            sql.add(item.getValTotalItem() + ","); //subtotal
            sql.add(item.getQtdItem() + ","); //quantidade
            sql.add(item.getPerAliquotaIcms()); //aliquota_icms
            sql.add("  ) RETURNING grid ");

            /*q.setParameter("codigo_ncm", item.getNcm() );
            q.setParameter("valor_ipi", item.getValIpi() );
            //q.setParameter("lancto, ");
            q.setParameter("base_subst", item.getValBcSubstituto() );
            q.setParameter("cst_ipi", item.getCodTributacaoIpi() );
            q.setParameter("valor", item.getValTotalItem() );
            q.setParameter("qtde_unid", item.getQtdItem() );
            q.setParameter("cfop_origem", item.getCodNaturezaOperacao() );
            q.setParameter("numero_item", item.getSeqItem() );
            q.setParameter("aliquota_icms_efetivo", item.getPerAliquotaIcms() );
            q.setParameter("cfop", item.getCodNaturezaOperacao() ) ;
            q.setParameter("valor_outr_des", item.getValIcmsOutros());
            q.setParameter("base_icms", item.getValBaseIcms() );
            q.setParameter("cst_origem", item.getCodTributacaoIcms() );
            q.setParameter("codigo_ajuste", item.getAjustes() );
            q.setParameter("tipo", item. );
            q.setParameter("valor_icms_efetivo", item.getValIcms());
            //##q.setParameter("descricao", produto.getDesItem());
            q.setParameter("codigo_barra", item.getCodBarra());
            q.setParameter("preco_unit", item.getValUnitario());
            q.setParameter("cst_cofins", item.getCodTributacaoCofins());;
            q.setParameter("valor_desconto", item.getValDesconto());;
            q.setParameter("cst_pis", item.getCodTributacaoPis());
            q.setParameter("empresa", nfe.getCodEmpresa());
            q.setParameter("valor_subst", item.getValIcmsSubstituicao());
            q.setParameter("soma_total_nota", item.getValTotalItem());
            q.setParameter("unid_med", item.getCodUnidadeCompra());
            q.setParameter("perc_dif", "");
            q.setParameter("numero_nota", nfe.getNumNota());
            q.setParameter("perc_red_base_icms", null);
            q.setParameter("base_icms_efetivo", item.getValBaseIcms());
            q.setParameter("valor_icms", item.getValBaseIcms());
            q.setParameter("codigo", item.getNumItemXml());;
            q.setParameter("valor_acrescimo", item.getValAcrescimo());
            q.setParameter("csosn", item.getCodTributacaoIcms());
            q.setParameter("produto", item.getCodItem());
            q.setParameter("nota_fiscal", nota_fiscal);
            q.setParameter("cst", item.getCodTributacaoIcms());
            q.setParameter("cst_tributacao", item.getCodTributacaoIcms());
            //q.setParameter("aliquota_ipi", item.getPerAliquotaIpi());
            //q.setParameter("subtotal", item.getValTotalItem());
            //q.setParameter("quantidade", item.getQtdItem());
            //q.setParameter("aliquota_icms", item.getPerAliquotaIcms()); */

            Query q = em.createNativeQuery(sql.toString());
            BigInteger nf_produto = Cast.of(q.getSingleResult());

        }

    }

    public void gravaMovtoFinanceiro(final NFe nfe, BigInteger mlid) {
        StringList sql = new StringList();
        for (ParcelaNFe parcela : nfe.getParcelas()) {
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
            sql.add(" (SELECT grid FROM empresa WHERE codigo = '" + nfe.getCodEmpresa() + "'),");
            sql.add(" row_number() OVER (PARTITION by 0), ");
            sql.add(" (SELECT conta_creditar FROM motivo_movto WHERE codigo = '" + nfe.getCodTipoCobranca() + "'),");
            sql.add(" '" + nfe.getDtaEmissao() + "',");
            sql.add(nfe.getCodTipoCobranca() + ",");
            sql.add(" '" + nfe.getNomUsuario() + "',");
            sql.add(" '" + nfe.getDtaEntrada() + "',");
            sql.add(parcela.getValParcela() + ",");
            sql.add("(SELECT grid FROM pessoa WHERE codigo = '" + nfe.getCodPessoaFornecedor() + "'),");
            sql.add(nfe.getNumNota() + ",");
            sql.add(mlid + ",");
            sql.add(" '" + parcela.getDtaVencimento() + "',");
            sql.add(" (SELECT conta_debitar FROM motivo_movto WHERE codigo = '" + nfe.getCodTipoCobranca() + "'),");
            sql.add(" '" + nfe.getNomUsuario() + "',");
            sql.add("99, ");
            sql.add("'') RETURNING grid");

            Query q = em.createNativeQuery(sql.toString());
            BigInteger nf_parcelas = Cast.of(q.getSingleResult());
        }
    }

    public NFeResumo buscaNfe(BigInteger grid) {
        NFeResumo nf = new NFeResumo();
        return nf;
    }

}

