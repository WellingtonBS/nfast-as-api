package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProdutoFor {
    @Id
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "cod_unidade_agrupamento")
    private Integer codUnidadeAgrupamento;
    @Column(name = "qtd_unidade_agrupamento")
    private Double qtdUnidadeAgrupamento = 1.0;

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public Integer getCodUnidadeAgrupamento() {
        return codUnidadeAgrupamento;
    }

    public void setCodUnidadeAgrupamento(Integer codUnidadeAgrupamento) {
        this.codUnidadeAgrupamento = codUnidadeAgrupamento;
    }

    public Double getQtdUnidadeAgrupamento() {
        return qtdUnidadeAgrupamento;
    }

    public void setQtdUnidadeAgrupamento(Double qtdUnidadeAgrupamento) {
        this.qtdUnidadeAgrupamento = qtdUnidadeAgrupamento;
    }

}