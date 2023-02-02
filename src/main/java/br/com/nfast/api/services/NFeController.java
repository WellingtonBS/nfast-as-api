package br.com.nfast.api.services;

import br.com.nfast.api.model.nfe.CustoNFe;
import br.com.nfast.api.model.nfe.NFe;
import br.com.nfast.api.model.nfe.NFeResumo;
import br.com.nfast.api.repo.nfe.NFeRepo;
import br.com.nfast.api.repo.nfe.NFeResumoRepo;
import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.web.util.ApiSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class NFeController implements NFeApi {
    @Autowired
    private NFeRepo nfeRepo;
    @Autowired
    private NFeResumoRepo nfeResumoRepo;

    @Override
    public ResponseEntity<NFe> nfe(String token, String clientId, Long seqNota) {
        NFe item = nfeRepo.findById(seqNota).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NFeResumo> gravaNFe(String token, String clientId, NFe nfe) {
        NFeResumo item = nfeRepo.gravaNFe(nfe);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiSuccess> excluiNFe(String token, String clientId, String numChaveNfe, Integer codEmpresa) {
        nfeRepo.excluiNFe(numChaveNfe, codEmpresa);
        return new ResponseEntity<>(new ApiSuccess("Ok"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NFeResumo>> listaNFeUsuario(String token, String clientId, String dataIni, String dataFim, String codEmpresas, String nomUsuario) {
        LocalDate ini = Dates.toDate(dataIni, "dd-MM-yyyy");
        if (ini == null)
            throw new RuntimeException("Data inicial inválida");
        LocalDate fim = Dates.toDate(dataFim, "dd-MM-yyyy");
        if (fim == null)
            throw new RuntimeException("Data final inválida");

        List<NFeResumo> list = nfeResumoRepo.listagem(ini, fim, codEmpresas, nomUsuario);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NFeResumo>> listaNFeChave(String token, String clientId, Integer codEmpresa, String chaves) {
        List<NFeResumo> list = nfeResumoRepo.listaChave(codEmpresa, chaves);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustoNFe>> auditoria(String token, String clientId, String dataIni, String dataFim, String codEmpresas, Integer codFornecedor, Double perVariacaoUp, Double perVariacaoDown, String custoAntZero, String filtroProduto) {
        LocalDate ini = Dates.toDate(dataIni, "dd-MM-yyyy");
        if (ini == null)
            throw new RuntimeException("Data inicial inválida");
        LocalDate fim = Dates.toDate(dataFim, "dd-MM-yyyy");
        if (fim == null)
            throw new RuntimeException("Data final inválida");

        List<CustoNFe> list = nfeResumoRepo.auditoria(ini, fim, codEmpresas, codFornecedor, perVariacaoUp, perVariacaoDown, custoAntZero, filtroProduto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
