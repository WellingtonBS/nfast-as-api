package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClasseFiscal {
    @Id
    @Column(name = "cod_classe_fiscal")
    private Integer codClasseFiscal;
    @Column(name = "des_classe_fiscal")
    private String desClasseFiscal;

    public Integer getCodClasseFiscal() {
        return codClasseFiscal;
    }

    public void setCodClasseFiscal(Integer codClasseFiscal) {
        this.codClasseFiscal = codClasseFiscal;
    }

    public String getDesClasseFiscal() {
        return desClasseFiscal;
    }

    public void setDesClasseFiscal(String desClasseFiscal) {
        this.desClasseFiscal = desClasseFiscal;
    }

}
