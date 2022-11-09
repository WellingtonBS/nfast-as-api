package br.com.nfast.api.model.crm;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    private String id;
    private String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
