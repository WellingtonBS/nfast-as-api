package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AjusteIcms {
    @Id
    @Column(name = "seq_aj_apur_icms")
    private Integer seqAjApurIcms;
    @Column(name = "cod_icms")
    private String codIcms;
    @Column(name = "des_icms")
    private String desIcms;
    @Column(name = "ind_tipo")
    private String indTipo;
    @Column(name = "ind_tipo_ajuste")
    private String indTipoAjuste;

    public Integer getSeqAjApurIcms() {
        return seqAjApurIcms;
    }

    public void setSeqAjApurIcms(Integer seqAjApurIcms) {
        this.seqAjApurIcms = seqAjApurIcms;
    }

    public String getCodIcms() {
        return codIcms;
    }

    public void setCodIcms(String codIcms) {
        this.codIcms = codIcms;
    }

    public String getDesIcms() {
        return desIcms;
    }

    public void setDesIcms(String desIcms) {
        this.desIcms = desIcms;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

    public String getIndTipoAjuste() {
        return indTipoAjuste;
    }

    public void setIndTipoAjuste(String indTipoAjuste) {
        this.indTipoAjuste = indTipoAjuste;
    }

}
