package br.com.nfast.api.repo.adm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.adm.TipoDespesa;
import org.springframework.stereotype.Repository;

@Repository
public class TipoDespesaRepo extends DataRepository<TipoDespesa, Integer> {

    public TipoDespesaRepo() {
        super(TipoDespesa.class);
    }

}
