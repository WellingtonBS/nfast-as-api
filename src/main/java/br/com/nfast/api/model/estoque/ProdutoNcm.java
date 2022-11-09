package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_item")
public class ProdutoNcm {
    @Id
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "seq_ncm")
    private Integer seqNcm;

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public Integer getSeqNcm() {
        return seqNcm;
    }

    public void setSeqNcm(Integer seqNcm) {
        this.seqNcm = seqNcm;
    }

}