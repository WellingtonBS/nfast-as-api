package br.com.nfast.api.model.conhecimento;

import br.com.nfast.api.model.adm.Despesa;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Conhecimento {

    @Id
    @Column(name = "seq_conhecimento")
    private Integer seqConhecimento;
    @Column(name = "seq_modal")
    private Integer seqModal;
    @Column(name = "cod_especie_caixa")
    private Integer codEspecieCaixa;
    @Column(name = "cod_modelo_documento")
    private Integer codModeloDocumento;
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "cod_pessoa_transportadora")
    private Long codPessoaTransportadora;
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "des_marca_volume")
    private String desMarcaVolume;
    @Column(name = "des_especie_volume")
    private String desEspecieVolume;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "dta_predatado_banco")
    private LocalDate dtaPredatadoBanco;
    @Column(name = "ind_tipofrete")
    private String indTipofrete;
    @Column(name = "ind_credita_icms")
    private String indCreditaIcms;
    @Column(name = "num_conhecimento")
    private String numConhecimento;
    @Column(name = "num_doc_caixa")
    private String numDocCaixa;
    @Column(name = "num_doc_banco")
    private String numDocBanco;
    @Column(name = "num_mnemonico")
    private String numMnemonico;
    @Column(name = "num_placa_veiculo1")
    private String numPlacaVeiculo1;
    @Column(name = "num_placa_veiculo2")
    private String numPlacaVeiculo2;
    @Column(name = "num_placa_veiculo3")
    private String numPlacaVeiculo3;
    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "num_sub_serie")
    private String numSubSerie;
    @Column(name = "uf_veiculo1")
    private String ufVeiculo1;
    @Column(name = "uf_veiculo2")
    private String ufVeiculo2;
    @Column(name = "uf_veiculo3")
    private String ufVeiculo3;
    @Column(name = "val_despesa_acessoria")
    private Double valDespesaAcessoria = 0.0;
    @Column(name = "val_frete")
    private Double valFrete = 0.0;
    @Column(name = "val_seguro")
    private Double valSeguro = 0.0;
    @Column(name = "val_base_icms")
    private Double valBaseIcms = 0.0;
    @Column(name = "val_icms")
    private Double valIcms = 0.0;
    @Column(name = "val_icms_nao_tributado")
    private Double valIcmsNaoTributado = 0.0;
    @Column(name = "val_icms_outros")
    private Double valIcmsOutros = 0.0;
    @Column(name = "val_pagamento_caixa")
    private Double valPagamentoCaixa = 0.0;
    @Column(name = "val_pagamento_banco")
    private Double valPagamentoBanco = 0.0;
    @Column(name = "val_aliquota")
    private Double valAliquota = 0.0;
    @Column(name = "val_frete_peso_volume")
    private Double valFretePesoVolume = 0.0;
    @Column(name = "val_sec_cat")
    private Double valSecCat = 0.0;
    @Column(name = "val_despacho")
    private Double valDespacho = 0.0;
    @Column(name = "val_pedagio")
    private Double valPedagio = 0.0;
    @Column(name = "val_total_frete")
    private Double valTotalFrete = 0.0;
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "seq_movimento_banco")
    private Integer seqMovimentoBanco;
    @Column(name = "seq_movimento_caixa")
    private Integer seqMovimentoCaixa;
    @Column(name = "dta_entrada")
    private LocalDate dtaEntrada;
    @Column(name = "val_base_st")
    private Double valBaseSt = 0.0;
    @Column(name = "val_st")
    private Double valSt = 0.0;
    @Column(name = "val_dif_aliquota")
    private Double valDifAliquota = 0.0;
    @Column(name = "des_observacao")
    private String desObservacao;
    @Column(name = "cod_aeroporto_origem")
    private Integer codAeroportoOrigem;
    @Column(name = "cod_aeroporto_destino")
    private Integer codAeroportoDestino;
    @Column(name = "num_chave_nfe")
    private String numChaveNfe;
    @Column(name = "cod_tributacao_pis")
    private Integer codTributacaoPis;
    @Column(name = "cod_tributacao_cofins")
    private Integer codTributacaoCofins;
    @Column(name = "val_pis")
    private Double valPis = 0.0;
    @Column(name = "val_cofins")
    private Double valCofins = 0.0;
    @Column(name = "ind_nat_frete")
    private String indNatFrete = "9";
    @Column(name = "per_aliquota_icms_st")
    private Double perAliquotaIcmsSt = 0.0;
    @Column(name = "ind_finalidade")
    private String indFinalidade;
    @Column(name = "seq_lr_credito_pis")
    private Integer seqLrCreditoPis;
    @Column(name = "seq_lr_credito_cofins")
    private Integer seqLrCreditoCofins;
    @Column(name = "seq_lr_credito_icms")
    private Integer seqLrCreditoIcms;
    @Column(name = "val_pis_recuperar")
    private Double valPisRecuperar = 0.0;
    @Column(name = "val_cofins_recuperar")
    private Double valCofinsRecuperar = 0.0;
    @Column(name = "val_icms_recuperar")
    private Double valIcmsRecuperar = 0.0;
    @Column(name = "seq_despesa_dif_aliquota")
    private Integer seqDespesaDifAliquota;
    @Column(name = "seq_tp_dif_aliquota")
    private Integer seqTpDifAliquota;
    @Column(name = "cod_cidade_destino")
    private Integer codCidadeDestino;
    @Column(name = "ind_compor_custo_item")
    private String indComporCustoItem = "N";

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seq_despesa", referencedColumnName = "seq_despesa")
    private Despesa despesa;

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

    public Integer getSeqMovimentoBanco() {
        return seqMovimentoBanco;
    }

    public void setSeqMovimentoBanco(Integer seqMovimentoBanco) {
        this.seqMovimentoBanco = seqMovimentoBanco;
    }

    public Integer getSeqMovimentoCaixa() {
        return seqMovimentoCaixa;
    }

    public void setSeqMovimentoCaixa(Integer seqMovimentoCaixa) {
        this.seqMovimentoCaixa = seqMovimentoCaixa;
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

    public Integer getCodAeroportoOrigem() {
        return codAeroportoOrigem;
    }

    public void setCodAeroportoOrigem(Integer codAeroportoOrigem) {
        this.codAeroportoOrigem = codAeroportoOrigem;
    }

    public Integer getCodAeroportoDestino() {
        return codAeroportoDestino;
    }

    public void setCodAeroportoDestino(Integer codAeroportoDestino) {
        this.codAeroportoDestino = codAeroportoDestino;
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

    public Integer getSeqLrCreditoPis() {
        return seqLrCreditoPis;
    }

    public void setSeqLrCreditoPis(Integer seqLrCreditoPis) {
        this.seqLrCreditoPis = seqLrCreditoPis;
    }

    public Integer getSeqLrCreditoCofins() {
        return seqLrCreditoCofins;
    }

    public void setSeqLrCreditoCofins(Integer seqLrCreditoCofins) {
        this.seqLrCreditoCofins = seqLrCreditoCofins;
    }

    public Integer getSeqLrCreditoIcms() {
        return seqLrCreditoIcms;
    }

    public void setSeqLrCreditoIcms(Integer seqLrCreditoIcms) {
        this.seqLrCreditoIcms = seqLrCreditoIcms;
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

    public Integer getSeqDespesaDifAliquota() {
        return seqDespesaDifAliquota;
    }

    public void setSeqDespesaDifAliquota(Integer seqDespesaDifAliquota) {
        this.seqDespesaDifAliquota = seqDespesaDifAliquota;
    }

    public Integer getSeqTpDifAliquota() {
        return seqTpDifAliquota;
    }

    public void setSeqTpDifAliquota(Integer seqTpDifAliquota) {
        this.seqTpDifAliquota = seqTpDifAliquota;
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

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }


}
