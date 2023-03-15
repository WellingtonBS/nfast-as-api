package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RateioDespesaResumo {

    @Id
    @Column(name = "seq_despesa")
    private Integer id;
    @Column(name = "cod_centro_custo")
    private Integer codCentroCusto;
    @Column(name = "val_rateio")
    private Double valRateio = 0.0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Integer codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public Double getValRateio() {
        return valRateio;
    }

    public void setValRateio(Double valRateio) {
        this.valRateio = valRateio;
    }
}
