package br.com.nfast.api.services;

import br.com.nfast.api.model.fiscal.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "/api/fiscal")
@RequestMapping("/api/fiscal")
public interface FiscalApi {

    @ApiOperation(value = "Natureza de Operação", response = Nop.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Nop.class)
    @RequestMapping(value = "/nop", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Nop> nop(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codNaturezaOperacao") Integer codNaturezaOperacao
    );

    @ApiOperation(value = "Naturezas de Operação", response = Nop.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Nop.class, responseContainer = "List")
    @RequestMapping(value = "/nop-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Nop>> nopList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Entrada / Saída") @RequestParam(value = "indEntradaSaida", required = false) String indEntradaSaida,
            @ApiParam(value = "Frete") @RequestParam(value = "indNopFrete", required = false) String indNopFrete,
            @ApiParam(value = "Ativo Imobilizado") @RequestParam(value = "indAtivoImobilizado", required = false) String indAtivoImobilizado,
            @ApiParam(value = "Origem / Destino") @RequestParam(value = "indOrigemDestino", required = false) String indOrigemDestino,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Tributação", response = Tributacao.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Tributacao.class)
    @RequestMapping(value = "/tributacao", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Tributacao> tributacao(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codTributacao") Integer codTributacao
    );

    @ApiOperation(value = "Tributações", response = Tributacao.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Tributacao.class, responseContainer = "List")
    @RequestMapping(value = "/tributacao-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Tributacao>> tributacaoList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Entrada / Saída") @RequestParam(value = "indEntradaSaida", required = false) String indEntradaSaida,
            @ApiParam(value = "Tipo de Tributo") @RequestParam(value = "indTipoTributo", required = false) String indTipoTributo,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Tributação NF-e", response = TributacaoNfe.class)
    @ApiResponse(code = 200, message = "Sucesso", response = TributacaoNfe.class)
    @RequestMapping(value = "/tributacao-nfe", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<TributacaoNfe> tributacaoNfe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Long codItem,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Long codEmpresa,
            @ApiParam(value = "Natureza") @RequestParam(value = "natureza", required = false) String natureza,
            @ApiParam(value = "UF") @RequestParam(value = "uf", required = false) String uf
    );

    @ApiOperation(value = "ICMS NF-e", response = Integer.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Integer.class)
    @RequestMapping(value = "/icms-nfe", method = RequestMethod.GET, produces = "text/plain")
    ResponseEntity<String> icmsNfe(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Long codItem,
            @ApiParam(value = "Empresa", required = true) @RequestParam("codEmpresa") Long codEmpresa,
            @ApiParam(value = "Natureza", required = true) @RequestParam(value = "natureza") String natureza,
            @ApiParam(value = "UF", required = true) @RequestParam(value = "uf") String uf,
            @ApiParam(value = "CST", required = true) @RequestParam(value = "cst") String cst,
            @ApiParam(value = "% Aliquota", required = true) @RequestParam(value = "perAliquota") Double perAliquota,
            @ApiParam(value = "% Reducao BC", required = true) @RequestParam(value = "perReducaoBc") Double perReducaoBc,
            @ApiParam(value = "Verifica Classe ICMS", required = true) @RequestParam(value = "verificaClasseIcms") String verificaClasseIcms
    );

    @ApiOperation(value = "Modelo de Documento", response = ModeloDocumento.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ModeloDocumento.class)
    @RequestMapping(value = "/modelo-documento", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ModeloDocumento> modeloDocumento(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codModeloDocumento") Integer codModeloDocumento
    );

    @ApiOperation(value = "Modelos de Documento", response = ModeloDocumento.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = ModeloDocumento.class, responseContainer = "List")
    @RequestMapping(value = "/modelo-documento-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ModeloDocumento>> modeloDocumentoList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Empresa") @RequestParam(value = "codEmpresa", required = false) Long codEmpresa,
            @ApiParam(value = "Tipo Documento") @RequestParam(value = "codModeloDocAnexo7", required = false) String codModeloDocAnexo7,
            @ApiParam(value = "Entrada") @RequestParam(value = "indEntrada", required = false) String indEntrada,
            @ApiParam(value = "Saída") @RequestParam(value = "indSaida", required = false) String indSaida,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Ajuste ICMS", response = AjusteIcms.class)
    @ApiResponse(code = 200, message = "Sucesso", response = AjusteIcms.class)
    @RequestMapping(value = "/ajuste-icms", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<AjusteIcms> ajusteIcms(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sequência", required = true) @RequestParam("seqAjApurIcms") Integer seqAjApurIcms
    );

    @ApiOperation(value = "Ajustes ICMS", response = AjusteIcms.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = AjusteIcms.class, responseContainer = "List")
    @RequestMapping(value = "/ajuste-icms-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<AjusteIcms>> ajusteIcmsList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "NCM", response = Ncm.class)
    @ApiResponse(code = 200, message = "Sucesso", response = Ncm.class)
    @RequestMapping(value = "/ncm", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Ncm> ncm(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Sequência", required = true) @RequestParam("seqNcm") Integer seqNcm
    );

    @ApiOperation(value = "Modelos de Documento", response = Ncm.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = Ncm.class, responseContainer = "List")
    @RequestMapping(value = "/ncm-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<Ncm>> ncmList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código") @RequestParam(value = "codNcm", required = false) String codNcm,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "NCM ST", response = NcmSt.class)
    @ApiResponse(code = 200, message = "Sucesso", response = NcmSt.class)
    @RequestMapping(value = "/ncm-st", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<NcmSt> ncmSt(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Produto", required = true) @RequestParam("codItem") Long codItem,
            @ApiParam(value = "UF", required = true) @RequestParam(value = "uf") String uf,
            @ApiParam(value = "Data", required = true) @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    );

    @ApiOperation(value = "Classe Fiscal", response = ClasseFiscal.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ClasseFiscal.class)
    @RequestMapping(value = "/classe-fiscal", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ClasseFiscal> classeFiscal(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código", required = true) @RequestParam("codClasseFiscal") Integer codClasseFiscal
    );

    @ApiOperation(value = "Classes Fiscais", response = ClasseFiscal.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "Sucesso", response = ClasseFiscal.class, responseContainer = "List")
    @RequestMapping(value = "/classe-fiscal-list", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<List<ClasseFiscal>> classeFiscalList(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Filtro") @RequestParam(value = "filtro", required = false) String filtro,
            @ApiParam(value = "Limit", defaultValue = "50") @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Offset") @RequestParam(value = "offset", required = false) Integer offset
    );

    @ApiOperation(value = "Classe Fiscal", response = ClasseFiscal.class)
    @ApiResponse(code = 200, message = "Sucesso", response = ClasseFiscal.class)
    @RequestMapping(value = "/classe-fiscal-icms", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<ClasseFiscal> classeFiscalIcms(
            @ApiParam(value = "Access Token", required = true) @RequestHeader("token") String token,
            @ApiParam(value = "Client ID", required = true) @RequestHeader("clientId") String clientId,
            @ApiParam(value = "Código da Tributação") @RequestParam(value = "codTributacao", required = false) Integer codTributacao,
            @ApiParam(value = "UF do Emitente") @RequestParam(value = "sglEstado", required = false) String sglEstado,
            @ApiParam(value = "Natureza do Emitente") @RequestParam(value = "indNatureza", required = false) String indNatureza
    );

}
