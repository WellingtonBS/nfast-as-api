package br.com.nfast.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DespesaDTO {

    private Integer seqDespesa;
    private Long codEmpresa;
    private LocalDate dtaDespesa;
    private Long codTipoDespesa;
    private Long codTipoDespesaSint;
    private Long codPessoaFavorecido;
    private String numDocDespesa;
    private Double valDespesa = 0.0;
    private String desObservacao;
    private Double valPagamentoBanco = 0.0;
    private String numDocBanco;
    private String numMnemonico;
    private LocalDate dtaPredatadoBanco;
    private Double valPagamentoCaixa = 0.0;
    private String numDocCaixa;
    private Integer codEspecieCaixa;
    private Integer codTipoCobranca;
    private LocalDate dtaInclusao;
    private String nomUsuario;
    private LocalDate dtaValidacao;
    private String nomUsuarioValidacao;
    private String indAprovada = "S";
    private String indStatus;
    private String nomUsuarioAprovacao;
    private LocalDate dtaAprovacao;
    private Integer codProcessoProducao;
    private Integer codEvento;
    private String codOcorrenciaRh;
    private String indCapexOpex;
    private String indIdaVolta;
    private String indTipoProvisao;
    private Double valReversao = 0.0;
    private String indRpa = "N";
    private Integer codNaturezaRetencao;
    private String codIdentificadorImposto;
    private LocalDate dtaReferenciaImposto;
    private String perDarf;
    private String codPagamento;
    private String nomRecolhedor;
    private String desExtraImposto;
    private Double valReceitaImposto = 0.0;

    private List<ParcelaDespesaDTO> parcelas = new ArrayList<>();
    private List<RateioDespesaDTO> rateio = new ArrayList<>();

    public Integer getSeqDespesa() {
        return seqDespesa;
    }

    public void setSeqDespesa(Integer seqDespesa) {
        this.seqDespesa = seqDespesa;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public LocalDate getDtaDespesa() {
        return dtaDespesa;
    }

    public void setDtaDespesa(LocalDate dtaDespesa) {
        this.dtaDespesa = dtaDespesa;
    }

    public Long getCodTipoDespesa() {
        return codTipoDespesa;
    }

    public void setCodTipoDespesa(Long codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }

    public Long getCodTipoDespesaSint() {
        return codTipoDespesaSint;
    }

    public void setCodTipoDespesaSint(Long codTipoDespesaSint) {
        this.codTipoDespesaSint = codTipoDespesaSint;
    }

    public Long getCodPessoaFavorecido() {
        return codPessoaFavorecido;
    }

    public void setCodPessoaFavorecido(Long codPessoaFavorecido) {
        this.codPessoaFavorecido = codPessoaFavorecido;
    }

    public String getNumDocDespesa() {
        return numDocDespesa;
    }

    public void setNumDocDespesa(String numDocDespesa) {
        this.numDocDespesa = numDocDespesa;
    }

    public Double getValDespesa() {
        return valDespesa;
    }

    public void setValDespesa(Double valDespesa) {
        this.valDespesa = valDespesa;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public Double getValPagamentoBanco() {
        return valPagamentoBanco;
    }

    public void setValPagamentoBanco(Double valPagamentoBanco) {
        this.valPagamentoBanco = valPagamentoBanco;
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

    public LocalDate getDtaPredatadoBanco() {
        return dtaPredatadoBanco;
    }

    public void setDtaPredatadoBanco(LocalDate dtaPredatadoBanco) {
        this.dtaPredatadoBanco = dtaPredatadoBanco;
    }

    public Double getValPagamentoCaixa() {
        return valPagamentoCaixa;
    }

    public void setValPagamentoCaixa(Double valPagamentoCaixa) {
        this.valPagamentoCaixa = valPagamentoCaixa;
    }

    public String getNumDocCaixa() {
        return numDocCaixa;
    }

    public void setNumDocCaixa(String numDocCaixa) {
        this.numDocCaixa = numDocCaixa;
    }

    public Integer getCodEspecieCaixa() {
        return codEspecieCaixa;
    }

    public void setCodEspecieCaixa(Integer codEspecieCaixa) {
        this.codEspecieCaixa = codEspecieCaixa;
    }

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public LocalDate getDtaInclusao() {
        return dtaInclusao;
    }

    public void setDtaInclusao(LocalDate dtaInclusao) {
        this.dtaInclusao = dtaInclusao;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public LocalDate getDtaValidacao() {
        return dtaValidacao;
    }

    public void setDtaValidacao(LocalDate dtaValidacao) {
        this.dtaValidacao = dtaValidacao;
    }

    public String getNomUsuarioValidacao() {
        return nomUsuarioValidacao;
    }

    public void setNomUsuarioValidacao(String nomUsuarioValidacao) {
        this.nomUsuarioValidacao = nomUsuarioValidacao;
    }

    public String getIndAprovada() {
        return indAprovada;
    }

    public void setIndAprovada(String indAprovada) {
        this.indAprovada = indAprovada;
    }

    public String getIndStatus() {
        return indStatus;
    }

    public void setIndStatus(String indStatus) {
        this.indStatus = indStatus;
    }

    public String getNomUsuarioAprovacao() {
        return nomUsuarioAprovacao;
    }

    public void setNomUsuarioAprovacao(String nomUsuarioAprovacao) {
        this.nomUsuarioAprovacao = nomUsuarioAprovacao;
    }

    public LocalDate getDtaAprovacao() {
        return dtaAprovacao;
    }

    public void setDtaAprovacao(LocalDate dtaAprovacao) {
        this.dtaAprovacao = dtaAprovacao;
    }

    public Integer getCodProcessoProducao() {
        return codProcessoProducao;
    }

    public void setCodProcessoProducao(Integer codProcessoProducao) {
        this.codProcessoProducao = codProcessoProducao;
    }

    public Integer getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(Integer codEvento) {
        this.codEvento = codEvento;
    }

    public String getCodOcorrenciaRh() {
        return codOcorrenciaRh;
    }

    public void setCodOcorrenciaRh(String codOcorrenciaRh) {
        this.codOcorrenciaRh = codOcorrenciaRh;
    }

    public String getIndCapexOpex() {
        return indCapexOpex;
    }

    public void setIndCapexOpex(String indCapexOpex) {
        this.indCapexOpex = indCapexOpex;
    }

    public String getIndIdaVolta() {
        return indIdaVolta;
    }

    public void setIndIdaVolta(String indIdaVolta) {
        this.indIdaVolta = indIdaVolta;
    }

    public String getIndTipoProvisao() {
        return indTipoProvisao;
    }

    public void setIndTipoProvisao(String indTipoProvisao) {
        this.indTipoProvisao = indTipoProvisao;
    }

    public Double getValReversao() {
        return valReversao;
    }

    public void setValReversao(Double valReversao) {
        this.valReversao = valReversao;
    }

    public String getIndRpa() {
        return indRpa;
    }

    public void setIndRpa(String indRpa) {
        this.indRpa = indRpa;
    }

    public Integer getCodNaturezaRetencao() {
        return codNaturezaRetencao;
    }

    public void setCodNaturezaRetencao(Integer codNaturezaRetencao) {
        this.codNaturezaRetencao = codNaturezaRetencao;
    }

    public String getCodIdentificadorImposto() {
        return codIdentificadorImposto;
    }

    public void setCodIdentificadorImposto(String codIdentificadorImposto) {
        this.codIdentificadorImposto = codIdentificadorImposto;
    }

    public LocalDate getDtaReferenciaImposto() {
        return dtaReferenciaImposto;
    }

    public void setDtaReferenciaImposto(LocalDate dtaReferenciaImposto) {
        this.dtaReferenciaImposto = dtaReferenciaImposto;
    }

    public String getPerDarf() {
        return perDarf;
    }

    public void setPerDarf(String perDarf) {
        this.perDarf = perDarf;
    }

    public String getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(String codPagamento) {
        this.codPagamento = codPagamento;
    }

    public String getNomRecolhedor() {
        return nomRecolhedor;
    }

    public void setNomRecolhedor(String nomRecolhedor) {
        this.nomRecolhedor = nomRecolhedor;
    }

    public String getDesExtraImposto() {
        return desExtraImposto;
    }

    public void setDesExtraImposto(String desExtraImposto) {
        this.desExtraImposto = desExtraImposto;
    }

    public Double getValReceitaImposto() {
        return valReceitaImposto;
    }

    public void setValReceitaImposto(Double valReceitaImposto) {
        this.valReceitaImposto = valReceitaImposto;
    }

    public List<ParcelaDespesaDTO> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ParcelaDespesaDTO> parcelas) {
        this.parcelas = parcelas;
    }

    public List<RateioDespesaDTO> getRateio() {
        return rateio;
    }

    public void setRateio(List<RateioDespesaDTO> rateio) {
        this.rateio = rateio;
    }
}
