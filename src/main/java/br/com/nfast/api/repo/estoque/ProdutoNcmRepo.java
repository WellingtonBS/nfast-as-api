package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoNcm;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoNcmRepo extends DataRepository<ProdutoNcm, Long> {

    public ProdutoNcmRepo() {
        super(ProdutoNcm.class);
    }

}
