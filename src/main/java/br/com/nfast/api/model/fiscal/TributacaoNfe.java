package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TributacaoNfe {
    @Id
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "cod_icms")
    private Integer codIcms;
    @Column(name = "cod_ipi")
    private Integer codIpi;
    @Column(name = "cod_pis")
    private Integer codPis;
    @Column(name = "cod_cofins")
    private Integer codCofins;

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

    public Integer getCodIcms() {
        return codIcms;
    }

    public void setCodIcms(Integer codIcms) {
        this.codIcms = codIcms;
    }

    public Integer getCodIpi() {
        return codIpi;
    }

    public void setCodIpi(Integer codIpi) {
        this.codIpi = codIpi;
    }

    public Integer getCodPis() {
        return codPis;
    }

    public void setCodPis(Integer codPis) {
        this.codPis = codPis;
    }

    public Integer getCodCofins() {
        return codCofins;
    }

    public void setCodCofins(Integer codCofins) {
        this.codCofins = codCofins;
    }

}
