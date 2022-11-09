package br.com.nfast.api.services;

import br.com.nfast.api.config.auth.ApiInfo;
import br.com.nfast.api.config.auth.Bearer;
import br.com.nfast.api.config.auth.ClientDatabaseConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "/auth")
@RequestMapping("/auth")
public interface AuthApi {

    @ApiOperation(value = "Autentica Usuário", response = Bearer.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Bearer.class)
    @RequestMapping(value = "/token", produces = "application/json", method = RequestMethod.POST)
    ResponseEntity<Bearer> token(
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Usuário", required = true) @RequestHeader("user") String user,
            @ApiParam(value = "Senha", required = true) @RequestHeader("pass") String pass
    );

    @ApiIgnore
    @ApiOperation(value = "Database Config", response = ClientDatabaseConfig.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ClientDatabaseConfig.class)
    @RequestMapping(value = "/config", produces = "application/json", method = RequestMethod.GET)
    ResponseEntity<ClientDatabaseConfig> config(
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId
    );

    @ApiOperation(value = "API Info", response = ApiInfo.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ApiInfo.class)
    @RequestMapping(value = "/api-info", produces = "application/json", method = RequestMethod.GET)
    ResponseEntity<ApiInfo> apiInfo();

}
