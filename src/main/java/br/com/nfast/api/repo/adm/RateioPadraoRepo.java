package br.com.nfast.api.repo.adm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.adm.RateioPadrao;
import org.springframework.stereotype.Repository;

@Repository
public class RateioPadraoRepo extends DataRepository<RateioPadrao, Integer> {

    public RateioPadraoRepo() {
        super(RateioPadrao.class);
    }

}
