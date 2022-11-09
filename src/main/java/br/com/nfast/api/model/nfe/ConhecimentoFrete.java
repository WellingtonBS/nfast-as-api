package br.com.nfast.api.model.nfe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_conhecimento_frete_nfe")
public class ConhecimentoFrete {
    @Id
    @JsonIgnore
    @Column(name = "seq_nota")
    private Integer seqNota;

    @MapsId
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "seq_nota")
    private NFe nfe;

    @Column(name = "cod_natureza_operacao")
    private Integer codNaturezaOperacao;
    @Column(name = "cod_pessoa_transportadora")
    private Integer codPessoaTransportadora;
    @Column(name = "num_conhecimento")
    private String numConhecimento;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "val_despesa_acessoria")
    private Double valDespesaAcessoria = 0.0;
    @Column(name = "val_frete")
    private Double valFrete = 0.0;
    @Column(name = "val_base_icms")
    private Double valBaseIcms = 0.0;
    @Column(name = "val_icms")
    private Double valIcms = 0.0;
    @Column(name = "seq_titulo_frete")
    private Integer seqTituloFrete;
    @Column(name = "ind_tipofrete")
    private String indTipofrete;
    @Column(name = "val_icms_isento")
    private Double valIcmsIsento = 0.0;
    @Column(name = "val_icms_nao_tributado")
    private Double valIcmsNaoTributado = 0.0;
    @Column(name = "val_icms_outros")
    private Double valIcmsOutros = 0.0;
    @Column(name = "cod_modelo_documento")
    private Integer codModeloDocumento;
    @Column(name = "seq_modal")
    private Integer seqModal;
    @Column(name = "num_placa_veiculo1")
    private String numPlacaVeiculo1;
    @Column(name = "num_placa_veiculo2")
    private String numPlacaVeiculo2;
    @Column(name = "num_placa_veiculo3")
    private String numPlacaVeiculo3;
    @Column(name = "uf_veiculo1")
    private String ufVeiculo1;
    @Column(name = "uf_veiculo2")
    private String ufVeiculo2;
    @Column(name = "uf_veiculo3")
    private String ufVeiculo3;
    @Column(name = "ind_dispensado_crtc")
    private String indDispensadoCrtc = "N";
    @Column(name = "des_observacao")
    private String desObservacao;
    @Column(name = "val_base_st")
    private Double valBaseSt = 0.0;
    @Column(name = "val_st")
    private Double valSt = 0.0;
    @Column(name = "val_dif_aliquota")
    private Double valDifAliquota = 0.0;
    @Column(name = "per_aliquota_icms")
    private Double perAliquotaIcms = 0.0;
    @Column(name = "num_chave_nfe")
    private String numChaveNfe;
    @Column(name = "cod_aeroporto_origem")
    private Integer codAeroportoOrigem;
    @Column(name = "cod_aeroporto_destino")
    private Integer codAeroportoDestino;
    @Column(name = "cod_tributacao_pis")
    private Integer codTributacaoPis;
    @Column(name = "cod_tributacao_cofins")
    private Integer codTributacaoCofins;
    @Column(name = "val_pis")
    private Double valPis = 0.0;
    @Column(name = "val_cofins")
    private Double valCofins = 0.0;
    @Column(name = "ind_nat_frete")
    private String indNatFrete = "9";
    @Column(name = "ind_frete_integra_bc_st")
    private String indFreteIntegraBcSt = "N";
    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "num_sub_serie")
    private String numSubSerie;
    @Column(name = "per_aliquota_icms_st")
    private Double perAliquotaIcmsSt = 0.0;
    @Column(name = "ind_finalidade")
    private String indFinalidade;

    @OrderBy("seq_parcela")
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "id.conhecimento", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ConhecimentoFretePag> parcelas = new ArrayList<>();

    public Integer getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Integer seqNota) {
        this.seqNota = seqNota;
    }

    public NFe getNfe() {
        return nfe;
    }

    public void setNfe(NFe nfe) {
        this.nfe = nfe;
    }

    public Integer getCodNaturezaOperacao() {
        return codNaturezaOperacao;
    }

    public void setCodNaturezaOperacao(Integer codNaturezaOperacao) {
        this.codNaturezaOperacao = codNaturezaOperacao;
    }

    public Integer getCodPessoaTransportadora() {
        return codPessoaTransportadora;
    }

    public void setCodPessoaTransportadora(Integer codPessoaTransportadora) {
        this.codPessoaTransportadora = codPessoaTransportadora;
    }

    public String getNumConhecimento() {
        return numConhecimento;
    }

    public void setNumConhecimento(String numConhecimento) {
        this.numConhecimento = numConhecimento;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public Double getValDespesaAcessoria() {
        return valDespesaAcessoria;
    }

    public void setValDespesaAcessoria(Double valDespesaAcessoria) {
        this.valDespesaAcessoria = valDespesaAcessoria;
    }

    public Double getValFrete() {
        return valFrete;
    }

    public void setValFrete(Double valFrete) {
        this.valFrete = valFrete;
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

    public Integer getSeqTituloFrete() {
        return seqTituloFrete;
    }

    public void setSeqTituloFrete(Integer seqTituloFrete) {
        this.seqTituloFrete = seqTituloFrete;
    }

    public String getIndTipofrete() {
        return indTipofrete;
    }

    public void setIndTipofrete(String indTipofrete) {
        this.indTipofrete = indTipofrete;
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

    public Integer getCodModeloDocumento() {
        return codModeloDocumento;
    }

    public void setCodModeloDocumento(Integer codModeloDocumento) {
        this.codModeloDocumento = codModeloDocumento;
    }

    public Integer getSeqModal() {
        return seqModal;
    }

    public void setSeqModal(Integer seqModal) {
        this.seqModal = seqModal;
    }

    public String getNumPlacaVeiculo1() {
        return numPlacaVeiculo1;
    }

    public void setNumPlacaVeiculo1(String numPlacaVeiculo1) {
        this.numPlacaVeiculo1 = numPlacaVeiculo1;
    }

    public String getNumPlacaVeiculo2() {
        return numPlacaVeiculo2;
    }

    public void setNumPlacaVeiculo2(String numPlacaVeiculo2) {
        this.numPlacaVeiculo2 = numPlacaVeiculo2;
    }

    public String getNumPlacaVeiculo3() {
        return numPlacaVeiculo3;
    }

    public void setNumPlacaVeiculo3(String numPlacaVeiculo3) {
        this.numPlacaVeiculo3 = numPlacaVeiculo3;
    }

    public String getUfVeiculo1() {
        return ufVeiculo1;
    }

    public void setUfVeiculo1(String ufVeiculo1) {
        this.ufVeiculo1 = ufVeiculo1;
    }

    public String getUfVeiculo2() {
        return ufVeiculo2;
    }

    public void setUfVeiculo2(String ufVeiculo2) {
        this.ufVeiculo2 = ufVeiculo2;
    }

    public String getUfVeiculo3() {
        return ufVeiculo3;
    }

    public void setUfVeiculo3(String ufVeiculo3) {
        this.ufVeiculo3 = ufVeiculo3;
    }

    public String getIndDispensadoCrtc() {
        return indDispensadoCrtc;
    }

    public void setIndDispensadoCrtc(String indDispensadoCrtc) {
        this.indDispensadoCrtc = indDispensadoCrtc;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public Double getValBaseSt() {
        return valBaseSt;
    }

    public void setValBaseSt(Double valBaseSt) {
        this.valBaseSt = valBaseSt;
    }

    public Double getValSt() {
        return valSt;
    }

    public void setValSt(Double valSt) {
        this.valSt = valSt;
    }

    public Double getValDifAliquota() {
        return valDifAliquota;
    }

    public void setValDifAliquota(Double valDifAliquota) {
        this.valDifAliquota = valDifAliquota;
    }

    public Double getPerAliquotaIcms() {
        return perAliquotaIcms;
    }

    public void setPerAliquotaIcms(Double perAliquotaIcms) {
        this.perAliquotaIcms = perAliquotaIcms;
    }

    public String getNumChaveNfe() {
        return numChaveNfe;
    }

    public void setNumChaveNfe(String numChaveNfe) {
        this.numChaveNfe = numChaveNfe;
    }

    public Integer getCodAeroportoOrigem() {
        return codAeroportoOrigem;
    }

    public void setCodAeroportoOrigem(Integer codAeroportoOrigem) {
        this.codAeroportoOrigem = codAeroportoOrigem;
    }

    public Integer getCodAeroportoDestino() {
        return codAeroportoDestino;
    }

    public void setCodAeroportoDestino(Integer codAeroportoDestino) {
        this.codAeroportoDestino = codAeroportoDestino;
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

    public String getIndNatFrete() {
        return indNatFrete;
    }

    public void setIndNatFrete(String indNatFrete) {
        this.indNatFrete = indNatFrete;
    }

    public String getIndFreteIntegraBcSt() {
        return indFreteIntegraBcSt;
    }

    public void setIndFreteIntegraBcSt(String indFreteIntegraBcSt) {
        this.indFreteIntegraBcSt = indFreteIntegraBcSt;
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

    public Double getPerAliquotaIcmsSt() {
        return perAliquotaIcmsSt;
    }

    public void setPerAliquotaIcmsSt(Double perAliquotaIcmsSt) {
        this.perAliquotaIcmsSt = perAliquotaIcmsSt;
    }

    public String getIndFinalidade() {
        return indFinalidade;
    }

    public void setIndFinalidade(String indFinalidade) {
        this.indFinalidade = indFinalidade;
    }

    public List<ConhecimentoFretePag> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<ConhecimentoFretePag> parcelas) {
        this.parcelas = parcelas;
    }

}
