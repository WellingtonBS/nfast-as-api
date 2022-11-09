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

            if (Numbers.isNonEmpty(codCidade)) {
                query.add("AND a.cod_cidade = :codCidade ");
                query.set("codCidade", codCidade);
            }

            if (Strings.isNonEmpty(codIbge)) {
                query.add("AND (c.cod_uf||c.cod_municipio) = :codIbge ");
                query.set("codIbge", codIbge);
            }

            if (Strings.isNonEmpty(nomCidade)) {
                query.add("AND a.nom_cidade ILIKE :nomCidade ");
                query.set("nomCidade", Strings.normalize(nomCidade));
            }

            if (Strings.isNonEmpty(sglEstado)) {
                query.add("AND b.sgl_estado ILIKE :sglEstado ");
                query.set("sglEstado", sglEstado);
            }

            query.add("ORDER BY a.nom_cidade ");
        });

        return item;
    }

    private void montaSqlCidade(QueryBuilder query) {
        query.clear();
        query.add("SELECT ");
        query.add("  a.cod_cidade, ");
        query.add("  a.nom_cidade, ");
        query.add("  b.sgl_estado, ");
        query.add("  (c.cod_uf||c.cod_municipio) as cod_ibge ");
        query.add("FROM tab_cidade a ");
        query.add("INNER JOIN tab_estado b ON(b.cod_estado = a.cod_estado) ");
        query.add("INNER JOIN tab_municipio_ibge c ON(c.seq_municipio_ibge = a.seq_municipio_ibge) ");
    }

}
