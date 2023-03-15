package br.com.nfast.api.dto;

public class ConhecimentoRateioDTO {
    private Long codCentroCusto;
    private Double valRateio = 0.0;

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
