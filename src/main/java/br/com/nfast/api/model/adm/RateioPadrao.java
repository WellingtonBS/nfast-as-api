package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class RateioPadrao {
    @Id
    @Column(name = "seq_rateio")
    private Integer seqRateio;
    @Column(name = "seq_origem")
    private Integer seqOrigem;
    @Column(name = "ind_origem")
    private String indOrigem;
    @Column(name = "cod_centro_custo")
    private Long codCentroCusto;
    @Column(name = "per_rateio")
    private Double perRateio;

    public Integer getSeqRateio() {
        return seqRateio;
    }

    public void setSeqRateio(Integer seqRateio) {
        this.seqRateio = seqRateio;
    }

    public Integer getSeqOrigem() {
        return seqOrigem;
    }

    public void setSeqOrigem(Integer seqOrigem) {
        this.seqOrigem = seqOrigem;
    }

    public String getIndOrigem() {
        return indOrigem;
    }

    public void setIndOrigem(String indOrigem) {
        this.indOrigem = indOrigem;
    }

    public Long getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Long codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public Double getPerRateio() {
        return perRateio;
    }

    public void setPerRateio(Double perRateio) {
        this.perRateio = perRateio;
    }

}
