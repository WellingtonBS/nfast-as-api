package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.AjusteIcms;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AjusteIcmsRepo extends DataRepository<AjusteIcms, Integer> {

    public AjusteIcmsRepo() {
        super(AjusteIcms.class);
    }

    public AjusteIcms ajusteIcms(Integer seqAjApurIcms) {
        AjusteIcms ajusteIcms = nativeFind(query -> {
            query.add("SELECT DISTINCT a.seq_aj_apur_icms, ");
            query.add("       b.codigo AS cod_icms, ");
            query.add("       '' AS ind_tipo, ");
            query.add("       '' AS ind_tipo_ajuste, ");
            query.add("       normali(b.nome) AS des_icms ");
            query.add("FROM nfast_ajuste_icms a ");
            query.add("INNER JOIN sped_codigo_ajuste b ON (a.codigo = b.codigo AND a.tabela = 'sped_codigo_ajuste'), ");
            query.add("           empresa d ");
            query.add("WHERE b.codigo ILIKE ''|| d.estado || '%' ");
            query.add("AND seq_aj_apur_icms = " + seqAjApurIcms + "' ");
            query.add("UNION ALL ");
            query.add("SELECT DISTINCT a.seq_aj_apur_icms, ");
            query.add("       c.codigo AS cod_icms, ");
            query.add("       '' AS ind_tipo, ");
            query.add("       '' AS ind_tipo_ajuste, ");
            query.add("       normali(c.descricao) AS des_icms ");
            query.add("FROM nfast_ajuste_icms a ");
            query.add("INNER JOIN sped_codigo_ajuste_e111 c ON (a.codigo = c.codigo AND a.tabela = 'sped_codigo_ajuste_e111'), ");
            query.add("           empresa d ");
            query.add("WHERE a.codigo ILIKE ''|| d.estado || '%' ");
            query.add("AND seq_aj_apur_icms = " + seqAjApurIcms + "' ");
        });

        return ajusteIcms;

    }

    public List<AjusteIcms> ajusteIcmsList(String filtro, Integer limit, Integer offset) {
        List<AjusteIcms> list = nativeFindAll(query -> {
            query.add("SELECT DISTINCT a.seq_aj_apur_icms, ");
            query.add("       b.codigo AS cod_icms, ");
            query.add("       '' AS ind_tipo, ");
            query.add("       '' AS ind_tipo_ajuste, ");
            query.add("       normali(b.nome) AS des_icms ");
            query.add("FROM nfast_ajuste_icms a ");
            query.add("INNER JOIN sped_codigo_ajuste b ON (a.codigo = b.codigo AND a.tabela = 'sped_codigo_ajuste'), ");
            query.add("           empresa d ");
            query.add("WHERE b.codigo ILIKE ''|| d.estado || '%' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(b.codigo) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(b.nome)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);

            query.add("UNION ALL ");
            query.add("SELECT DISTINCT a.seq_aj_apur_icms, ");
            query.add("       c.codigo AS cod_icms, ");
            query.add("       '' AS ind_tipo, ");
            query.add("       '' AS ind_tipo_ajuste, ");
            query.add("       normali(c.descricao) AS des_icms ");
            query.add("FROM nfast_ajuste_icms a ");
            query.add("INNER JOIN sped_codigo_ajuste_e111 c ON (a.codigo = c.codigo AND a.tabela = 'sped_codigo_ajuste_e111'), ");
            query.add("           empresa d ");
            query.add("WHERE a.codigo ILIKE ''|| d.estado || '%' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(c.codigo) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(c.descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY 2,4 ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);

        });

        return list;

    }

}
