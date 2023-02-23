package br.com.nfast.api.model.adm;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Despesa {
    @Id
    @Column(name = "seq_despesa")
    private Integer seqDespesa;
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "dta_despesa")
    private LocalDate dtaDespesa;
    @Column(name = "cod_tipo_despesa")
    private Long codTipoDespesa;
    @Column(name = "cod_tipo_despesa_sint")
    private Long codTipoDespesaSint;
    @Column(name = "cod_pessoa_favorecido")
    private Integer codPessoaFavorecido;
    @Column(name = "num_doc_despesa")
    private String numDocDespesa;
    @Column(name = "val_despesa")
    private Double valDespesa = 0.0;
    @Column(name = "des_observacao")
    private String desObservacao;
    @Column(name = "val_pagamento_banco")
    private Double valPagamentoBanco = 0.0;
    @Column(name = "num_doc_banco")
    private String numDocBanco;
    @Column(name = "num_mnemonico")
    private String numMnemonico;
    @Column(name = "dta_predatado_banco")
    private LocalDate dtaPredatadoBanco;
    @Column(name = "val_pagamento_caixa")
    private Double valPagamentoCaixa = 0.0;
    @Column(name = "num_doc_caixa")
    private String numDocCaixa;
    @Column(name = "cod_especie_caixa")
    private Integer codEspecieCaixa;
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "dta_inclusao")
    private LocalDate dtaInclusao;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "dta_validacao")
    private LocalDate dtaValidacao;
    @Column(name = "nom_usuario_validacao")
    private String nomUsuarioValidacao;
    @Column(name = "ind_aprovada")
    private String indAprovada = "S";
    @Column(name = "nom_usuario_aprovacao")
    private String nomUsuarioAprovacao;
    @Column(name = "dta_aprovacao")
    private LocalDate dtaAprovacao;
    @Column(name = "cod_processo_producao")
    private Integer codProcessoProducao;
    @Column(name = "cod_evento")
    private Integer codEvento;
    @Column(name = "cod_ocorrencia_rh")
    private String codOcorrenciaRh;
    @Column(name = "ind_capex_opex")
    private String indCapexOpex;
    @Column(name = "ind_ida_volta")
    private String indIdaVolta;
    @Column(name = "ind_tipo_provisao")
    private String indTipoProvisao;
    @Column(name = "val_reversao")
    private Double valReversao = 0.0;
    @Column(name = "ind_rpa")
    private String indRpa = "N";
    @Column(name = "cod_natureza_retencao")
    private Integer codNaturezaRetencao;
    @Column(name = "cod_identificador_imposto")
    private String codIdentificadorImposto;
    @Column(name = "dta_referencia_imposto")
    private LocalDate dtaReferenciaImposto;
    @Column(name = "per_darf")
    private String perDarf;
    @Column(name = "cod_pagamento")
    private String codPagamento;
    @Column(name = "nom_recolhedor")
    private String nomRecolhedor;
    @Column(name = "des_extra_imposto")
    private String desExtraImposto;
    @Column(name = "val_receita_imposto")
    private Double valReceitaImposto = 0.0;

    @Fetch(FetchMode.SELECT)
    @OrderBy("cod_centro_custo")
    @OneToMany(mappedBy = "id.despesa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RateioDespesa> rateio = new ArrayList<>();

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

    public Integer getCodPessoaFavorecido() {
        return codPessoaFavorecido;
    }

    public void setCodPessoaFavorecido(Integer codPessoaFavorecido) {
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

    public List<RateioDespesa> getRateio() {
        return rateio;
    }

    public void setRateio(List<RateioDespesa> rateio) {
        this.rateio = rateio;
    }

    public void prepareToSave() {
        if (rateio != null) {
            for (RateioDespesa rateio : rateio) {
                if (rateio.getId() == null) {
                    rateio.setId(new RateioDespesaId());
                    rateio.getId().setDespesa(this);
                    rateio.getId().setCodCentroCusto(rateio.getCodCentroCusto());
                }
            }
        }
    }

}
