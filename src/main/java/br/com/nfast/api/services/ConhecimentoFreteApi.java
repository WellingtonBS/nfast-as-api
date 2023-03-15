package br.com.nfast.api.services;

import br.com.nfast.api.model.nfe.ConhecimentoFreteResumo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "/api/conhecimento-frete/")
@RequestMapping("/api/conhecimento-frete")
public interface ConhecimentoFreteApi {

    @ApiOperation(value = "Obtem conhecimento de frete", response = ConhecimentoFreteResumo.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ConhecimentoFreteResumo.class)
    @RequestMapping(value = "/resumo", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ConhecimentoFreteResumo> obtemConhecimentoFrete(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Chave", required = true) @RequestParam(value = "chave") String chave
    );
}
