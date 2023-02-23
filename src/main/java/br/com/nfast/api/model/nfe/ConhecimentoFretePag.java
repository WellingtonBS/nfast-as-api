package br.com.nfast.api.model.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ConhecimentoFretePag {
    @EmbeddedId
    @JsonIgnore
    private ConhecimentoFretePagId id;

    @Column(name = "seq_parcela", insertable = false, updatable = false)
    private Integer seqParcela;
    @Column(name = "dta_vencimento")
    private LocalDate dtaVencimento;
    @Column(name = "val_titulo")
    private Double valTitulo = 0.0;

    public ConhecimentoFretePagId getId() {
        return id;
    }

    public void setId(ConhecimentoFretePagId id) {
        this.id = id;
    }

    public Integer getSeqParcela() {
        return seqParcela;
    }

    public void setSeqParcela(Integer seqParcela) {
        this.seqParcela = seqParcela;
    }

    public LocalDate getDtaVencimento() {
        return dtaVencimento;
    }

    public void setDtaVencimento(LocalDate dtaVencimento) {
        this.dtaVencimento = dtaVencimento;
    }

    public Double getValTitulo() {
        return valTitulo;
    }

    public void setValTitulo(Double valTitulo) {
        this.valTitulo = valTitulo;
    }

}
