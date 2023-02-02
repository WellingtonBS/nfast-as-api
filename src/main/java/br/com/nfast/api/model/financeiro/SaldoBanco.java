package br.com.nfast.api.model.financeiro;

public class SaldoBanco {
    private Long codEmpresa;
    private String numMnemonico;
    private Double vlrSaldo = 0.0;

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
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
