package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConhecimentoFretePagId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "seq_nota")
    private ConhecimentoFrete conhecimento;
    @Column(name = "seq_parcela", insertable = false, updatable = false)
    private Integer seqParcela;

    public ConhecimentoFretePagId() {

    }

    public ConhecimentoFrete getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(ConhecimentoFrete conhecimento) {
        this.conhecimento = conhecimento;
    }

    public Integer getSeqParcela() {
        return seqParcela;
    }

    public void setSeqParcela(Integer seqParcela) {
        this.seqParcela = seqParcela;
    }

    @Override
    public int hashCode() {
        return Objects.hash(conhecimento, seqParcela);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final ConhecimentoFretePagId other = (ConhecimentoFretePagId) obj;
        return Objects.equals(conhecimento, other.conhecimento) && Objects.equals(seqParcela, other.seqParcela);
    }

}
