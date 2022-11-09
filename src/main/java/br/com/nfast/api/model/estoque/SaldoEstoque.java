package br.com.nfast.api.model.estoque;

public class SaldoEstoque {
    private Integer codItem;
    private Integer codEmpresa;
    private Integer codAlmoxarifado;
    private Double qtdSaldo = 0.0;

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Integer getCodAlmoxarifado() {
        return codAlmoxarifado;
    }

    public void setCodAlmoxarifado(Integer codAlmoxarifado) {
        this.codAlmoxarifado = codAlmoxarifado;
    }

    public Double getQtdSaldo() {
        return qtdSaldo;
    }

    public void setQtdSaldo(Double qtdSaldo) {
        this.qtdSaldo = qtdSaldo;
    }

}
