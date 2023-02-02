package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CentroCusto {
    @Id
    @Column(name = "cod_centro_custo")
    private Long codCentroCusto;
    @Column(name = "des_centro_custo")
    private String desCentroCusto;
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "dta_inicio_validade")
    private LocalDate dtaInicioValidade;
    @Column(name = "dta_fim_validade")
    private LocalDate dtaFimValidade;
    @Column(name = "ind_tipo")
    private String indTipo;

    public Long getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Long codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public String getDesCentroCusto() {
        return desCentroCusto;
    }

    public void setDesCentroCusto(String desCentroCusto) {
        this.desCentroCusto = desCentroCusto;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public LocalDate getDtaInicioValidade() {
        return dtaInicioValidade;
    }

    public void setDtaInicioValidade(LocalDate dtaInicioValidade) {
        this.dtaInicioValidade = dtaInicioValidade;
    }

    public LocalDate getDtaFimValidade() {
        return dtaFimValidade;
    }

    public void setDtaFimValidade(LocalDate dtaFimValidade) {
        this.dtaFimValidade = dtaFimValidade;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }
}
