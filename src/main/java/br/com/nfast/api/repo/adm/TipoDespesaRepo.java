package br.com.nfast.api.repo.adm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.adm.TipoDespesa;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class TipoDespesaRepo extends DataRepository<TipoDespesa, Integer> {

    public TipoDespesaRepo() {
        super(TipoDespesa.class);
    }

    public TipoDespesa tipoDespesa (Long codTipoDespesa) {
        TipoDespesa item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.grid AS cod_tipo_despesa,  ");
            query.add("  a.nome AS des_tipo_despesa, ");
            query.add("  CAST('A' AS CHAR(1)) AS ind_tipo_despesa, ");
            query.add("  CAST('A' AS CHAR(1)) AS ind_tipo, ");
            query.add("  CASE WHEN a.lancar = 't' THEN 'S' ELSE 'N' END AS ind_status  ");
            query.add("FROM conta a ");
            query.add("WHERE tipo_despesa = 't' ");
            query.add("AND credor = 'f' ");
            query.add("AND a.grid = :codTipoDespesa");
            query.set("codTipoDespesa", codTipoDespesa);
        }, TipoDespesa.class);
        return item;

    }

    public List<TipoDespesa> tipoDespesaList(String indTipo, String filtro, Integer limit, Integer offset) {
        List<TipoDespesa> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.grid AS cod_tipo_despesa,  ");
            query.add("  a.nome AS des_tipo_despesa, ");
            query.add("  CAST('A' AS CHAR(1)) AS ind_tipo_despesa, ");
            query.add("  CAST('A' AS CHAR(1)) AS ind_tipo, ");
            query.add("  CASE WHEN a.lancar = 't' THEN 'S' ELSE 'N' END AS ind_status  ");
            query.add("FROM conta a ");
            query.add("WHERE tipo_despesa = 't' ");
            query.add("AND credor = 'f' ");

            /*if (Numbers.isNonEmpty(indTipo))
            query.add("AND a.indTipo = " + indTipo + " ");*/

            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.grid, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.nome) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.nome ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }
}
