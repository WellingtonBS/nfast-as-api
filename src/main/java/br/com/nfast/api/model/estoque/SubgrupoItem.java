package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubgrupoItem {
    @Id
    @Column(name = "cod_subgrupo_item")
    private Integer codSubgrupoItem;
    @Column(name = "des_subgrupo_item")
    private String desSubgrupoItem;

    public Integer getCodSubgrupoItem() {
        return codSubgrupoItem;
    }

    public void setCodSubgrupoItem(Integer codSubgrupoItem) {
        this.codSubgrupoItem = codSubgrupoItem;
    }

    public String getDesSubgrupoItem() {
        return desSubgrupoItem;
    }

    public void setDesSubgrupoItem(String desSubgrupoItem) {
        this.desSubgrupoItem = desSubgrupoItem;
    }

}