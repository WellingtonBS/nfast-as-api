package br.com.nfast.api.model.financeiro;

public class SaldoBanco {
    private Integer codEmpresa;
    private String numMnemonico;
    private Double vlrSaldo = 0.0;

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNumMnemonico() {
        return numMnemonico;
    }

    public void setNumMnemonico(String numMnemonico) {
        this.numMnemonico = numMnemonico;
    }

    public Double getVlrSaldo() {
        return vlrSaldo;
    }

    public void setVlrSaldo(Double vlrSaldo) {
        this.vlrSaldo = vlrSaldo;
    }

}
