package br.com.nfast.api.services;

import br.com.nfast.api.model.adm.CentroCusto;
import br.com.nfast.api.model.adm.Despesa;
import br.com.nfast.api.model.adm.RateioPadrao;
import br.com.nfast.api.model.adm.TipoDespesa;
import br.com.nfast.api.model.crm.Empresa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/admin")
@RequestMapping("/api/admin")
public interface AdminApi {

    @ApiOperation(value = "Empresa", response = Empresa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class)
    @RequestMapping(value = "/empresa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Empresa> empresa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codEmpresa") Integer codEmpresa
    );

    @ApiOperation(value = "Empresas", response = Empresa.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Empresa.class, responseContainer = "List")
    @RequestMapping(value = "/empresa-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Empresa>> empresaList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Tipo de Despesa", response = TipoDespesa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = TipoDespesa.class)
    @RequestMapping(value = "/tipo-despesa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<TipoDespesa> tipoDespesa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codTipoDespesa") Long codTipoDespesa
    );

    @ApiOperation(value = "Tipos de Despesa", response = TipoDespesa.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = TipoDespesa.class, responseContainer = "List")
    @RequestMapping(value = "/tipo-despesa-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<TipoDespesa>> tipoDespesaList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Tipo") @RequestParam(value = "indTipo", required = false) String indTipo,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Centro de Custo", response = CentroCusto.class)
    @ApiResponse(code = 200, message = "Sucesso", response = CentroCusto.class)
    @RequestMapping(value = "/centro-custo", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<CentroCusto> centroCusto(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codCentroCusto") Integer codCentroCusto
    );

    @ApiOperation(value = "Centros de Custo", response = CentroCusto.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = CentroCusto.class, responseContainer = "List")
    @RequestMapping(value = "/centro-custo-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<CentroCusto>> centroCustoList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa") @RequestParam(value = "codEmpresa", required = false) Integer codEmpresa,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Parâmetro do Sistema", response = String.class)
    @ApiResponse(code = 200, message = "Sucesso", response = String.class)
    @RequestMapping(value = "/parametro-sistema", method = RequestMethod.GET, produces = "text/plain")
    ResponseEntity<String> parametroSistema(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codParametro") Integer codParametro
    );

    @ApiOperation(value = "Parâmetro da Empresa", response = String.class)
    @ApiResponse(code = 200, message = "Sucesso", response = String.class)
    @RequestMapping(value = "/parametro-empresa", method = RequestMethod.GET, produces = "text/plain")
    ResponseEntity<String> parametroEmpresa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codParametro") Integer codParametro,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Integer codEmpresa
    );

    @ApiOperation(value = "Despesa", response = Despesa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Despesa.class)
    @RequestMapping(value = "/despesa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Despesa> despesa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sequência", required = true) @RequestParam("seqDespesa") Integer seqDespesa
    );

    @ApiOperation(value = "Grava Despesa", response = Despesa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Despesa.class)
    @RequestMapping(value = "/grava-despesa", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<Despesa> gravaDespesa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Despesa", required = true) @RequestBody(required = false) Despesa despesa
    );

    @ApiOperation(value = "Rateio Padrão Despesa", response = RateioPadrao.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = RateioPadrao.class, responseContainer = "List")
    @RequestMapping(value = "/rateio-padrao-despesa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<RateioPadrao>> rateioPadraoDespesa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Tipo de Despesa", required = true) @RequestParam(value = "codTipoDespesa") Integer codTipoDespesa,
            @ApiParam(value = "Empresa", required = true) @RequestParam(value = "codEmpresa") Integer codEmpresa
    );

}
