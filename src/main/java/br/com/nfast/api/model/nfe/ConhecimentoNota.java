package br.com.nfast.api.model.nfe;

import br.com.nfast.api.model.conhecimento.Conhecimento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ConhecimentoNota {

    @Id
    @JsonIgnore
    @Column(name = "seq_nota")
    private Long seqNota;

    @MapsId
    @OneToOne
    @JoinColumn(name = "seq_nota")
    private NFe nfe;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seq_conhecimento")
    private Conhecimento conhecimento;

    @Column(name = "ind_entrada_saida")
    private String indEntradaSaida = "E";

    public Long getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Long seqNota) {
        this.seqNota = seqNota;
    }

    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

    public Conhecimento getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(Conhecimento conhecimento) {
        this.conhecimento = conhecimento;
    }

    public String getIndEntradaSaida() {
        return indEntradaSaida;
    }

    public void setIndEntradaSaida(String indEntradaSaida) {
        this.indEntradaSaida = indEntradaSaida;
    }

}
