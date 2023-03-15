package br.com.nfast.api.model.nfe;

import br.com.nfast.api.model.adm.Despesa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ItemNFe {
    @EmbeddedId
    @JsonIgnore
    private ItemNFeId id;

    @Column(name = "seq_item", insertable = false, updatable = false)
    private Integer seqItem;
    @Column(name = "cod_item")
    private Long codItem;
    @Column(name = "cod_almoxarifado")
    private Long codAlmoxarifado;
    @Column(name = "qtd_item")
    private Double qtdItem = 0.0;
    @Column(name = "cod_unidade_compra")
    private Integer codUnidadeCompra;
    @Column(name = "qtd_item_convertido")
    private Double qtdItemConvertido = 0.0;
    @Column(name = "val_unitario")
    private Double valUnitario = 0.0;
    @Column(name = "val_bruto_item")
    private Double valBrutoItem = 0.0;
    @Column(name = "val_desconto")
    private Double valDesconto = 0.0;
    @Column(name = "cod_tributacao_ipi")
    private Integer codTributacaoIpi;
    @Column(name = "val_base_ipi")
    private Double valBaseIpi = 0.0;
    @Column(name = "val_ipi")
    private Double valIpi = 0.0;
    @Column(name = "val_total_item")
    private Double valTotalItem = 0.0;
    @Column(name = "cod_tributacao_icms")
    private Integer codTributacaoIcms;
    @Column(name = "val_base_icms_bruto")
    private Double valBaseIcmsBruto = 0.0;
    @Column(name = "val_base_icms")
    private Double valBaseIcms = 0.0;
    @Column(name = "val_icms")
    private Double valIcms = 0.0;
    @Column(name = "val_base_icms_substituicao")
    private Double valBaseIcmsSubstituicao = 0.0;
    @Column(name = "val_icms_substituicao")
    private Double valIcmsSubstituicao = 0.0;
    @Column(name = "val_frete_fob")
    private Double valFreteFob = 0.0;
    @Column(name = "val_frete_cif")
    private Double valFreteCif = 0.0;
    @Column(name = "val_icms_frete_fob")
    private Double valIcmsFreteFob = 0.0;
    @Column(name = "val_seguro")
    private Double valSeguro = 0.0;
    @Column(name = "val_despesa_acessoria")
    private Double valDespesaAcessoria = 0.0;
    @Column(name = "val_custo")
    private Double valCusto = 0.0;
    @Column(name = "seq_nota_devolucao")
    private Long seqNotaDevolucao;
    @Column(name = "seq_lote")
    private Integer seqLote;
    @Column(name = "seq_pedido")
    private Integer seqPedido;
    @Column(name = "seq_item_pedido")
    private Integer seqItemPedido;
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "val_acrescimo")
    private Double valAcrescimo = 0.0;
    @Column(name = "val_icms_isento")
    private Double valIcmsIsento = 0.0;
    @Column(name = "val_icms_nao_tributado")
    private Double valIcmsNaoTributado = 0.0;
    @Column(name = "val_icms_outros")
    private Double valIcmsOutros = 0.0;
    @Column(name = "val_ipi_isento")
    private Double valIpiIsento = 0.0;
    @Column(name = "val_ipi_nao_tributado")
    private Double valIpiNaoTributado = 0.0;
    @Column(name = "val_ipi_outros")
    private Double valIpiOutros = 0.0;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seq_despesa")
    private Despesa despesa;
    @Column(name = "val_icms_st_repassar")
    private Double valIcmsStRepassar = 0.0;
    @Column(name = "val_icms_st_complementar")
    private Double valIcmsStComplementar = 0.0;
    @Column(name = "val_bc_retencao")
    private Double valBcRetencao = 0.0;
    @Column(name = "val_parcela_imp_retido")
    private Double valParcelaImpRetido = 0.0;
    @Column(name = "per_aliquota_icms")
    private Double perAliquotaIcms = 0.0;
    @Column(name = "per_aliquota_icms_st")
    private Double perAliquotaIcmsSt = 0.0;
    @Column(name = "per_aliquota_ipi")
    private Double perAliquotaIpi = 0.0;
    @Column(name = "val_preco_pauta_st")
    private Double valPrecoPautaSt = 0.0;
    @Column(name = "val_desconto_fiscal")
    private Double valDescontoFiscal = 0.0;
    @Column(name = "val_acrescimo_rateio")
    private Double valAcrescimoRateio = 0.0;
    @Column(name = "val_desconto_rateio")
    private Double valDescontoRateio = 0.0;
    @Column(name = "num_classe_gnre")
    private String numClasseGnre;
    @Column(name = "val_desconto_especial")
    private Double valDescontoEspecial = 0.0;
    @Column(name = "cod_tanque_terceiro")
    private Integer codTanqueTerceiro;
    @Column(name = "val_dif_aliquota")
    private Double valDifAliquota = 0.0;
    @Column(name = "per_aliquota_iss")
    private Double perAliquotaIss = 0.0;
    @Column(name = "val_base_iss")
    private Double valBaseIss = 0.0;
    @Column(name = "val_iss")
    private Double valIss = 0.0;
    @Column(name = "cod_tanque")
    private Integer codTanque;
    @Column(name = "val_icms_recolhido_origem")
    private Double valIcmsRecolhidoOrigem = 0.0;
    @Column(name = "cod_situacao_tributaria")
    private String codSituacaoTributaria;
    @Column(name = "per_mva_st")
    private Double perMvaSt = 0.0;
    @Column(name = "val_preco_st")
    private Double valPrecoSt = 0.0;
    @Column(name = "val_unitario_st")
    private Double valUnitarioSt = 0.0;
    @Column(name = "val_unitario_ipi")
    private Double valUnitarioIpi = 0.0;
    @Column(name = "des_observacao")
    private String desObservacao = "";
    @Column(name = "per_credito_pis")
    private Double perCreditoPis = 0.0;
    @Column(name = "per_credito_cofins")
    private Double perCreditoCofins = 0.0;
    @Column(name = "per_aliquota_pis")
    private Double perAliquotaPis = 0.0;
    @Column(name = "per_aliquota_cofins")
    private Double perAliquotaCofins = 0.0;
    @Column(name = "val_pis_recuperar")
    private Double valPisRecuperar = 0.0;
    @Column(name = "val_cofins_recuperar")
    private Double valCofinsRecuperar = 0.0;
    @Column(name = "cod_tributacao_pis")
    private Integer codTributacaoPis;
    @Column(name = "cod_tributacao_cofins")
    private Integer codTributacaoCofins;
    @Column(name = "val_pis")
    private Double valPis = 0.0;
    @Column(name = "val_cofins")
    private Double valCofins = 0.0;
    @Column(name = "val_base_pis")
    private Double valBasePis = 0.0;
    @Column(name = "val_base_cofins")
    private Double valBaseCofins = 0.0;
    @Column(name = "cod_almoxarifado_retorno")
    private Long codAlmoxarifadoRetorno;
    @Column(name = "val_bc_st_scanc")
    private Double valBcStScanc = 0.0;
    @Column(name = "val_st_scanc")
    private Double valStScanc = 0.0;
    @Column(name = "qtd_item_scanc")
    private Double qtdItemScanc = 0.0;
    @Column(name = "val_indice_scanc")
    private Double valIndiceScanc = 0.0;
    @Column(name = "per_composicao_scanc")
    private Double perComposicaoScanc = 0.0;
    @Column(name = "per_aliquota_scanc")
    private Double perAliquotaScanc = 0.0;
    @Column(name = "val_dif_st")
    private Double valDifSt = 0.0;
    @Column(name = "ind_altera_trib_manual")
    private String indAlteraTribManual;
    @Column(name = "ind_ipi_integra_icms")
    private String indIpiIntegraIcms;
    @Column(name = "val_icms_antecipado")
    private Double valIcmsAntecipado = 0.0;
    @Column(name = "hra_descarga")
    private String hraDescarga;
    @Column(name = "cod_barra")
    private String codBarra;
    @Column(name = "val_pis_frete_recuperar")
    private Double valPisFreteRecuperar = 0.0;
    @Column(name = "val_cofins_frete_recuperar")
    private Double valCofinsFreteRecuperar = 0.0;
    @Column(name = "per_rateio")
    private Double perRateio = 0.0;
    @Column(name = "qtd_peso")
    private Double qtdPeso = 0.0;
    @Column(name = "val_aduaneiro")
    private Double valAduaneiro = 0.0;
    @Column(name = "val_base_ii")
    private Double valBaseIi = 0.0;
    @Column(name = "val_ii")
    private Double valIi = 0.0;
    @Column(name = "val_base_iof")
    private Double valBaseIof = 0.0;
    @Column(name = "val_iof")
    private Double valIof = 0.0;
    @Column(name = "val_fecp")
    private Double valFecp = 0.0;
    @Column(name = "val_capatazia")
    private Double valCapatazia = 0.0;
    @Column(name = "val_afrmm")
    private Double valAfrmm = 0.0;
    @Column(name = "val_dmm")
    private Double valDmm = 0.0;
    @Column(name = "val_siscomex")
    private Double valSiscomex = 0.0;
    @Column(name = "val_armazenagem")
    private Double valArmazenagem = 0.0;
    @Column(name = "val_despacho")
    private Double valDespacho = 0.0;
    @Column(name = "val_taxa_ag_carga")
    private Double valTaxaAgCarga = 0.0;
    @Column(name = "val_prodepe")
    private Double valProdepe = 0.0;
    @Column(name = "val_trading")
    private Double valTrading = 0.0;
    @Column(name = "cod_tributacao_ii")
    private Integer codTributacaoIi;
    @Column(name = "val_bc_fcp")
    private Double valBcFcp = 0.0;
    @Column(name = "per_fcp")
    private Double perFcp = 0.0;
    @Column(name = "val_fcp")
    private Double valFcp = 0.0;
    @Column(name = "val_bc_fcp_st")
    private Double valBcFcpSt = 0.0;
    @Column(name = "per_fcp_st")
    private Double perFcpSt = 0.0;
    @Column(name = "val_fcp_st")
    private Double valFcpSt = 0.0;
    @Column(name = "val_bc_fcp_st_ret")
    private Double valBcFcpStRet = 0.0;
    @Column(name = "per_fcp_st_ret")
    private Double perFcpStRet = 0.0;
    @Column(name = "val_fcp_st_ret")
    private Double valFcpStRet = 0.0;
    @Column(name = "ind_escala_relevante")
    private String indEscalaRelevante;
    @Column(name = "num_cnpj_fabricante")
    private String numCnpjFabricante;
    @Column(name = "cod_centro_custo")
    private Long codCentroCusto;
    @Column(name = "ind_gera_custo")
    private String indGeraCusto;
    @Column(name = "val_icms_desoneracao")
    private Double valIcmsDesoneracao = 0.0;

    @Column(name = "ind_desoneracao")
    private String indDesoneracao = "N";
    @Column(name = "val_icms_diferimento")
    private Double valIcmsDiferimento = 0.0;
    @Column(name = "per_aliquota_diferimento")
    private Double perAliquotaDiferimento = 0.0;
    @Column(name = "per_icms_retido")
    private Double perIcmsRetido = 0.0;
    @Column(name = "cst_st_xml")
    private String cstStXml;
    @Column(name = "val_bc_substituto")
    private Double valBcSubstituto = 0.0;
    @Column(name = "per_icms_substituto")
    private Double perIcmsSubstituto = 0.0;
    @Column(name = "val_icms_substituto")
    private Double valIcmsSubstituto = 0.0;
    @Column(name = "num_item_xml")
    private Integer numItemXml;
    @Column(name = "des_item_xml")
    private String desItemXml;
    @Column(name = "val_bc_st_xml")
    private Double valBcStXml = 0.0;
    @Column(name = "val_icms_st_xml")
    private Double valIcmsStXml = 0.0;
    @Column(name = "val_bc_st_dest")
    private Double valBcStDest = 0.0;
    @Column(name = "val_icms_st_dest")
    private Double valIcmsStDest = 0.0;

    @Transient
    private String ncm;

    @Transient
    private Integer cfopXml;

    @OrderBy("seq_aj_doc_fiscal")
    @Fetch(FetchMode.SELECT)
    @Where(clause = "ind_tipo = '1'")
    @JoinColumns({@JoinColumn(name = "seq_nota"), @JoinColumn(name = "seq_item")})
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AjusteDocFiscal> ajustes = new ArrayList<>();

    public ItemNFeId getId() {
        return id;
    }

    public void setId(ItemNFeId id) {
        this.id = id;
    }

    public Integer getSeqItem() {
        return seqItem;
    }

    public void setSeqItem(Integer seqItem) {
        this.seqItem = seqItem;
    }

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public Long getCodAlmoxarifado() {
        return codAlmoxarifado;
    }

    public void setCodAlmoxarifado(Long codAlmoxarifado) {
        this.codAlmoxarifado = codAlmoxarifado;
    }

    public Double getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Double qtdItem) {
        this.qtdItem = qtdItem;
    }

    public Integer getCodUnidadeCompra() {
        return codUnidadeCompra;
    }

    public void setCodUnidadeCompra(Integer codUnidadeCompra) {
        this.codUnidadeCompra = codUnidadeCompra;
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

    public Double getValBrutoItem() {
        return valBrutoItem;
    }

    public void setValBrutoItem(Double valBrutoItem) {
        this.valBrutoItem = valBrutoItem;
    }

    public Double getValDesconto() {
        return valDesconto;
    }

    public void setValDesconto(Double valDesconto) {
        this.valDesconto = valDesconto;
    }

    public Integer getCodTributacaoIpi() {
        return codTributacaoIpi;
    }

    public void setCodTributacaoIpi(Integer codTributacaoIpi) {
        this.codTributacaoIpi = codTributacaoIpi;
    }

    public Double getValBaseIpi() {
        return valBaseIpi;
    }

    public void setValBaseIpi(Double valBaseIpi) {
        this.valBaseIpi = valBaseIpi;
    }

    public Double getValIpi() {
        return valIpi;
    }

    public void setValIpi(Double valIpi) {
        this.valIpi = valIpi;
    }

    public Double getValTotalItem() {
        return valTotalItem;
    }

    public void setValTotalItem(Double valTotalItem) {
        this.valTotalItem = valTotalItem;
    }

    public Integer getCodTributacaoIcms() {
        return codTributacaoIcms;
    }

    public void setCodTributacaoIcms(Integer codTributacaoIcms) {
        this.codTributacaoIcms = codTributacaoIcms;
    }

    public Double getValBaseIcmsBruto() {
        return valBaseIcmsBruto;
    }

    public void setValBaseIcmsBruto(Double valBaseIcmsBruto) {
        this.valBaseIcmsBruto = valBaseIcmsBruto;
    }

    public Double getValBaseIcms() {
        return valBaseIcms;
    }

    public void setValBaseIcms(Double valBaseIcms) {
        this.valBaseIcms = valBaseIcms;
    }

    public Double getValIcms() {
        return valIcms;
    }

    public void setValIcms(Double valIcms) {
        this.valIcms = valIcms;
    }

    public Double getValBaseIcmsSubstituicao() {
        return valBaseIcmsSubstituicao;
    }

    public void setValBaseIcmsSubstituicao(Double valBaseIcmsSubstituicao) {
        this.valBaseIcmsSubstituicao = valBaseIcmsSubstituicao;
    }

    public Double getValIcmsSubstituicao() {
        return valIcmsSubstituicao;
    }

    public void setValIcmsSubstituicao(Double valIcmsSubstituicao) {
        this.valIcmsSubstituicao = valIcmsSubstituicao;
    }

    public Double getValFreteFob() {
        return valFreteFob;
    }

    public void setValFreteFob(Double valFreteFob) {
        this.valFreteFob = valFreteFob;
    }

    public Double getValFreteCif() {
        return valFreteCif;
    }

    public void setValFreteCif(Double valFreteCif) {
        this.valFreteCif = valFreteCif;
    }

    public Double getValIcmsFreteFob() {
        return valIcmsFreteFob;
    }

    public void setValIcmsFreteFob(Double valIcmsFreteFob) {
        this.valIcmsFreteFob = valIcmsFreteFob;
    }

    public Double getValSeguro() {
        return valSeguro;
    }

    public void setValSeguro(Double valSeguro) {
        this.valSeguro = valSeguro;
    }

    public Double getValDespesaAcessoria() {
        return valDespesaAcessoria;
    }

    public void setValDespesaAcessoria(Double valDespesaAcessoria) {
        this.valDespesaAcessoria = valDespesaAcessoria;
    }

    public Double getValCusto() {
        return valCusto;
    }

    public void setValCusto(Double valCusto) {
        this.valCusto = valCusto;
    }

    public Long getSeqNotaDevolucao() {
        return seqNotaDevolucao;
    }

    public void setSeqNotaDevolucao(Long seqNotaDevolucao) {
        this.seqNotaDevolucao = seqNotaDevolucao;
    }

    public Integer getSeqLote() {
        return seqLote;
    }

    public void setSeqLote(Integer seqLote) {
        this.seqLote = seqLote;
    }

    public Integer getSeqPedido() {
        return seqPedido;
    }

    public void setSeqPedido(Integer seqPedido) {
        this.seqPedido = seqPedido;
    }

    public Integer getSeqItemPedido() {
        return seqItemPedido;
    }

    public void setSeqItemPedido(Integer seqItemPedido) {
        this.seqItemPedido = seqItemPedido;
    }

    public Integer getCodNaturezaOperacao() {
        return codNaturezaOperacao;
    }

    public void setCodNaturezaOperacao(Integer codNaturezaOperacao) {
        this.codNaturezaOperacao = codNaturezaOperacao;
    }

    public Double getValAcrescimo() {
        return valAcrescimo;
    }

    public void setValAcrescimo(Double valAcrescimo) {
        this.valAcrescimo = valAcrescimo;
    }

    public Double getValIcmsIsento() {
        return valIcmsIsento;
    }

    public void setValIcmsIsento(Double valIcmsIsento) {
        this.valIcmsIsento = valIcmsIsento;
    }

    public Double getValIcmsNaoTributado() {
        return valIcmsNaoTributado;
    }

    public void setValIcmsNaoTributado(Double valIcmsNaoTributado) {
        this.valIcmsNaoTributado = valIcmsNaoTributado;
    }

    public Double getValIcmsOutros() {
        return valIcmsOutros;
    }

    public void setValIcmsOutros(Double valIcmsOutros) {
        this.valIcmsOutros = valIcmsOutros;
    }

    public Double getValIpiIsento() {
        return valIpiIsento;
    }

    public void setValIpiIsento(Double valIpiIsento) {
        this.valIpiIsento = valIpiIsento;
    }

    public Double getValIpiNaoTributado() {
        return valIpiNaoTributado;
    }

    public void setValIpiNaoTributado(Double valIpiNaoTributado) {
        this.valIpiNaoTributado = valIpiNaoTributado;
    }

    public Double getValIpiOutros() {
        return valIpiOutros;
    }

    public void setValIpiOutros(Double valIpiOutros) {
        this.valIpiOutros = valIpiOutros;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public Double getValIcmsStRepassar() {
        return valIcmsStRepassar;
    }

    public void setValIcmsStRepassar(Double valIcmsStRepassar) {
        this.valIcmsStRepassar = valIcmsStRepassar;
    }

    public Double getValIcmsStComplementar() {
        return valIcmsStComplementar;
    }

    public void setValIcmsStComplementar(Double valIcmsStComplementar) {
        this.valIcmsStComplementar = valIcmsStComplementar;
    }

    public Double getValBcRetencao() {
        return valBcRetencao;
    }

    public void setValBcRetencao(Double valBcRetencao) {
        this.valBcRetencao = valBcRetencao;
    }

    public Double getValParcelaImpRetido() {
        return valParcelaImpRetido;
    }

    public void setValParcelaImpRetido(Double valParcelaImpRetido) {
        this.valParcelaImpRetido = valParcelaImpRetido;
    }

    public Double getPerAliquotaIcms() {
        return perAliquotaIcms;
    }

    public void setPerAliquotaIcms(Double perAliquotaIcms) {
        this.perAliquotaIcms = perAliquotaIcms;
    }

    public Double getPerAliquotaIcmsSt() {
        return perAliquotaIcmsSt;
    }

    public void setPerAliquotaIcmsSt(Double perAliquotaIcmsSt) {
        this.perAliquotaIcmsSt = perAliquotaIcmsSt;
    }

    public Double getPerAliquotaIpi() {
        return perAliquotaIpi;
    }

    public void setPerAliquotaIpi(Double perAliquotaIpi) {
        this.perAliquotaIpi = perAliquotaIpi;
    }

    public Double getValPrecoPautaSt() {
        return valPrecoPautaSt;
    }

    public void setValPrecoPautaSt(Double valPrecoPautaSt) {
        this.valPrecoPautaSt = valPrecoPautaSt;
    }

    public Double getValDescontoFiscal() {
        return valDescontoFiscal;
    }

    public void setValDescontoFiscal(Double valDescontoFiscal) {
        this.valDescontoFiscal = valDescontoFiscal;
    }

    public Double getValAcrescimoRateio() {
        return valAcrescimoRateio;
    }

    public void setValAcrescimoRateio(Double valAcrescimoRateio) {
        this.valAcrescimoRateio = valAcrescimoRateio;
    }

    public Double getValDescontoRateio() {
        return valDescontoRateio;
    }

    public void setValDescontoRateio(Double valDescontoRateio) {
        this.valDescontoRateio = valDescontoRateio;
    }

    public String getNumClasseGnre() {
        return numClasseGnre;
    }

    public void setNumClasseGnre(String numClasseGnre) {
        this.numClasseGnre = numClasseGnre;
    }

    public Double getValDescontoEspecial() {
        return valDescontoEspecial;
    }

    public void setValDescontoEspecial(Double valDescontoEspecial) {
        this.valDescontoEspecial = valDescontoEspecial;
    }

    public Integer getCodTanqueTerceiro() {
        return codTanqueTerceiro;
    }

    public void setCodTanqueTerceiro(Integer codTanqueTerceiro) {
        this.codTanqueTerceiro = codTanqueTerceiro;
    }

    public Double getValDifAliquota() {
        return valDifAliquota;
    }

    public void setValDifAliquota(Double valDifAliquota) {
        this.valDifAliquota = valDifAliquota;
    }

    public Double getPerAliquotaIss() {
        return perAliquotaIss;
    }

    public void setPerAliquotaIss(Double perAliquotaIss) {
        this.perAliquotaIss = perAliquotaIss;
    }

    public Double getValBaseIss() {
        return valBaseIss;
    }

    public void setValBaseIss(Double valBaseIss) {
        this.valBaseIss = valBaseIss;
    }

    public Double getValIss() {
        return valIss;
    }

    public void setValIss(Double valIss) {
        this.valIss = valIss;
    }

    public Integer getCodTanque() {
        return codTanque;
    }

    public void setCodTanque(Integer codTanque) {
        this.codTanque = codTanque;
    }

    public Double getValIcmsRecolhidoOrigem() {
        return valIcmsRecolhidoOrigem;
    }

    public void setValIcmsRecolhidoOrigem(Double valIcmsRecolhidoOrigem) {
        this.valIcmsRecolhidoOrigem = valIcmsRecolhidoOrigem;
    }

    public String getCodSituacaoTributaria() {
        return codSituacaoTributaria;
    }

    public void setCodSituacaoTributaria(String codSituacaoTributaria) {
        this.codSituacaoTributaria = codSituacaoTributaria;
    }

    public Double getPerMvaSt() {
        return perMvaSt;
    }

    public void setPerMvaSt(Double perMvaSt) {
        this.perMvaSt = perMvaSt;
    }

    public Double getValPrecoSt() {
        return valPrecoSt;
    }

    public void setValPrecoSt(Double valPrecoSt) {
        this.valPrecoSt = valPrecoSt;
    }

    public Double getValUnitarioSt() {
        return valUnitarioSt;
    }

    public void setValUnitarioSt(Double valUnitarioSt) {
        this.valUnitarioSt = valUnitarioSt;
    }

    public Double getValUnitarioIpi() {
        return valUnitarioIpi;
    }

    public void setValUnitarioIpi(Double valUnitarioIpi) {
        this.valUnitarioIpi = valUnitarioIpi;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public Double getPerCreditoPis() {
        return perCreditoPis;
    }

    public void setPerCreditoPis(Double perCreditoPis) {
        this.perCreditoPis = perCreditoPis;
    }

    public Double getPerCreditoCofins() {
        return perCreditoCofins;
    }

    public void setPerCreditoCofins(Double perCreditoCofins) {
        this.perCreditoCofins = perCreditoCofins;
    }

    public Double getPerAliquotaPis() {
        return perAliquotaPis;
    }

    public void setPerAliquotaPis(Double perAliquotaPis) {
        this.perAliquotaPis = perAliquotaPis;
    }

    public Double getPerAliquotaCofins() {
        return perAliquotaCofins;
    }

    public void setPerAliquotaCofins(Double perAliquotaCofins) {
        this.perAliquotaCofins = perAliquotaCofins;
    }

    public Double getValPisRecuperar() {
        return valPisRecuperar;
    }

    public void setValPisRecuperar(Double valPisRecuperar) {
        this.valPisRecuperar = valPisRecuperar;
    }

    public Double getValCofinsRecuperar() {
        return valCofinsRecuperar;
    }

    public void setValCofinsRecuperar(Double valCofinsRecuperar) {
        this.valCofinsRecuperar = valCofinsRecuperar;
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

    public Double getValPis() {
        return valPis;
    }

    public void setValPis(Double valPis) {
        this.valPis = valPis;
    }

    public Double getValCofins() {
        return valCofins;
    }

    public void setValCofins(Double valCofins) {
        this.valCofins = valCofins;
    }

    public Double getValBasePis() {
        return valBasePis;
    }

    public void setValBasePis(Double valBasePis) {
        this.valBasePis = valBasePis;
    }

    public Double getValBaseCofins() {
        return valBaseCofins;
    }

    public void setValBaseCofins(Double valBaseCofins) {
        this.valBaseCofins = valBaseCofins;
    }

    public Long getCodAlmoxarifadoRetorno() {
        return codAlmoxarifadoRetorno;
    }

    public void setCodAlmoxarifadoRetorno(Long codAlmoxarifadoRetorno) {
        this.codAlmoxarifadoRetorno = codAlmoxarifadoRetorno;
    }

    public Double getValBcStScanc() {
        return valBcStScanc;
    }

    public void setValBcStScanc(Double valBcStScanc) {
        this.valBcStScanc = valBcStScanc;
    }

    public Double getValStScanc() {
        return valStScanc;
    }

    public void setValStScanc(Double valStScanc) {
        this.valStScanc = valStScanc;
    }

    public Double getQtdItemScanc() {
        return qtdItemScanc;
    }

    public void setQtdItemScanc(Double qtdItemScanc) {
        this.qtdItemScanc = qtdItemScanc;
    }

    public Double getValIndiceScanc() {
        return valIndiceScanc;
    }

    public void setValIndiceScanc(Double valIndiceScanc) {
        this.valIndiceScanc = valIndiceScanc;
    }

    public Double getPerComposicaoScanc() {
        return perComposicaoScanc;
    }

    public void setPerComposicaoScanc(Double perComposicaoScanc) {
        this.perComposicaoScanc = perComposicaoScanc;
    }

    public Double getPerAliquotaScanc() {
        return perAliquotaScanc;
    }

    public void setPerAliquotaScanc(Double perAliquotaScanc) {
        this.perAliquotaScanc = perAliquotaScanc;
    }

    public Double getValDifSt() {
        return valDifSt;
    }

    public void setValDifSt(Double valDifSt) {
        this.valDifSt = valDifSt;
    }

    public String getIndAlteraTribManual() {
        return indAlteraTribManual;
    }

    public void setIndAlteraTribManual(String indAlteraTribManual) {
        this.indAlteraTribManual = indAlteraTribManual;
    }

    public String getIndIpiIntegraIcms() {
        return indIpiIntegraIcms;
    }

    public void setIndIpiIntegraIcms(String indIpiIntegraIcms) {
        this.indIpiIntegraIcms = indIpiIntegraIcms;
    }

    public Double getValIcmsAntecipado() {
        return valIcmsAntecipado;
    }

    public void setValIcmsAntecipado(Double valIcmsAntecipado) {
        this.valIcmsAntecipado = valIcmsAntecipado;
    }

    public String getHraDescarga() {
        return hraDescarga;
    }

    public void setHraDescarga(String hraDescarga) {
        this.hraDescarga = hraDescarga;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public Double getValPisFreteRecuperar() {
        return valPisFreteRecuperar;
    }

    public void setValPisFreteRecuperar(Double valPisFreteRecuperar) {
        this.valPisFreteRecuperar = valPisFreteRecuperar;
    }

    public Double getValCofinsFreteRecuperar() {
        return valCofinsFreteRecuperar;
    }

    public void setValCofinsFreteRecuperar(Double valCofinsFreteRecuperar) {
        this.valCofinsFreteRecuperar = valCofinsFreteRecuperar;
    }

    public Double getPerRateio() {
        return perRateio;
    }

    public void setPerRateio(Double perRateio) {
        this.perRateio = perRateio;
    }

    public Double getQtdPeso() {
        return qtdPeso;
    }

    public void setQtdPeso(Double qtdPeso) {
        this.qtdPeso = qtdPeso;
    }

    public Double getValAduaneiro() {
        return valAduaneiro;
    }

    public void setValAduaneiro(Double valAduaneiro) {
        this.valAduaneiro = valAduaneiro;
    }

    public Double getValBaseIi() {
        return valBaseIi;
    }

    public void setValBaseIi(Double valBaseIi) {
        this.valBaseIi = valBaseIi;
    }

    public Double getValIi() {
        return valIi;
    }

    public void setValIi(Double valIi) {
        this.valIi = valIi;
    }

    public Double getValBaseIof() {
        return valBaseIof;
    }

    public void setValBaseIof(Double valBaseIof) {
        this.valBaseIof = valBaseIof;
    }

    public Double getValIof() {
        return valIof;
    }

    public void setValIof(Double valIof) {
        this.valIof = valIof;
    }

    public Double getValFecp() {
        return valFecp;
    }

    public void setValFecp(Double valFecp) {
        this.valFecp = valFecp;
    }

    public Double getValCapatazia() {
        return valCapatazia;
    }

    public void setValCapatazia(Double valCapatazia) {
        this.valCapatazia = valCapatazia;
    }

    public Double getValAfrmm() {
        return valAfrmm;
    }

    public void setValAfrmm(Double valAfrmm) {
        this.valAfrmm = valAfrmm;
    }

    public Double getValDmm() {
        return valDmm;
    }

    public void setValDmm(Double valDmm) {
        this.valDmm = valDmm;
    }

    public Double getValSiscomex() {
        return valSiscomex;
    }

    public void setValSiscomex(Double valSiscomex) {
        this.valSiscomex = valSiscomex;
    }

    public Double getValArmazenagem() {
        return valArmazenagem;
    }

    public void setValArmazenagem(Double valArmazenagem) {
        this.valArmazenagem = valArmazenagem;
    }

    public Double getValDespacho() {
        return valDespacho;
    }

    public void setValDespacho(Double valDespacho) {
        this.valDespacho = valDespacho;
    }

    public Double getValTaxaAgCarga() {
        return valTaxaAgCarga;
    }

    public void setValTaxaAgCarga(Double valTaxaAgCarga) {
        this.valTaxaAgCarga = valTaxaAgCarga;
    }

    public Double getValProdepe() {
        return valProdepe;
    }

    public void setValProdepe(Double valProdepe) {
        this.valProdepe = valProdepe;
    }

    public Double getValTrading() {
        return valTrading;
    }

    public void setValTrading(Double valTrading) {
        this.valTrading = valTrading;
    }

    public Integer getCodTributacaoIi() {
        return codTributacaoIi;
    }

    public void setCodTributacaoIi(Integer codTributacaoIi) {
        this.codTributacaoIi = codTributacaoIi;
    }

    public Double getValBcFcp() {
        return valBcFcp;
    }

    public void setValBcFcp(Double valBcFcp) {
        this.valBcFcp = valBcFcp;
    }

    public Double getPerFcp() {
        return perFcp;
    }

    public void setPerFcp(Double perFcp) {
        this.perFcp = perFcp;
    }

    public Double getValFcp() {
        return valFcp;
    }

    public void setValFcp(Double valFcp) {
        this.valFcp = valFcp;
    }

    public Double getValBcFcpSt() {
        return valBcFcpSt;
    }

    public void setValBcFcpSt(Double valBcFcpSt) {
        this.valBcFcpSt = valBcFcpSt;
    }

    public Double getPerFcpSt() {
        return perFcpSt;
    }

    public void setPerFcpSt(Double perFcpSt) {
        this.perFcpSt = perFcpSt;
    }

    public Double getValFcpSt() {
        return valFcpSt;
    }

    public void setValFcpSt(Double valFcpSt) {
        this.valFcpSt = valFcpSt;
    }

    public Double getValBcFcpStRet() {
        return valBcFcpStRet;
    }

    public void setValBcFcpStRet(Double valBcFcpStRet) {
        this.valBcFcpStRet = valBcFcpStRet;
    }

    public Double getPerFcpStRet() {
        return perFcpStRet;
    }

    public void setPerFcpStRet(Double perFcpStRet) {
        this.perFcpStRet = perFcpStRet;
    }

    public Double getValFcpStRet() {
        return valFcpStRet;
    }

    public void setValFcpStRet(Double valFcpStRet) {
        this.valFcpStRet = valFcpStRet;
    }

    public String getIndEscalaRelevante() {
        return indEscalaRelevante;
    }

    public void setIndEscalaRelevante(String indEscalaRelevante) {
        this.indEscalaRelevante = indEscalaRelevante;
    }

    public String getNumCnpjFabricante() {
        return numCnpjFabricante;
    }

    public void setNumCnpjFabricante(String numCnpjFabricante) {
        this.numCnpjFabricante = numCnpjFabricante;
    }

    public Long getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Long codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public String getIndGeraCusto() {
        return indGeraCusto;
    }

    public void setIndGeraCusto(String indGeraCusto) {
        this.indGeraCusto = indGeraCusto;
    }

    public Double getValIcmsDesoneracao() {
        return valIcmsDesoneracao;
    }

    public void setValIcmsDesoneracao(Double valIcmsDesoneracao) {
        this.valIcmsDesoneracao = valIcmsDesoneracao;
    }

    public Double getValIcmsSubstituto() {
        return valIcmsSubstituto;
    }

    public void setValIcmsSubstituto(Double valIcmsSubstituto) {
        this.valIcmsSubstituto = valIcmsSubstituto;
    }

    public String getIndDesoneracao() {
        return indDesoneracao;
    }

    public void setIndDesoneracao(String indDesoneracao) {
        this.indDesoneracao = indDesoneracao;
    }

    public Double getValIcmsDiferimento() {
        return valIcmsDiferimento;
    }

    public void setValIcmsDiferimento(Double valIcmsDiferimento) {
        this.valIcmsDiferimento = valIcmsDiferimento;
    }

    public Double getPerAliquotaDiferimento() {
        return perAliquotaDiferimento;
    }

    public void setPerAliquotaDiferimento(Double perAliquotaDiferimento) {
        this.perAliquotaDiferimento = perAliquotaDiferimento;
    }

    public Double getPerIcmsRetido() {
        return perIcmsRetido;
    }

    public void setPerIcmsRetido(Double perIcmsRetido) {
        this.perIcmsRetido = perIcmsRetido;
    }

    public String getCstStXml() {
        return cstStXml;
    }

    public void setCstStXml(String cstStXml) {
        this.cstStXml = cstStXml;
    }

    public Double getValBcSubstituto() {
        return valBcSubstituto;
    }

    public void setValBcSubstituto(Double valBcSubstituto) {
        this.valBcSubstituto = valBcSubstituto;
    }

    public Double getPerIcmsSubstituto() {
        return perIcmsSubstituto;
    }

    public void setPerIcmsSubstituto(Double perIcmsSubstituto) {
        this.perIcmsSubstituto = perIcmsSubstituto;
    }

    public Integer getNumItemXml() {
        return numItemXml;
    }

    public void setNumItemXml(Integer numItemXml) {
        this.numItemXml = numItemXml;
    }

    public String getDesItemXml() {
        return desItemXml;
    }

    public void setDesItemXml(String desItemXml) {
        this.desItemXml = desItemXml;
    }

    public Double getValBcStXml() {
        return valBcStXml;
    }

    public void setValBcStXml(Double valBcStXml) {
        this.valBcStXml = valBcStXml;
    }

    public Double getValIcmsStXml() {
        return valIcmsStXml;
    }

    public void setValIcmsStXml(Double valIcmsStXml) {
        this.valIcmsStXml = valIcmsStXml;
    }

    public Double getValBcStDest() {
        return valBcStDest;
    }

    public void setValBcStDest(Double valBcStDest) {
        this.valBcStDest = valBcStDest;
    }

    public Double getValIcmsStDest() {
        return valIcmsStDest;
    }

    public void setValIcmsStDest(Double valIcmsStDest) {
        this.valIcmsStDest = valIcmsStDest;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public List<AjusteDocFiscal> getAjustes() {
        return ajustes;
    }

    public void setAjustes(List<AjusteDocFiscal> ajustes) {
        this.ajustes = ajustes;
    }

    public Integer getCfopXml() {
        return cfopXml;
    }

    public void setCfopXml(Integer cfopXml) {
        this.cfopXml = cfopXml;
    }


}