package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.adm.TipoDespesa;
import br.com.nfast.api.model.estoque.SubgrupoItem;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubgrupoItemRepo extends DataRepository<SubgrupoItem, Integer> {

    public SubgrupoItemRepo() {
        super(SubgrupoItem.class);
    }

    public SubgrupoItem subGrupoItem(Long codSubgrupoItem) {
        SubgrupoItem item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.grid AS cod_subgrupo_item,  ");
            query.add("  normalize(a.nome) AS des_subgrupo_item ");
            query.add("FROM subgrupo_produto a ");
            query.add("WHERE TRUE = 't' ");
            query.add("AND a.grid = :codSubgrupoItem");
            query.set("codSubgrupoItem", codSubgrupoItem);
        }, SubgrupoItem.class);
        return item;

    }

    public List<SubgrupoItem> subgrupoItemList(String filtro, Integer limit, Integer offset) {
        List<SubgrupoItem> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.grid AS cod_subgrupo_item,  ");
            query.add("  normalize(a.nome) AS des_subgrupo_item ");
            query.add("FROM subgrupo_produto a ");
            query.add("WHERE TRUE = 't' ");

            if (Strings.isNonEmpty(filtro)) {
                query.add("WHERE ( ");
                query.add("  (CONCAT(a.grid, '') LIKE :filtro) OR ");
                query.add("  (LOWER(normalize(a.nome)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY normalize(a.nome)");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

}
