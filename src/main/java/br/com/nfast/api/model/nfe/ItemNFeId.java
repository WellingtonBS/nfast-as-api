package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemNFeId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "seq_nota")
    private NFe nfe;
    @Column(name = "seq_item", insertable = false, updatable = false)
    private Integer seqItem;

    public ItemNFeId() {

    }

    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

    public Integer getSeqItem() {
        return seqItem;
    }

    public void setSeqItem(Integer seqItem) {
        this.seqItem = seqItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nfe, seqItem);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final ItemNFeId other = (ItemNFeId) obj;
        return Objects.equals(nfe, other.nfe) && Objects.equals(seqItem, other.seqItem);
    }

}
