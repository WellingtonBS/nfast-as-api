package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.Ncm;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NcmRepo extends DataRepository<Ncm, Integer> {

    public NcmRepo() {
        super(Ncm.class);
    }

    public Ncm ncm(Integer seqNcm) {
        Ncm item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  only_numbers(codigo) AS seq_ncm, ");
            query.add("  only_numbers(codigo) AS cod_ncm, ");
            query.add("  normali(descricao) AS des_ncm ");
            query.add("FROM ncm ");
            query.add("WHERE only_numbers(codigo) = '" + seqNcm + "' ");
            //query.set("seqNcm", seqNcm);
        });

        return item;

    }

    public List<Ncm> ncmList(String codNcm, String filtro, Integer limit, Integer offset) {
        List<Ncm> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  only_numbers(codigo) AS seq_ncm, ");
            query.add("  only_numbers(codigo) AS cod_ncm, ");
            query.add("  normali(descricao) AS des_ncm ");
            query.add("FROM ncm ");
            query.add("WHERE TRUE ");
            if (Strings.isNonEmpty(codNcm)) {
                query.add("AND CAST(only_numbers(codigo) AS TEXT) = :codNcm ");
                query.set("codNcm", codNcm);
            }
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (only_numbers(codigo) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY only_numbers(codigo) ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;

    }

}
