package br.com.nfast.api.model.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class AjusteDocFiscal {
    @Id
    @Column(name = "seq_aj_doc_fiscal")
    private Integer seqAjDocFiscal;

    @JsonIgnore
    @Column(name = "seq_nota")
    private Long seqNota;
    @JsonIgnore
    @Column(name = "seq_item")
    private Integer seqItem;
    @Column(name = "ind_tipo")
    private String indTipo;

    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "cod_item")
    private Long codItem;
    @Column(name = "seq_aj_apur_icms")
    private Integer seqAjApurIcms;
    @Column(name = "des_observacao")
    private String desObservacao;

    @Column(name = "val_base")
    private Double valBase = 0.0;
    @Column(name = "per_aliquota")
    private Double perAliquota = 0.0;
    @Column(name = "val_imposto")
    private Double valImposto = 0.0;
    @Column(name = "val_outro_imposto")
    private Double valOutroImposto = 0.0;

    public Integer getSeqAjDocFiscal() {
        return seqAjDocFiscal;
    }

    public void setSeqAjDocFiscal(Integer seqAjDocFiscal) {
        this.seqAjDocFiscal = seqAjDocFiscal;
    }

    public Long getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Long seqNota) {
        this.seqNota = seqNota;
    }

    public Integer getSeqItem() {
        return seqItem;
    }

    public void setSeqItem(Integer seqItem) {
        this.seqItem = seqItem;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public Integer getSeqAjApurIcms() {
        return seqAjApurIcms;
    }

    public void setSeqAjApurIcms(Integer seqAjApurIcms) {
        this.seqAjApurIcms = seqAjApurIcms;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public Double getValBase() {
        return valBase;
    }

    public void setValBase(Double valBase) {
        this.valBase = valBase;
    }

    public Double getPerAliquota() {
        return perAliquota;
    }

    public void setPerAliquota(Double perAliquota) {
        this.perAliquota = perAliquota;
    }

    public Double getValImposto() {
        return valImposto;
    }

    public void setValImposto(Double valImposto) {
        this.valImposto = valImposto;
    }

    public Double getValOutroImposto() {
        return valOutroImposto;
    }

    public void setValOutroImposto(Double valOutroImposto) {
        this.valOutroImposto = valOutroImposto;
    }

}