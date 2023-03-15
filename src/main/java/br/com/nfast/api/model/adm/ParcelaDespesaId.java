package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParcelaDespesaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "seq_despesa")
    private Despesa despesa;
    @Column(name = "num_parcela", insertable = false, updatable = false)
    private Integer numParcela;

    public ParcelaDespesaId() {

    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    @Override
    public int hashCode() {
        return Objects.hash(despesa, numParcela);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final ParcelaDespesaId other = (ParcelaDespesaId) obj;
        return Objects.equals(despesa, other.despesa) && Objects.equals(numParcela, other.numParcela);
    }

}
