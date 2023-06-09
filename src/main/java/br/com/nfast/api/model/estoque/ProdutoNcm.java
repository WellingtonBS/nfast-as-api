package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProdutoNcm {
    @Id
    @Column(name = "cod_item")
    private Long codItem;
    @Column(name = "seq_ncm")
    private Integer seqNcm;

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public Integer getSeqNcm() {
        return seqNcm;
    }

    public void setSeqNcm(Integer seqNcm) {
        this.seqNcm = seqNcm;
    }

}