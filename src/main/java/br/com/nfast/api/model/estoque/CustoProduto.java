package br.com.nfast.api.model.estoque;

public class CustoProduto {
    private Integer codItem;
    private Integer codEmpresa;
    private Double vlrCusto = 0.0;

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

    public Double getVlrCusto() {
        return vlrCusto;
    }

    public void setVlrCusto(Double vlrCusto) {
        this.vlrCusto = vlrCusto;
    }

}
