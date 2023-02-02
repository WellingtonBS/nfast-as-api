package br.com.nfast.api.services;

import br.com.nfast.api.model.nfe.CustoNFe;
import br.com.nfast.api.model.nfe.NFe;
import br.com.nfast.api.model.nfe.NFeResumo;
import br.com.nfast.api.web.util.ApiSuccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/nfe")
@RequestMapping("/api/nfe")
public interface NFeApi {

    @ApiOperation(value = "NFE", response = NFe.class)
    @ApiResponse(code = 200, message = "Sucesso", response = NFe.class)
    @RequestMapping(value = "/nfe", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<NFe> nfe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sequência", required = true) @RequestParam("seqNota") Long seqNota
    );

    @ApiOperation(value = "Grava NFE", response = NFeResumo.class)
    @ApiResponse(code = 200, message = "Sucesso", response = NFeResumo.class)
    @RequestMapping(value = "/grava-nfe", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<NFeResumo> gravaNFe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Nota Fiscal", required = true) @RequestBody(required = false) NFe nfe
    );

    @ApiOperation(value = "Exclui NFE", response = String.class)
    @ApiResponse(code = 200, message = "Sucesso", response = String.class)
    @RequestMapping(value = "/exclui-nfe", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<ApiSuccess> excluiNFe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Chave", required = true) @RequestParam(value = "numChaveNfe") String numChaveNfe,
            @ApiParam(value = "Empresa", required = true) @RequestParam(value = "codEmpresa") Integer codEmpresa
    );

    @ApiOperation(value = "Listagem por Usuário", response = NFeResumo.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = NFeResumo.class, responseContainer = "List")
    @RequestMapping(value = "/lista-nfe-usuario", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<NFeResumo>> listaNFeUsuario(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Data Inicial", required = true) @RequestParam("dataIni") String dataIni,
            @ApiParam(value = "Data Final", required = true) @RequestParam("dataFim") String dataFim,
            @ApiParam(value = "Empresas") @RequestParam(value = "codEmpresas", required = false) String codEmpresas,
            @ApiParam(value = "Usuário") @RequestParam(value = "nomUsuario", required = false) String nomUsuario
    );

    @ApiOperation(value = "Listagem por Chave", response = NFeResumo.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = NFeResumo.class, responseContainer = "List")
    @RequestMapping(value = "/lista-nfe-chave", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<NFeResumo>> listaNFeChave(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam(value = "codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Chaves", required = true) @RequestBody(required = false) String chaves
    );

    @ApiOperation(value = "Auditoria de Custo", response = CustoNFe.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = CustoNFe.class, responseContainer = "List")
    @RequestMapping(value = "/auditoria", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<CustoNFe>> auditoria(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Data Inicial", required = true) @RequestParam("dataIni") String dataIni,
            @ApiParam(value = "Data Final", required = true) @RequestParam("dataFim") String dataFim,
            @ApiParam(value = "Empresas") @RequestParam(value = "codEmpresas", required = false) String codEmpresas,
            @ApiParam(value = "Fornecedor") @RequestParam(value = "codFornecedor", required = false) Integer codFornecedor,
            @ApiParam(value = "% Variação (+)") @RequestParam(value = "perVariacaoUp", required = false) Double perVariacaoUp,
            @ApiParam(value = "% Variação (-)") @RequestParam(value = "perVariacaoDown", required = false) Double perVariacaoDown,
            @ApiParam(value = "Trazer Itens c/ Custo Anterior Zerado", required = true) @RequestParam(value = "custoAntZero") String custoAntZero,
            @ApiParam(value = "Produto") @RequestParam(value = "filtroProduto", required = false) String filtroProduto
    );

}
