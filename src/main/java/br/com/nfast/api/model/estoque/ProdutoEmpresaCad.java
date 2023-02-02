package br.com.nfast.api.model.estoque;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tab_item_empresa")
public class ProdutoEmpresaCad {
    @JsonIgnore
    @EmbeddedId
    private ProdutoEmpresaIdCad id;

    @Column(name = "cod_item", insertable = false, updatable = false)
    private Long codItem;
    @Column(name = "cod_empresa", insertable = false, updatable = false)
    private Long codEmpresa;

    @Column(name = "qtd_estoque_minimo")
    private Double qtdEstoqueMinimo = 0.0;
    @Column(name = "qtd_estoque_maximo")
    private Double qtdEstoqueMaximo = 0.0;
    @Column(name = "qtd_estoque_seguranca")
    private Double qtdEstoqueSeguranca = 0.0;
    @Column(name = "qtd_lote_economico_producao")
    private Double qtdLoteEconomicoProducao = 0.0;
    @Column(name = "qtd_lote_economico_compra")
    private Double qtdLoteEconomicoCompra = 0.0;
    @Column(name = "qtd_empenho")
    private Double qtdEmpenho = 0.0;
    @Column(name = "qtd_ponto_reposicao")
    private Double qtdPontoReposicao = 0.0;
    @Column(name = "qtd_tempo_medio_reposicao")
    private Double qtdTempoMedioReposicao = 0.0;
    @Column(name = "val_preco_reposicao")
    private Double valPrecoReposicao = 0.0;
    @Column(name = "per_mrp")
    private Double perMrp = 0.0;
    @Column(name = "val_custo_unitario")
    private Double valCustoUnitario = 0.0;
    @Column(name = "per_desconto_maximo")
    private Double perDescontoMaximo = 0.0;
    @Column(name = "per_margem_desejada")
    private Double perMargemDesejada = 0.0;
    @Column(name = "per_margem_minima")
    private Double perMargemMinima = 0.0;
    @Column(name = "fat_valor_b")
    private Double fatValorB = 0.0;
    @Column(name = "fat_valor_c")
    private Double fatValorC = 0.0;
    @Column(name = "fat_valor_d")
    private Double fatValorD = 0.0;
    @Column(name = "fat_valor_e")
    private Double fatValorE = 0.0;
    @Column(name = "ind_planejado")
    private String indPlanejado = "N";
    @Column(name = "ind_abc_estoque")
    private String indAbcEstoque = "N";
    @Column(name = "ind_abc_movimento")
    private String indAbcMovimento = "N";
    @Column(name = "val_custo_medio")
    private Double valCustoMedio = 0.0;
    @Column(name = "ind_item_ativo")
    private String indItemAtivo = "S";
    @Column(name = "cod_tributacao_ipi")
    private Integer codTributacaoIpi;
    @Column(name = "cod_classe_fiscal")
    private Integer codClasseFiscal;
    @Column(name = "ind_producao_automatica")
    private String indProducaoAutomatica = "N";
    @Column(name = "ind_tipo_item_fiscal")
    private String indTipoItemFiscal;
    @Column(name = "cod_tributacao_pis")
    private Integer codTributacaoPis;
    @Column(name = "cod_tributacao_cofins")
    private Integer codTributacaoCofins;
    @Column(name = "qtd_tara")
    private Double qtdTara = 0.0;
    @Column(name = "ind_tipo_controle")
    private String indTipoControle = "U";
    @Column(name = "per_cred_icms")
    private Double perCredIcms = 0.0;
    @Column(name = "per_cred_piscofins")
    private Double perCredPiscofins = 0.0;
    @Column(name = "per_deb_icms")
    private Double perDebIcms = 0.0;
    @Column(name = "per_deb_piscofins")
    private Double perDebPiscofins = 0.0;
    @Column(name = "per_margem_ram")
    private Double perMargemRam = 0.0;
    @Column(name = "per_margem_padrao_ram")
    private Double perMargemPadraoRam = 0.0;
    @Column(name = "per_markup_ram")
    private Double perMarkupRam = 0.0;
    @Column(name = "ind_tipo_entrega")
    private String indTipoEntrega = "D";
    @Column(name = "ind_item_critico")
    private String indItemCritico = "N";
    @Column(name = "per_margem")
    private Double perMargem = 0.0;
    @Column(name = "ind_bloqueado_compra")
    private String indBloqueadoCompra = "N";
    @Column(name = "ind_solicita_vendedor")
    private String indSolicitaVendedor = "N";
    @Column(name = "cod_tributacao_pis_entrada")
    private Integer codTributacaoPisEntrada;
    @Column(name = "cod_tributacao_cofins_entrada")
    private Integer codTributacaoCofinsEntrada;
    @Column(name = "ind_controle_anvisa")
    private String indControleAnvisa = "N";

    public ProdutoEmpresaIdCad getId() {
        return id;
    }

    public void setId(ProdutoEmpresaIdCad id) {
        this.id = id;
    }

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Double getQtdEstoqueMinimo() {
        return qtdEstoqueMinimo;
    }

    public void setQtdEstoqueMinimo(Double qtdEstoqueMinimo) {
        this.qtdEstoqueMinimo = qtdEstoqueMinimo;
    }

    public Double getQtdEstoqueMaximo() {
        return qtdEstoqueMaximo;
    }

    public void setQtdEstoqueMaximo(Double qtdEstoqueMaximo) {
        this.qtdEstoqueMaximo = qtdEstoqueMaximo;
    }

    public Double getQtdEstoqueSeguranca() {
        return qtdEstoqueSeguranca;
    }

    public void setQtdEstoqueSeguranca(Double qtdEstoqueSeguranca) {
        this.qtdEstoqueSeguranca = qtdEstoqueSeguranca;
    }

    public Double getQtdLoteEconomicoProducao() {
        return qtdLoteEconomicoProducao;
    }

    public void setQtdLoteEconomicoProducao(Double qtdLoteEconomicoProducao) {
        this.qtdLoteEconomicoProducao = qtdLoteEconomicoProducao;
    }

    public Double getQtdLoteEconomicoCompra() {
        return qtdLoteEconomicoCompra;
    }

    public void setQtdLoteEconomicoCompra(Double qtdLoteEconomicoCompra) {
        this.qtdLoteEconomicoCompra = qtdLoteEconomicoCompra;
    }

    public Double getQtdEmpenho() {
        return qtdEmpenho;
    }

    public void setQtdEmpenho(Double qtdEmpenho) {
        this.qtdEmpenho = qtdEmpenho;
    }

    public Double getQtdPontoReposicao() {
        return qtdPontoReposicao;
    }

    public void setQtdPontoReposicao(Double qtdPontoReposicao) {
        this.qtdPontoReposicao = qtdPontoReposicao;
    }

    public Double getQtdTempoMedioReposicao() {
        return qtdTempoMedioReposicao;
    }

    public void setQtdTempoMedioReposicao(Double qtdTempoMedioReposicao) {
        this.qtdTempoMedioReposicao = qtdTempoMedioReposicao;
    }

    public Double getValPrecoReposicao() {
        return valPrecoReposicao;
    }

    public void setValPrecoReposicao(Double valPrecoReposicao) {
        this.valPrecoReposicao = valPrecoReposicao;
    }

    public Double getPerMrp() {
        return perMrp;
    }

    public void setPerMrp(Double perMrp) {
        this.perMrp = perMrp;
    }

    public Double getValCustoUnitario() {
        return valCustoUnitario;
    }

    public void setValCustoUnitario(Double valCustoUnitario) {
        this.valCustoUnitario = valCustoUnitario;
    }

    public Double getPerDescontoMaximo() {
        return perDescontoMaximo;
    }

    public void setPerDescontoMaximo(Double perDescontoMaximo) {
        this.perDescontoMaximo = perDescontoMaximo;
    }

    public Double getPerMargemDesejada() {
        return perMargemDesejada;
    }

    public void setPerMargemDesejada(Double perMargemDesejada) {
        this.perMargemDesejada = perMargemDesejada;
    }

    public Double getPerMargemMinima() {
        return perMargemMinima;
    }

    public void setPerMargemMinima(Double perMargemMinima) {
        this.perMargemMinima = perMargemMinima;
    }

    public Double getFatValorB() {
        return fatValorB;
    }

    public void setFatValorB(Double fatValorB) {
        this.fatValorB = fatValorB;
    }

    public Double getFatValorC() {
        return fatValorC;
    }

    public void setFatValorC(Double fatValorC) {
        this.fatValorC = fatValorC;
    }

    public Double getFatValorD() {
        return fatValorD;
    }

    public void setFatValorD(Double fatValorD) {
        this.fatValorD = fatValorD;
    }

    public Double getFatValorE() {
        return fatValorE;
    }

    public void setFatValorE(Double fatValorE) {
        this.fatValorE = fatValorE;
    }

    public String getIndPlanejado() {
        return indPlanejado;
    }

    public void setIndPlanejado(String indPlanejado) {
        this.indPlanejado = indPlanejado;
    }

    public String getIndAbcEstoque() {
        return indAbcEstoque;
    }

    public void setIndAbcEstoque(String indAbcEstoque) {
        this.indAbcEstoque = indAbcEstoque;
    }

    public String getIndAbcMovimento() {
        return indAbcMovimento;
    }

    public void setIndAbcMovimento(String indAbcMovimento) {
        this.indAbcMovimento = indAbcMovimento;
    }

    public Double getValCustoMedio() {
        return valCustoMedio;
    }

    public void setValCustoMedio(Double valCustoMedio) {
        this.valCustoMedio = valCustoMedio;
    }

    public String getIndItemAtivo() {
        return indItemAtivo;
    }

    public void setIndItemAtivo(String indItemAtivo) {
        this.indItemAtivo = indItemAtivo;
    }

    public Integer getCodTributacaoIpi() {
        return codTributacaoIpi;
    }

    public void setCodTributacaoIpi(Integer codTributacaoIpi) {
        this.codTributacaoIpi = codTributacaoIpi;
    }

    public Integer getCodClasseFiscal() {
        return codClasseFiscal;
    }

    public void setCodClasseFiscal(Integer codClasseFiscal) {
        this.codClasseFiscal = codClasseFiscal;
    }

    public String getIndProducaoAutomatica() {
        return indProducaoAutomatica;
    }

    public void setIndProducaoAutomatica(String indProducaoAutomatica) {
        this.indProducaoAutomatica = indProducaoAutomatica;
    }

    public String getIndTipoItemFiscal() {
        return indTipoItemFiscal;
    }

    public void setIndTipoItemFiscal(String indTipoItemFiscal) {
        this.indTipoItemFiscal = indTipoItemFiscal;
    }

    public Integer getCodTributacaoPis() {
        return codTributacaoPis;
    }

    public void setCodTributacaoPis(Integer codTributacaoPis) {
        this.codTributacaoPis = codTributacaoPis;
    }

    public Integer getCodTributacaoCofins() {
        return codTributacaoCofins;
    }

    public void setCodTributacaoCofins(Integer codTributacaoCofins) {
        this.codTributacaoCofins = codTributacaoCofins;
    }

    public Double getQtdTara() {
        return qtdTara;
    }

    public void setQtdTara(Double qtdTara) {
        this.qtdTara = qtdTara;
    }

    public String getIndTipoControle() {
        return indTipoControle;
    }

    public void setIndTipoControle(String indTipoControle) {
        this.indTipoControle = indTipoControle;
    }

    public Double getPerCredIcms() {
        return perCredIcms;
    }

    public void setPerCredIcms(Double perCredIcms) {
        this.perCredIcms = perCredIcms;
    }

    public Double getPerCredPiscofins() {
        return perCredPiscofins;
    }

    public void setPerCredPiscofins(Double perCredPiscofins) {
        this.perCredPiscofins = perCredPiscofins;
    }

    public Double getPerDebIcms() {
        return perDebIcms;
    }

    public void setPerDebIcms(Double perDebIcms) {
        this.perDebIcms = perDebIcms;
    }

    public Double getPerDebPiscofins() {
        return perDebPiscofins;
    }

    public void setPerDebPiscofins(Double perDebPiscofins) {
        this.perDebPiscofins = perDebPiscofins;
    }

    public Double getPerMargemRam() {
        return perMargemRam;
    }

    public void setPerMargemRam(Double perMargemRam) {
        this.perMargemRam = perMargemRam;
    }

    public Double getPerMargemPadraoRam() {
        return perMargemPadraoRam;
    }

    public void setPerMargemPadraoRam(Double perMargemPadraoRam) {
        this.perMargemPadraoRam = perMargemPadraoRam;
    }

    public Double getPerMarkupRam() {
        return perMarkupRam;
    }

    public void setPerMarkupRam(Double perMarkupRam) {
        this.perMarkupRam = perMarkupRam;
    }

    public String getIndTipoEntrega() {
        return indTipoEntrega;
    }

    public void setIndTipoEntrega(String indTipoEntrega) {
        this.indTipoEntrega = indTipoEntrega;
    }

    public String getIndItemCritico() {
        return indItemCritico;
    }

    public void setIndItemCritico(String indItemCritico) {
        this.indItemCritico = indItemCritico;
    }

    public Double getPerMargem() {
        return perMargem;
    }

    public void setPerMargem(Double perMargem) {
        this.perMargem = perMargem;
    }

    public String getIndBloqueadoCompra() {
        return indBloqueadoCompra;
    }

    public void setIndBloqueadoCompra(String indBloqueadoCompra) {
        this.indBloqueadoCompra = indBloqueadoCompra;
    }

    public String getIndSolicitaVendedor() {
        return indSolicitaVendedor;
    }

    public void setIndSolicitaVendedor(String indSolicitaVendedor) {
        this.indSolicitaVendedor = indSolicitaVendedor;
    }

    public Integer getCodTributacaoPisEntrada() {
        return codTributacaoPisEntrada;
    }

    public void setCodTributacaoPisEntrada(Integer codTributacaoPisEntrada) {
        this.codTributacaoPisEntrada = codTributacaoPisEntrada;
    }

    public Integer getCodTributacaoCofinsEntrada() {
        return codTributacaoCofinsEntrada;
    }

    public void setCodTributacaoCofinsEntrada(Integer codTributacaoCofinsEntrada) {
        this.codTributacaoCofinsEntrada = codTributacaoCofinsEntrada;
    }

    public String getIndControleAnvisa() {
        return indControleAnvisa;
    }

    public void setIndControleAnvisa(String indControleAnvisa) {
        this.indControleAnvisa = indControleAnvisa;
    }

}