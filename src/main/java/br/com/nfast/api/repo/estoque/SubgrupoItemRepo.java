package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.SubgrupoItem;
import org.springframework.stereotype.Repository;

@Repository
public class SubgrupoItemRepo extends DataRepository<SubgrupoItem, Integer> {

    public SubgrupoItemRepo() {
        super(SubgrupoItem.class);
    }

}
