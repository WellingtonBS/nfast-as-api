package br.com.nfast.api.model.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_tipo_cobranca_empresa")
public class TipoCobrancaEmpresa {
    @Id
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "ind_tipo")
    private String indTipo;

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

}
