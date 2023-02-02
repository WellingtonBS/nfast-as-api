package br.com.nfast.api.model.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Empresa {
    @Id
    @Column(name = "cod_empresa")
    private Long codEmpresa;
    @Column(name = "num_cnpj")
    private String numCnpj;
    @Column(name = "nom_razao_social")
    private String nomRazaoSocial;
    @Column(name = "nom_fantasia")
    private String nomFantasia;
    @Column(name = "sgl_estado")
    private String sglEstado;
    @Column(name = "ind_ativo")
    private String indAtivo;

    @Column(name = "cod_almoxarifado_venda")
    private Long codAlmoxarifadoVenda;
    @Column(name = "ind_verificar_saldo_caixa")
    private String indVerificarSaldoCaixa;

    @Column(name = "ind_contribuinte_icms")
    private String indContribuinteIcms;
    @Column(name = "ind_contribuinte_ipi")
    private String indContribuinteIpi;
    @Column(name = "ind_regime_deb_cred_icms")
    private String indRegimeDebCredIcms;
    @Column(name = "ind_regime_deb_cred_ipi")
    private String indRegimeDebCredIpi;
    @Column(name = "ind_regime_deb_cred_iss")
    private String indRegimeDebCredIss;
    @Column(name = "ind_regime_deb_cred_piscof")
    private String indRegimeDebCredPiscof;

    @Column(name = "ind_nfe_pedido")
    private String indNfePedido;

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNumCnpj() {
        return numCnpj;
    }

    public void setNumCnpj(String numCnpj) {
        this.numCnpj = numCnpj;
    }

    public String getNomRazaoSocial() {
        return nomRazaoSocial;
    }

    public void setNomRazaoSocial(String nomRazaoSocial) {
        this.nomRazaoSocial = nomRazaoSocial;
    }

    public String getNomFantasia() {
        return nomFantasia;
    }

    public void setNomFantasia(String nomFantasia) {
        this.nomFantasia = nomFantasia;
    }

    public String getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(String indAtivo) {
        this.indAtivo = indAtivo;
    }

    public Long getCodAlmoxarifadoVenda() {
        return codAlmoxarifadoVenda;
    }

    public void setCodAlmoxarifadoVenda(Long codAlmoxarifadoVenda) {
        this.codAlmoxarifadoVenda = codAlmoxarifadoVenda;
    }

    public String getIndVerificarSaldoCaixa() {
        return indVerificarSaldoCaixa;
    }

    public void setIndVerificarSaldoCaixa(String indVerificarSaldoCaixa) {
        this.indVerificarSaldoCaixa = indVerificarSaldoCaixa;
    }

    public String getSglEstado() {
        return sglEstado;
    }

    public void setSglEstado(String sglEstado) {
        this.sglEstado = sglEstado;
    }

    public String getIndContribuinteIcms() {
        return indContribuinteIcms;
    }

    public void setIndContribuinteIcms(String indContribuinteIcms) {
        this.indContribuinteIcms = indContribuinteIcms;
    }

    public String getIndContribuinteIpi() {
        return indContribuinteIpi;
    }

    public void setIndContribuinteIpi(String indContribuinteIpi) {
        this.indContribuinteIpi = indContribuinteIpi;
    }

    public String getIndRegimeDebCredIcms() {
        return indRegimeDebCredIcms;
    }

    public void setIndRegimeDebCredIcms(String indRegimeDebCredIcms) {
        this.indRegimeDebCredIcms = indRegimeDebCredIcms;
    }

    public String getIndRegimeDebCredIpi() {
        return indRegimeDebCredIpi;
    }

    public void setIndRegimeDebCredIpi(String indRegimeDebCredIpi) {
        this.indRegimeDebCredIpi = indRegimeDebCredIpi;
    }

    public String getIndRegimeDebCredIss() {
        return indRegimeDebCredIss;
    }

    public void setIndRegimeDebCredIss(String indRegimeDebCredIss) {
        this.indRegimeDebCredIss = indRegimeDebCredIss;
    }

    public String getIndRegimeDebCredPiscof() {
        return indRegimeDebCredPiscof;
    }

    public void setIndRegimeDebCredPiscof(String indRegimeDebCredPiscof) {
        this.indRegimeDebCredPiscof = indRegimeDebCredPiscof;
    }

    public String getIndNfePedido() {
        return indNfePedido;
    }

    public void setIndNfePedido(String indNfePedido) {
        this.indNfePedido = indNfePedido;
    }
}
