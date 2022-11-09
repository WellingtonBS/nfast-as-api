package br.com.nfast.api.model.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContaBanco {
    @Id
    @Column(name = "seq_conta_banco")
    private Integer seqContaBanco;
    @Column(name = "num_mnemonico")
    private String numMnemonico;
    @Column(name = "num_conta")
    private String numConta;
    @Column(name = "num_agencia")
    private String numAgencia;
    @Column(name = "num_banco")
    private String numBanco;
    @Column(name = "nom_banco")
    private String nomBanco;
    @Column(name = "ind_ativo")
    private String indAtivo;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;

    public Integer getSeqContaBanco() {
        return seqContaBanco;
    }

    public void setSeqContaBanco(Integer seqContaBanco) {
        this.seqContaBanco = seqContaBanco;
    }

    public String getNumMnemonico() {
        return numMnemonico;
    }

    public void setNumMnemonico(String numMnemonico) {
        this.numMnemonico = numMnemonico;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public String getNumBanco() {
        return numBanco;
    }

    public void setNumBanco(String numBanco) {
        this.numBanco = numBanco;
    }

    public String getNomBanco() {
        return nomBanco;
    }

    public void setNomBanco(String nomBanco) {
        this.nomBanco = nomBanco;
    }

    public String getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(String indAtivo) {
        this.indAtivo = indAtivo;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

}
