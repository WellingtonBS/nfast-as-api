package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.AjusteIcms;
import org.springframework.stereotype.Repository;

@Repository
public class AjusteIcmsRepo extends DataRepository<AjusteIcms, Integer> {

    public AjusteIcmsRepo() {
        super(AjusteIcms.class);
    }

}
