package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ConhecimentoFreteResumo {

    @Id
    @Column(name = "seq_nota")
    private Long seqNota;
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "cod_pessoa_transportadora")
    private Long codPessoaTransportadora;
    @Column(name = "num_conhecimento")
    private String numConhecimento;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "val_frete")
    private Double valFrete = 0.0;
    @Column(name = "num_chave_nfe")
    private String numChaveNfe;

    public Long getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Long seqNota) {
        this.seqNota = seqNota;
    }

    public Integer getCodNaturezaOperacao() {
        return codNaturezaOperacao;
    }

    public void setCodNaturezaOperacao(Integer codNaturezaOperacao) {
        this.codNaturezaOperacao = codNaturezaOperacao;
    }

    public Long getCodPessoaTransportadora() {
        return codPessoaTransportadora;
    }

    public void setCodPessoaTransportadora(Long codPessoaTransportadora) {
        this.codPessoaTransportadora = codPessoaTransportadora;
    }

    public String getNumConhecimento() {
        return numConhecimento;
    }

    public void setNumConhecimento(String numConhecimento) {
        this.numConhecimento = numConhecimento;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public Double getValFrete() {
        return valFrete;
    }

    public void setValFrete(Double valFrete) {
        this.valFrete = valFrete;
    }

    public String getNumChaveNfe() {
        return numChaveNfe;
    }

    public void setNumChaveNfe(String numChaveNfe) {
        this.numChaveNfe = numChaveNfe;
    }
}
