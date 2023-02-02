package br.com.nfast.api.services;

import br.com.nfast.api.model.estoque.*;
import br.com.nfast.api.repo.estoque.*;
import br.com.nfast.api.web.util.ApiSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EstoqueController implements EstoqueApi {
    @Autowired
    private ProdutoRepo produtoRepo;
    @Autowired
    private SubgrupoItemRepo subgrupoItemRepo;
    @Autowired
    private UnidadeRepo unidadeRepo;
    @Autowired
    private AlmoxarifadoRepo almoxarifadoRepo;
    @Autowired
    private ProdutoNfeRepo produtoNfeRepo;
    @Autowired
    private PedidoCompraRepo pedidoCompraRepo;
    @Autowired
    private ItemPedidoCompraRepo itemPedidoCompraRepo;
    @Autowired
    private ProdutoCadRepo produtoCadRepo;

    @Override
    public ResponseEntity<Produto> produto(String token, String clientId, Long codItem) {
        Produto item = produtoRepo.produto(codItem);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Produto>> produtoList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<Produto> list = produtoRepo.produtoList(filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProdutoCad> gravaProduto(String token, String clientId, ProdutoCad produto) {
        ProdutoCad item = produtoCadRepo.gravaProduto(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SubgrupoItem> subgrupoItem(String token, String clientId, Long codSubgrupoItem) {
        SubgrupoItem item = subgrupoItemRepo.subGrupoItem(codSubgrupoItem);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SubgrupoItem>> subgrupoItemList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<SubgrupoItem> list = subgrupoItemRepo.subgrupoItemList(filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Almoxarifado> almoxarifado(String token, String clientId, Long codAlmoxarifado) {
        Almoxarifado item = almoxarifadoRepo.almoxarifado(codAlmoxarifado);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Almoxarifado>> almoxarifadoList(String token, String clientId, Long codEmpresa, String indTanque, Long codItemTanque, String filtro, Integer limit, Integer offset) {
        List<Almoxarifado> list = almoxarifadoRepo.almoxarifadoList(codEmpresa, indTanque, codItemTanque, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Unidade> unidade(String token, String clientId, Integer codUnidade) {
        Unidade item = unidadeRepo.unidade(codUnidade);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Unidade>> unidadeList(String token, String clientId, String sglUnidade, String filtro, Integer limit, Integer offset) {
        List<Unidade> list = unidadeRepo.unidadeList(sglUnidade, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProdutoNfe> produtoNfe(String token, String clientId, Long codItem, Long codEmpresa) {
        ProdutoNfe item = produtoNfeRepo.produtoNfe(codItem, codEmpresa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProdutoNfe>> produtoNfeList(String token, String clientId, Long codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ProdutoNfe> list = produtoNfeRepo.produtoNfeList(codEmpresa, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProdutoNfe> produtoNfeCodigo(String token, String clientId, String cprod, String cean, String ceanTrib, Integer codFornecedor, Long codEmpresa) {
        ProdutoNfe item = produtoNfeRepo.produtoNfeCodigo(cprod, cean, ceanTrib, codFornecedor, codEmpresa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProdutoFor> produtoFor(String token, String clientId, Long codItem, Integer codFornecedor, String codItemFornecedor) {
        ProdutoFor item = produtoNfeRepo.getProdutoFor(codItem, codFornecedor, codItemFornecedor);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> unidadeNfe(String token, String clientId, String sglUnidade, Long codItem, String codItemFornecedor, Integer codFornecedor) {
        Integer item = unidadeRepo.getUnidadeNfe(sglUnidade, codItem, codItemFornecedor, codFornecedor);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaldoEstoque> saldoEstoque(String token, String clientId, Long codEmpresa, Long codItem, Long codAlmoxarifado, LocalDate data) {
        SaldoEstoque saldoEstoque = new SaldoEstoque();
        saldoEstoque.setCodEmpresa(codEmpresa);
        saldoEstoque.setCodItem(codItem);
        saldoEstoque.setCodAlmoxarifado(codAlmoxarifado);
        saldoEstoque.setQtdSaldo(produtoRepo.getSaldoEstoque(codEmpresa, codItem, codAlmoxarifado, data));
        return new ResponseEntity<>(saldoEstoque, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustoProduto> custoProduto(String token, String clientId, Long codEmpresa, Long codItem, String tipo) {
        CustoProduto custoProduto = new CustoProduto();
        custoProduto.setCodEmpresa(codEmpresa);
        custoProduto.setCodItem(codItem);
        custoProduto.setVlrCusto(produtoRepo.getCustoProduto(codEmpresa, codItem, tipo));
        return new ResponseEntity<>(custoProduto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PrecoProduto> precoProduto(String token, String clientId, Long codEmpresa, Long codItem) {
        PrecoProduto precoProduto = produtoRepo.getPrecoProduto(codEmpresa, codItem);
        return new ResponseEntity<>(precoProduto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiSuccess> vinculaProdutoFor(String token, String clientId, Long codItem, Integer codFornecedor, String codItemFornecedor, Integer codUnidadeAgrup, Double qtdUnidadeAgrup) {
        produtoRepo.vinculaProdutoFor(codItem, codFornecedor, codItemFornecedor, codUnidadeAgrup, qtdUnidadeAgrup);
        return new ResponseEntity<>(new ApiSuccess("Ok"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiSuccess> vinculaProdutoEan(String token, String clientId, Long codItem, String codBarra, Long codEmpresa) {
        produtoRepo.vinculaProdutoEan(codItem, codBarra, codEmpresa);
        return new ResponseEntity<>(new ApiSuccess("Ok"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PedidoCompra> pedidoCompra(String token, String clientId, Integer seqPedido) {
        PedidoCompra item = pedidoCompraRepo.pedidoCompra(seqPedido);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PedidoCompra>> pedidoCompraList(String token, String clientId, String cnpjCpfFor, String cnpjEmpresa, Long codItem, String filtro, Integer limit, Integer offset) {
        List<PedidoCompra> list = pedidoCompraRepo.pedidoCompraList(cnpjCpfFor, cnpjEmpresa, codItem, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemPedidoCompra> itemPedidoCompra(String token, String clientId, Integer seqPedido, Integer seqItem) {
        ItemPedidoCompra item = itemPedidoCompraRepo.itemPedidoCompra(seqPedido, seqItem);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemPedidoCompra> itemPedidoCompraNfe(String token, String clientId, String cnpjCpfFor, String cnpjEmpresa, Long codItem, Double qtdTotal, String verificaQtdPedido, String permiteQtdMenorPedido) {
        ItemPedidoCompra item = itemPedidoCompraRepo.itemPedidoCompraNfe(cnpjCpfFor, cnpjEmpresa, codItem, qtdTotal, verificaQtdPedido, permiteQtdMenorPedido);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ItemPedidoCompra>> itemPedidoCompraList(String token, String clientId, String cnpjCpfFor, String cnpjEmpresa, Long codItem, String somenteAutorizado, String filtro, Integer limit, Integer offset) {
        List<ItemPedidoCompra> list = itemPedidoCompraRepo.itemPedidoCompraList(cnpjCpfFor, cnpjEmpresa, codItem, somenteAutorizado, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
