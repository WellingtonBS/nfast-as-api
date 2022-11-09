package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_ncm")
public class Ncm {
    @Id
    @Column(name = "seq_ncm")
    private Integer seqNcm;
    @Column(name = "cod_ncm")
    private String codNcm;
    @Column(name = "des_ncm")
    private String desNcm;

    public Integer getSeqNcm() {
        return seqNcm;
    }

    public void setSeqNcm(Integer seqNcm) {
        this.seqNcm = seqNcm;
    }

    public String getCodNcm() {
        return codNcm;
    }

    public void setCodNcm(String codNcm) {
        this.codNcm = codNcm;
    }

    public String getDesNcm() {
        return desNcm;
    }

    public void setDesNcm(String desNcm) {
        this.desNcm = desNcm;
    }
}
