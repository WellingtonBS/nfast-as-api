package br.com.nfast.api.model.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class TipoCobrancaEmpresa {
    @Id
    @Column(name = "cod_tipo_cobranca")
    private Integer codTipoCobranca;
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "ind_tipo")
    private String indTipo;

    public Integer getCodTipoCobranca() {
        return codTipoCobranca;
    }

    public void setCodTipoCobranca(Integer codTipoCobranca) {
        this.codTipoCobranca = codTipoCobranca;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getIndTipo() {
        return indTipo;
    }

    public void setIndTipo(String indTipo) {
        this.indTipo = indTipo;
    }

}
