package br.com.nfast.api.repo.conhecimento;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.dto.ConhecimentoDTO;
import br.com.nfast.api.dto.ParcelaDespesaDTO;
import br.com.nfast.api.dto.RateioDespesaDTO;
import br.com.nfast.api.model.adm.Despesa;
import br.com.nfast.api.model.adm.ParcelaDespesa;
import br.com.nfast.api.model.adm.RateioDespesa;
import br.com.nfast.api.model.conhecimento.Conhecimento;
import br.com.nfast.api.utils.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConhecimentoRepo extends DataRepository<Conhecimento, Integer> {

    @Autowired
    private ConhecimentoNotaResumoRepo conhecimentoNotaResumoRepo;
    @Autowired
    private ConhecimentoNotaRepo conhecimentoNotaRepo;

    public ConhecimentoRepo() {
        super(Conhecimento.class);
    }

    public Conhecimento obtemConhecimento(String chave) {
        return null;
        //return findBy("numChaveNfe", chave);
    }

    public void excluiConhecimento(Long seqConhecimento, Long seqNotaEntrada) {
        /*var qtdNotas = conhecimentoNotaResumoRepo.obtemQtdConhecimentoNota(seqConhecimento);
        conhecimentoNotaRepo.excluiConhecimentoNota(seqNotaEntrada);
        if (Numbers.equals(Numbers.asInt(qtdNotas), 1))
            executeNative("DELETE FROM tab_conhecimento WHERE seq_conhecimento = " + seqConhecimento);*/
    }

    public Conhecimento gravarConhecimento(ConhecimentoDTO conhecimento) {
        return null;
        /*var salvarConhecimento = preencherConhecimento(conhecimento);
        return save(salvarConhecimento);*/
    }

    public Conhecimento preencherConhecimento(ConhecimentoDTO conhecimentoDTO) {
        Conhecimento conhecimento = new Conhecimento();
        Integer seqConhecimento = getSeqConhecimento(conhecimentoDTO.getSeqConhecimento());

        conhecimento.setSeqConhecimento(seqConhecimento);
        conhecimento.setSeqModal(conhecimentoDTO.getSeqModal());
        conhecimento.setCodEspecieCaixa(conhecimentoDTO.getCodEspecieCaixa());
        conhecimento.setCodModeloDocumento(conhecimentoDTO.getCodModeloDocumento());
        conhecimento.setCodNaturezaOperacao(conhecimentoDTO.getCodNaturezaOperacao());
        conhecimento.setCodPessoaTransportadora(conhecimentoDTO.getCodPessoaTransportadora());
        conhecimento.setCodTipoCobranca(conhecimentoDTO.getCodTipoCobranca());
        conhecimento.setDesMarcaVolume(conhecimentoDTO.getDesMarcaVolume());
        conhecimento.setDesEspecieVolume(conhecimentoDTO.getDesEspecieVolume());
        conhecimento.setDtaEmissao(conhecimentoDTO.getDtaEmissao());
        conhecimento.setDtaPredatadoBanco(conhecimentoDTO.getDtaPredatadoBanco());
        conhecimento.setIndTipofrete(conhecimentoDTO.getIndTipofrete());
        conhecimento.setIndCreditaIcms(conhecimentoDTO.getIndCreditaIcms());
        conhecimento.setNumConhecimento(conhecimentoDTO.getNumConhecimento());
        conhecimento.setNumDocCaixa(conhecimentoDTO.getNumDocCaixa());
        conhecimento.setNumDocBanco(conhecimentoDTO.getNumDocBanco());
        conhecimento.setNumMnemonico(conhecimentoDTO.getNumMnemonico());
        conhecimento.setNumPlacaVeiculo1(conhecimentoDTO.getNumPlacaVeiculo1());
        conhecimento.setNumPlacaVeiculo2(conhecimentoDTO.getNumPlacaVeiculo2());
        conhecimento.setNumPlacaVeiculo3(conhecimentoDTO.getNumPlacaVeiculo3());
        conhecimento.setNumSerie(conhecimentoDTO.getNumSerie());
        conhecimento.setNumSubSerie(conhecimentoDTO.getNumSubSerie());
        conhecimento.setUfVeiculo1(conhecimentoDTO.getUfVeiculo1());
        conhecimento.setUfVeiculo2(conhecimentoDTO.getUfVeiculo2());
        conhecimento.setUfVeiculo3(conhecimentoDTO.getUfVeiculo3());
        conhecimento.setValDespesaAcessoria(conhecimentoDTO.getValDespesaAcessoria());
        conhecimento.setValFrete(conhecimentoDTO.getValFrete());
        conhecimento.setValSeguro(conhecimentoDTO.getValSeguro());
        conhecimento.setValBaseIcms(conhecimentoDTO.getValBaseIcms());
        conhecimento.setValIcms(conhecimentoDTO.getValIcms());
        conhecimento.setValIcmsNaoTributado(conhecimentoDTO.getValIcmsNaoTributado());
        conhecimento.setValIcmsOutros(conhecimentoDTO.getValIcmsOutros());
        conhecimento.setValPagamentoCaixa(conhecimentoDTO.getValPagamentoCaixa());
        conhecimento.setValPagamentoBanco(conhecimentoDTO.getValPagamentoBanco());
        conhecimento.setValAliquota(conhecimentoDTO.getValAliquota());
        conhecimento.setValFretePesoVolume(conhecimentoDTO.getValFretePesoVolume());
        conhecimento.setValSecCat(conhecimentoDTO.getValSecCat());
        conhecimento.setValDespacho(conhecimentoDTO.getValDespacho());
        conhecimento.setValPedagio(conhecimentoDTO.getValPedagio());
        conhecimento.setValTotalFrete(conhecimentoDTO.getValTotalFrete());
        conhecimento.setCodEmpresa(conhecimentoDTO.getCodEmpresa());
        conhecimento.setDtaEntrada(conhecimentoDTO.getDtaEntrada());
        conhecimento.setValBaseSt(conhecimentoDTO.getValBaseSt());
        conhecimento.setValSt(conhecimentoDTO.getValSt());
        conhecimento.setValDifAliquota(conhecimentoDTO.getValDifAliquota());
        conhecimento.setDesObservacao(conhecimentoDTO.getDesObservacao());
        conhecimento.setNumChaveNfe(conhecimentoDTO.getNumChaveNfe());
        conhecimento.setCodTributacaoPis(conhecimentoDTO.getCodTributacaoPis());
        conhecimento.setCodTributacaoCofins(conhecimentoDTO.getCodTributacaoCofins());
        conhecimento.setValPis(conhecimentoDTO.getValPis());
        conhecimento.setValCofins(conhecimentoDTO.getValCofins());
        conhecimento.setIndNatFrete(conhecimentoDTO.getIndNatFrete());
        conhecimento.setPerAliquotaIcmsSt(conhecimentoDTO.getPerAliquotaIcmsSt());
        conhecimento.setIndFinalidade(conhecimentoDTO.getIndFinalidade());
        conhecimento.setValPisRecuperar(conhecimentoDTO.getValPisRecuperar());
        conhecimento.setValCofinsRecuperar(conhecimentoDTO.getValCofinsRecuperar());
        conhecimento.setValIcmsRecuperar(conhecimentoDTO.getValIcmsRecuperar());
        conhecimento.setCodCidadeDestino(conhecimentoDTO.getCodCidadeDestino());
        conhecimento.setIndComporCustoItem(conhecimentoDTO.getIndComporCustoItem());
        conhecimento.setNumPlacaVeiculo1(conhecimentoDTO.getNumPlacaVeiculo1());
        conhecimento.setNumPlacaVeiculo2(conhecimentoDTO.getNumPlacaVeiculo2());
        conhecimento.setNumPlacaVeiculo3(conhecimentoDTO.getNumPlacaVeiculo3());

        if (conhecimentoDTO.getDespesa() != null) {
            Despesa despesa = new Despesa();
            conhecimento.setDespesa(despesa);

            Integer seqDespesa = getSeqDespesa(conhecimentoDTO.getDespesa().getSeqDespesa());

            despesa.setSeqDespesa(seqDespesa);
            despesa.setCodEmpresa(conhecimentoDTO.getDespesa().getCodEmpresa());
            despesa.setDtaDespesa(conhecimentoDTO.getDespesa().getDtaDespesa());
            despesa.setNumDocDespesa(conhecimentoDTO.getNumConhecimento());
            despesa.setCodTipoDespesa(conhecimentoDTO.getDespesa().getCodTipoDespesa());
            despesa.setCodTipoDespesaSint(conhecimentoDTO.getDespesa().getCodTipoDespesaSint());
            despesa.setCodTipoCobranca(conhecimentoDTO.getCodTipoCobranca());
            despesa.setCodPessoaFavorecido(conhecimentoDTO.getDespesa().getCodPessoaFavorecido());
            despesa.setValDespesa(conhecimentoDTO.getDespesa().getValDespesa());
            despesa.setValPagamentoBanco(conhecimentoDTO.getDespesa().getValPagamentoBanco());
            despesa.setValPagamentoCaixa(conhecimentoDTO.getDespesa().getValPagamentoCaixa());
            despesa.setIndAprovada(conhecimentoDTO.getDespesa().getIndAprovada());
            despesa.setDesObservacao("DESPESA REFERENTE AO CONHECIMENTO DE FRETE DE SEQ: " + seqConhecimento);
            despesa.setDtaInclusao(conhecimentoDTO.getDespesa().getDtaInclusao());
            despesa.setNomUsuario(conhecimentoDTO.getDespesa().getNomUsuario());
            despesa.setIndStatus(conhecimentoDTO.getDespesa().getIndStatus());

            for (ParcelaDespesaDTO parcelaDTO : conhecimentoDTO.getDespesa().getParcelas()) {
                ParcelaDespesa parcela = despesa.addParcela();
                parcela.setDtaVencimento(parcelaDTO.getDtaVencimento());
                parcela.setValParcela(parcelaDTO.getValParcela());
                parcela.setValJuros(parcelaDTO.getValJuros());
            }

            for (RateioDespesaDTO rateioDTO : conhecimentoDTO.getDespesa().getRateio()) {
                RateioDespesa rateio = despesa.addRateio();
                rateio.setValRateio(rateioDTO.getValRateio());
                rateio.setCodCentroCusto(rateioDTO.getCodCentroCusto());
            }
            despesa.prepareToSave();
        }
        return conhecimento;
    }

    private Integer getSeqDespesa(Integer seqDespesa) {
        return Numbers.isNonEmpty(seqDespesa)
                ? seqDespesa
                : nativeFindValue("SELECT CAST(nextval('gen_outras_despesas') as INTEGER)");
    }

    private Integer getSeqConhecimento(Integer pSeqConhecimento) {
        Integer seqConhecimento = Numbers.isNonEmpty(pSeqConhecimento)
                ? pSeqConhecimento
                : nativeFindValue("SELECT CAST(nextval('gen_conhecimento') as INTEGER)");
        return seqConhecimento;
    }
}
