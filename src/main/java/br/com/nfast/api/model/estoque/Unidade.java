package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Unidade {
    @Id
    @Column(name = "cod_unidade")
    private Integer codUnidade;
    @Column(name = "des_unidade")
    private String desUnidade;
    @Column(name = "sgl_unidade")
    private String sglUnidade;
    @Column(name = "num_fator_conversao")
    private Double numFatorConversao = 1.0;

    public Integer getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(Integer codUnidade) {
        this.codUnidade = codUnidade;
    }

    public String getDesUnidade() {
        return desUnidade;
    }

    public void setDesUnidade(String desUnidade) {
        this.desUnidade = desUnidade;
    }

    public String getSglUnidade() {
        return sglUnidade;
    }

    public void setSglUnidade(String sglUnidade) {
        this.sglUnidade = sglUnidade;
    }

    public Double getNumFatorConversao() {
        return numFatorConversao;
    }

    public void setNumFatorConversao(Double numFatorConversao) {
        this.numFatorConversao = numFatorConversao;
    }

}