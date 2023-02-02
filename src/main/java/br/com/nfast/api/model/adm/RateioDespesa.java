package br.com.nfast.api.model.adm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tab_rateio_despesa")
public class RateioDespesa {
    @EmbeddedId
    @JsonIgnore
    private RateioDespesaId id;

    @Column(name = "cod_centro_custo", insertable = false, updatable = false)
    private Long codCentroCusto;
    @Column(name = "val_rateio")
    private Double valRateio = 0.0;

    public RateioDespesaId getId() {
        return id;
    }

    public void setId(RateioDespesaId id) {
        this.id = id;
    }

    public Long getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Long codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public Double getValRateio() {
        return valRateio;
    }

    public void setValRateio(Double valRateio) {
        this.valRateio = valRateio;
    }

}
