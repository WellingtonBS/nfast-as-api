package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProdutoCad {
    @Id
    @Column(name = "cod_item")
    private Long codItem;
    @Column(name = "des_item")
    private String desItem;
    @Column(name = "cod_barra")
    private String codBarra;
    @Column(name = "cod_referencia")
    private String codReferencia = "";
    @Column(name = "cod_unidade")
    private Integer codUnidade;
    @Column(name = "cod_subgrupo_item")
    private Integer codSubgrupoItem;
    @Column(name = "seq_ncm")
    private Integer seqNcm;
    @Column(name = "cod_ncm")
    private String codNcm;
    @Column(name = "cod_pessoa_fornecedor")
    private Integer codPessoaFornecedor;

    @Column(name = "qtd_unidade_agrupamento_1")
    private Double qtdUnidadeAgrupamento1 = 0.0;
    @Column(name = "qtd_unidade_agrupamento_2")
    private Double qtdUnidadeAgrupamento2 = 0.0;
    @Column(name = "ind_tipo_item")
    private String indTipoItem = "P";
    @Column(name = "cod_moeda_venda")
    private Integer codMoedaVenda = 1;
    @Column(name = "ind_solicita_preco")
    private String indSolicitaPreco = "N";
    @Column(name = "qtd_peso_bruto")
    private Double qtdPesoBruto = 0.0;
    @Column(name = "qtd_peso_liquido")
    private Double qtdPesoLiquido = 0.0;
    @Column(name = "qtd_volume")
    private Double qtdVolume = 0.0;
    @Column(name = "qtd_peso_padrao")
    private Double qtdPesoPadrao = 0.0;
    @Column(name = "per_variacao_peso_padrao")
    private Double perVariacaoPesoPadrao = 0.0;
    @Column(name = "per_icms_compra")
    private Double perIcmsCompra = 0.0;
    @Column(name = "des_item_ecf")
    private String desItemEcf;
    @Column(name = "qtd_casas_decimais")
    private Integer qtdCasasDecimais = 0;
    @Column(name = "num_largura")
    private Double numLargura = 0.0;
    @Column(name = "num_altura")
    private Double numAltura = 0.0;
    @Column(name = "num_profundidade")
    private Double numProfundidade = 0.0;
    @Column(name = "ind_alteracao_sincronizada")
    private String indAlteracaoSincronizada = "N";
    @Column(name = "nom_usuario_alteracao")
    private String nomUsuarioAlteracao = "";
    @Column(name = "ind_gera_brinde")
    private String indGeraBrinde = "N";
    @Column(name = "ind_und_brinde")
    private String indUndBrinde = "Q";
    @Column(name = "val_und_brinde")
    private Double valUndBrinde = 0.0;
    @Column(name = "qtd_pontos")
    private Double qtdPontos = 0.0;
    @Column(name = "ind_produto_br")
    private String indProdutoBr = "N";
    @Column(name = "ind_lava_mania")
    private String indLavaMania = "N";
    @Column(name = "fat_conversao_m3")
    private Double fatConversaoM3 = 0.0;
    @Column(name = "val_preco_mercado")
    private Double valPrecoMercado = 0.0;
    @Column(name = "ind_gera_estoque")
    private String indGeraEstoque = "S";
    @Column(name = "ind_exporta_balanca")
    private String indExportaBalanca = "N";
    @Column(name = "ind_classificacao_item")
    private String indClassificacaoItem = "";
    @Column(name = "dta_cadastro")
    private LocalDate dtaCadastro = LocalDate.now();
    @Column(name = "num_densidade")
    private Double numDensidade = 0.0;
    @Column(name = "ind_pneu")
    private String indPneu = "N";
    @Column(name = "num_usuario_cadastro")
    private String numUsuarioCadastro;
    @Column(name = "ind_bloquear_formula_produto")
    private String indBloquearFormulaProduto = "N";
    @Column(name = "ind_perigoso")
    private String indPerigoso = "N";
    @Column(name = "ind_manutencao")
    private String indManutencao = "N";

    @Transient
    private List<ProdutoEmpresaCad> empresas = new ArrayList<>();

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
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

    public String getCodReferencia() {
        return codReferencia;
    }

    public void setCodReferencia(String codReferencia) {
        this.codReferencia = codReferencia;
    }

    public Integer getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(Integer codUnidade) {
        this.codUnidade = codUnidade;
    }

    public Integer getCodSubgrupoItem() {
        return codSubgrupoItem;
    }

    public void setCodSubgrupoItem(Integer codSubgrupoItem) {
        this.codSubgrupoItem = codSubgrupoItem;
    }

    public Integer getSeqNcm() {
        return seqNcm;
    }

    public void setSeqNcm(Integer seqNcm) {
        this.seqNcm = seqNcm;
    }

    public String getCodNcm() {
        return codNcm;
    }

    public void setCodNcm(String codNcm) {
        this.codNcm = codNcm;
    }

    public Integer getCodPessoaFornecedor() {
        return codPessoaFornecedor;
    }

    public void setCodPessoaFornecedor(Integer codPessoaFornecedor) {
        this.codPessoaFornecedor = codPessoaFornecedor;
    }

    public Double getQtdUnidadeAgrupamento1() {
        return qtdUnidadeAgrupamento1;
    }

    public void setQtdUnidadeAgrupamento1(Double qtdUnidadeAgrupamento1) {
        this.qtdUnidadeAgrupamento1 = qtdUnidadeAgrupamento1;
    }

    public Double getQtdUnidadeAgrupamento2() {
        return qtdUnidadeAgrupamento2;
    }

    public void setQtdUnidadeAgrupamento2(Double qtdUnidadeAgrupamento2) {
        this.qtdUnidadeAgrupamento2 = qtdUnidadeAgrupamento2;
    }

    public String getIndTipoItem() {
        return indTipoItem;
    }

    public void setIndTipoItem(String indTipoItem) {
        this.indTipoItem = indTipoItem;
    }

    public Integer getCodMoedaVenda() {
        return codMoedaVenda;
    }

    public void setCodMoedaVenda(Integer codMoedaVenda) {
        this.codMoedaVenda = codMoedaVenda;
    }

    public String getIndSolicitaPreco() {
        return indSolicitaPreco;
    }

    public void setIndSolicitaPreco(String indSolicitaPreco) {
        this.indSolicitaPreco = indSolicitaPreco;
    }

    public Double getQtdPesoBruto() {
        return qtdPesoBruto;
    }

    public void setQtdPesoBruto(Double qtdPesoBruto) {
        this.qtdPesoBruto = qtdPesoBruto;
    }

    public Double getQtdPesoLiquido() {
        return qtdPesoLiquido;
    }

    public void setQtdPesoLiquido(Double qtdPesoLiquido) {
        this.qtdPesoLiquido = qtdPesoLiquido;
    }

    public Double getQtdVolume() {
        return qtdVolume;
    }

    public void setQtdVolume(Double qtdVolume) {
        this.qtdVolume = qtdVolume;
    }

    public Double getQtdPesoPadrao() {
        return qtdPesoPadrao;
    }

    public void setQtdPesoPadrao(Double qtdPesoPadrao) {
        this.qtdPesoPadrao = qtdPesoPadrao;
    }

    public Double getPerVariacaoPesoPadrao() {
        return perVariacaoPesoPadrao;
    }

    public void setPerVariacaoPesoPadrao(Double perVariacaoPesoPadrao) {
        this.perVariacaoPesoPadrao = perVariacaoPesoPadrao;
    }

    public Double getPerIcmsCompra() {
        return perIcmsCompra;
    }

    public void setPerIcmsCompra(Double perIcmsCompra) {
        this.perIcmsCompra = perIcmsCompra;
    }

    public String getDesItemEcf() {
        return desItemEcf;
    }

    public void setDesItemEcf(String desItemEcf) {
        this.desItemEcf = desItemEcf;
    }

    public Integer getQtdCasasDecimais() {
        return qtdCasasDecimais;
    }

    public void setQtdCasasDecimais(Integer qtdCasasDecimais) {
        this.qtdCasasDecimais = qtdCasasDecimais;
    }

    public Double getNumLargura() {
        return numLargura;
    }

    public void setNumLargura(Double numLargura) {
        this.numLargura = numLargura;
    }

    public Double getNumAltura() {
        return numAltura;
    }

    public void setNumAltura(Double numAltura) {
        this.numAltura = numAltura;
    }

    public Double getNumProfundidade() {
        return numProfundidade;
    }

    public void setNumProfundidade(Double numProfundidade) {
        this.numProfundidade = numProfundidade;
    }

    public String getIndAlteracaoSincronizada() {
        return indAlteracaoSincronizada;
    }

    public void setIndAlteracaoSincronizada(String indAlteracaoSincronizada) {
        this.indAlteracaoSincronizada = indAlteracaoSincronizada;
    }

    public String getNomUsuarioAlteracao() {
        return nomUsuarioAlteracao;
    }

    public void setNomUsuarioAlteracao(String nomUsuarioAlteracao) {
        this.nomUsuarioAlteracao = nomUsuarioAlteracao;
    }

    public String getIndGeraBrinde() {
        return indGeraBrinde;
    }

    public void setIndGeraBrinde(String indGeraBrinde) {
        this.indGeraBrinde = indGeraBrinde;
    }

    public String getIndUndBrinde() {
        return indUndBrinde;
    }

    public void setIndUndBrinde(String indUndBrinde) {
        this.indUndBrinde = indUndBrinde;
    }

    public Double getValUndBrinde() {
        return valUndBrinde;
    }

    public void setValUndBrinde(Double valUndBrinde) {
        this.valUndBrinde = valUndBrinde;
    }

    public Double getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(Double qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public String getIndProdutoBr() {
        return indProdutoBr;
    }

    public void setIndProdutoBr(String indProdutoBr) {
        this.indProdutoBr = indProdutoBr;
    }

    public String getIndLavaMania() {
        return indLavaMania;
    }

    public void setIndLavaMania(String indLavaMania) {
        this.indLavaMania = indLavaMania;
    }

    public Double getFatConversaoM3() {
        return fatConversaoM3;
    }

    public void setFatConversaoM3(Double fatConversaoM3) {
        this.fatConversaoM3 = fatConversaoM3;
    }

    public Double getValPrecoMercado() {
        return valPrecoMercado;
    }

    public void setValPrecoMercado(Double valPrecoMercado) {
        this.valPrecoMercado = valPrecoMercado;
    }

    public String getIndGeraEstoque() {
        return indGeraEstoque;
    }

    public void setIndGeraEstoque(String indGeraEstoque) {
        this.indGeraEstoque = indGeraEstoque;
    }

    public String getIndExportaBalanca() {
        return indExportaBalanca;
    }

    public void setIndExportaBalanca(String indExportaBalanca) {
        this.indExportaBalanca = indExportaBalanca;
    }

    public String getIndClassificacaoItem() {
        return indClassificacaoItem;
    }

    public void setIndClassificacaoItem(String indClassificacaoItem) {
        this.indClassificacaoItem = indClassificacaoItem;
    }

    public LocalDate getDtaCadastro() {
        return dtaCadastro;
    }

    public void setDtaCadastro(LocalDate dtaCadastro) {
        this.dtaCadastro = dtaCadastro;
    }

    public Double getNumDensidade() {
        return numDensidade;
    }

    public void setNumDensidade(Double numDensidade) {
        this.numDensidade = numDensidade;
    }

    public String getIndPneu() {
        return indPneu;
    }

    public void setIndPneu(String indPneu) {
        this.indPneu = indPneu;
    }

    public String getNumUsuarioCadastro() {
        return numUsuarioCadastro;
    }

    public void setNumUsuarioCadastro(String numUsuarioCadastro) {
        this.numUsuarioCadastro = numUsuarioCadastro;
    }

    public String getIndBloquearFormulaProduto() {
        return indBloquearFormulaProduto;
    }

    public void setIndBloquearFormulaProduto(String indBloquearFormulaProduto) {
        this.indBloquearFormulaProduto = indBloquearFormulaProduto;
    }

    public String getIndPerigoso() {
        return indPerigoso;
    }

    public void setIndPerigoso(String indPerigoso) {
        this.indPerigoso = indPerigoso;
    }

    public String getIndManutencao() {
        return indManutencao;
    }

    public void setIndManutencao(String indManutencao) {
        this.indManutencao = indManutencao;
    }

    public List<ProdutoEmpresaCad> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<ProdutoEmpresaCad> empresas) {
        this.empresas = empresas;
    }
}