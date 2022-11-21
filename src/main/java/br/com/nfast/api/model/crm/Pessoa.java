package br.com.nfast.api.model.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pessoa {
    @Id
    @Column(name = "cod_pessoa")
    private Integer codPessoa;
    @Column(name = "num_cnpj_cpf")
    private String numCnpjCpf;
    @Column(name = "nom_pessoa")
    private String nomPessoa;
    @Column(name = "ind_bloqueado")
    private String indBloqueado;
    @Column(name = "ind_pessoa_ativa")
    private String indPessoaAtiva;

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNumCnpjCpf() {
        return numCnpjCpf;
    }

    public void setNumCnpjCpf(String numCnpjCpf) {
        this.numCnpjCpf = numCnpjCpf;
    }

    public String getNomPessoa() {
        return nomPessoa;
    }

    public void setNomPessoa(String nomPessoa) {
        this.nomPessoa = nomPessoa;
    }

    public String getIndBloqueado() {
        return indBloqueado;
    }

    public void setIndBloqueado(String indBloqueado) {
        this.indBloqueado = indBloqueado;
    }

    public String getIndPessoaAtiva() {
        return indPessoaAtiva;
    }

    public void setIndPessoaAtiva(String indPessoaAtiva) {
        this.indPessoaAtiva = indPessoaAtiva;
    }

}
