package br.com.nfast.api.model.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cidade {
    @Id
    @Column(name = "cod_cidade")
    private Long codCidade;
    @Column(name = "nom_cidade")
    private String nomCidade;
    @Column(name = "sgl_estado")
    private String sglEstado;
    @Column(name = "cod_ibge")
    private String codIbge;

    public Long getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Long codCidade) {
        this.codCidade = codCidade;
    }

    public String getNomCidade() {
        return nomCidade;
    }

    public void setNomCidade(String nomCidade) {
        this.nomCidade = nomCidade;
    }

    public String getSglEstado() {
        return sglEstado;
    }

    public void setSglEstado(String sglEstado) {
        this.sglEstado = sglEstado;
    }

    public String getCodIbge() {
        return codIbge;
    }

    public void setCodIbge(String codIbge) {
        this.codIbge = codIbge;
    }
}
