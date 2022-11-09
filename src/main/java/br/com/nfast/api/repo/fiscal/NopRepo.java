package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.Nop;
import org.springframework.stereotype.Repository;

@Repository
public class NopRepo extends DataRepository<Nop, Integer> {

    public NopRepo() {
        super(Nop.class);
    }

}
