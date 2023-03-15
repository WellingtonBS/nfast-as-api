package br.com.nfast.api.services;

import br.com.nfast.api.web.util.ApiSuccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "/api/conhecimento-nota/")
@RequestMapping("/api/conhecimento-nota")
public interface ConhecimentoNotaApi {

    @ApiOperation(value = "Grava conhecimento nota", response = String.class)
    @ApiResponse(code = 200, message = "Sucesso", response = String.class)
    @RequestMapping(value = "/grava", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ApiSuccess> gravaConhecimentoNota(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Seq. Nota", required = true) @RequestParam(value = "seqNota") Integer seqNota,
            @ApiParam(value = "Seq. Conhecimento", required = true) @RequestParam(value = "seqConhecimento") Integer seqConhecimento,
            @ApiParam(value = "Ind Entrada ou Saida", required = true) @RequestParam(value = "indEntradaSaida") String indEntradaSaida
    );
}
