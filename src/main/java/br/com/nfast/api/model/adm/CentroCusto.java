package br.com.nfast.api.model.adm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
public class CentroCusto {
    @Id
    @Column(name = "cod_centro_custo")
    private Integer codCentroCusto;
    @Column(name = "des_centro_custo")
    private String desCentroCusto;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "dta_inicio_validade")
    private LocalDate dtaInicioValidade;
    @Column(name = "dta_fim_validade")
    private LocalDate dtaFimValidade;
    @Column(name = "ind_tipo")
    private String indTipo;

    public Integer getCodCentroCusto() {
        return codCentroCusto;
    }

    public void setCodCentroCusto(Integer codCentroCusto) {
        this.codCentroCusto = codCentroCusto;
    }

    public String getDesCentroCusto() {
        return desCentroCusto;
    }

    public void setDesCentroCusto(String desCentroCusto) {
        this.desCentroCusto = desCentroCusto;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
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
