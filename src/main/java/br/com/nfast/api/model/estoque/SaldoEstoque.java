package br.com.nfast.api.model.estoque;

public class SaldoEstoque {
    private Long codItem;
    private Long codEmpresa;
    private Long codAlmoxarifado;
    private Double qtdSaldo = 0.0;

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Long getCodAlmoxarifado() {
        return codAlmoxarifado;
    }

    public void setCodAlmoxarifado(Long codAlmoxarifado) {
        this.codAlmoxarifado = codAlmoxarifado;
    }

    public Double getQtdSaldo() {
        return qtdSaldo;
    }

    public void setQtdSaldo(Double qtdSaldo) {
        this.qtdSaldo = qtdSaldo;
    }

}
