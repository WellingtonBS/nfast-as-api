package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class PedidoCompra {
    @Id
    @Column(name = "seq_pedido")
    private Integer seqPedido;
    @Column(name = "cod_empresa")
    private Integer codEmpresa;
    @Column(name = "cod_pessoa_fornecedor")
    private Integer codPessoaFornecedor;
    @Column(name = "num_pedido_fornecedor")
    private String numPedidoFornecedor;
    @Column(name = "dta_emissao")
    private LocalDate dtaEmissao;
    @Column(name = "val_pedido")
    private Double valPedido = 0.0;
    @Column(name = "ind_pendente")
    private String indPendente;
    @Column(name = "des_observacao")
    private String desObservacao;
    @Column(name = "nom_usuario")
    private String nomUsuario;
    @Column(name = "seq_log_autorizacao")
    private Integer seqLogAutorizacao;
    @Column(name = "ind_status_aprovacao_alcada")
    private String indStatusAprovacaoAlcada;

    public Integer getSeqPedido() {
        return seqPedido;
    }

    public void setSeqPedido(Integer seqPedido) {
        this.seqPedido = seqPedido;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public Integer getCodPessoaFornecedor() {
        return codPessoaFornecedor;
    }

    public void setCodPessoaFornecedor(Integer codPessoaFornecedor) {
        this.codPessoaFornecedor = codPessoaFornecedor;
    }

    public String getNumPedidoFornecedor() {
        return numPedidoFornecedor;
    }

    public void setNumPedidoFornecedor(String numPedidoFornecedor) {
        this.numPedidoFornecedor = numPedidoFornecedor;
    }

    public LocalDate getDtaEmissao() {
        return dtaEmissao;
    }

    public void setDtaEmissao(LocalDate dtaEmissao) {
        this.dtaEmissao = dtaEmissao;
    }

    public Double getValPedido() {
        return valPedido;
    }

    public void setValPedido(Double valPedido) {
        this.valPedido = valPedido;
    }

    public String getIndPendente() {
        return indPendente;
    }

    public void setIndPendente(String indPendente) {
        this.indPendente = indPendente;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public Integer getSeqLogAutorizacao() {
        return seqLogAutorizacao;
    }

    public void setSeqLogAutorizacao(Integer seqLogAutorizacao) {
        this.seqLogAutorizacao = seqLogAutorizacao;
    }

    public String getIndStatusAprovacaoAlcada() {
        return indStatusAprovacaoAlcada;
    }

    public void setIndStatusAprovacaoAlcada(String indStatusAprovacaoAlcada) {
        this.indStatusAprovacaoAlcada = indStatusAprovacaoAlcada;
    }

}
