package br.com.nfast.api.services;

import br.com.nfast.api.dto.ConhecimentoDTO;
import br.com.nfast.api.model.conhecimento.Conhecimento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "/api/conhecimento/")
@RequestMapping("/api/conhecimento")
public interface ConhecimentoApi {

    @ApiOperation(value = "Obtem conhecimento por chave resumo", response = Conhecimento.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Conhecimento.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Conhecimento> obtemConhecimento(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Chave", required = true) @RequestParam(value = "chave") String chave
    );

    @ApiOperation(value = "Gravar conhecimento", response = ConhecimentoDTO.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ConhecimentoDTO.class)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<Conhecimento> gravarConhecimento(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Conhecimento", required = true) @RequestBody(required = false) ConhecimentoDTO conhecimento
    );
}
