package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.TributacaoNfe;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

@Repository
public class TributacaoNfeRepo extends DataRepository<TributacaoNfe, Integer> {

    public TributacaoNfeRepo() {
        super(TributacaoNfe.class);
    }

    public TributacaoNfe getTributacaoNfe(Long codItem, Long codEmpresa, String natureza, String uf) {
        TributacaoNfe item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.cod_item, ");
            query.add("  a.cod_empresa, ");
            if (Strings.isAllNonEmpty(natureza, uf)) {
                query.add("  (SELECT x.cod_tributacao_entrada_" + Strings.evalDef(natureza, "pj", "F", "pf", "J", "pj"));
                query.add("   FROM tab_tributacao_classe_fiscal x ");
                query.add("   INNER JOIN tab_estado y ON(y.cod_estado = x.cod_estado) ");
                query.add("   WHERE x.cod_classe_fiscal = a.cod_classe_fiscal ");
                query.add("   AND y.sgl_estado = '" + uf + "' ");
                query.add("  ) as cod_icms, ");
            } else {
                query.add("  CAST(NULL as INTEGER) as cod_icms, ");
            }
            query.add("  a.cod_tributacao_ipi as cod_ipi, ");
            query.add("  a.cod_tributacao_pis_entrada as cod_pis, ");
            query.add("  a.cod_tributacao_cofins_entrada as cod_cofins ");
            query.add("FROM tab_item_empresa a ");
            query.add("WHERE a.cod_item = " + codItem);
            query.add("AND a.cod_empresa = " + codEmpresa);
        }, TributacaoNfe.class);
        return item;
    }

}
