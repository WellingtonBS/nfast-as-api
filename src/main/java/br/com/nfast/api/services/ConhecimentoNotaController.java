package br.com.nfast.api.services;

import br.com.nfast.api.repo.conhecimento.ConhecimentoNotaRepo;
import br.com.nfast.api.web.util.ApiSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ConhecimentoNotaController implements ConhecimentoNotaApi {

    @Autowired
    private ConhecimentoNotaRepo conhecimentoNotaRepo;

    @Override
    public ResponseEntity<ApiSuccess> gravaConhecimentoNota(String token, String clientId, Integer seqNota, Integer seqConhecimento, String indEntradaSaida) {
        conhecimentoNotaRepo.gravaConhecimentoNota(seqNota, seqConhecimento, indEntradaSaida);
        return new ResponseEntity<>(new ApiSuccess("Ok"), HttpStatus.OK);
    }
}
