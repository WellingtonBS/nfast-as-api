package br.com.nfast.api.model.estoque;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class ItemPedidoCompra {
    @EmbeddedId
    @JsonIgnore
    private ItemPedidoCompraId id;

    @Column(name = "seq_pedido", insertable = false, updatable = false)
    private Integer seqPedido;
    @Column(name = "seq_item", insertable = false, updatable = false)
    private Integer seqItem;

    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "qtd_item")
    private Double qtdItem = 0.0;
    @Column(name = "qtd_item_convertido")
    private Double qtdItemConvertido = 0.0;
    @Column(name = "val_unitario")
    private Double valUnitario = 0.0;
    @Column(name = "val_desconto")
    private Double valDesconto = 0.0;
    @Column(name = "val_total")
    private Double valTotal = 0.0;
    @Column(name = "qtd_atendido")
    private Double qtdAtendido = 0.0;
    @Column(name = "qtd_cancelado")
    private Double qtdCancelado = 0.0;
    @Column(name = "qtd_restante")
    private Double qtdRestante = 0.0;
    @Column(name = "cod_unidade")
    private Integer codUnidade;
    @Column(name = "sgl_unidade")
    private String sglUnidade;
    @Column(name = "cod_almoxarifado")
    private Integer codAlmoxarifado;
    @Column(name = "des_almoxarifado")
    private String desAlmoxarifado;
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "des_natureza_operacao")
    private String desNaturezaOperacao;
    @Column(name = "cod_tipo_despesa")
    private Integer codTipoDespesa;
    @Column(name = "cod_centro_custo")
    private Integer codCentroCusto;
    @Column(name = "des_observacao")
    private String desObservacao;
    @Column(name = "dta_previsao_entrega")
    private LocalDate dtaPrevisaoEntrega;

    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "des_observacao_pc")
    private String desObservacaoPc;

    public ItemPedidoCompraId getId() {
        return id;
    }

    public void setId(ItemPedidoCompraId id) {
        this.id = id;
    }

    public Integer getSeqPedido() {
        return seqPedido;
    }

    public void setSeqPedido(Integer seqPedido) {
        this.seqPedido = seqPedido;
    }

    public Integer getSeqItem() {
        return seqItem;
    }

    public void setSeqItem(Integer seqItem) {
        this.seqItem = seqItem;
    }

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public Double getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Double qtdItem) {
        this.qtdItem = qtdItem;
    }

    public Double getQtdItemConvertido() {
        return qtdItemConvertido;
    }

    public void setQtdItemConvertido(Double qtdItemConvertido) {
        this.qtdItemConvertido = qtdItemConvertido;
    }

    public Double getValUnitario() {
        return valUnitario;
    }

    public void setValUnitario(Double valUnitario) {
        this.valUnitario = valUnitario;
    }

    public Double getValDesconto() {
        return valDesconto;
    }

    public void setValDesconto(Double valDesconto) {
        this.valDesconto = valDesconto;
    }

    public Double getValTotal() {
        return valTotal;
    }

    public void setValTotal(Double valTotal) {
        this.valTotal = valTotal;
    }

    public Double getQtdAtendido() {
        return qtdAtendido;
    }

    public void setQtdAtendido(Double qtdAtendido) {
        this.qtdAtendido = qtdAtendido;
    }

    public Double getQtdCancelado() {
        return qtdCancelado;
    }

    public void setQtdCancelado(Double qtdCancelado) {
        this.qtdCancelado = qtdCancelado;
    }

    public Double getQtdRestante() {
        return qtdRestante;
    }

    public void setQtdRestante(Double qtdRestante) {
        this.qtdRestante = qtdRestante;
    }

    public Integer getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(Integer codUnidade) {
        this.codUnidade = codUnidade;
    }

    public String getSglUnidade() {
        return sglUnidade;
    }

    public void setSglUnidade(String sglUnidade) {
        this.sglUnidade = sglUnidade;
    }

    public Integer getCodAlmoxarifado() {
        return codAlmoxarifado;
    }

    public void setCodAlmoxarifado(Integer codAlmoxarifado) {
        this.codAlmoxarifado = codAlmoxarifado;
    }

    public String getDesAlmoxarifado() {
        return desAlmoxarifado;
    }

    public void setDesAlmoxarifado(String desAlmoxarifado) {
        this.desAlmoxarifado = desAlmoxarifado;
    }

    public Integer getCodNaturezaOperacao() {
        return codNaturezaOperacao;
    }

    public void setCodNaturezaOperacao(Integer codNaturezaOperacao) {
        this.codNaturezaOperacao = codNaturezaOperacao;
    }

    public String getDesNaturezaOperacao() {
        return desNaturezaOperacao;
    }

    public void setDesNaturezaOperacao(String desNaturezaOperacao) {
        this.desNaturezaOperacao = desNaturezaOperacao;
    }

    public Integer getCodTipoDespesa() {
        return codTipoDespesa;
    }

    public void setCodTipoDespesa(Integer codTipoDespesa) {
        this.codTipoDespesa = codTipoDespesa;
    }

    public Integer getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Integer codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public LocalDate getDtaPrevisaoEntrega() {
        return dtaPrevisaoEntrega;
    }

    public void setDtaPrevisaoEntrega(LocalDate dtaPrevisaoEntrega) {
        this.dtaPrevisaoEntrega = dtaPrevisaoEntrega;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getDesObservacaoPc() {
        return desObservacaoPc;
    }

    public void setDesObservacaoPc(String desObservacaoPc) {
        this.desObservacaoPc = desObservacaoPc;
    }

}
