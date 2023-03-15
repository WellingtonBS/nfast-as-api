package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DespesaResumo {

    @Id
    @Column(name = "seq_despesa")
    private Integer seqDespesa;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "dta_despesa")
    private LocalDate dtaDespesa;
    @Transient
    private List<ParcelaDespesaResumo> parcelas = new ArrayList<>();
    @Transient
    private List<RateioDespesaResumo> rateio = new ArrayList<>();

    public Integer getSeqDespesa() {
        return seqDespesa;
    }

    public void setSeqDespesa(Integer seqDespesa) {
        this.seqDespesa = seqDespesa;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public LocalDate getDtaDespesa() {
        return dtaDespesa;
    }

    public void setDtaDespesa(LocalDate dtaDespesa) {
        this.dtaDespesa = dtaDespesa;
    }

    public List<ParcelaDespesaResumo> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ParcelaDespesaResumo> parcelas) {
        this.parcelas = parcelas;
    }

    public List<RateioDespesaResumo> getRateio() {
        return rateio;
    }

    public void setRateio(List<RateioDespesaResumo> rateio) {
        this.rateio = rateio;
    }
}
