package br.com.nfast.api.repo.nfe;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoNcm;
import br.com.nfast.api.model.fiscal.Ncm;
import br.com.nfast.api.model.nfe.*;
import br.com.nfast.api.repo.crm.EmpresaRepo;
import br.com.nfast.api.repo.estoque.ProdutoNcmRepo;
import br.com.nfast.api.repo.fiscal.NcmRepo;
import br.com.nfast.api.utils.*;
import org.hibernate.tool.schema.extract.internal.InformationExtractorJdbcDatabaseMetaDataImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NFeRepo extends DataRepository<NFe, Integer> {
    @Autowired
    private EmpresaRepo empresaRepo;
    @Autowired
    private NFeResumoRepo nfeResumoRepo;
    @Autowired
    private ProdutoNcmRepo produtoNcmRepo;
    @Autowired
    private NcmRepo ncmRepo;

    public NFeRepo() {
        super(NFe.class);
    }

    public NFe gravaNFe(final NFe nfe) {
        if (!empresaRepo.validaDataLimite(nfe.getCodEmpresa(), nfe.getDtaEntrada()))
            throw new RuntimeException("Lançamento Retroativo não Permitido - Data de Entrada " + Dates.format(nfe.getDtaEntrada()));

        if (nfe.getItens() == null)
            nfe.setItens(new ArrayList<>());

        /*Integer seqLivro = nativeFindValue(query -> {
            query.add("SELECT a.seq_livro ");
            query.add("FROM tab_livro_fiscal a ");
            query.add("WHERE a.cod_empresa = " + nfe.getCodEmpresa() + " ");
            query.add("AND '" + Dates.format(nfe.getDtaEntrada(), "yyyy-MM-dd") + "' BETWEEN a.dta_periodo_inicial AND a.dta_periodo_final ");
            query.add("AND a.ind_tipo = 'E' ");
            query.add("AND a.ind_status IN('C', 'E') ");
        });

        if (Numbers.isNonEmpty(seqLivro))
            throw new RuntimeException("Livro Fiscal de Sequência " + seqLivro + " Encontra-se Confirmado ou Encerrado");

        if (Numbers.isEmpty(nfe.getSeqNota()))
            nfe.setSeqNota(nativeFindValue("SELECT CAST(nextval('gen_nota_fiscal_entrada') as INTEGER)"));
*/
        int seqMensNfRel = 0;
        int seqAjDocFiscal = 0;

        StringList sql = new StringList();

        sql.clear();
        sql.add("INSERT INTO nota_fiscal_pessoa ( ");
        sql.add("	fone, ");
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
        sql.add("WHERE codigo = :cod_empresa  ");
        sql.add(" ) RETURNING grid ");

        Query q = em.createNativeQuery(sql.toString());
        q.setParameter("cod_empresa", nfe.getCodEmpresa());
        BigInteger nf_pessoa_empresa = Cast.of(q.getSingleResult());

        sql.clear();
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
        sql.add("  pessoa, ");
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

        q = em.createNativeQuery(sql.toString());
        q.setParameter("cod_pessoa", nfe.getCodPessoaFornecedor());
        BigInteger nf_pessoa_fornecedor = Cast.of(q.getSingleResult());

        Long mlid = nativeFindValue("SELECT pgd_gxid_f(pgd_sid::int4, NEXTVAL('pgd_rid_seq')::int8) FROM pgd_lsite) ");

        sql.clear();
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
        sql.add("  obs,empresa, ");
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
        sql.add("  modelo_doc) ");
        sql.add(" ) SELECT (");
        sql.add("  a.fone, ");
        sql.add("  :val_ipi, ");
        sql.add("  :val_base_st, ");
        sql.add("  a.cep, ");
        sql.add("  :mlid, ");
        sql.add("  a.municipio, ");
        sql.add("  CURRENT_TIMESTAMP, ");
        sql.add("  :cfop, ");
        sql.add("  :valor_outr_des, ");
        sql.add("  :base_icms, ");
        sql.add("  CURRENT_DATE, ");
        sql.add("  :valor_cofins, ");
        sql.add("  :valor_nota, ");
        sql.add("  'T' AS tipo_emitente, ");
        sql.add("  'C' AS tipo, ");
        sql.add("  :valor_frete_avulso, ");
        sql.add("  :versao, ");
        sql.add("  'CONTRATACAO PELO EMITENTE' AS trans_frete, ");
        sql.add("  a.cidade, ");
        sql.add("  a.nome, ");
        sql.add("  'Empresa optante do simples nacional. Credito de ICMS de R$21.20, ");
        sql.add("  aliquota 1.86%, ");
        sql.add("  Base calc. R$1140.00 Art. 23 da LC 123/2006->Valor aproximado de tributos R$ 0.00 (fonte IBPT).->->', ");
        sql.add("  (SELECT grid FROM pessoa WHERE codigo = " + nfe.getCodEmpresa() + "), ");
        sql.add("  :valor_subst, ");
        sql.add("  :data_saida, ");
        sql.add("  :valor_frete , ");
        sql.add("  a.grid AS pessoa, ");
        sql.add("  '1' AS tipo_frete, ");
        sql.add("  0.000000 AS valor_st_fcp, ");
        sql.add("  :serie, ");
        sql.add("  :numero_nota, ");
        sql.add("  :finalidade, ");
        sql.add("  :valor_icms, ");
        sql.add("  :valor_seguro, ");
        sql.add("  :emitente, ");
        sql.add("  :valor_pis, ");
        sql.add("  a.cpf, ");
        sql.add("  :destinatario  ");
        sql.add("  :total_produto, ");
        sql.add("  a.bairro, ");
        sql.add("  a.numero, ");
        sql.add("  '55' AS modelo_doc, ");
        sql.add("  a.inscr_est, ");
        sql.add("  a.estado, ");
        sql.add("  1.000000, ");
        sql.add("  a.endereco, ");
        sql.add("  '55' AS modelo_doc  ");
        sql.add("FROM pessoa a");
        sql.add("WHERE codigo = :cod_fornecedor ");
        sql.add(" ) RETURNING grid ");

        q = em.createNativeQuery(sql.toString());
        q.setParameter("cod_fornecedor", nfe.getCodPessoaFornecedor());
        q.setParameter("val_ipi", nfe.getValIpi());
        q.setParameter("val_base_st", nfe.getValBaseIcmsSubstituicao());
        q.setParameter("mlid", mlid);
        q.setParameter("cfop", nfe.getCodNaturezaOperacao());
        q.setParameter("valor_outr_des", nfe.getValDespesaAcessoria());
        q.setParameter("base_icms", nfe.getValBaseIcms());
        q.setParameter("valor_cofins", nfe.getValCofins());
        q.setParameter("valor_nota", nfe.getValTotalNota());
        q.setParameter("valor_frete_avulso", nfe.getValFreteCif());
        q.setParameter("versao", nfe.getNumVersaoApliNfe());
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
        q.setParameter("valor_subst", nfe.getValIcmsSubstituicao());
        BigInteger nota_fiscal = Cast.of(q.getSingleResult());

        sql.clear();
        sql.add("INSERT INTO nfe ( ");
        sql.add("  forma_emissao, ");
        sql.add("  avulsa, ");
        sql.add("  versao, ");
        sql.add("  nota_fiscal, ");
        sql.add("  chave_acesso, ");
        sql.add("  retorno, ");
        sql.add("  tipo_ambiente ");
        sql.add(" ) ( SELECT ");
        sql.add("  1, ");
        sql.add("  FALSE, ");
        sql.add("  '4.00', ");
        sql.add("  :nota_fiscal, ");
        sql.add("  :chave_nfe, ");
        sql.add("  :cod_situacao_nfe, ");
        sql.add("  :tipo_ambiente, ");
        sql.add(")  ");

        q.setParameter("nota_fiscal", nfe.getNumNota());
        q.setParameter("chave_nfe", nfe.getNumChaveNfe());
        q.setParameter("cod_situacao_nfe", nfe.getCodSituacaoNfe());
        q.setParameter("tipo_ambiente", nfe.getIndTipoAmbienteNfe());

        for (ItemNFe item : nfe.getItens()) {
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
            sql.add("  tipo, ");
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
            sql.add("  aliquota_icms, ");
            sql.add("  paf_hash ");
            sql.add(") ( SELECT ");
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
            sql.add("  tipo, ");
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
            sql.add("  aliquota_icms, ");
            sql.add("  paf_hash ");
            sql.add("  ) ");

            /*q.setParameter("nota_fiscal", nfe.getItens().get
                    q.setParameter("codigo_ncm, ");
            q.setParameter("cest, ");
            q.setParameter("valor_ipi, ");
            q.setParameter("lancto, ");
            q.setParameter("base_subst, ");
            q.setParameter("cst_ipi, ");
            q.setParameter("valor, ");
            q.setParameter("qtde_unid, ");
            q.setParameter("cfop_origem, ");
            q.setParameter("numero_item, ");
            q.setParameter("aliquota_icms_efetivo, ");
            q.setParameter("cfop, ");
            q.setParameter("valor_outr_des, ");
            q.setParameter("base_icms, ");
            q.setParameter("cst_origem, ");
            q.setParameter("codigo_ajuste, ");
            q.setParameter("tipo, ");
            q.setParameter("valor_icms_efetivo, ");
            q.setParameter("descricao, ");
            q.setParameter("codigo_barra, ");
            q.setParameter("preco_unit, ");
            q.setParameter("cst_cofins, ");
            q.setParameter("valor_desconto, ");
            q.setParameter("cst_pis, ");
            q.setParameter("empresa, ");
            q.setParameter("valor_subst, ");
            q.setParameter("soma_total_nota, ");
            q.setParameter("unid_med, ");
            q.setParameter("perc_dif, ");
            q.setParameter("numero_nota, ");
            q.setParameter("perc_red_base_icms, ");
            q.setParameter("base_icms_efetivo, ");
            q.setParameter("valor_icms, ");
            q.setParameter("codigo, ");
            q.setParameter("valor_acrescimo, ");
            q.setParameter("csosn, ");
            q.setParameter("produto, ");
            q.setParameter("nota_fiscal, ");
            q.setParameter("cst, ");
            q.setParameter("cst_tributacao, ");
            q.setParameter("aliquota_ipi, ");
            q.setParameter("subtotal, ");
            q.setParameter("quantidade, ");
            q.setParameter("aliquota_icms, ");
            q.setParameter("paf_hash ")
*/
        }

        if (nfe.getConhecimentoFrete() != null) {
            nfe.getConhecimentoFrete().setSeqNota(nfe.getSeqNota());
            nfe.getConhecimentoFrete().setNfe(nfe);
            if (nfe.getConhecimentoFrete().getParcelas() != null) {
                for (ConhecimentoFretePag parcela : nfe.getConhecimentoFrete().getParcelas()) {
                    Assert.nonEmpty(parcela.getSeqParcela(), "seqParcela não informada nas parcelas do conhecimento");

                    if (parcela.getId() == null) {
                        parcela.setId(new ConhecimentoFretePagId());
                        parcela.getId().setConhecimento(nfe.getConhecimentoFrete());
                        parcela.getId().setSeqParcela(parcela.getSeqParcela());
                    }
                }
            }
        }

        for (ItemNFe item : nfe.getItens()) {
            Assert.nonEmpty(item.getSeqItem(), "seqItem não informada nos itens da nota");

            if (item.getId() == null) {
                item.setId(new ItemNFeId());
                item.getId().setNfe(nfe);
                item.getId().setSeqItem(item.getSeqItem());
            }

            if (item.getDespesa() != null) {
                item.getDespesa().prepareToSave();
                if (Numbers.isEmpty(item.getDespesa().getSeqDespesa()))
                    item.getDespesa().setSeqDespesa(nativeFindValue("SELECT CAST(nextval('gen_outras_despesas') as INTEGER)"));
            }

            if (item.getAjustes() != null) {
                for (AjusteDocFiscal ajuste : item.getAjustes()) {
                    ajuste.setSeqNota(nfe.getSeqNota());
                    ajuste.setSeqItem(item.getSeqItem());
                    if (Numbers.isEmpty(ajuste.getCodItem()))
                        ajuste.setCodItem(item.getCodItem());

                    if (Numbers.isEmpty(ajuste.getSeqAjDocFiscal())) {
                        if (seqAjDocFiscal <= 0)
                            seqAjDocFiscal = maxSeqAjDocFiscal();

                        seqAjDocFiscal++;
                        ajuste.setSeqAjDocFiscal(seqAjDocFiscal);
                    }
                }
            }
        }

        if (nfe.getParcelas() != null) {
            for (ParcelaNFe parcela : nfe.getParcelas()) {
                if (parcela.getId() == null) {
                    parcela.setId(new ParcelaNFeId());
                    parcela.getId().setNfe(nfe);
                    parcela.getId().setSeqParcela(parcela.getSeqParcela());
                }
            }
        }

        if (nfe.getPagtosBanco() != null) {
            for (NFePagtoBanco pagtoBanco : nfe.getPagtosBanco()) {
                if (pagtoBanco.getId() == null) {
                    pagtoBanco.setId(new NFePagtoBancoId());
                    pagtoBanco.getId().setNfe(nfe);
                    pagtoBanco.getId().setSeqPagamento(pagtoBanco.getSeqPagamento());
                }
            }
        }

        if (nfe.getMensagens() != null) {
            for (MensNF mensagem : nfe.getMensagens()) {
                mensagem.setNfe(nfe);
                if (Numbers.isEmpty(mensagem.getSeqMensNfRel())) {
                    if (seqMensNfRel <= 0)
                        seqMensNfRel = maxSeqMensNfRel();

                    seqMensNfRel++;
                    mensagem.setSeqMensNfRel(seqMensNfRel);
                }
            }
        }

        if (nfe.getAjustes() != null) {
            for (AjusteDocFiscal ajuste : nfe.getAjustes()) {
                ajuste.setSeqNota(nfe.getSeqNota());
                ajuste.setSeqItem(0);
                ajuste.setCodItem(0);

                if (Numbers.isEmpty(ajuste.getSeqAjDocFiscal())) {
                    if (seqAjDocFiscal <= 0)
                        seqAjDocFiscal = maxSeqAjDocFiscal();

                    seqAjDocFiscal++;
                    ajuste.setSeqAjDocFiscal(seqAjDocFiscal);
                }
            }
        }

        if (Strings.equals(nfe.getAtualizaNcm(), "S"))
            atualizaNcm(nfe);

        NFe nf = save(nfe);
        executeNative("UPDATE tab_nota_fiscal_entrada SET seq_nota = seq_nota WHERE seq_nota = " + nf.getSeqNota());
        return nf;
    }

    private void atualizaNcm(NFe nfe) {
        for (ItemNFe item : nfe.getItens()) {
            if (Strings.isNonEmpty(item.getNcm())) {
                ProdutoNcm produtoNcm = produtoNcmRepo.findById(item.getCodItem()).orElse(null);
                if (produtoNcm != null) {
                    Ncm ncm = null;
                    if (Numbers.isNonEmpty(produtoNcm.getSeqNcm()))
                        ncm = ncmRepo.findById(produtoNcm.getSeqNcm()).orElse(null);

                    if ((ncm == null) || Strings.diff(ncm.getCodNcm(), item.getNcm())) {
                        ncm = ncmRepo.findBy("codNcm", item.getNcm());
                        if (ncm != null) {
                            produtoNcm.setSeqNcm(ncm.getSeqNcm());
                            produtoNcmRepo.save(produtoNcm);
                        }
                    }
                }
            }
        }
    }

    private Integer maxSeqMensNfRel() {
        return nativeFindValue("SELECT COALESCE(MAX(seq_mens_nf_rel), 0) FROM tab_mens_nf_rel");
    }

    private Integer maxSeqAjDocFiscal() {
        return nativeFindValue("SELECT COALESCE(MAX(seq_aj_doc_fiscal), 0) FROM tab_aj_doc_fiscal");
    }

    public void excluiNFe(String numChaveNfe, Integer codEmpresa) {
        List<NFeResumo> notas = nfeResumoRepo.listaChave(codEmpresa, numChaveNfe);
        if (notas.isEmpty())
            return;
        if (notas.size() > 1)
            throw new RuntimeException("Foram encontradas " + notas.size() + " notas com essa chave");

        NFeResumo nfeResumo = notas.get(0);
        Integer seqLivro = nativeFindValue(query -> {
            query.add("SELECT a.seq_livro ");
            query.add("FROM tab_livro_fiscal a ");
            query.add("WHERE a.cod_empresa = " + nfeResumo.getCodEmpresa() + " ");
            query.add("AND '" + Dates.format(nfeResumo.getDtaEntrada(), "yyyy-MM-dd") + "' BETWEEN a.dta_periodo_inicial AND a.dta_periodo_final ");
            query.add("AND a.ind_tipo = 'E' ");
            query.add("AND a.ind_status IN('C', 'E') ");
        });

        if (Numbers.isNonEmpty(seqLivro))
            throw new RuntimeException("Livro Fiscal de Sequência " + seqLivro + " Encontra-se Confirmado ou Encerrado");

        executeNative("DELETE FROM tab_nota_fiscal_entrada WHERE seq_nota = " + nfeResumo.getSeqNota());
    }

}
