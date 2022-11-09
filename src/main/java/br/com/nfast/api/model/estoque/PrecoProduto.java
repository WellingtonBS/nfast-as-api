package br.com.nfast.api.model.estoque;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrecoProduto {
    @Id
    @JsonIgnore
    private String id;
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "val_preco_venda")
    private Double valPrecoVenda = 0.0;
    @Column(name = "per_margem")
    private Double perMargem = 0.0;
    @Column(name = "per_markup")
    private Double perMarkup = 0.0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getValPrecoVenda() {
        return valPrecoVenda;
    }

    public void setValPrecoVenda(Double valPrecoVenda) {
        this.valPrecoVenda = valPrecoVenda;
    }

    public Double getPerMargem() {
        return perMargem;
    }

    public void setPerMargem(Double perMargem) {
        this.perMargem = perMargem;
    }

    public Double getPerMarkup() {
        return perMarkup;
    }

    public void setPerMarkup(Double perMarkup) {
        this.perMarkup = perMarkup;
    }
}
