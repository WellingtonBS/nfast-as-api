package br.com.nfast.api.model.adm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ParcelaDespesa {
    @EmbeddedId
    @JsonIgnore
    private ParcelaDespesaId id;

    @Column(name = "num_parcela", insertable = false, updatable = false)
    private Integer numParcela;
    @Column(name = "dta_vencimento")
    private LocalDate dtaVencimento;
    @Column(name = "val_parcela")
    private Double valParcela = 0.0;
    @Column(name = "val_juros")
    private Double valJuros = 0.0;
    @Column(name = "cod_pessoa_sacador")
    private Long codPessoaSacador;
    @Column(name = "num_cod_barra")
    private String numCodBarra = "";
    @Column(name = "num_nosso_numero")
    private String numNossoNumero = "";

    public ParcelaDespesaId getId() {
        return id;
    }

    public void setId(ParcelaDespesaId id) {
        this.id = id;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
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

    public Long getCodPessoaSacador() {
        return codPessoaSacador;
    }

    public void setCodPessoaSacador(Long codPessoaSacador) {
        this.codPessoaSacador = codPessoaSacador;
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
}
