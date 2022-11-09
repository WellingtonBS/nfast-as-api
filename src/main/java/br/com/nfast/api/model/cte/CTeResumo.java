package br.com.nfast.api.model.cte;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CTeResumo {
    @Id
    @Column(name = "seq_conhecimento")
    private Integer seqConhecimento;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "num_chave")
    private String numChave;
    @Column(name = "nom_tabela")
    private String nomTabela;

    public Integer getSeqConhecimento() {
        return seqConhecimento;
    }

    public void setSeqConhecimento(Integer seqConhecimento) {
        this.seqConhecimento = seqConhecimento;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNumChave() {
        return numChave;
    }

    public void setNumChave(String numChave) {
        this.numChave = numChave;
    }

    public String getNomTabela() {
        return nomTabela;
    }

    public void setNomTabela(String nomTabela) {
        this.nomTabela = nomTabela;
    }

}