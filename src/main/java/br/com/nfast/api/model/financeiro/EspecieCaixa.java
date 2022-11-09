package br.com.nfast.api.model.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_especie_caixa")
public class EspecieCaixa {
    @Id
    @Column(name = "cod_especie_caixa")
    private Integer codEspecieCaixa;
    @Column(name = "des_especie_caixa")
    private String desEspecieCaixa;
    @Column(name = "ind_status")
    private String indStatus;

    public Integer getCodEspecieCaixa() {
        return codEspecieCaixa;
    }

    public void setCodEspecieCaixa(Integer codEspecieCaixa) {
        this.codEspecieCaixa = codEspecieCaixa;
    }

    public String getDesEspecieCaixa() {
        return desEspecieCaixa;
    }

    public void setDesEspecieCaixa(String desEspecieCaixa) {
        this.desEspecieCaixa = desEspecieCaixa;
    }

    public String getIndStatus() {
        return indStatus;
    }

    public void setIndStatus(String indStatus) {
        this.indStatus = indStatus;
    }

}
