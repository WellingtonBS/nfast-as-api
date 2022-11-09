package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoEmpresaCad;
import br.com.nfast.api.model.estoque.ProdutoEmpresaIdCad;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoEmpresaCadRepo extends DataRepository<ProdutoEmpresaCad, ProdutoEmpresaIdCad> {

    public ProdutoEmpresaCadRepo() {
        super(ProdutoEmpresaCad.class);
    }

}
