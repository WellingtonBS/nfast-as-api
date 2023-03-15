package br.com.nfast.api.model.conhecimento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ConhecimentoResumo {

    @Id
    @Column(name = "seq_conhecimento")
    private Integer seqConhecimento;
    @Column(name = "seq_modal")
    private Integer seqModal;
    @Column(name = "cod_modelo_documento")
    private Integer codModeloDocumento;
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "cod_pessoa_transportadora")
    private Long codPessoaTransportadora;
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "ind_tipofrete")
    private String indTipofrete;
    @Column(name = "ind_credita_icms")
    private String indCreditaIcms;
    @Column(name = "num_conhecimento")
    private String numConhecimento;
    @Column(name = "num_placa_veiculo1")
    private String numPlacaVeiculo1;
    @Column(name = "num_placa_veiculo2")
    private String numPlacaVeiculo2;
    @Column(name = "num_placa_veiculo3")
    private String numPlacaVeiculo3;
    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "val_frete")
    private Double valFrete;
    @Column(name = "val_seguro")
    private Double valSeguro;
    @Column(name = "val_base_icms")
    private Double valBaseIcms;
    @Column(name = "val_icms")
    private Double valIcms;
    @Column(name = "val_icms_nao_tributado")
    private Double valIcmsNaoTributado;
    @Column(name = "val_icms_outros")
    private Double valIcmsOutros;
    @Column(name = "val_pagamento_caixa")
    private Double valPagamentoCaixa;
    @Column(name = "val_pagamento_banco")
    private Double valPagamentoBanco;
    @Column(name = "val_aliquota")
    private Double valAliquota;
    @Column(name = "val_frete_peso_volume")
    private Double valFretePesoVolume;
    @Column(name = "val_sec_cat")
    private Double valSecCat;
    @Column(name = "val_despacho")
    private Double valDespacho;
    @Column(name = "val_pedagio")
    private Double valPedagio;
    @Column(name = "val_total_frete")
    private Double valTotalFrete;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "dta_entrada")
    private LocalDate dtaEntrada;
    @Column(name = "val_base_st")
    private Double valBaseSt;
    @Column(name = "val_st")
    private Double valSt;
    @Column(name = "val_dif_aliquota")
    private Double valDifAliquota;
    @Column(name = "des_observacao")
    private String desObservacao;
    @Column(name = "num_chave_nfe")
    private String numChaveNfe;
    @Column(name = "cod_tributacao_pis")
    private Integer codTributacaoPis;
    @Column(name = "cod_tributacao_cofins")
    private Integer codTributacaoCofins;
    @Column(name = "val_pis")
    private Double valPis;
    @Column(name = "val_cofins")
    private Double valCofins;
    @Column(name = "ind_nat_frete")
    private String indNatFrete = "9";
    @Column(name = "per_aliquota_icms_st")
    private Double perAliquotaIcmsSt;
    @Column(name = "ind_finalidade")
    private String indFinalidade;
    @Column(name = "seq_despesa")
    private Integer seqDespesa;

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

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
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

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
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

    public Integer getSeqDespesa() {
        return seqDespesa;
    }

    public void setSeqDespesa(Integer seqDespesa) {
        this.seqDespesa = seqDespesa;
    }
}
