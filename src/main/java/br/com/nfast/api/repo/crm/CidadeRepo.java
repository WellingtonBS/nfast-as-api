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
            query.add("AND normalize(a.municipio) not in ('') ");
            if (Numbers.isNonEmpty(codCidade)) {
                query.add("AND a.codigo = :codCidade ");
                query.set("codCidade", codCidade);
            }

            if (Strings.isNonEmpty(codIbge)) {
                query.add("AND a.codigo = :codIbge ");
                query.set("codIbge", codIbge);
            }

            if (Strings.isNonEmpty(nomCidade)) {
                query.add("AND normalize(a.municipio) ILIKE :nomCidade ");
                query.set("nomCidade", Strings.normalize(nomCidade));
            }

            if (Strings.isNonEmpty(sglEstado)) {
                query.add("AND normalize(a.uf) ILIKE :sglEstado ");
                query.set("sglEstado", sglEstado);
            }

            query.add("ORDER BY normalize(a.municipio) ");
        });

        return item;
    }

    private void montaSqlCidade(QueryBuilder query) {
        query.clear();
        query.add("SELECT DISTINCT ");
        query.add("  a.codigo as cod_cidade, ");
        query.add("  normalize(a.municipio) as nom_cidade, ");
        query.add("  normalize(a.uf) as sgl_estado, ");
        query.add("  a.codigo as cod_ibge ");
        query.add("FROM municipio_view a ");
    }

}
