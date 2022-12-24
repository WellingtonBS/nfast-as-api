package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Tributacao {
    @Id
    @Column(name = "cod_tributacao")
    private Integer codTributacao;
    @Column(name = "des_tributacao")
    private String desTributacao;
    @Column(name = "ind_tipo_tributo")
    private String indTipoTributo;
    @Column(name = "ind_coluna_tributo")
    private String indColunaTributo;
    @Column(name = "ind_entrada_saida")
    private String indEntradaSaida;
    @Column(name = "cod_situacao_tributaria")
    private String codSituacaoTributaria;
    @Column(name = "cod_csosn")
    private String codCsosn;
    @Column(name = "per_aliquota")
    private Double perAliquota;
    @Column(name = "per_reducao_base")
    private Double perReducaoBase;
    @Column(name = "ind_substituicao_tributaria")
    private String indSubstituicaoTributaria;
    @Column(name = "ind_ipi_integra_base")
    private String indIpiIntegraBase;
    @Column(name = "ind_inativa")
    private String indInativa;

    public Integer getCodTributacao() {
        return codTributacao;
    }

    public void setCodTributacao(Integer codTributacao) {
        this.codTributacao = codTributacao;
    }

    public String getDesTributacao() {
        return desTributacao;
    }

    public void setDesTributacao(String desTributacao) {
        this.desTributacao = desTributacao;
    }

    public String getIndTipoTributo() {
        return indTipoTributo;
    }

    public void setIndTipoTributo(String indTipoTributo) {
        this.indTipoTributo = indTipoTributo;
    }

    public String getIndColunaTributo() {
        return indColunaTributo;
    }

    public void setIndColunaTributo(String indColunaTributo) {
        this.indColunaTributo = indColunaTributo;
    }

    public String getIndEntradaSaida() {
        return indEntradaSaida;
    }

    public void setIndEntradaSaida(String indEntradaSaida) {
        this.indEntradaSaida = indEntradaSaida;
    }

    public String getCodSituacaoTributaria() {
        return codSituacaoTributaria;
    }

    public void setCodSituacaoTributaria(String codSituacaoTributaria) {
        this.codSituacaoTributaria = codSituacaoTributaria;
    }

    public String getCodCsosn() {
        return codCsosn;
    }

    public void setCodCsosn(String codCsosn) {
        this.codCsosn = codCsosn;
    }

    public Double getPerAliquota() {
        return perAliquota;
    }

    public void setPerAliquota(Double perAliquota) {
        this.perAliquota = perAliquota;
    }

    public Double getPerReducaoBase() {
        return perReducaoBase;
    }

    public void setPerReducaoBase(Double perReducaoBase) {
        this.perReducaoBase = perReducaoBase;
    }

    public String getIndSubstituicaoTributaria() {
        return indSubstituicaoTributaria;
    }

    public void setIndSubstituicaoTributaria(String indSubstituicaoTributaria) {
        this.indSubstituicaoTributaria = indSubstituicaoTributaria;
    }

    public String getIndIpiIntegraBase() {
        return indIpiIntegraBase;
    }

    public void setIndIpiIntegraBase(String indIpiIntegraBase) {
        this.indIpiIntegraBase = indIpiIntegraBase;
    }

    public String getIndInativa() {
        return indInativa;
    }

    public void setIndInativa(String indInativa) {
        this.indInativa = indInativa;
    }

}
