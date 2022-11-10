package br.com.nfast.api.services;

import br.com.nfast.api.model.estoque.*;
import br.com.nfast.api.web.util.ApiSuccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "/api/estoque")
@RequestMapping("/api/estoque")
public interface EstoqueApi {

    @ApiOperation(value = "Produto", response = Produto.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Produto.class)
    @RequestMapping(value = "/produto", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Produto> produto(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codItem") Integer codItem
    );

    @ApiOperation(value = "Produtos", response = Produto.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Produto.class, responseContainer = "List")
    @RequestMapping(value = "/produto-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Produto>> produtoList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Grava Produto", response = ProdutoCad.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ProdutoCad.class)
    @RequestMapping(value = "/grava-produto", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<ProdutoCad> gravaProduto(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestBody(required = false) ProdutoCad produto
    );

    @ApiOperation(value = "Subgrupo de Produto", response = SubgrupoItem.class)
    @ApiResponse(code = 200, message = "Sucesso", response = SubgrupoItem.class)
    @RequestMapping(value = "/subgrupo-item", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<SubgrupoItem> subgrupoItem(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codSubgrupoItem") Long codSubgrupoItem
    );

    @ApiOperation(value = "Subgrupos de Produto", response = SubgrupoItem.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = SubgrupoItem.class, responseContainer = "List")
    @RequestMapping(value = "/subgrupo-item-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<SubgrupoItem>> subgrupoItemList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Almoxarifado", response = Almoxarifado.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Almoxarifado.class)
    @RequestMapping(value = "/almoxarifado", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Almoxarifado> almoxarifado(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codAlmoxarifado") Long codAlmoxarifado
    );

    @ApiOperation(value = "Almoxarifados", response = Almoxarifado.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Almoxarifado.class, responseContainer = "List")
    @RequestMapping(value = "/almoxarifado-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Almoxarifado>> almoxarifadoList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Tanque", required = true) @RequestParam("indTanque") String indTanque,
            @ApiParam(value = "Combustivel", required = true) @RequestParam("codItemTanque") Long codItemTanque,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Unidade", response = Unidade.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Unidade.class)
    @RequestMapping(value = "/unidade", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Unidade> unidade(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codUnidade") Integer codUnidade
    );

    @ApiOperation(value = "Unidades", response = Unidade.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Unidade.class, responseContainer = "List")
    @RequestMapping(value = "/unidade-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Unidade>> unidadeList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sigla") @RequestParam(value = "sglUnidade", required = false) String sglUnidade,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Produto NF-e", response = ProdutoNfe.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ProdutoNfe.class)
    @RequestMapping(value = "/produto-nfe", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ProdutoNfe> produtoNfe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Integer codItem,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa
    );

    @ApiOperation(value = "Produtos NF-e", response = ProdutoNfe.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = ProdutoNfe.class, responseContainer = "List")
    @RequestMapping(value = "/produto-nfe-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ProdutoNfe>> produtoNfeList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Produto NF-e", response = ProdutoNfe.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ProdutoNfe.class)
    @RequestMapping(value = "/produto-nfe-codigo", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ProdutoNfe> produtoNfeCodigo(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "cProd", required = true) @RequestParam("cprod") String cprod,
            @ApiParam(value = "cEAN") @RequestParam(value = "cean", required = false) String cean,
            @ApiParam(value = "cEANTrib") @RequestParam(value = "ceanTrib", required = false) String ceanTrib,
            @ApiParam(value = "Fornecedor") @RequestParam(value = "codFornecedor", required = false) Integer codFornecedor,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa
    );

    @ApiOperation(value = "Produto x Fornecedor", response = ProdutoFor.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ProdutoFor.class)
    @RequestMapping(value = "/produto-for", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ProdutoFor> produtoFor(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Integer codItem,
            @ApiParam(value = "Fornecedor", required = true) @RequestParam("codFornecedor") Integer codFornecedor,
            @ApiParam(value = "Código Item Fornecedor", required = true) @RequestParam("codItemFornecedor") String codItemFornecedor
    );

    @ApiOperation(value = "Unidade NF-e", response = Unidade.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Unidade.class)
    @RequestMapping(value = "/unidade-nfe", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Integer> unidadeNfe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sigla da Unidade") @RequestParam(value = "sglUnidade", required = false) String sglUnidade,
            @ApiParam(value = "Produto") @RequestParam(value = "codItem", required = false) Integer codItem,
            @ApiParam(value = "Código Item Fornecedor") @RequestParam(value = "codItemFornecedor", required = false) String codItemFornecedor,
            @ApiParam(value = "Fornecedor") @RequestParam(value = "codFornecedor", required = false) Integer codFornecedor
    );

    @ApiOperation(value = "Saldo Estoque", response = SaldoEstoque.class)
    @ApiResponse(code = 200, message = "Sucesso", response = SaldoEstoque.class)
    @RequestMapping(value = "/saldo-estoque", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<SaldoEstoque> saldoEstoque(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Integer codItem,
            @ApiParam(value = "Almoxarifado", required = true) @RequestParam("codAlmoxarifado") Integer codAlmoxarifado,
            @ApiParam(value = "Data", required = true) @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    );

    @ApiOperation(value = "Custo Produto", response = SaldoEstoque.class)
    @ApiResponse(code = 200, message = "Sucesso", response = SaldoEstoque.class)
    @RequestMapping(value = "/custo-produto", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<CustoProduto> custoProduto(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Integer codItem,
            @ApiParam(value = "Tipo", required = true) @RequestParam("tipo") String tipo
    );

    @ApiOperation(value = "Preço Produto", response = PrecoProduto.class)
    @ApiResponse(code = 200, message = "Sucesso", response = PrecoProduto.class)
    @RequestMapping(value = "/preco-produto", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<PrecoProduto> precoProduto(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Integer codItem
    );

    @ApiOperation(value = "Vincula Produto Fornecedor", response = ApiSuccess.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ApiSuccess.class)
    @RequestMapping(value = "/vincula-produto-for", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<ApiSuccess> vinculaProdutoFor(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto") @RequestParam(value = "codItem", required = false) Integer codItem,
            @ApiParam(value = "Fornecedor") @RequestParam(value = "codFornecedor", required = false) Integer codFornecedor,
            @ApiParam(value = "Código Item Fornecedor") @RequestParam(value = "codItemFornecedor", required = false) String codItemFornecedor,
            @ApiParam(value = "Unidade Agrupamento") @RequestParam(value = "codUnidadeAgrup", required = false) Integer codUnidadeAgrup,
            @ApiParam(value = "Quantidade Agrupamento", required = true) @RequestParam(value = "qtdUnidadeAgrup") Double qtdUnidadeAgrup
    );

    @ApiOperation(value = "Vincula EAN Produto", response = ApiSuccess.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ApiSuccess.class)
    @RequestMapping(value = "/vincula-produto-ean", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<ApiSuccess> vinculaProdutoEan(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestParam(value = "codItem") Integer codItem,
            @ApiParam(value = "Código de Barras", required = true) @RequestParam(value = "codBarra") String codBarra,
            @ApiParam(value = "Empresa", required = true) @RequestParam(value = "codEmpresa") Integer codEmpresa
    );

    @ApiOperation(value = "Pedido de Compra", response = PedidoCompra.class)
    @ApiResponse(code = 200, message = "Sucesso", response = PedidoCompra.class)
    @RequestMapping(value = "/pedido-compra", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<PedidoCompra> pedidoCompra(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sequência", required = true) @RequestParam("seqPedido") Integer seqPedido
    );

    @ApiOperation(value = "Pedidos de Compra", response = PedidoCompra.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = PedidoCompra.class, responseContainer = "List")
    @RequestMapping(value = "/pedido-compra-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<PedidoCompra>> pedidoCompraList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "CNPJ/CPF Fornecedor", required = true) @RequestParam("cnpjCpfFor") String cnpjCpfFor,
            @ApiParam(value = "CNPJ Empresa", required = true) @RequestParam("cnpjEmpresa") String cnpjEmpresa,
            @ApiParam(value = "Produto") @RequestParam(value = "codItem", required = false) Integer codItem,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Item do Pedido de Compra", response = ItemPedidoCompra.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ItemPedidoCompra.class)
    @RequestMapping(value = "/item-pedido-compra", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ItemPedidoCompra> itemPedidoCompra(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sequência do Pedido", required = true) @RequestParam("seqPedido") Integer seqPedido,
            @ApiParam(value = "Sequência do Item", required = true) @RequestParam("seqItem") Integer seqItem
    );

    @ApiOperation(value = "Item do Pedido de Compra NF-e", response = ItemPedidoCompra.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ItemPedidoCompra.class)
    @RequestMapping(value = "/item-pedido-compra-nfe", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ItemPedidoCompra> itemPedidoCompraNfe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "CNPJ/CPF Fornecedor", required = true) @RequestParam("cnpjCpfFor") String cnpjCpfFor,
            @ApiParam(value = "CNPJ Empresa", required = true) @RequestParam("cnpjEmpresa") String cnpjEmpresa,
            @ApiParam(value = "Produto", required = true) @RequestParam(value = "codItem") Integer codItem,
            @ApiParam(value = "Quantidade", required = true) @RequestParam(value = "qtdTotal") Double qtdTotal,
            @ApiParam(value = "Verifica Qtde do Pedido", defaultValue = "S", required = true) @RequestParam(value = "verificaQtdPedido") String verificaQtdPedido,
            @ApiParam(value = "Permite Qtde Menor que a do Pedido", defaultValue = "N", required = true) @RequestParam(value = "permiteQtdMenorPedido") String permiteQtdMenorPedido
    );

    @ApiOperation(value = "Itens do Pedido de Compra", response = ItemPedidoCompra.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = ItemPedidoCompra.class, responseContainer = "List")
    @RequestMapping(value = "/item-pedido-compra-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ItemPedidoCompra>> itemPedidoCompraList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "CNPJ/CPF Fornecedor", required = true) @RequestParam("cnpjCpfFor") String cnpjCpfFor,
            @ApiParam(value = "CNPJ Empresa", required = true) @RequestParam("cnpjEmpresa") String cnpjEmpresa,
            @ApiParam(value = "Produto", required = true) @RequestParam(value = "codItem") Integer codItem,
            @ApiParam(value = "Somente Autorizados", defaultValue = "S", required = true) @RequestParam(value = "somenteAutorizado") String somenteAutorizado,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

}
