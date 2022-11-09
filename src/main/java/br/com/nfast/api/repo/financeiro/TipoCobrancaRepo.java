package br.com.nfast.api.repo.financeiro;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.financeiro.TipoCobranca;
import org.springframework.stereotype.Repository;

@Repository
public class TipoCobrancaRepo extends DataRepository<TipoCobranca, Integer> {

    public TipoCobrancaRepo() {
        super(TipoCobranca.class);
    }

}
