package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.Almoxarifado;
import org.springframework.stereotype.Repository;

@Repository
public class AlmoxarifadoRepo extends DataRepository<Almoxarifado, Integer> {

    public AlmoxarifadoRepo() {
        super(Almoxarifado.class);
    }

}
