package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.config.jpa.QueryBuilder;
import br.com.nfast.api.model.crm.Cidade;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

@Repository
public class CidadeRepo extends DataRepository<Cidade, Integer> {

    public CidadeRepo() {
        super(Cidade.class);
    }

    public Cidade obtemCidade(Long codCidade, String codIbge, String nomCidade, String sglEstado) {
        Cidade item = nativeFind(query -> {
            montaSqlCidade(query);
            query.add("WHERE TRUE ");
            query.add("AND a.codigo IS NOT NULL ");
            query.add("AND normali(a.municipio) not in ('') ");
            //query.add("AND a.municipio not in ('') ");
            if (Numbers.isNonEmpty(codCidade)) {
                query.add("AND a.codigo = :codCidade ");
                query.set("codCidade", codCidade);
            }

            if (Strings.isNonEmpty(codIbge)) {
                query.add("AND a.codigo = :codIbge ");
                query.set("codIbge", codIbge);
            }

            if (Strings.isNonEmpty(nomCidade)) {
                query.add("AND normali(a.municipio) ILIKE :nomCidade ");
                //query.add("AND a.municipio ILIKE :nomCidade ");
                query.set("nomCidade", Strings.normali(nomCidade));
            }

            if (Strings.isNonEmpty(sglEstado)) {
                query.add("AND normali(a.uf) ILIKE :sglEstado ");
                //query.add("AND a.uf ILIKE :sglEstado ");
                query.set("sglEstado", sglEstado);
            }

            query.add("ORDER BY normali(a.municipio) ");
            //query.add("ORDER BY a.municipio ");
        });

        return item;
    }

    private void montaSqlCidade(QueryBuilder query) {
        query.clear();
        query.add("SELECT DISTINCT ");
        query.add("  CAST(a.codigo AS integer) AS cod_cidade, ");
        query.add("  normali(a.municipio) AS nom_cidade, ");
        query.add("  normali(a.uf) AS sgl_estado, ");
        //query.add("  a.municipio AS nom_cidade, ");
        //query.add("  a.uf AS sgl_estado, ");
        query.add("  CAST(a.codigo AS TEXT) AS cod_ibge ");
        query.add("FROM municipio_view a ");
    }

}
