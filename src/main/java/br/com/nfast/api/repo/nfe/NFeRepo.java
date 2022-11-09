package br.com.nfast.api.repo.nfe;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoNcm;
import br.com.nfast.api.model.fiscal.Ncm;
import br.com.nfast.api.model.nfe.*;
import br.com.nfast.api.repo.crm.EmpresaRepo;
import br.com.nfast.api.repo.estoque.ProdutoNcmRepo;
import br.com.nfast.api.repo.fiscal.NcmRepo;
import br.com.nfast.api.utils.Assert;
import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        Integer seqLivro = nativeFindValue(query -> {
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

        int seqMensNfRel = 0;
        int seqAjDocFiscal = 0;

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
