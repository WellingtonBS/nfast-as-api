package br.com.nfast.api.services;

import br.com.nfast.api.model.crm.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "/api/search")
@RequestMapping("/api/search")
public interface SearchApi {

    @ApiIgnore
    @ApiOperation(value = "Search Value", response = String.class)
    @ApiResponse(code = 200, message = "Sucesso", response = String.class)
    @RequestMapping(value = "/value", method = RequestMethod.POST, produces = "text/plain")
    ResponseEntity<String> searchValue(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Query", required = true) @RequestBody(required = false) String query
    );

    @ApiIgnore
    @ApiOperation(value = "Search List", response = Item.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Item.class, responseContainer = "List")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<List<Item>> searchList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Query", required = true) @RequestBody(required = false) String query,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

}
