package br.com.nfast.api.services;

import br.com.nfast.api.dto.ConhecimentoDTO;
import br.com.nfast.api.model.conhecimento.Conhecimento;
import br.com.nfast.api.repo.conhecimento.ConhecimentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ConhecimentoController implements ConhecimentoApi {

    @Autowired
    private ConhecimentoRepo conhecimentoRepo;

    @Override
    public ResponseEntity<Conhecimento> obtemConhecimento(String token, String clientId, String chave) {
        var conhecimento = conhecimentoRepo.obtemConhecimento(chave);
        return new ResponseEntity<>(conhecimento, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Conhecimento> gravarConhecimento(String token, String clientId, ConhecimentoDTO conhecimento) {
        var conhecimentoResponse = conhecimentoRepo.gravarConhecimento(conhecimento);
        return new ResponseEntity<>(conhecimentoResponse, HttpStatus.OK);
    }
}
