package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.Unidade;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnidadeRepo extends DataRepository<Unidade, Integer> {

    public UnidadeRepo() {
        super(Unidade.class);
    }

    public Unidade unidade(Integer codUnidade) {
        Unidade item = nativeFind(query -> {
            query.add("SELECT  ");
            query.add("  b.cod_unidade AS cod_unidade, ");
            query.add("  a.descricao AS des_unidade, ");
            query.add("  a.codigo AS sgl_unidade, ");
            query.add("  1 AS num_fator_conversao ");
            query.add("FROM unidade_medida a ");
            query.add("INNER JOIN nfast_unidade b ON (a.codigo = b.sgl_unidade) ");
            query.add("WHERE b.cod_unidade = " + codUnidade);
            query.add("ORDER BY a.descricao");
        }, Unidade.class);
        return item;
    }

    public List<Unidade> unidadeList(String sglUnidade, String filtro, Integer limit, Integer offset) {
        List<Unidade> list = nativeFindAll(query -> {
            query.add("SELECT  ");
            query.add("  b.cod_unidade AS cod_unidade, ");
            query.add("  a.descricao AS des_unidade, ");
            query.add("  a.codigo AS sgl_unidade, ");
            query.add("  1 AS num_fator_conversao ");
            query.add("FROM unidade_medida a ");
            query.add("INNER JOIN nfast_unidade b ON (a.codigo = b.sgl_unidade) ");
            query.add("WHERE TRUE ");
            if (Strings.isNonEmpty(sglUnidade)) {
                query.add("AND LOWER(a.codigo) = :sglUnidade ");
                query.set("sglUnidade", sglUnidade.toLowerCase());
            }
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(a.descricao) LIKE :filtro) OR ");
                query.add("  (LOWER(a.codigo) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.descricao");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }

    public Integer getUnidadeNfe(String sglUnidade, Integer codItem, String codItemFornecedor, Integer codFornecedor) {
        Integer codUnidade = null;


        if (Strings.isNonEmpty(codItemFornecedor) && Numbers.isNonEmpty(codFornecedor)) {
            codUnidade = nativeFindValue(query -> {
                query.add("SELECT c.cod_unidade AS cod_unidade_agrupamento ");
                query.add("FROM produto a ");
                query.add("INNER JOIN produto_fornec b ON (b.produto = a.grid) ");
                query.add("INNER JOIN nfast_unidade c ON (c.sgl_unidade = b.unid_med)  ");
                query.add("INNER JOIN pessoa d on (d.grid = b.fornecedor)   ");
                query.add("WHERE d.codigo = " + codFornecedor + " ");
                query.add("AND b.codigo = '" + codItemFornecedor + "' ");
                query.add("LIMIT 1 ");
            });
        }

        if (Numbers.isEmpty(codUnidade)) {
            codUnidade = nativeFindValue("SELECT a.cod_unidade FROM nfast_unidade a WHERE a.sgl_unidade = '" + sglUnidade + "'");
            if (Numbers.isEmpty(codUnidade) && Numbers.isNonEmpty(codItem))
                codUnidade = nativeFindValue("SELECT c.cod_unidade " +
                        "FROM produto a " +
                        "INNER JOIN nfast_unidade c ON (c.sgl_unidade = a.unid_med) " +
                        "WHERE a.codigo = '" + codItem + "' ");
        }

        return codUnidade;
    }

}
