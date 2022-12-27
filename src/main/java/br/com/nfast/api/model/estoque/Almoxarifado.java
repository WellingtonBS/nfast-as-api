package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Almoxarifado {
    @Id
    @Column(name = "cod_almoxarifado")
    private Integer codAlmoxarifado;
    @Column(name = "des_almoxarifado")
    private String desAlmoxarifado;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "cod_item_tanque")
    private Integer codItemTanque;
    @Column(name = "qtd_capacidade_tanque")
    private Double qtdCapacidadeTanque = 0.0;
    @Column(name = "qtd_lastro_tanque")
    private Double qtdLastroTanque = 0.0;
    @Column(name = "ind_tanque")
    private String indTanque;
    @Column(name = "ind_desativado")
    private String indDesativado;

    public Integer getCodAlmoxarifado() {
        return codAlmoxarifado;
    }

    public void setCodAlmoxarifado(Integer codAlmoxarifado) {
        this.codAlmoxarifado = codAlmoxarifado;
    }

    public String getDesAlmoxarifado() {
        return desAlmoxarifado;
    }

    public void setDesAlmoxarifado(String desAlmoxarifado) {
        this.desAlmoxarifado = desAlmoxarifado;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Integer getCodItemTanque() {
        return codItemTanque;
    }

    public void setCodItemTanque(Integer codItemTanque) {
        this.codItemTanque = codItemTanque;
    }

    public Double getQtdCapacidadeTanque() {
        return qtdCapacidadeTanque;
    }

    public void setQtdCapacidadeTanque(Double qtdCapacidadeTanque) {
        this.qtdCapacidadeTanque = qtdCapacidadeTanque;
    }

    public Double getQtdLastroTanque() {
        return qtdLastroTanque;
    }

    public void setQtdLastroTanque(Double qtdLastroTanque) {
        this.qtdLastroTanque = qtdLastroTanque;
    }

    public String getIndTanque() {
        return indTanque;
    }

    public void setIndTanque(String indTanque) {
        this.indTanque = indTanque;
    }

    public String getIndDesativado() {
        return indDesativado;
    }

    public void setIndDesativado(String indDesativado) {
        this.indDesativado = indDesativado;
    }

}

