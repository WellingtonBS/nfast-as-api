package br.com.nfast.api.model.nfe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CustoNFe {
    @Id
    @Column(name = "seq_movimento")
    private Integer seqMovimento;
    @Column(name = "cod_item")
    private Integer codItem;
    @Column(name = "des_item")
    private String desItem;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "dta_entrada")
    private LocalDate dtaEntrada;
    @Column(name = "qtd_bruto")
    private Double qtdBruto;
    @Column(name = "qtd_total")
    private Double qtdTotal;
    @Column(name = "sgl_unidade")
    private String sglUnidade;
    @Column(name = "val_custo_ant")
    private Double valCustoAnt;
    @Column(name = "val_custo")
    private Double valCusto;
    @Column(name = "per_variacao")
    private Double perVariacao;
    @Column(name = "seq_nota")
    private Integer seqNota;
    @Column(name = "num_nota")
    private String numNota;
    @Column(name = "nom_fornecedor")
    private String nomFornecedor;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "nom_fantasia")
    private String nomFantasia;
    @Column(name = "cod_almoxarifado")
    private Integer codAlmoxarifado;
    @Column(name = "des_almoxarifado")
    private String desAlmoxarifado;

    public Integer getSeqMovimento() {
        return seqMovimento;
    }

    public void setSeqMovimento(Integer seqMovimento) {
        this.seqMovimento = seqMovimento;
    }

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public String getDesItem() {
        return desItem;
    }

    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public LocalDate getDtaEntrada() {
        return dtaEntrada;
    }

    public void setDtaEntrada(LocalDate dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    public Double getQtdBruto() {
        return qtdBruto;
    }

    public void setQtdBruto(Double qtdBruto) {
        this.qtdBruto = qtdBruto;
    }

    public Double getQtdTotal() {
        return qtdTotal;
    }

    public void setQtdTotal(Double qtdTotal) {
        this.qtdTotal = qtdTotal;
    }

    public String getSglUnidade() {
        return sglUnidade;
    }

    public void setSglUnidade(String sglUnidade) {
        this.sglUnidade = sglUnidade;
    }

    public Double getValCustoAnt() {
        return valCustoAnt;
    }

    public void setValCustoAnt(Double valCustoAnt) {
        this.valCustoAnt = valCustoAnt;
    }

    public Double getValCusto() {
        return valCusto;
    }

    public void setValCusto(Double valCusto) {
        this.valCusto = valCusto;
    }

    public Double getPerVariacao() {
        return perVariacao;
    }

    public void setPerVariacao(Double perVariacao) {
        this.perVariacao = perVariacao;
    }

    public Integer getSeqNota() {
        return seqNota;
    }

    public void setSeqNota(Integer seqNota) {
        this.seqNota = seqNota;
    }

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }

    public String getNomFornecedor() {
        return nomFornecedor;
    }

    public void setNomFornecedor(String nomFornecedor) {
        this.nomFornecedor = nomFornecedor;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getNomFantasia() {
        return nomFantasia;
    }

    public void setNomFantasia(String nomFantasia) {
        this.nomFantasia = nomFantasia;
    }

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
}
