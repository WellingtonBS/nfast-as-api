package br.com.nfast.api.repo.adm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.adm.CentroCusto;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CentroCustoRepo extends DataRepository<CentroCusto, Integer> {

    public CentroCustoRepo() {
        super(CentroCusto.class);
    }

    public CentroCusto centroCusto(Integer codCentroCusto) {
        CentroCusto item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.codigo as cod_centro_custo, ");
            query.add("  a.nome, ");
            query.add("  1 as cod_empresa, ");
            query.add("  CAST('2000-01-01' as DATE) as dta_inicio_validade, ");
            query.add("  CAST('2099-01-01' as DATE) as dta_fim_validade, ");
            query.add("  'A' as ind_tipo ");
            query.add("SELECT a ");
            query.add("FROM centro_custo a ");
            query.add("WHERE a.codCentroCusto = :codCentroCusto ");
            query.set("codCentroCusto", codCentroCusto);
        }, CentroCusto.class);
        return item;
    }

    public List<CentroCusto> centroCustoList(Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<CentroCusto> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo as cod_centro_custo, ");
            query.add("  a.nome, ");
            query.add("  1 as cod_empresa, ");
            query.add("  CAST('2000-01-01' as DATE) as dta_inicio_validade, ");
            query.add("  CAST('2099-01-01' as DATE) as dta_fim_validade, ");
            query.add("  'A' as ind_tipo ");
            query.add("SELECT a ");
            query.add("FROM centro_custo a ");
            query.add("WHERE a.flag = 'A' ");
            //if (Numbers.isNonEmpty(codEmpresa))
            //query.add("AND a.codEmpresa = " + codEmpresa + " ");/
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codigo, '') LIKE :filtro) OR ");
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
