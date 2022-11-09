package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParcelaNFeId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "seq_nota")
    private NFe nfe;
    @Column(name = "seq_parcela", insertable = false, updatable = false)
    private Integer seqParcela;

    public ParcelaNFeId() {

    }

    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

    public Integer getSeqParcela() {
        return seqParcela;
    }

    public void setSeqParcela(Integer seqParcela) {
        this.seqParcela = seqParcela;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nfe, seqParcela);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final ParcelaNFeId other = (ParcelaNFeId) obj;
        return Objects.equals(nfe, other.nfe) && Objects.equals(seqParcela, other.seqParcela);
    }

}
