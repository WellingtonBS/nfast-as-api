package br.com.nfast.api.repo.conhecimentofrete;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.nfe.ConhecimentoFreteResumo;
import org.springframework.stereotype.Repository;

@Repository
public class ConhecimentoFreteRepo extends DataRepository<ConhecimentoFreteResumo, Long> {

    public ConhecimentoFreteRepo() {
        super(ConhecimentoFreteResumo.class);
    }

    public ConhecimentoFreteResumo obtemConhecimentoFrete(String chave) {
        return null;
        //return findBy("numChaveNfe", chave);
    }

}
