package br.com.nfast.api.repo.cte;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.cte.CTeResumo;
import br.com.nfast.api.utils.Cast;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.StringList;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CTeRepo extends DataRepository<CTeResumo, Integer> {

    public CTeRepo() {
        super(CTeResumo.class);
    }

    public List<CTeResumo> listaChave(Integer codEmpresa, String chaves) {
        StringBuilder items = new StringBuilder();
        for (String chave : Strings.split(chaves, ",")) {
            if (items.length() > 0)
                items.append(",");
            items.append("'").append(chave).append("'");
        }

        StringList sql = new StringList();
        sql.add("SELECT ");
        sql.add("  a.seq_nota as seq_conhecimento, ");
        sql.add("  b.cod_empresa, ");
        sql.add("  a.num_chave_nfe as num_chave, ");
        sql.add("  'tab_conhecimento_frete_nfe' as nom_tabela ");
        sql.add("FROM tab_conhecimento_frete_nfe a ");
        sql.add("INNER JOIN tab_nota_fiscal_entrada b ON(b.seq_nota = a.seq_nota) ");
        sql.add("WHERE a.num_chave_nfe IN(" + items.toString() + ") ");
        if (Numbers.isNonEmpty(codEmpresa))
            sql.add("AND b.cod_empresa = " + codEmpresa + " ");
        sql.add("UNION ALL ");
        sql.add("SELECT ");
        sql.add("  a.seq_conhecimento, ");
        sql.add("  a.cod_empresa, ");
        sql.add("  a.num_chave_nfe as num_chave, ");
        sql.add("  'tab_conhecimento' as nom_tabela ");
        sql.add("FROM tab_conhecimento a ");
        sql.add("WHERE a.num_chave_nfe IN(" + items.toString() + ") ");
        if (Numbers.isNonEmpty(codEmpresa))
            sql.add("AND a.cod_empresa = " + codEmpresa + " ");
        sql.add("ORDER BY 1 ");

        Query query = em.createNativeQuery(sql.toString(), CTeResumo.class);
        return Cast.of(query.getResultList());
    }

}
