package br.com.nfast.api.repo.financeiro;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.financeiro.TipoCobranca;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoCobrancaRepo extends DataRepository<TipoCobranca, Integer> {

    public TipoCobrancaRepo() {
        super(TipoCobranca.class);
    }

    public TipoCobranca tipoCobranca(Integer codTipoCobranca) {
        TipoCobranca item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  codigo AS cod_tipo_cobranca, ");
            query.add("  normalize(nome) as des_tipo_cobranca, ");
            query.add("  CASE WHEN flag='A' THEN 'S' ELSE 'N' END AS ind_status ");
            query.add("FROM motivo_movto ");
            query.add("WHERE flag = 'A' ");
            query.add("AND codigo = :codTipoCobranca");
            query.set("codTipoCobranca", codTipoCobranca);
        }, TipoCobranca.class);

        return item;
    }

    public List<TipoCobranca> tipoCobrancaList(Long codEmpresa, String filtro, Integer limit, Integer offset) {
        List<TipoCobranca> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  codigo AS cod_tipo_cobranca, ");
            query.add("  normalize(nome) as des_tipo_cobranca, ");
            query.add("  CASE WHEN flag='A' THEN 'S' ELSE 'N' END AS ind_status ");
            query.add("FROM motivo_movto ");
            query.add("WHERE flag = 'A' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(codigo, '') LIKE :filtro) OR ");
                query.add("  (LOWER(normalize(nome) ) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY normalize(nome) ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }
}