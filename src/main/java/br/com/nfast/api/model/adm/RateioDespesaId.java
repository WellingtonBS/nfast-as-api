package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RateioDespesaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "seq_despesa")
    private Despesa despesa;
    @Column(name = "cod_centro_custo", insertable = false, updatable = false)
    private Long codCentroCusto;

    public RateioDespesaId() {

    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public Long getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Long codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(despesa, codCentroCusto);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final RateioDespesaId other = (RateioDespesaId) obj;
        return Objects.equals(despesa, other.despesa) && Objects.equals(codCentroCusto, other.codCentroCusto);
    }

}
