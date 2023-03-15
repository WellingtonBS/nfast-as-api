package br.com.nfast.api.model.conhecimento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConhecimentoNotaResumo {

    @Id
    @Column(name = "seq_nota")
    private Integer seqNota;
    @Column(name = "seq_conhecimento")
    private Integer seqConhecimento;
    @Column(name = "ind_entrada_saida")
    private String indEntradaSaida;

    public ConhecimentoNotaResumo() {
    }

    public Integer getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Integer seqNota) {
        this.seqNota = seqNota;
    }

    public Integer getSeqConhecimento() {
        return seqConhecimento;
    }

    public void setSeqConhecimento(Integer seqConhecimento) {
        this.seqConhecimento = seqConhecimento;
    }

    public String getIndEntradaSaida() {
        return indEntradaSaida;
    }

    public void setIndEntradaSaida(String indEntradaSaida) {
        this.indEntradaSaida = indEntradaSaida;
    }
}
