package br.com.nfast.api.model.financeiro;

public class SaldoCaixa {
    private Integer codEmpresa;
    private Integer codEspecieCaixa;
    private Double vlrSaldo = 0.0;

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Integer getCodEspecieCaixa() {
        return codEspecieCaixa;
    }

    public void setCodEspecieCaixa(Integer codEspecieCaixa) {
        this.codEspecieCaixa = codEspecieCaixa;
    }

    public Double getVlrSaldo() {
        return vlrSaldo;
    }

    public void setVlrSaldo(Double vlrSaldo) {
        this.vlrSaldo = vlrSaldo;
    }

}
