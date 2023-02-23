package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.ClasseFiscal;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClasseFiscalRepo extends DataRepository<ClasseFiscal, Integer> {

    public ClasseFiscalRepo() {
        super(ClasseFiscal.class);
    }

    public ClasseFiscal classeFiscal(Integer codClasseFiscal) {
        ClasseFiscal item = nativeFind(query -> {
            query.add("SELECT b.cod_tributacao AS cod_classe_fiscal, ");
            query.add(" normali(a.descricao) AS des_classe_fiscal ");
            query.add("FROM tributacao a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE b.tipo = 'ICMS' ");
            query.add("AND b.cod_tributacao = '" + codClasseFiscal + "' ");
            query.set("codClasseFiscal", codClasseFiscal);
        });
        return item;

    }

    public List<ClasseFiscal> classeFiscalList(String filtro, Integer limit, Integer offset) {
        List<ClasseFiscal> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  b.cod_tributacao AS cod_classe_fiscal, ");
            query.add("  normali(a.descricao) AS des_classe_fiscal ");
            query.add("FROM tributacao a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE TRUE ");
            query.add("AND b.tipo = 'ICMS' ");

            if (Strings.isNonEmpty(filtro)) {
                query.add("WHERE ( ");
                query.add("  (CONCAT(b.cod_tributacao, '') LIKE :filtro) OR ");
                query.add("  (LOWER(normali(a.descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY b.cod_tributacao ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;

    }


    public Integer classeFiscalIcms(Integer codTributacao, String sglEstado, String indNatureza) {
        Integer codClasseFiscal = nativeFindValue(query -> {
            query.add("SELECT a.cod_classe_fiscal ");
            query.add("FROM tab_tributacao_classe_fiscal a ");
            query.add("INNER JOIN tab_estado b ON(b.cod_estado = a.cod_estado) ");
            query.add("WHERE a.cod_tributacao_entrada_" + Strings.evalDef(indNatureza, "pj", "F", "pf", "J", "pj") + " = " + codTributacao);
            query.add("AND b.sgl_estado = '" + sglEstado + "' ");
        });
        return codClasseFiscal;
    }

}
