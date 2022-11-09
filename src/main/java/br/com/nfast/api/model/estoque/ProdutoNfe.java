package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProdutoNfe {
    @Id
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "des_item")
    private String desItem;
    @Column(name = "cod_barra")
    private String codBarra;
    @Column(name = "ind_item_ativo")
    private String indItemAtivo;

    @Column(name = "cod_unidade")
    private Integer codUnidade;
    @Column(name = "cod_unidade_agrupamento_1")
    private Integer codUnidadeAgrupamento1;
    @Column(name = "sgl_unidade_agrupamento_1")
    private String sglUnidadeAgrupamento1;
    @Column(name = "qtd_unidade_agrupamento_1")
    private Double qtdUnidadeAgrupamento1;
    @Column(name = "cod_unidade_agrupamento_2")
    private Integer codUnidadeAgrupamento2;
    @Column(name = "sgl_unidade_agrupamento_2")
    private String sglUnidadeAgrupamento2;
    @Column(name = "qtd_unidade_agrupamento_2")
    private Double qtdUnidadeAgrupamento2;

    @Column(name = "cod_tipo_despesa")
    private Integer codTipoDespesa;

    @Column(name = "cod_ncm")
    private String codNcm;
    @Column(name = "cod_nop_de_ent")
    private Integer codNopDeEnt;
    @Column(name = "cod_nop_fe_ent")
    private Integer codNopFeEnt;
    @Column(name = "cod_nop_de_tra")
    private Integer codNopDeTra;
    @Column(name = "cod_nop_fe_tra")
    private Integer codNopFeTra;

    @Column(name = "cod_ipi")
    private Integer codIpi;
    @Column(name = "cod_pis")
    private Integer codPis;
    @Column(name = "cod_cofins")
    private Integer codCofins;

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public String getDesItem() {
        return desItem;
    }

    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getIndItemAtivo() {
        return indItemAtivo;
    }

    public void setIndItemAtivo(String indItemAtivo) {
        this.indItemAtivo = indItemAtivo;
    }

    public Integer getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(Integer codUnidade) {
        this.codUnidade = codUnidade;
    }

    public Integer getCodUnidadeAgrupamento1() {
        return codUnidadeAgrupamento1;
    }

    public void setCodUnidadeAgrupamento1(Integer codUnidadeAgrupamento1) {
        this.codUnidadeAgrupamento1 = codUnidadeAgrupamento1;
    }

    public String getSglUnidadeAgrupamento1() {
        return sglUnidadeAgrupamento1;
    }

    public void setSglUnidadeAgrupamento1(String sglUnidadeAgrupamento1) {
        this.sglUnidadeAgrupamento1 = sglUnidadeAgrupamento1;
    }

    public Double getQtdUnidadeAgrupamento1() {
        return qtdUnidadeAgrupamento1;
    }

    public void setQtdUnidadeAgrupamento1(Double qtdUnidadeAgrupamento1) {
        this.qtdUnidadeAgrupamento1 = qtdUnidadeAgrupamento1;
    }

    public Integer getCodUnidadeAgrupamento2() {
        return codUnidadeAgrupamento2;
    }

    public void setCodUnidadeAgrupamento2(Integer codUnidadeAgrupamento2) {
        this.codUnidadeAgrupamento2 = codUnidadeAgrupamento2;
    }

    public String getSglUnidadeAgrupamento2() {
        return sglUnidadeAgrupamento2;
    }

    public void setSglUnidadeAgrupamento2(String sglUnidadeAgrupamento2) {
        this.sglUnidadeAgrupamento2 = sglUnidadeAgrupamento2;
    }

    public Double getQtdUnidadeAgrupamento2() {
        return qtdUnidadeAgrupamento2;
    }

    public void setQtdUnidadeAgrupamento2(Double qtdUnidadeAgrupamento2) {
        this.qtdUnidadeAgrupamento2 = qtdUnidadeAgrupamento2;
    }

    public Integer getCodTipoDespesa() {
        return codTipoDespesa;
    }

    public void setCodTipoDespesa(Integer codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }

    public String getCodNcm() {
        return codNcm;
    }

    public void setCodNcm(String codNcm) {
        this.codNcm = codNcm;
    }

    public Integer getCodNopDeEnt() {
        return codNopDeEnt;
    }

    public void setCodNopDeEnt(Integer codNopDeEnt) {
        this.codNopDeEnt = codNopDeEnt;
    }

    public Integer getCodNopFeEnt() {
        return codNopFeEnt;
    }

    public void setCodNopFeEnt(Integer codNopFeEnt) {
        this.codNopFeEnt = codNopFeEnt;
    }

    public Integer getCodNopDeTra() {
        return codNopDeTra;
    }

    public void setCodNopDeTra(Integer codNopDeTra) {
        this.codNopDeTra = codNopDeTra;
    }

    public Integer getCodNopFeTra() {
        return codNopFeTra;
    }

    public void setCodNopFeTra(Integer codNopFeTra) {
        this.codNopFeTra = codNopFeTra;
    }

    public Integer getCodIpi() {
        return codIpi;
    }

    public void setCodIpi(Integer codIpi) {
        this.codIpi = codIpi;
    }

    public Integer getCodPis() {
        return codPis;
    }

    public void setCodPis(Integer codPis) {
        this.codPis = codPis;
    }

    public Integer getCodCofins() {
        return codCofins;
    }

    public void setCodCofins(Integer codCofins) {
        this.codCofins = codCofins;
    }
}
