package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.Ncm;
import org.springframework.stereotype.Repository;

@Repository
public class NcmRepo extends DataRepository<Ncm, Integer> {

    public NcmRepo() {
        super(Ncm.class);
    }

}
