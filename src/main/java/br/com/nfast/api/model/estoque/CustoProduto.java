package br.com.nfast.api.model.estoque;

public class CustoProduto {
    private Long codItem;
    private Long codEmpresa;
    private Double vlrCusto = 0.0;

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

    public Double getVlrCusto() {
        return vlrCusto;
    }

    public void setVlrCusto(Double vlrCusto) {
        this.vlrCusto = vlrCusto;
    }

}
