package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.ClasseFiscal;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

@Repository
public class ClasseFiscalRepo extends DataRepository<ClasseFiscal, Integer> {

    public ClasseFiscalRepo() {
        super(ClasseFiscal.class);
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
