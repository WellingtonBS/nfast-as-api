package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.Nop;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NopRepo extends DataRepository<Nop, Integer> {

    public NopRepo() {
        super(Nop.class);
    }

    public Nop nop(Integer codNaturezaOperacao) {
        Nop item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  only_numbers(a.codigo) AS cod_natureza_operacao, ");
            query.add("  normali(a.nome) AS des_natureza_operacao, ");
            query.add("  only_numbers(a.codigo) AS num_cfop, ");
            query.add("  CASE WHEN CAST(only_numbers(a.codigo) AS INTEGER) > 5000 THEN 'S' ELSE 'E' END AS ind_entrada_saida, ");
            query.add("  CASE WHEN only_numbers(a.codigo) NOT IN ('1556','2556') THEN 'S' ELSE 'N' END AS ind_gera_estoque, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1556','2556') THEN 'S' ELSE 'N' END AS ind_gera_despesa, ");
            query.add("  'S' AS ind_gera_financeiro, ");
            query.add("  CASE WHEN only_numbers(a.codigo) ILIKE '5%' THEN 'D' ");
            query.add("       WHEN only_numbers(a.codigo) ILIKE '1%' THEN 'D' ");
            query.add("       ELSE 'F' END AS ind_origem_destino, ");
            query.add("  'N' AS ind_ativo_imobilizado, ");
            query.add("  'N' AS ind_entrega_futura, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1353','2353') THEN 'S' ELSE 'N' END AS ind_nop_frete, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1409','2409','5409','6409') THEN 'S' ELSE 'N' END AS ind_transferencia, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1910','2910','5910','6910') THEN 'S' ELSE 'N' END AS ind_bonificacao, ");
            query.add("  64 AS cod_tipo_movimento_estoque, ");
            query.add("  'S' AS ind_desativada ");
            query.add("FROM cfop a ");
            query.add("WHERE only_numbers(a.codigo) = '" + codNaturezaOperacao + "' ");
            query.add("ORDER BY only_numbers(a.codigo), normali(a.nome) ");
            //query.add("ORDER BY only_numbers(a.codigo), a.nome ");
        });

        return item;
    }

    public List<Nop> nopList(String indEntradaSaida, String indNopFrete, String indAtivoImobilizado, String indOrigemDestino, String filtro, Integer limit, Integer offset) {
        List<Nop> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  only_numbers(a.codigo) AS cod_natureza_operacao, ");
            query.add("  normali(a.nome) AS des_natureza_operacao, ");
            query.add("  only_numbers(a.codigo) AS num_cfop, ");
            query.add("  CASE WHEN CAST(only_numbers(a.codigo) AS INTEGER) > 5000 THEN 'S' ELSE 'E' END AS ind_entrada_saida, ");
            query.add("  CASE WHEN only_numbers(a.codigo) NOT IN ('1556','2556') THEN 'S' ELSE 'N' END AS ind_gera_estoque, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1556','2556') THEN 'S' ELSE 'N' END AS ind_gera_despesa, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1910','2910','5910','6910') THEN 'N' ELSE 'S' END  AS ind_gera_financeiro, ");
            query.add("  CASE WHEN only_numbers(a.codigo) ILIKE '5%' THEN 'D' ");
            query.add("       WHEN only_numbers(a.codigo) ILIKE '1%' THEN 'D' ");
            query.add("       ELSE 'F' END AS ind_origem_destino, ");
            query.add("  'N' AS ind_ativo_imobilizado, ");
            query.add("  'N' AS ind_entrega_futura, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1353','2353') THEN 'S' ELSE 'N' END AS ind_nop_frete, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1409','2409','5409','6409') THEN 'S' ELSE 'N' END AS ind_transferencia, ");
            query.add("  CASE WHEN only_numbers(a.codigo) IN ('1910','2910','5910','6910') THEN 'S' ELSE 'N' END AS ind_bonificacao, ");
            query.add("  64 AS cod_tipo_movimento_estoque, ");
            query.add("  'S' AS ind_desativada ");
            query.add("FROM cfop a ");
            query.add("WHERE TRUE ");
            if (Strings.isNonEmpty(indEntradaSaida))
                query.add("AND CASE WHEN CAST(only_numbers(a.codigo) AS INTEGER) > 5000 THEN 'S' ELSE 'E' END = '" + indEntradaSaida + "' ");
            if (Strings.isNonEmpty(indNopFrete))
                query.add("AND CASE WHEN only_numbers(a.codigo) IN ('1353','2353') THEN 'S' ELSE 'N' END = '" + indNopFrete + "' ");
            if (Strings.isNonEmpty(indAtivoImobilizado))
                query.add("AND 'N' = '" + indAtivoImobilizado + "' ");
            if (Strings.isNonEmpty(indOrigemDestino)) {
                query.add("AND CASE WHEN only_numbers(a.codigo) ILIKE '5%' THEN 'D' ");
                query.add("         WHEN only_numbers(a.codigo) ILIKE '1%' THEN 'D' ");
                query.add("         ELSE 'F' END = '" + indOrigemDestino + "' ");
            }
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(only_numbers(a.codigo)) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(a.nome)) LIKE :filtro) ");
                //query.add("  (LOWER(a.nome) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY only_numbers(a.codigo), normali(a.nome) ");
            //query.add("ORDER BY only_numbers(a.codigo), a.nome ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

}
