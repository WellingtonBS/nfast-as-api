package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NFePagtoBancoId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "seq_nota")
    private NFe nfe;
    @Column(name = "seq_pagamento", insertable = false, updatable = false)
    private Integer seqPagamento;

    public NFePagtoBancoId() {

    }

    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

    public Integer getSeqPagamento() {
        return seqPagamento;
    }

    public void setSeqPagamento(Integer seqPagamento) {
        this.seqPagamento = seqPagamento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nfe, seqPagamento);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final NFePagtoBancoId other = (NFePagtoBancoId) obj;
        return Objects.equals(nfe, other.nfe) && Objects.equals(seqPagamento, other.seqPagamento);
    }

}
