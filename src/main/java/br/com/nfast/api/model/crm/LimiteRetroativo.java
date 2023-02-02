package br.com.nfast.api.model.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class LimiteRetroativo {
    @Id
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "ind_tipo_limite_retroativo")
    private String indTipoLimiteRetroativo;
    @Column(name = "dta_limite_retroativo")
    private LocalDate dtaLimiteRetroativo;
    @Column(name = "qtd_dias_limite_retroacao")
    private Integer qtdDiasLimiteRetroacao = 0;

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getIndTipoLimiteRetroativo() {
        return indTipoLimiteRetroativo;
    }

    public void setIndTipoLimiteRetroativo(String indTipoLimiteRetroativo) {
        this.indTipoLimiteRetroativo = indTipoLimiteRetroativo;
    }

    public LocalDate getDtaLimiteRetroativo() {
        return dtaLimiteRetroativo;
    }

    public void setDtaLimiteRetroativo(LocalDate dtaLimiteRetroativo) {
        this.dtaLimiteRetroativo = dtaLimiteRetroativo;
    }

    public Integer getQtdDiasLimiteRetroacao() {
        return qtdDiasLimiteRetroacao;
    }

    public void setQtdDiasLimiteRetroacao(Integer qtdDiasLimiteRetroacao) {
        this.qtdDiasLimiteRetroacao = qtdDiasLimiteRetroacao;
    }

}
