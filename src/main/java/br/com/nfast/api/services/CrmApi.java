package br.com.nfast.api.services;

import br.com.nfast.api.model.crm.Cidade;
import br.com.nfast.api.model.crm.Pessoa;
import br.com.nfast.api.model.crm.PessoaFull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "/api/crm")
@RequestMapping("/api/crm")
public interface CrmApi {

    @ApiOperation(value = "Pessoa", response = Pessoa.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Pessoa.class)
    @RequestMapping(value = "/pessoa", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Pessoa> pessoa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código") @RequestParam(value = "codPessoa", required = false) Integer codPessoa,
            @ApiParam(value = "CNPJ/CPF") @RequestParam(value = "numCnpjCpf", required = false) String numCnpjCpf
    );

    @ApiOperation(value = "Pessoas", response = Pessoa.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Pessoa.class, responseContainer = "List")
    @RequestMapping(value = "/pessoa-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Pessoa>> pessoaList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Pessoas", response = Pessoa.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Pessoa.class, responseContainer = "List")
    @RequestMapping(value = "/pessoa-list-cnpj-cpf", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Pessoa>> pessoaListCnpjCpf(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "CNPJ/CPF") @RequestParam(value = "cnpjCpf", required = false) String cnpjCpf,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Grava Pessoa", response = PessoaFull.class)
    @ApiResponse(code = 200, message = "Sucesso", response = PessoaFull.class)
    @RequestMapping(value = "/grava-pessoa", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<PessoaFull> gravaPessoa(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Pessoa", required = true) @RequestBody(required = false) PessoaFull pessoa
    );

    @ApiOperation(value = "Cidade", response = Cidade.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Cidade.class)
    @RequestMapping(value = "/obtem-cidade", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Cidade> obtemCidade(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código da Cidade") @RequestParam(value = "codCidade", required = false) Long codCidade,
            @ApiParam(value = "Código IBGE") @RequestParam(value = "codIbge", required = false) String codIbge,
            @ApiParam(value = "Nome da Cidade") @RequestParam(value = "nomCidade", required = false) String nomCidade,
            @ApiParam(value = "UF") @RequestParam(value = "sglEstado", required = false) String sglEstado
    );

}
