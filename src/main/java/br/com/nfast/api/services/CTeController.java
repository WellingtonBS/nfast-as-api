package br.com.nfast.api.services;

import br.com.nfast.api.model.cte.CTeResumo;
import br.com.nfast.api.repo.cte.CTeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CTeController implements CTeApi {
    @Autowired
    private CTeRepo cteRepo;

    @Override
    public ResponseEntity<List<CTeResumo>> listaChave(String token, String clientId, Integer codEmpresa, String chaves) {
        List<CTeResumo> list = cteRepo.listaChave(codEmpresa, chaves);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
