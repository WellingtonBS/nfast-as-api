package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class NFeResumo {
    @Id
    @Column(name = "seq_nota")
    private Long seqNota;
    @Column(name = "num_nota")
    private String numNota;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "dta_entrada")
    private LocalDate dtaEntrada;
    @Column(name = "val_total_nota")
    private Double valTotalNota = 0.0;
    @Column(name = "nom_usuario")
    private String nomUsuario = "";
    @Column(name = "dta_digitacao")
    private LocalDate dtaDigitacao;
    @Column(name = "hra_digitacao")
    private String hraDigitacao;
    @Column(name = "cod_pessoa_fornecedor")
    private Integer codPessoaFornecedor;
    @Column(name = "nom_pessoa_fornecedor")
    private String nomPessoaFornecedor;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "nom_fantasia")
    private String nomFantasia;
    @Column(name = "num_chave_nfe")
    private String numChaveNfe;

    public Long getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Long seqNota) {
        this.seqNota = seqNota;
    }

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public LocalDate getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(LocalDate dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    public Double getValTotalNota() {
        return valTotalNota;
    }

    public void setValTotalNota(Double valTotalNota) {
        this.valTotalNota = valTotalNota;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public LocalDate getDtaDigitacao() {
        return dtaDigitacao;
    }

    public void setDtaDigitacao(LocalDate dtaDigitacao) {
        this.dtaDigitacao = dtaDigitacao;
    }

    public String getHraDigitacao() {
        return hraDigitacao;
    }

    public void setHraDigitacao(String hraDigitacao) {
        this.hraDigitacao = hraDigitacao;
    }

    public Integer getCodPessoaFornecedor() {
        return codPessoaFornecedor;
    }

    public void setCodPessoaFornecedor(Integer codPessoaFornecedor) {
        this.codPessoaFornecedor = codPessoaFornecedor;
    }

    public String getNomPessoaFornecedor() {
        return nomPessoaFornecedor;
    }

    public void setNomPessoaFornecedor(String nomPessoaFornecedor) {
        this.nomPessoaFornecedor = nomPessoaFornecedor;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNomFantasia() {
        return nomFantasia;
    }

    public void setNomFantasia(String nomFantasia) {
        this.nomFantasia = nomFantasia;
    }

    public String getNumChaveNfe() {
        return numChaveNfe;
    }

    public void setNumChaveNfe(String numChaveNfe) {
        this.numChaveNfe = numChaveNfe;
    }

}