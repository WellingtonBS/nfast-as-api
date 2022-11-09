package br.com.nfast.api.services;

import br.com.nfast.api.model.financeiro.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(tags = "/api/financeiro")
@RequestMapping("/api/financeiro")
public interface FinanceiroApi {

    @ApiOperation(value = "Espécie de Caixa", response = EspecieCaixa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = EspecieCaixa.class)
    @RequestMapping(value = "/especie-caixa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<EspecieCaixa> especieCaixa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codEspecieCaixa") Integer codEspecieCaixa
    );

    @ApiOperation(value = "Espécies de Caixa", response = EspecieCaixa.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = EspecieCaixa.class, responseContainer = "List")
    @RequestMapping(value = "/especie-caixa-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<EspecieCaixa>> especieCaixaList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Conta Bancária", response = ContaBanco.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ContaBanco.class)
    @RequestMapping(value = "/conta-banco", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ContaBanco> contaBanco(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Mnemonico", required = true) @RequestParam("numMnemonico") String numMnemonico
    );

    @ApiOperation(value = "Contas Bancárias", response = ContaBanco.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = ContaBanco.class, responseContainer = "List")
    @RequestMapping(value = "/conta-banco-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ContaBanco>> contaBancoList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam(value = "codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Tipo de Cobrança", response = TipoCobranca.class)
    @ApiResponse(code = 200, message = "Sucesso", response = TipoCobranca.class)
    @RequestMapping(value = "/tipo-cobranca", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<TipoCobranca> tipoCobranca(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codTipoCobranca") Integer codTipoCobranca
    );

    @ApiOperation(value = "Tributações", response = TipoCobranca.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = TipoCobranca.class, responseContainer = "List")
    @RequestMapping(value = "/tipo-cobranca-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<TipoCobranca>> tipoCobrancaList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam(value = "codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Saldo de Caixa", response = SaldoCaixa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = SaldoCaixa.class)
    @RequestMapping(value = "/saldo-caixa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<SaldoCaixa> saldoCaixa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Código", required = true) @RequestParam("codEspecieCaixa") Integer codEspecieCaixa
    );

    @ApiOperation(value = "Saldo de Banco", response = SaldoBanco.class)
    @ApiResponse(code = 200, message = "Sucesso", response = SaldoBanco.class)
    @RequestMapping(value = "/saldo-banco", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<SaldoBanco> saldoBanco(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa,
            @ApiParam(value = "Mnemonico", required = true) @RequestParam("numMnemonico") String numMnemonico
    );

}
