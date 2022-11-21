package br.com.nfast.api.repo.financeiro;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.financeiro.EspecieCaixa;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EspecieCaixaRepo extends DataRepository<EspecieCaixa, Integer> {

    public EspecieCaixaRepo() {
        super(EspecieCaixa.class);
    }

    public Double getSaldoCaixa(Integer codEmpresa, Integer codEspecieCaixa) {
        Double vlrSaldo = nativeFindValue("SELECT sp_obtem_saldo_caixa(" + codEmpresa + ", " + codEspecieCaixa + ") ");
        return vlrSaldo;
    }

    public List<EspecieCaixa> especieCaixaList(String filtro, Integer limit, Integer offset) {

        List<EspecieCaixa> list = nativeFindAll(query -> {
            query.add("SELECT  ");
            query.add("  codigo AS cod_especie_caixa,");
            query.add("  normalize(nome) AS des_especie_caixa,");
            query.add("  'S' AS ind_status");
            query.add("FROM motivo_movto a ");
            query.add("WHERE codigo = 1 ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(codigo, '') LIKE :filtro) OR ");
                query.add("  (LOWER(normalize(nome)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY codigo ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

}
