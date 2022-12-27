package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nop {
    @Id
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "des_natureza_operacao")
    private String desNaturezaOperacao;
    @Column(name = "num_cfop")
    private String numCfop;
    @Column(name = "ind_entrada_saida")
    private String indEntradaSaida;
    @Column(name = "ind_gera_estoque")
    private String indGeraEstoque;
    @Column(name = "ind_gera_despesa")
    private String indGeraDespesa;
    @Column(name = "ind_gera_financeiro")
    private String indGeraFinanceiro;
    @Column(name = "ind_origem_destino")
    private String indOrigemDestino;
    @Column(name = "ind_ativo_imobilizado")
    private String indAtivoImobilizado;
    @Column(name = "ind_entrega_futura")
    private String indEntregaFutura;
    @Column(name = "ind_nop_frete")
    private String indNopFrete;
    @Column(name = "ind_transferencia")
    private String indTransferencia;
    @Column(name = "ind_bonificacao")
    private String indBonificacao;
    @Column(name = "cod_tipo_movimento_estoque")
    private Integer codTipoMovimentoEstoque;
    @Column(name = "ind_desativada")
    private String indDesativada;

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

    public String getNumCfop() {
        return numCfop;
    }

    public void setNumCfop(String numCfop) {
        this.numCfop = numCfop;
    }

    public String getIndEntradaSaida() {
        return indEntradaSaida;
    }

    public void setIndEntradaSaida(String indEntradaSaida) {
        this.indEntradaSaida = indEntradaSaida;
    }

    public String getIndGeraEstoque() {
        return indGeraEstoque;
    }

    public void setIndGeraEstoque(String indGeraEstoque) {
        this.indGeraEstoque = indGeraEstoque;
    }

    public String getIndGeraDespesa() {
        return indGeraDespesa;
    }

    public void setIndGeraDespesa(String indGeraDespesa) {
        this.indGeraDespesa = indGeraDespesa;
    }

    public String getIndGeraFinanceiro() {
        return indGeraFinanceiro;
    }

    public void setIndGeraFinanceiro(String indGeraFinanceiro) {
        this.indGeraFinanceiro = indGeraFinanceiro;
    }

    public String getIndOrigemDestino() {
        return indOrigemDestino;
    }

    public void setIndOrigemDestino(String indOrigemDestino) {
        this.indOrigemDestino = indOrigemDestino;
    }

    public String getIndAtivoImobilizado() {
        return indAtivoImobilizado;
    }

    public void setIndAtivoImobilizado(String indAtivoImobilizado) {
        this.indAtivoImobilizado = indAtivoImobilizado;
    }

    public String getIndEntregaFutura() {
        return indEntregaFutura;
    }

    public void setIndEntregaFutura(String indEntregaFutura) {
        this.indEntregaFutura = indEntregaFutura;
    }

    public String getIndNopFrete() {
        return indNopFrete;
    }

    public void setIndNopFrete(String indNopFrete) {
        this.indNopFrete = indNopFrete;
    }

    public String getIndTransferencia() {
        return indTransferencia;
    }

    public void setIndTransferencia(String indTransferencia) {
        this.indTransferencia = indTransferencia;
    }

    public String getIndBonificacao() {
        return indBonificacao;
    }

    public void setIndBonificacao(String indBonificacao) {
        this.indBonificacao = indBonificacao;
    }

    public Integer getCodTipoMovimentoEstoque() {
        return codTipoMovimentoEstoque;
    }

    public void setCodTipoMovimentoEstoque(Integer codTipoMovimentoEstoque) {
        this.codTipoMovimentoEstoque = codTipoMovimentoEstoque;
    }

    public String getIndDesativada() {
        return indDesativada;
    }

    public void setIndDesativada(String indDesativada) {
        this.indDesativada = indDesativada;
    }

}