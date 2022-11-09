package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.crm.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepo extends DataRepository<Item, Integer> {

    public ItemRepo() {
        super(Item.class);
    }

}
