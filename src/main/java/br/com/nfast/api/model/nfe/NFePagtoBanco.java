package br.com.nfast.api.model.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class NFePagtoBanco {
    @EmbeddedId
    @JsonIgnore
    private NFePagtoBancoId id;

    @Column(name = "seq_pagamento", insertable = false, updatable = false)
    private Integer seqPagamento;
    @Column(name = "num_mnemonico")
    private String numMnemonico;
    @Column(name = "dta_predatado_banco")
    private LocalDate dtaPredatadoBanco;
    @Column(name = "num_doc_banco")
    private String numDocBanco;
    @Column(name = "val_pagamento_banco")
    private Double valPagamentoBanco = 0.0;

    public NFePagtoBancoId getId() {
        return id;
    }

    public void setId(NFePagtoBancoId id) {
        this.id = id;
    }

    public Integer getSeqPagamento() {
        return seqPagamento;
    }

    public void setSeqPagamento(Integer seqPagamento) {
        this.seqPagamento = seqPagamento;
    }

    public String getNumMnemonico() {
        return numMnemonico;
    }

    public void setNumMnemonico(String numMnemonico) {
        this.numMnemonico = numMnemonico;
    }

    public LocalDate getDtaPredatadoBanco() {
        return dtaPredatadoBanco;
    }

    public void setDtaPredatadoBanco(LocalDate dtaPredatadoBanco) {
        this.dtaPredatadoBanco = dtaPredatadoBanco;
    }

    public String getNumDocBanco() {
        return numDocBanco;
    }

    public void setNumDocBanco(String numDocBanco) {
        this.numDocBanco = numDocBanco;
    }

    public Double getValPagamentoBanco() {
        return valPagamentoBanco;
    }

    public void setValPagamentoBanco(Double valPagamentoBanco) {
        this.valPagamentoBanco = valPagamentoBanco;
    }

}