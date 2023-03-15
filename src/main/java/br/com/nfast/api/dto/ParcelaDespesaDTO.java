package br.com.nfast.api.dto;

import java.time.LocalDate;

public class ParcelaDespesaDTO {

    private Integer numParcela;
    private LocalDate dtaVencimento;
    private Double valParcela = 0.0;
    private Double valJuros = 0.0;


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
