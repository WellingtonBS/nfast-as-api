package br.com.nfast.api.services;

import br.com.nfast.api.model.cte.CTeResumo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/cte")
@RequestMapping("/api/cte")
public interface CTeApi {

    @ApiOperation(value = "Listagem por Chave", response = CTeResumo.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = CTeResumo.class, responseContainer = "List")
    @RequestMapping(value = "/lista-chave", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<CTeResumo>> listaChave(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa") @RequestParam(value = "codEmpresa", required = false) Integer codEmpresa,
            @ApiParam(value = "Chaves", required = true) @RequestBody(required = false) String chaves
    );

}
