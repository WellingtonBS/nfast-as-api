package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NcmSt {
    @Id
    @Column(name = "seq_ncm")
    private Integer seqNcm;
    @Column(name = "per_aliquota")
    private Double perAliquota = 0.0;
    @Column(name = "per_mark_up")
    private Double perMarkUp = 0.0;
    @Column(name = "val_unitario")
    private Double valUnitario = 0.0;
    @Column(name = "val_preco")
    private Double valPreco = 0.0;

    public Integer getSeqNcm() {
        return seqNcm;
    }

    public void setSeqNcm(Integer seqNcm) {
        this.seqNcm = seqNcm;
    }

    public Double getPerAliquota() {
        return perAliquota;
    }

    public void setPerAliquota(Double perAliquota) {
        this.perAliquota = perAliquota;
    }

    public Double getPerMarkUp() {
        return perMarkUp;
    }

    public void setPerMarkUp(Double perMarkUp) {
        this.perMarkUp = perMarkUp;
    }

    public Double getValUnitario() {
        return valUnitario;
    }

    public void setValUnitario(Double valUnitario) {
        this.valUnitario = valUnitario;
    }

    public Double getValPreco() {
        return valPreco;
    }

    public void setValPreco(Double valPreco) {
        this.valPreco = valPreco;
    }

}