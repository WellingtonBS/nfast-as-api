package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ParcelaDespesaResumo {

    @Id
    @Column(name = "seq_despesa")
    private Integer id;
    @Column(name = "num_parcela")
    private Integer numParcela;
    @Column(name = "dta_vencimento")
    private LocalDate dtaVencimento;
    @Column(name = "val_parcela")
    private Double valParcela;
    @Column(name = "val_juros")
    private Double valJuros;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public LocalDate getDtaVencimento() {
        return dtaVencimento;
    }

    public void setDtaVencimento(LocalDate dtaVencimento) {
        this.dtaVencimento = dtaVencimento;
    }

    public Double getValParcela() {
        return valParcela;
    }

    public void setValParcela(Double valParcela) {
        this.valParcela = valParcela;
    }

    public Double getValJuros() {
        return valJuros;
    }

    public void setValJuros(Double valJuros) {
        this.valJuros = valJuros;
    }
}
