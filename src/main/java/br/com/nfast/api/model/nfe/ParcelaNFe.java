package br.com.nfast.api.model.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tab_parcela_nfe")
public class ParcelaNFe {
    @EmbeddedId
    @JsonIgnore
    private ParcelaNFeId id;

    @Column(name = "seq_parcela", insertable = false, updatable = false)
    private Integer seqParcela;
    @Column(name = "dta_vencimento")
    private LocalDate dtaVencimento;
    @Column(name = "val_parcela")
    private Double valParcela = 0.0;
    @Column(name = "val_juros")
    private Double valJuros = 0.0;
    @Column(name = "num_cod_barra")
    private String numCodBarra;
    @Column(name = "num_nosso_numero")
    private String numNossoNumero;
    @Column(name = "val_desconto")
    private Double valDesconto = 0.0;
    @Column(name = "val_despesa_acessoria")
    private Double valDespesaAcessoria = 0.0;

    public ParcelaNFeId getId() {
        return id;
    }

    public void setId(ParcelaNFeId id) {
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

    public Double getValParcela() {
        return valParcela;
    }

    public void setValParcela(Double valParcela) {
        this.valParcela = valParcela;
    }

    public Double getValJuros() {
        return valJuros;
    }

    public void setValJuros(Double valJuros) {
        this.valJuros = valJuros;
    }

    public String getNumCodBarra() {
        return numCodBarra;
    }

    public void setNumCodBarra(String numCodBarra) {
        this.numCodBarra = numCodBarra;
    }

    public String getNumNossoNumero() {
        return numNossoNumero;
    }

    public void setNumNossoNumero(String numNossoNumero) {
        this.numNossoNumero = numNossoNumero;
    }

    public Double getValDesconto() {
        return valDesconto;
    }

    public void setValDesconto(Double valDesconto) {
        this.valDesconto = valDesconto;
    }

    public Double getValDespesaAcessoria() {
        return valDespesaAcessoria;
    }

    public void setValDespesaAcessoria(Double valDespesaAcessoria) {
        this.valDespesaAcessoria = valDespesaAcessoria;
    }

}