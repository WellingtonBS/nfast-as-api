package br.com.nfast.api.model.fiscal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ModeloDocumento {
    @Id
    @Column(name = "cod_modelo_documento")
    private Integer codModeloDocumento;
    @Column(name = "des_modelo_documento")
    private String desModeloDocumento;
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "num_sub_serie")
    private String numSubSerie;
    @Column(name = "ind_numeracao_automatica")
    private String indNumeracaoAutomatica;
    @Column(name = "num_inicial_doc_fiscal")
    private Integer numInicialDocFiscal;
    @Column(name = "cod_modelo_doc_anexo_7")
    private Integer codModeloDocAnexo7;
    @Column(name = "ind_entrada")
    private String indEntrada;
    @Column(name = "ind_saida")
    private String indSaida;
    @Column(name = "ind_emitente")
    private String indEmitente;
    @Column(name = "ind_ativo")
    private String indAtivo;

    public Integer getCodModeloDocumento() {
        return codModeloDocumento;
    }

    public void setCodModeloDocumento(Integer codModeloDocumento) {
        this.codModeloDocumento = codModeloDocumento;
    }

    public String getDesModeloDocumento() {
        return desModeloDocumento;
    }

    public void setDesModeloDocumento(String desModeloDocumento) {
        this.desModeloDocumento = desModeloDocumento;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
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

    public String getIndNumeracaoAutomatica() {
        return indNumeracaoAutomatica;
    }

    public void setIndNumeracaoAutomatica(String indNumeracaoAutomatica) {
        this.indNumeracaoAutomatica = indNumeracaoAutomatica;
    }

    public Integer getNumInicialDocFiscal() {
        return numInicialDocFiscal;
    }

    public void setNumInicialDocFiscal(Integer numInicialDocFiscal) {
        this.numInicialDocFiscal = numInicialDocFiscal;
    }

    public Integer getCodModeloDocAnexo7() {
        return codModeloDocAnexo7;
    }

    public void setCodModeloDocAnexo7(Integer codModeloDocAnexo7) {
        this.codModeloDocAnexo7 = codModeloDocAnexo7;
    }

    public String getIndEntrada() {
        return indEntrada;
    }

    public void setIndEntrada(String indEntrada) {
        this.indEntrada = indEntrada;
    }

    public String getIndSaida() {
        return indSaida;
    }

    public void setIndSaida(String indSaida) {
        this.indSaida = indSaida;
    }

    public String getIndEmitente() {
        return indEmitente;
    }

    public void setIndEmitente(String indEmitente) {
        this.indEmitente = indEmitente;
    }

    public String getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(String indAtivo) {
        this.indAtivo = indAtivo;
    }

}
