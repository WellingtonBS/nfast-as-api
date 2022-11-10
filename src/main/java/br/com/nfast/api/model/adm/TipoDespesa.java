package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_tipo_despesa")
public class TipoDespesa {
    @Id
    @Column(name = "cod_tipo_despesa")
    private Long codTipoDespesa;
    @Column(name = "des_tipo_despesa")
    private String desTipoDespesa;
    @Column(name = "ind_tipo_despesa")
    private String indTipoDespesa;
    @Column(name = "ind_tipo")
    private String indTipo;
    @Column(name = "ind_status")
    private String indStatus;

    public Long getCodTipoDespesa() {
        return codTipoDespesa;
    }

    public void setCodTipoDespesa(Long codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }

    public String getDesTipoDespesa() {
        return desTipoDespesa;
    }

    public void setDesTipoDespesa(String desTipoDespesa) {
        this.desTipoDespesa = desTipoDespesa;
    }

    public String getIndTipoDespesa() {
        return indTipoDespesa;
    }

    public void setIndTipoDespesa(String indTipoDespesa) {
        this.indTipoDespesa = indTipoDespesa;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

    public String getIndStatus() {
        return indStatus;
    }

    public void setIndStatus(String indStatus) {
        this.indStatus = indStatus;
    }

}
