package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.Unidade;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

@Repository
public class UnidadeRepo extends DataRepository<Unidade, Integer> {

    public UnidadeRepo() {
        super(Unidade.class);
    }

    public Integer getUnidadeNfe(String sglUnidade, Integer codItem, String codItemFornecedor, Integer codFornecedor) {
        Integer codUnidade = null;
        if (Strings.isNonEmpty(codItemFornecedor) && Numbers.isNonEmpty(codFornecedor)) {
            codUnidade = nativeFindValue(query -> {
                query.add("SELECT a.cod_unidade_agrupamento ");
                query.add("FROM tab_item_fornecedor a ");
                query.add("INNER JOIN tab_unidade b ON(b.cod_unidade = a.cod_unidade_agrupamento) ");
                query.add("WHERE a.cod_pessoa_fornecedor = " + codFornecedor + " ");
                query.add("AND a.cod_item_fornecedor = '" + codItemFornecedor + "' ");
                query.add("LIMIT 1 ");
            });
        }

        if (Numbers.isEmpty(codUnidade)) {
            codUnidade = nativeFindValue("SELECT a.cod_unidade FROM tab_unidade a WHERE a.sgl_unidade = '" + sglUnidade + "'");
            if (Numbers.isEmpty(codUnidade) && Numbers.isNonEmpty(codItem))
                codUnidade = nativeFindValue("SELECT a.cod_unidade FROM tab_item a WHERE a.cod_item = " + codItem);
        }

        return codUnidade;
    }

}
