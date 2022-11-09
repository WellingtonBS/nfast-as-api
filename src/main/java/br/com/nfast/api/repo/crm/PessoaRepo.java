package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.crm.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepo extends DataRepository<Pessoa, Integer> {

    public PessoaRepo() {
        super(Pessoa.class);
    }

}
