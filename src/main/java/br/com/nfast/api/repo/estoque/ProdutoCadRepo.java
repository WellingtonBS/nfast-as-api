package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoCad;
import br.com.nfast.api.model.estoque.ProdutoEmpresaCad;
import br.com.nfast.api.model.estoque.ProdutoEmpresaIdCad;
import br.com.nfast.api.utils.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoCadRepo extends DataRepository<ProdutoCad, Integer> {
    @Autowired
    ProdutoRepo produtoRepo;
    @Autowired
    ProdutoEmpresaCadRepo produtoEmpresaRepo;

    public ProdutoCadRepo() {
        super(ProdutoCad.class);
    }

    public ProdutoCad gravaProduto(ProdutoCad produto) {
        produto.setCodItem(nativeFindValue("SELECT COALESCE(MAX(cod_item), 0) + 1 FROM tab_item "));
        for (ProdutoEmpresaCad pe : produto.getEmpresas()) {
            pe.setCodItem(produto.getCodItem());

            pe.setId(new ProdutoEmpresaIdCad());
            pe.getId().setCodItem(pe.getCodItem());
            pe.getId().setCodEmpresa(pe.getCodEmpresa());
        }

        ProdutoCad result = save(produto);

        for (ProdutoEmpresaCad pe : produto.getEmpresas()) {
            produtoEmpresaRepo.save(pe);
        }

        if (Numbers.isNonEmpty(produto.getCodPessoaFornecedor())) {
            produtoRepo.vinculaProdutoFor(produto.getCodItem(), produto.getCodPessoaFornecedor(), produto.getCodReferencia(), null, 0.0);
        }

        return result;
    }

}
