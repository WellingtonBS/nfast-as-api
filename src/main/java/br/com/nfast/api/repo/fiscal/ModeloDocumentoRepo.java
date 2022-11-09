package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.ModeloDocumento;
import org.springframework.stereotype.Repository;

@Repository
public class ModeloDocumentoRepo extends DataRepository<ModeloDocumento, Integer> {

    public ModeloDocumentoRepo() {
        super(ModeloDocumento.class);
    }

}
