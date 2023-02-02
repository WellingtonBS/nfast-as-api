package br.com.nfast.api.model.nfe;

import br.com.nfast.api.utils.Numbers;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NFe {
    @Id
    @Column(name = "seq_nota")
    private Long seqNota;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "cod_modelo_documento")
    private Integer codModeloDocumento;
    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "cod_pessoa_fornecedor")
    private Integer codPessoaFornecedor;
    @Column(name = "num_nota")
    private String numNota;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "dta_entrada")
    private LocalDate dtaEntrada;
    @Column(name = "val_base_icms")
    private Double valBaseIcms = 0.0;
    @Column(name = "val_icms")
    private Double valIcms = 0.0;
    @Column(name = "val_base_icms_substituicao")
    private Double valBaseIcmsSubstituicao = 0.0;
    @Column(name = "val_icms_substituicao")
    private Double valIcmsSubstituicao = 0.0;
    @Column(name = "val_total_produtos")
    private Double valTotalProdutos = 0.0;
    @Column(name = "val_frete_cif")
    private Double valFreteCif = 0.0;
    @Column(name = "val_seguro")
    private Double valSeguro = 0.0;
    @Column(name = "val_despesa_acessoria")
    private Double valDespesaAcessoria = 0.0;
    @Column(name = "val_ipi")
    private Double valIpi = 0.0;
    @Column(name = "val_total_nota")
    private Double valTotalNota = 0.0;
    @Column(name = "des_observacao")
    private String desObservacao = "";
    @Column(name = "val_pagamento_caixa")
    private Double valPagamentoCaixa = 0.0;
    @Column(name = "num_doc_caixa")
    private String numDocCaixa;
    @Column(name = "cod_especie_caixa")
    private Integer codEspecieCaixa;
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "des_extra_1")
    private String desExtra1 = "";
    @Column(name = "des_extra_2")
    private String desExtra2 = "";
    @Column(name = "des_extra_3")
    private String desExtra3 = "";
    @Column(name = "des_extra_4")
    private String desExtra4 = "";
    @Column(name = "des_extra_5")
    private String desExtra5 = "";
    @Column(name = "nom_usuario")
    private String nomUsuario = "";
    @Column(name = "dta_digitacao")
    private LocalDate dtaDigitacao;
    @Column(name = "hra_digitacao")
    private String hraDigitacao;
    @Column(name = "seq_propriedade_rural")
    private Integer seqPropriedadeRural;
    @Column(name = "val_icms_st_repassar")
    private Double valIcmsStRepassar = 0.0;
    @Column(name = "val_icms_st_complementar")
    private Double valIcmsStComplementar = 0.0;
    @Column(name = "per_aliq_icms_acessorios")
    private Double perAliqIcmsAcessorios = 0.0;
    @Column(name = "val_icms_frete")
    private Double valIcmsFrete = 0.0;
    @Column(name = "val_icms_seguro")
    private Double valIcmsSeguro = 0.0;
    @Column(name = "val_icms_despesa")
    private Double valIcmsDespesa = 0.0;
    @Column(name = "per_aliq_ipi_acessorios")
    private Double perAliqIpiAcessorios = 0.0;
    @Column(name = "val_ipi_frete")
    private Double valIpiFrete = 0.0;
    @Column(name = "val_pis_cofins")
    private Double valPisCofins = 0.0;
    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "num_sub_serie")
    private String numSubSerie;
    @Column(name = "val_total_dif_aliquota")
    private Double valTotalDifAliquota = 0.0;
    @Column(name = "val_iss")
    private Double valIss = 0.0;
    @Column(name = "val_base_iss")
    private Double valBaseIss = 0.0;
    @Column(name = "val_total_servico")
    private Double valTotalServico = 0.0;
    @Column(name = "cod_tributacao_iss")
    private Integer codTributacaoIss;
    @Column(name = "num_selo_fiscal")
    private String numSeloFiscal = "";
    @Column(name = "num_passe_fiscal")
    private String numPasseFiscal = "";
    @Column(name = "dta_cancelamento")
    private LocalDateTime dtaCancelamento;
    @Column(name = "ind_reembolso")
    private String indReembolso;
    @Column(name = "cod_pessoa_reembolso")
    private Integer codPessoaReembolso;
    @Column(name = "num_contrato")
    private String numContrato;
    @Column(name = "des_mensagem")
    private String desMensagem;
    @Column(name = "num_chave_nfe")
    private String numChaveNfe;
    @Column(name = "ind_tipo_emissao_nfe")
    private Integer indTipoEmissaoNfe;
    @Column(name = "ind_tipo_ambiente_nfe")
    private String indTipoAmbienteNfe;
    @Column(name = "num_protocolo_nfe")
    private String numProtocoloNfe;
    @Column(name = "num_versao_prot_nfe")
    private String numVersaoProtNfe;
    @Column(name = "num_versao_apli_nfe")
    private String numVersaoApliNfe;
    @Column(name = "dta_recebimento_nfe")
    private LocalDate dtaRecebimentoNfe;
    @Column(name = "hra_recebimento_nfe")
    private String hraRecebimentoNfe;
    @Column(name = "dig_val_nfe")
    private String digValNfe;
    @Column(name = "cod_uf_proc_nfe")
    private String codUfProcNfe;
    @Column(name = "nom_usuario_transm")
    private String nomUsuarioTransm;
    @Column(name = "cod_situacao_nfe")
    private String codSituacaoNfe;
    @Column(name = "des_motivo_situacao")
    private String desMotivoSituacao;
    @Column(name = "ind_cancelado")
    private String indCancelado;
    @Column(name = "des_motivo_cancelamento")
    private String desMotivoCancelamento = "";
    @Column(name = "nom_usuario_cancelamento")
    private String nomUsuarioCancelamento = "";
    @Column(name = "cod_situacao_depec")
    private String codSituacaoDepec;
    @Column(name = "des_motivo_depec")
    private String desMotivoDepec;
    @Column(name = "dta_registro_depec")
    private LocalDateTime dtaRegistroDepec;
    @Column(name = "hra_registro_depec")
    private String hraRegistroDepec;
    @Column(name = "num_registro_depec")
    private String numRegistroDepec;
    @Column(name = "num_placa_veiculo")
    private String numPlacaVeiculo;
    @Column(name = "sgl_uf_veiculo")
    private String sglUfVeiculo;
    @Column(name = "des_marca_volume")
    private String desMarcaVolume;
    @Column(name = "des_especie_volume")
    private String desEspecieVolume;
    @Column(name = "qtd_volume")
    private Integer qtdVolume = 0;
    @Column(name = "qtd_peso_bruto")
    private Double qtdPesoBruto = 0.0;
    @Column(name = "qtd_peso_liquido")
    private Double qtdPesoLiquido = 0.0;
    @Column(name = "cod_pessoa_representante")
    private Integer codPessoaRepresentante;
    @Column(name = "cod_pessoa_transportador")
    private Integer codPessoaTransportador;
    @Column(name = "val_pis_recuperar")
    private Double valPisRecuperar = 0.0;
    @Column(name = "val_cofins_recuperar")
    private Double valCofinsRecuperar = 0.0;
    @Column(name = "num_protocolo_canc_nfe")
    private String numProtocoloCancNfe;
    @Column(name = "dta_recebimento_canc_nfe")
    private LocalDateTime dtaRecebimentoCancNfe;
    @Column(name = "hra_recebimento_canc_nfe")
    private String hraRecebimentoCancNfe;
    @Column(name = "ind_finalidade_nota")
    private String indFinalidadeNota;
    @Column(name = "val_pis")
    private Double valPis = 0.0;
    @Column(name = "val_cofins")
    private Double valCofins = 0.0;
    @Column(name = "ind_status_atual")
    private String indStatusAtual;
    @Column(name = "ind_calcula_pis_cofins")
    private String indCalculaPisCofins = "N";
    @Column(name = "ind_gnre_inclusa")
    private String indGnreInclusa;
    @Column(name = "ind_rateio_st")
    private String indRateioSt;
    @Column(name = "ind_desconto_especial")
    private String indDescontoEspecial;
    @Column(name = "per_aliquota_fr_ipi")
    private Double perAliquotaFrIpi = 0.0;
    @Column(name = "per_aliquota_fr_se_da_icms")
    private Double perAliquotaFrSeDaIcms = 0.0;
    @Column(name = "ind_frete_integra_ipi")
    private String indFreteIntegraIpi;
    @Column(name = "ind_da_integra_icms")
    private String indDaIntegraIcms;
    @Column(name = "ind_seguro_integra_icms")
    private String indSeguroIntegraIcms;
    @Column(name = "ind_frete_integra_icms")
    private String indFreteIntegraIcms;
    @Column(name = "ind_da_integra_bc_st")
    private String indDaIntegraBcSt;
    @Column(name = "ind_seguro_integra_bc_st")
    private String indSeguroIntegraBcSt;
    @Column(name = "ind_frete_integra_bc_st")
    private String indFreteIntegraBcSt;
    @Column(name = "ind_ipi_integra_bc_st")
    private String indIpiIntegraBcSt;
    @Column(name = "ind_debito_credito_pis_cofins")
    private String indDebitoCreditoPisCofins;
    @Column(name = "ind_debito_credito_iss")
    private String indDebitoCreditoIss;
    @Column(name = "ind_debito_credito_ipi")
    private String indDebitoCreditoIpi;
    @Column(name = "ind_debito_credito_icms")
    private String indDebitoCreditoIcms;
    @Column(name = "val_icms_antecipado")
    private Double valIcmsAntecipado = 0.0;
    @Column(name = "val_capatazia")
    private Double valCapatazia = 0.0;
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
    @Column(name = "per_aliq_trading")
    private Double perAliqTrading = 0.0;
    @Column(name = "val_ii")
    private Double valIi = 0.0;
    @Column(name = "val_iof")
    private Double valIof = 0.0;
    @Column(name = "ind_nota_importacao")
    private String indNotaImportacao = "N";
    @Column(name = "seq_despesa_dif_icms")
    private Integer seqDespesaDifIcms;
    @Column(name = "cod_mod_frete")
    private Integer codModFrete;
    @Column(name = "seg_cod_barra")
    private String segCodBarra;
    @Column(name = "val_icms_desoneracao")
    private Double valIcmsDesoneracao = 0.0;

    @Transient
    private String atualizaNcm;


    @OneToOne(mappedBy = "nfe", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ConhecimentoFrete conhecimentoFrete;

    @Transient
    private List<ItemNFe> itens = new ArrayList<>();

    @Transient
    private List<ParcelaNFe> parcelas = new ArrayList<>();

    @OrderBy("seq_pagamento")
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "id.nfe", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NFePagtoBanco> pagtosBanco = new ArrayList<>();

    @OrderBy("seq_mens_nf_rel")
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "nfe", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MensNF> mensagens = new ArrayList<>();

    @OrderBy("seq_aj_doc_fiscal")
    @Fetch(FetchMode.SELECT)
    @Where(clause = "ind_tipo = '1' AND seq_item = 0")
    @JoinColumn(name = "seq_nota")
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AjusteDocFiscal> ajustes = new ArrayList<>();

    public Long getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Long seqNota) {
        this.seqNota = seqNota;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Integer getCodModeloDocumento() {
        return codModeloDocumento;
    }

    public void setCodModeloDocumento(Integer codModeloDocumento) {
        this.codModeloDocumento = codModeloDocumento;
    }

    public Integer getCodNaturezaOperacao() {
        return codNaturezaOperacao;
    }

    public void setCodNaturezaOperacao(Integer codNaturezaOperacao) {
        this.codNaturezaOperacao = codNaturezaOperacao;
    }

    public Integer getCodPessoaFornecedor() {
        return codPessoaFornecedor;
    }

    public void setCodPessoaFornecedor(Integer codPessoaFornecedor) {
        this.codPessoaFornecedor = codPessoaFornecedor;
    }

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public LocalDate getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(LocalDate dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
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

    public Double getValTotalProdutos() {
        return valTotalProdutos;
    }

    public void setValTotalProdutos(Double valTotalProdutos) {
        this.valTotalProdutos = valTotalProdutos;
    }

    public Double getValFreteCif() {
        return valFreteCif;
    }

    public void setValFreteCif(Double valFreteCif) {
        this.valFreteCif = valFreteCif;
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

    public Double getValIpi() {
        return valIpi;
    }

    public void setValIpi(Double valIpi) {
        this.valIpi = valIpi;
    }

    public Double getValTotalNota() {
        return valTotalNota;
    }

    public void setValTotalNota(Double valTotalNota) {
        this.valTotalNota = valTotalNota;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public Double getValPagamentoCaixa() {
        return valPagamentoCaixa;
    }

    public void setValPagamentoCaixa(Double valPagamentoCaixa) {
        this.valPagamentoCaixa = valPagamentoCaixa;
    }

    public String getNumDocCaixa() {
        return numDocCaixa;
    }

    public void setNumDocCaixa(String numDocCaixa) {
        this.numDocCaixa = numDocCaixa;
    }

    public Integer getCodEspecieCaixa() {
        return codEspecieCaixa;
    }

    public void setCodEspecieCaixa(Integer codEspecieCaixa) {
        this.codEspecieCaixa = codEspecieCaixa;
    }

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public String getDesExtra1() {
        return desExtra1;
    }

    public void setDesExtra1(String desExtra1) {
        this.desExtra1 = desExtra1;
    }

    public String getDesExtra2() {
        return desExtra2;
    }

    public void setDesExtra2(String desExtra2) {
        this.desExtra2 = desExtra2;
    }

    public String getDesExtra3() {
        return desExtra3;
    }

    public void setDesExtra3(String desExtra3) {
        this.desExtra3 = desExtra3;
    }

    public String getDesExtra4() {
        return desExtra4;
    }

    public void setDesExtra4(String desExtra4) {
        this.desExtra4 = desExtra4;
    }

    public String getDesExtra5() {
        return desExtra5;
    }

    public void setDesExtra5(String desExtra5) {
        this.desExtra5 = desExtra5;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public LocalDate getDtaDigitacao() {
        return dtaDigitacao;
    }

    public void setDtaDigitacao(LocalDate dtaDigitacao) {
        this.dtaDigitacao = dtaDigitacao;
    }

    public String getHraDigitacao() {
        return hraDigitacao;
    }

    public void setHraDigitacao(String hraDigitacao) {
        this.hraDigitacao = hraDigitacao;
    }

    public Integer getSeqPropriedadeRural() {
        return seqPropriedadeRural;
    }

    public void setSeqPropriedadeRural(Integer seqPropriedadeRural) {
        this.seqPropriedadeRural = seqPropriedadeRural;
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

    public Double getPerAliqIcmsAcessorios() {
        return perAliqIcmsAcessorios;
    }

    public void setPerAliqIcmsAcessorios(Double perAliqIcmsAcessorios) {
        this.perAliqIcmsAcessorios = perAliqIcmsAcessorios;
    }

    public Double getValIcmsFrete() {
        return valIcmsFrete;
    }

    public void setValIcmsFrete(Double valIcmsFrete) {
        this.valIcmsFrete = valIcmsFrete;
    }

    public Double getValIcmsSeguro() {
        return valIcmsSeguro;
    }

    public void setValIcmsSeguro(Double valIcmsSeguro) {
        this.valIcmsSeguro = valIcmsSeguro;
    }

    public Double getValIcmsDespesa() {
        return valIcmsDespesa;
    }

    public void setValIcmsDespesa(Double valIcmsDespesa) {
        this.valIcmsDespesa = valIcmsDespesa;
    }

    public Double getPerAliqIpiAcessorios() {
        return perAliqIpiAcessorios;
    }

    public void setPerAliqIpiAcessorios(Double perAliqIpiAcessorios) {
        this.perAliqIpiAcessorios = perAliqIpiAcessorios;
    }

    public Double getValIpiFrete() {
        return valIpiFrete;
    }

    public void setValIpiFrete(Double valIpiFrete) {
        this.valIpiFrete = valIpiFrete;
    }

    public Double getValPisCofins() {
        return valPisCofins;
    }

    public void setValPisCofins(Double valPisCofins) {
        this.valPisCofins = valPisCofins;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNumSubSerie() {
        return numSubSerie;
    }

    public void setNumSubSerie(String numSubSerie) {
        this.numSubSerie = numSubSerie;
    }

    public Double getValTotalDifAliquota() {
        return valTotalDifAliquota;
    }

    public void setValTotalDifAliquota(Double valTotalDifAliquota) {
        this.valTotalDifAliquota = valTotalDifAliquota;
    }

    public Double getValIss() {
        return valIss;
    }

    public void setValIss(Double valIss) {
        this.valIss = valIss;
    }

    public Double getValBaseIss() {
        return valBaseIss;
    }

    public void setValBaseIss(Double valBaseIss) {
        this.valBaseIss = valBaseIss;
    }

    public Double getValTotalServico() {
        return valTotalServico;
    }

    public void setValTotalServico(Double valTotalServico) {
        this.valTotalServico = valTotalServico;
    }

    public Integer getCodTributacaoIss() {
        return codTributacaoIss;
    }

    public void setCodTributacaoIss(Integer codTributacaoIss) {
        this.codTributacaoIss = codTributacaoIss;
    }

    public String getNumSeloFiscal() {
        return numSeloFiscal;
    }

    public void setNumSeloFiscal(String numSeloFiscal) {
        this.numSeloFiscal = numSeloFiscal;
    }

    public String getNumPasseFiscal() {
        return numPasseFiscal;
    }

    public void setNumPasseFiscal(String numPasseFiscal) {
        this.numPasseFiscal = numPasseFiscal;
    }

    public LocalDateTime getDtaCancelamento() {
        return dtaCancelamento;
    }

    public void setDtaCancelamento(LocalDateTime dtaCancelamento) {
        this.dtaCancelamento = dtaCancelamento;
    }

    public String getIndReembolso() {
        return indReembolso;
    }

    public void setIndReembolso(String indReembolso) {
        this.indReembolso = indReembolso;
    }

    public Integer getCodPessoaReembolso() {
        return codPessoaReembolso;
    }

    public void setCodPessoaReembolso(Integer codPessoaReembolso) {
        this.codPessoaReembolso = codPessoaReembolso;
    }

    public String getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }

    public String getDesMensagem() {
        return desMensagem;
    }

    public void setDesMensagem(String desMensagem) {
        this.desMensagem = desMensagem;
    }

    public String getNumChaveNfe() {
        return numChaveNfe;
    }

    public void setNumChaveNfe(String numChaveNfe) {
        this.numChaveNfe = numChaveNfe;
    }

    public Integer getIndTipoEmissaoNfe() {
        return indTipoEmissaoNfe;
    }

    public void setIndTipoEmissaoNfe(Integer indTipoEmissaoNfe) {
        this.indTipoEmissaoNfe = indTipoEmissaoNfe;
    }

    public String getIndTipoAmbienteNfe() {
        return indTipoAmbienteNfe;
    }

    public void setIndTipoAmbienteNfe(String indTipoAmbienteNfe) {
        this.indTipoAmbienteNfe = indTipoAmbienteNfe;
    }

    public String getNumProtocoloNfe() {
        return numProtocoloNfe;
    }

    public void setNumProtocoloNfe(String numProtocoloNfe) {
        this.numProtocoloNfe = numProtocoloNfe;
    }

    public String getNumVersaoProtNfe() {
        return numVersaoProtNfe;
    }

    public void setNumVersaoProtNfe(String numVersaoProtNfe) {
        this.numVersaoProtNfe = numVersaoProtNfe;
    }

    public String getNumVersaoApliNfe() {
        return numVersaoApliNfe;
    }

    public void setNumVersaoApliNfe(String numVersaoApliNfe) {
        this.numVersaoApliNfe = numVersaoApliNfe;
    }

    public LocalDate getDtaRecebimentoNfe() {
        return dtaRecebimentoNfe;
    }

    public void setDtaRecebimentoNfe(LocalDate dtaRecebimentoNfe) {
        this.dtaRecebimentoNfe = dtaRecebimentoNfe;
    }

    public String getHraRecebimentoNfe() {
        return hraRecebimentoNfe;
    }

    public void setHraRecebimentoNfe(String hraRecebimentoNfe) {
        this.hraRecebimentoNfe = hraRecebimentoNfe;
    }

    public String getDigValNfe() {
        return digValNfe;
    }

    public void setDigValNfe(String digValNfe) {
        this.digValNfe = digValNfe;
    }

    public String getCodUfProcNfe() {
        return codUfProcNfe;
    }

    public void setCodUfProcNfe(String codUfProcNfe) {
        this.codUfProcNfe = codUfProcNfe;
    }

    public String getNomUsuarioTransm() {
        return nomUsuarioTransm;
    }

    public void setNomUsuarioTransm(String nomUsuarioTransm) {
        this.nomUsuarioTransm = nomUsuarioTransm;
    }

    public String getCodSituacaoNfe() {
        return codSituacaoNfe;
    }

    public void setCodSituacaoNfe(String codSituacaoNfe) {
        this.codSituacaoNfe = codSituacaoNfe;
    }

    public String getDesMotivoSituacao() {
        return desMotivoSituacao;
    }

    public void setDesMotivoSituacao(String desMotivoSituacao) {
        this.desMotivoSituacao = desMotivoSituacao;
    }

    public String getIndCancelado() {
        return indCancelado;
    }

    public void setIndCancelado(String indCancelado) {
        this.indCancelado = indCancelado;
    }

    public String getDesMotivoCancelamento() {
        return desMotivoCancelamento;
    }

    public void setDesMotivoCancelamento(String desMotivoCancelamento) {
        this.desMotivoCancelamento = desMotivoCancelamento;
    }

    public String getNomUsuarioCancelamento() {
        return nomUsuarioCancelamento;
    }

    public void setNomUsuarioCancelamento(String nomUsuarioCancelamento) {
        this.nomUsuarioCancelamento = nomUsuarioCancelamento;
    }

    public String getCodSituacaoDepec() {
        return codSituacaoDepec;
    }

    public void setCodSituacaoDepec(String codSituacaoDepec) {
        this.codSituacaoDepec = codSituacaoDepec;
    }

    public String getDesMotivoDepec() {
        return desMotivoDepec;
    }

    public void setDesMotivoDepec(String desMotivoDepec) {
        this.desMotivoDepec = desMotivoDepec;
    }

    public LocalDateTime getDtaRegistroDepec() {
        return dtaRegistroDepec;
    }

    public void setDtaRegistroDepec(LocalDateTime dtaRegistroDepec) {
        this.dtaRegistroDepec = dtaRegistroDepec;
    }

    public String getHraRegistroDepec() {
        return hraRegistroDepec;
    }

    public void setHraRegistroDepec(String hraRegistroDepec) {
        this.hraRegistroDepec = hraRegistroDepec;
    }

    public String getNumRegistroDepec() {
        return numRegistroDepec;
    }

    public void setNumRegistroDepec(String numRegistroDepec) {
        this.numRegistroDepec = numRegistroDepec;
    }

    public String getNumPlacaVeiculo() {
        return numPlacaVeiculo;
    }

    public void setNumPlacaVeiculo(String numPlacaVeiculo) {
        this.numPlacaVeiculo = numPlacaVeiculo;
    }

    public String getSglUfVeiculo() {
        return sglUfVeiculo;
    }

    public void setSglUfVeiculo(String sglUfVeiculo) {
        this.sglUfVeiculo = sglUfVeiculo;
    }

    public String getDesMarcaVolume() {
        return desMarcaVolume;
    }

    public void setDesMarcaVolume(String desMarcaVolume) {
        this.desMarcaVolume = desMarcaVolume;
    }

    public String getDesEspecieVolume() {
        return desEspecieVolume;
    }

    public void setDesEspecieVolume(String desEspecieVolume) {
        this.desEspecieVolume = desEspecieVolume;
    }

    public Integer getQtdVolume() {
        return qtdVolume;
    }

    public void setQtdVolume(Integer qtdVolume) {
        this.qtdVolume = qtdVolume;
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

    public Integer getCodPessoaRepresentante() {
        return codPessoaRepresentante;
    }

    public void setCodPessoaRepresentante(Integer codPessoaRepresentante) {
        this.codPessoaRepresentante = codPessoaRepresentante;
    }

    public Integer getCodPessoaTransportador() {
        return codPessoaTransportador;
    }

    public void setCodPessoaTransportador(Integer codPessoaTransportador) {
        this.codPessoaTransportador = codPessoaTransportador;
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

    public String getNumProtocoloCancNfe() {
        return numProtocoloCancNfe;
    }

    public void setNumProtocoloCancNfe(String numProtocoloCancNfe) {
        this.numProtocoloCancNfe = numProtocoloCancNfe;
    }

    public LocalDateTime getDtaRecebimentoCancNfe() {
        return dtaRecebimentoCancNfe;
    }

    public void setDtaRecebimentoCancNfe(LocalDateTime dtaRecebimentoCancNfe) {
        this.dtaRecebimentoCancNfe = dtaRecebimentoCancNfe;
    }

    public String getHraRecebimentoCancNfe() {
        return hraRecebimentoCancNfe;
    }

    public void setHraRecebimentoCancNfe(String hraRecebimentoCancNfe) {
        this.hraRecebimentoCancNfe = hraRecebimentoCancNfe;
    }

    public String getIndFinalidadeNota() {
        return indFinalidadeNota;
    }

    public void setIndFinalidadeNota(String indFinalidadeNota) {
        this.indFinalidadeNota = indFinalidadeNota;
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

    public String getIndStatusAtual() {
        return indStatusAtual;
    }

    public void setIndStatusAtual(String indStatusAtual) {
        this.indStatusAtual = indStatusAtual;
    }

    public String getIndCalculaPisCofins() {
        return indCalculaPisCofins;
    }

    public void setIndCalculaPisCofins(String indCalculaPisCofins) {
        this.indCalculaPisCofins = indCalculaPisCofins;
    }

    public String getIndGnreInclusa() {
        return indGnreInclusa;
    }

    public void setIndGnreInclusa(String indGnreInclusa) {
        this.indGnreInclusa = indGnreInclusa;
    }

    public String getIndRateioSt() {
        return indRateioSt;
    }

    public void setIndRateioSt(String indRateioSt) {
        this.indRateioSt = indRateioSt;
    }

    public String getIndDescontoEspecial() {
        return indDescontoEspecial;
    }

    public void setIndDescontoEspecial(String indDescontoEspecial) {
        this.indDescontoEspecial = indDescontoEspecial;
    }

    public Double getPerAliquotaFrIpi() {
        return perAliquotaFrIpi;
    }

    public void setPerAliquotaFrIpi(Double perAliquotaFrIpi) {
        this.perAliquotaFrIpi = perAliquotaFrIpi;
    }

    public Double getPerAliquotaFrSeDaIcms() {
        return perAliquotaFrSeDaIcms;
    }

    public void setPerAliquotaFrSeDaIcms(Double perAliquotaFrSeDaIcms) {
        this.perAliquotaFrSeDaIcms = perAliquotaFrSeDaIcms;
    }

    public String getIndFreteIntegraIpi() {
        return indFreteIntegraIpi;
    }

    public void setIndFreteIntegraIpi(String indFreteIntegraIpi) {
        this.indFreteIntegraIpi = indFreteIntegraIpi;
    }

    public String getIndDaIntegraIcms() {
        return indDaIntegraIcms;
    }

    public void setIndDaIntegraIcms(String indDaIntegraIcms) {
        this.indDaIntegraIcms = indDaIntegraIcms;
    }

    public String getIndSeguroIntegraIcms() {
        return indSeguroIntegraIcms;
    }

    public void setIndSeguroIntegraIcms(String indSeguroIntegraIcms) {
        this.indSeguroIntegraIcms = indSeguroIntegraIcms;
    }

    public String getIndFreteIntegraIcms() {
        return indFreteIntegraIcms;
    }

    public void setIndFreteIntegraIcms(String indFreteIntegraIcms) {
        this.indFreteIntegraIcms = indFreteIntegraIcms;
    }

    public String getIndDaIntegraBcSt() {
        return indDaIntegraBcSt;
    }

    public void setIndDaIntegraBcSt(String indDaIntegraBcSt) {
        this.indDaIntegraBcSt = indDaIntegraBcSt;
    }

    public String getIndSeguroIntegraBcSt() {
        return indSeguroIntegraBcSt;
    }

    public void setIndSeguroIntegraBcSt(String indSeguroIntegraBcSt) {
        this.indSeguroIntegraBcSt = indSeguroIntegraBcSt;
    }

    public String getIndFreteIntegraBcSt() {
        return indFreteIntegraBcSt;
    }

    public void setIndFreteIntegraBcSt(String indFreteIntegraBcSt) {
        this.indFreteIntegraBcSt = indFreteIntegraBcSt;
    }

    public String getIndIpiIntegraBcSt() {
        return indIpiIntegraBcSt;
    }

    public void setIndIpiIntegraBcSt(String indIpiIntegraBcSt) {
        this.indIpiIntegraBcSt = indIpiIntegraBcSt;
    }

    public String getIndDebitoCreditoPisCofins() {
        return indDebitoCreditoPisCofins;
    }

    public void setIndDebitoCreditoPisCofins(String indDebitoCreditoPisCofins) {
        this.indDebitoCreditoPisCofins = indDebitoCreditoPisCofins;
    }

    public String getIndDebitoCreditoIss() {
        return indDebitoCreditoIss;
    }

    public void setIndDebitoCreditoIss(String indDebitoCreditoIss) {
        this.indDebitoCreditoIss = indDebitoCreditoIss;
    }

    public String getIndDebitoCreditoIpi() {
        return indDebitoCreditoIpi;
    }

    public void setIndDebitoCreditoIpi(String indDebitoCreditoIpi) {
        this.indDebitoCreditoIpi = indDebitoCreditoIpi;
    }

    public String getIndDebitoCreditoIcms() {
        return indDebitoCreditoIcms;
    }

    public void setIndDebitoCreditoIcms(String indDebitoCreditoIcms) {
        this.indDebitoCreditoIcms = indDebitoCreditoIcms;
    }

    public Double getValIcmsAntecipado() {
        return valIcmsAntecipado;
    }

    public void setValIcmsAntecipado(Double valIcmsAntecipado) {
        this.valIcmsAntecipado = valIcmsAntecipado;
    }

    public Double getValCapatazia() {
        return valCapatazia;
    }

    public void setValCapatazia(Double valCapatazia) {
        this.valCapatazia = valCapatazia;
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

    public Double getPerAliqTrading() {
        return perAliqTrading;
    }

    public void setPerAliqTrading(Double perAliqTrading) {
        this.perAliqTrading = perAliqTrading;
    }

    public Double getValIi() {
        return valIi;
    }

    public void setValIi(Double valIi) {
        this.valIi = valIi;
    }

    public Double getValIof() {
        return valIof;
    }

    public void setValIof(Double valIof) {
        this.valIof = valIof;
    }

    public String getIndNotaImportacao() {
        return indNotaImportacao;
    }

    public void setIndNotaImportacao(String indNotaImportacao) {
        this.indNotaImportacao = indNotaImportacao;
    }

    public Integer getSeqDespesaDifIcms() {
        return seqDespesaDifIcms;
    }

    public void setSeqDespesaDifIcms(Integer seqDespesaDifIcms) {
        this.seqDespesaDifIcms = seqDespesaDifIcms;
    }

    public Integer getCodModFrete() {
        return codModFrete;
    }

    public void setCodModFrete(Integer codModFrete) {
        this.codModFrete = codModFrete;
    }

    public String getSegCodBarra() {
        return segCodBarra;
    }

    public void setSegCodBarra(String segCodBarra) {
        this.segCodBarra = segCodBarra;
    }

    public Double getValIcmsDesoneracao() {
        return valIcmsDesoneracao;
    }

    public void setValIcmsDesoneracao(Double valIcmsDesoneracao) {
        this.valIcmsDesoneracao = valIcmsDesoneracao;
    }

    public String getAtualizaNcm() {
        return atualizaNcm;
    }

    public void setAtualizaNcm(String atualizaNcm) {
        this.atualizaNcm = atualizaNcm;
    }

    public ConhecimentoFrete getConhecimentoFrete() {
        return conhecimentoFrete;
    }

    public void setConhecimentoFrete(ConhecimentoFrete conhecimentoFrete) {
        this.conhecimentoFrete = conhecimentoFrete;
    }

    public List<ItemNFe> getItens() {
        return itens;
    }

    public void setItens(List<ItemNFe> itens) {
        this.itens = itens;
    }

    public List<ParcelaNFe> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ParcelaNFe> parcelas) {
        this.parcelas = parcelas;
    }

    public List<NFePagtoBanco> getPagtosBanco() {
        return pagtosBanco;
    }

    public void setPagtosBanco(List<NFePagtoBanco> pagtosBanco) {
        this.pagtosBanco = pagtosBanco;
    }

    public List<MensNF> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<MensNF> mensagens) {
        this.mensagens = mensagens;
    }

    public List<AjusteDocFiscal> getAjustes() {
        return ajustes;
    }

    public void setAjustes(List<AjusteDocFiscal> ajustes) {
        this.ajustes = ajustes;
    }

    public ItemNFe getItem(Integer codigo) {
        if ((itens == null) || (itens.size() == 0))
            return null;
        for (ItemNFe item: itens) {
            if (Numbers.equals(item.getCodItem(), codigo)) {
                return item;
            }
        }
        return null;
    }


}