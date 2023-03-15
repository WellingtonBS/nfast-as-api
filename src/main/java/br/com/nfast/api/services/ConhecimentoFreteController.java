package br.com.nfast.api.services;

import br.com.nfast.api.model.nfe.ConhecimentoFreteResumo;
import br.com.nfast.api.repo.conhecimentofrete.ConhecimentoFreteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ConhecimentoFreteController implements ConhecimentoFreteApi {

    @Autowired
    private ConhecimentoFreteRepo conhecimentoFreteRepo;

    @Override
    public ResponseEntity<ConhecimentoFreteResumo> obtemConhecimentoFrete(String token, String clientId, String chave) {
        var resultado = conhecimentoFreteRepo.obtemConhecimentoFrete(chave);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
