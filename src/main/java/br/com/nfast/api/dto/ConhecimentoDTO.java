package br.com.nfast.api.dto;

import java.time.LocalDate;

public class ConhecimentoDTO {
    private Integer seqConhecimento;
    private Integer seqModal;
    private Integer codEspecieCaixa;
    private Integer codModeloDocumento;
    private Integer codNaturezaOperacao;
    private Long codPessoaTransportadora;
    private Integer codTipoCobranca;
    private String desMarcaVolume;
    private String desEspecieVolume;
    private LocalDate dtaEmissao;
    private LocalDate dtaPredatadoBanco;
    private String indTipofrete;
    private String indCreditaIcms;
    private String numConhecimento;
    private String numDocCaixa;
    private String numDocBanco;
    private String numMnemonico;
    private String numPlacaVeiculo1;
    private String numPlacaVeiculo2;
    private String numPlacaVeiculo3;
    private String numSerie;
    private String numSubSerie;
    private String ufVeiculo1;
    private String ufVeiculo2;
    private String ufVeiculo3;
    private Double valDespesaAcessoria = 0.0;
    private Double valFrete = 0.0;
    private Double valSeguro = 0.0;
    private Double valBaseIcms = 0.0;
    private Double valIcms = 0.0;
    private Double valIcmsNaoTributado = 0.0;
    private Double valIcmsOutros = 0.0;
    private Double valPagamentoCaixa = 0.0;
    private Double valPagamentoBanco = 0.0;
    private Double valAliquota = 0.0;
    private Double valFretePesoVolume = 0.0;
    private Double valSecCat = 0.0;
    private Double valDespacho = 0.0;
    private Double valPedagio = 0.0;
    private Double valTotalFrete = 0.0;
    private Long codEmpresa;
    private LocalDate dtaEntrada;
    private Double valBaseSt = 0.0;
    private Double valSt = 0.0;
    private Double valDifAliquota = 0.0;
    private String desObservacao;
    private String numChaveNfe;
    private Integer codTributacaoPis;
    private Integer codTributacaoCofins;
    private Double valPis = 0.0;
    private Double valCofins = 0.0;
    private String indNatFrete = "9";
    private Double perAliquotaIcmsSt = 0.0;
    private String indFinalidade;
    private Double valPisRecuperar = 0.0;
    private Double valCofinsRecuperar = 0.0;
    private Double valIcmsRecuperar = 0.0;
    private Integer codCidadeDestino;
    private String indComporCustoItem = "N";
    private DespesaDTO despesa;

    public Integer getSeqConhecimento() {
        return seqConhecimento;
    }

    public void setSeqConhecimento(Integer seqConhecimento) {
        this.seqConhecimento = seqConhecimento;
    }

    public Integer getSeqModal() {
        return seqModal;
    }

    public void setSeqModal(Integer seqModal) {
        this.seqModal = seqModal;
    }

    public Integer getCodEspecieCaixa() {
        return codEspecieCaixa;
    }

    public void setCodEspecieCaixa(Integer codEspecieCaixa) {
        this.codEspecieCaixa = codEspecieCaixa;
    }

    public Integer getCodModeloDocumento() {
        return codModeloDocumento;
    }

    public void setCodModeloDocumento(Integer codModeloDocumento) {
        this.codModeloDocumento = codModeloDocumento;
    }

    public Integer getCodNaturezaOperacao() {
        return codNaturezaOperacao;
    }

    public void setCodNaturezaOperacao(Integer codNaturezaOperacao) {
        this.codNaturezaOperacao = codNaturezaOperacao;
    }

    public Long getCodPessoaTransportadora() {
        return codPessoaTransportadora;
    }

    public void setCodPessoaTransportadora(Long codPessoaTransportadora) {
        this.codPessoaTransportadora = codPessoaTransportadora;
    }

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public String getDesMarcaVolume() {
        return desMarcaVolume;
    }

    public void setDesMarcaVolume(String desMarcaVolume) {
        this.desMarcaVolume = desMarcaVolume;
    }

    public String getDesEspecieVolume() {
        return desEspecieVolume;
    }

    public void setDesEspecieVolume(String desEspecieVolume) {
        this.desEspecieVolume = desEspecieVolume;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public LocalDate getDtaPredatadoBanco() {
        return dtaPredatadoBanco;
    }

    public void setDtaPredatadoBanco(LocalDate dtaPredatadoBanco) {
        this.dtaPredatadoBanco = dtaPredatadoBanco;
    }

    public String getIndTipofrete() {
        return indTipofrete;
    }

    public void setIndTipofrete(String indTipofrete) {
        this.indTipofrete = indTipofrete;
    }

    public String getIndCreditaIcms() {
        return indCreditaIcms;
    }

    public void setIndCreditaIcms(String indCreditaIcms) {
        this.indCreditaIcms = indCreditaIcms;
    }

    public String getNumConhecimento() {
        return numConhecimento;
    }

    public void setNumConhecimento(String numConhecimento) {
        this.numConhecimento = numConhecimento;
    }

    public String getNumDocCaixa() {
        return numDocCaixa;
    }

    public void setNumDocCaixa(String numDocCaixa) {
        this.numDocCaixa = numDocCaixa;
    }

    public String getNumDocBanco() {
        return numDocBanco;
    }

    public void setNumDocBanco(String numDocBanco) {
        this.numDocBanco = numDocBanco;
    }

    public String getNumMnemonico() {
        return numMnemonico;
    }

    public void setNumMnemonico(String numMnemonico) {
        this.numMnemonico = numMnemonico;
    }

    public String getNumPlacaVeiculo1() {
        return numPlacaVeiculo1;
    }

    public void setNumPlacaVeiculo1(String numPlacaVeiculo1) {
        this.numPlacaVeiculo1 = numPlacaVeiculo1;
    }

    public String getNumPlacaVeiculo2() {
        return numPlacaVeiculo2;
    }

    public void setNumPlacaVeiculo2(String numPlacaVeiculo2) {
        this.numPlacaVeiculo2 = numPlacaVeiculo2;
    }

    public String getNumPlacaVeiculo3() {
        return numPlacaVeiculo3;
    }

    public void setNumPlacaVeiculo3(String numPlacaVeiculo3) {
        this.numPlacaVeiculo3 = numPlacaVeiculo3;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNumSubSerie() {
        return numSubSerie;
    }

    public void setNumSubSerie(String numSubSerie) {
        this.numSubSerie = numSubSerie;
    }

    public String getUfVeiculo1() {
        return ufVeiculo1;
    }

    public void setUfVeiculo1(String ufVeiculo1) {
        this.ufVeiculo1 = ufVeiculo1;
    }

    public String getUfVeiculo2() {
        return ufVeiculo2;
    }

    public void setUfVeiculo2(String ufVeiculo2) {
        this.ufVeiculo2 = ufVeiculo2;
    }

    public String getUfVeiculo3() {
        return ufVeiculo3;
    }

    public void setUfVeiculo3(String ufVeiculo3) {
        this.ufVeiculo3 = ufVeiculo3;
    }

    public Double getValDespesaAcessoria() {
        return valDespesaAcessoria;
    }

    public void setValDespesaAcessoria(Double valDespesaAcessoria) {
        this.valDespesaAcessoria = valDespesaAcessoria;
    }

    public Double getValFrete() {
        return valFrete;
    }

    public void setValFrete(Double valFrete) {
        this.valFrete = valFrete;
    }

    public Double getValSeguro() {
        return valSeguro;
    }

    public void setValSeguro(Double valSeguro) {
        this.valSeguro = valSeguro;
    }

    public Double getValBaseIcms() {
        return valBaseIcms;
    }

    public void setValBaseIcms(Double valBaseIcms) {
        this.valBaseIcms = valBaseIcms;
    }

    public Double getValIcms() {
        return valIcms;
    }

    public void setValIcms(Double valIcms) {
        this.valIcms = valIcms;
    }

    public Double getValIcmsNaoTributado() {
        return valIcmsNaoTributado;
    }

    public void setValIcmsNaoTributado(Double valIcmsNaoTributado) {
        this.valIcmsNaoTributado = valIcmsNaoTributado;
    }

    public Double getValIcmsOutros() {
        return valIcmsOutros;
    }

    public void setValIcmsOutros(Double valIcmsOutros) {
        this.valIcmsOutros = valIcmsOutros;
    }

    public Double getValPagamentoCaixa() {
        return valPagamentoCaixa;
    }

    public void setValPagamentoCaixa(Double valPagamentoCaixa) {
        this.valPagamentoCaixa = valPagamentoCaixa;
    }

    public Double getValPagamentoBanco() {
        return valPagamentoBanco;
    }

    public void setValPagamentoBanco(Double valPagamentoBanco) {
        this.valPagamentoBanco = valPagamentoBanco;
    }

    public Double getValAliquota() {
        return valAliquota;
    }

    public void setValAliquota(Double valAliquota) {
        this.valAliquota = valAliquota;
    }

    public Double getValFretePesoVolume() {
        return valFretePesoVolume;
    }

    public void setValFretePesoVolume(Double valFretePesoVolume) {
        this.valFretePesoVolume = valFretePesoVolume;
    }

    public Double getValSecCat() {
        return valSecCat;
    }

    public void setValSecCat(Double valSecCat) {
        this.valSecCat = valSecCat;
    }

    public Double getValDespacho() {
        return valDespacho;
    }

    public void setValDespacho(Double valDespacho) {
        this.valDespacho = valDespacho;
    }

    public Double getValPedagio() {
        return valPedagio;
    }

    public void setValPedagio(Double valPedagio) {
        this.valPedagio = valPedagio;
    }

    public Double getValTotalFrete() {
        return valTotalFrete;
    }

    public void setValTotalFrete(Double valTotalFrete) {
        this.valTotalFrete = valTotalFrete;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public LocalDate getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(LocalDate dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    public Double getValBaseSt() {
        return valBaseSt;
    }

    public void setValBaseSt(Double valBaseSt) {
        this.valBaseSt = valBaseSt;
    }

    public Double getValSt() {
        return valSt;
    }

    public void setValSt(Double valSt) {
        this.valSt = valSt;
    }

    public Double getValDifAliquota() {
        return valDifAliquota;
    }

    public void setValDifAliquota(Double valDifAliquota) {
        this.valDifAliquota = valDifAliquota;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public String getNumChaveNfe() {
        return numChaveNfe;
    }

    public void setNumChaveNfe(String numChaveNfe) {
        this.numChaveNfe = numChaveNfe;
    }

    public Integer getCodTributacaoPis() {
        return codTributacaoPis;
    }

    public void setCodTributacaoPis(Integer codTributacaoPis) {
        this.codTributacaoPis = codTributacaoPis;
    }

    public Integer getCodTributacaoCofins() {
        return codTributacaoCofins;
    }

    public void setCodTributacaoCofins(Integer codTributacaoCofins) {
        this.codTributacaoCofins = codTributacaoCofins;
    }

    public Double getValPis() {
        return valPis;
    }

    public void setValPis(Double valPis) {
        this.valPis = valPis;
    }

    public Double getValCofins() {
        return valCofins;
    }

    public void setValCofins(Double valCofins) {
        this.valCofins = valCofins;
    }

    public String getIndNatFrete() {
        return indNatFrete;
    }

    public void setIndNatFrete(String indNatFrete) {
        this.indNatFrete = indNatFrete;
    }

    public Double getPerAliquotaIcmsSt() {
        return perAliquotaIcmsSt;
    }

    public void setPerAliquotaIcmsSt(Double perAliquotaIcmsSt) {
        this.perAliquotaIcmsSt = perAliquotaIcmsSt;
    }

    public String getIndFinalidade() {
        return indFinalidade;
    }

    public void setIndFinalidade(String indFinalidade) {
        this.indFinalidade = indFinalidade;
    }

    public Double getValPisRecuperar() {
        return valPisRecuperar;
    }

    public void setValPisRecuperar(Double valPisRecuperar) {
        this.valPisRecuperar = valPisRecuperar;
    }

    public Double getValCofinsRecuperar() {
        return valCofinsRecuperar;
    }

    public void setValCofinsRecuperar(Double valCofinsRecuperar) {
        this.valCofinsRecuperar = valCofinsRecuperar;
    }

    public Double getValIcmsRecuperar() {
        return valIcmsRecuperar;
    }

    public void setValIcmsRecuperar(Double valIcmsRecuperar) {
        this.valIcmsRecuperar = valIcmsRecuperar;
    }

    public Integer getCodCidadeDestino() {
        return codCidadeDestino;
    }

    public void setCodCidadeDestino(Integer codCidadeDestino) {
        this.codCidadeDestino = codCidadeDestino;
    }

    public String getIndComporCustoItem() {
        return indComporCustoItem;
    }

    public void setIndComporCustoItem(String indComporCustoItem) {
        this.indComporCustoItem = indComporCustoItem;
    }

    public DespesaDTO getDespesa() {
        return despesa;
    }

    public void setDespesa(DespesaDTO despesa) {
        this.despesa = despesa;
    }
}
