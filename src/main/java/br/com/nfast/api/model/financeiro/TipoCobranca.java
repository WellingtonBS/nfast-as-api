package br.com.nfast.api.model.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoCobranca {
    @Id
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "des_tipo_cobranca")
    private String desTipoCobranca;
    @Column(name = "ind_status")
    private String indStatus;

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public String getDesTipoCobranca() {
        return desTipoCobranca;
    }

    public void setDesTipoCobranca(String desTipoCobranca) {
        this.desTipoCobranca = desTipoCobranca;
    }

    public String getIndStatus() {
        return indStatus;
    }

    public void setIndStatus(String indStatus) {
        this.indStatus = indStatus;
    }
}
