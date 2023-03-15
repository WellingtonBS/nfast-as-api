package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.Tributacao;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TributacaoRepo extends DataRepository<Tributacao, Integer> {

    public TributacaoRepo() {
        super(Tributacao.class);
    }

    public Tributacao tributacao(Integer codTributacao) {
        Tributacao item = nativeFind(query -> {
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'ICMS' AS ind_tipo_tributo, ");
            query.add("  CASE WHEN a.cst_tributacao IN ('60') THEN 'O' ");
            query.add("       WHEN a.cst_tributacao IN ('40','41') THEN 'I' ");
            query.add("       WHEN a.cst_tributacao IN ('20','70') THEN 'R' ");
            query.add("       ELSE 'T' END AS ind_coluna_tributo, ");
            query.add("  'E' AS ind_entrada_saida, ");
            query.add("  a.cst AS cod_situacao_tributaria, ");
            query.add("  a.csosn AS cod_csosn, ");
            query.add("  a.tributacao AS per_aliquota, ");
            query.add("  COALESCE(a.reducao_base,0) AS per_reducao_base, ");
            query.add("  CASE WHEN a.cst_tributacao IN ('60','70') THEN 'S' ELSE 'N' END AS ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'N' AS ind_inativa ");
            query.add("FROM tributacao a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE TRUE ");
            query.add("AND b.tipo = 'ICMS'  ");
            query.add("AND b.cod_tributacao = " + codTributacao);
            query.add("UNION ALL ");
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'PIS' AS ind_tipo_tributo, ");
            query.add("  'T' ind_coluna_tributo, ");
            query.add("  'E' AS ind_entrada_saida, ");
            query.add("  a.codigo AS cod_situacao_tributaria, ");
            query.add("  '' AS cod_csosn, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_reducao_base, ");
            query.add("  'N' ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'S' AS ind_inativa ");
            query.add("FROM cst_pis a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE a.tipo IN ('E', 'ES') ");
            query.add("AND b.tipo = 'PIS'  ");
            query.add("AND b.cod_tributacao = " + codTributacao);
            query.add("UNION ALL ");
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'COFINS' AS ind_tipo_tributo, ");
            query.add("  'T' ind_coluna_tributo, ");
            query.add("  'E' AS ind_entrada_saida, ");
            query.add("  a.codigo AS cod_situacao_tributaria, ");
            query.add("  '' AS cod_csosn, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_reducao_base, ");
            query.add("  'N' ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'S' AS ind_inativa ");
            query.add("FROM cst_cofins a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE a.tipo IN ('E', 'ES') ");
            query.add("AND b.tipo = 'COFINS'  ");
            query.add("AND b.cod_tributacao = " + codTributacao);
            query.add("UNION ALL ");
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'IPI' AS ind_tipo_tributo, ");
            query.add("  'T' AS ind_coluna_tributo, ");
            query.add("  'E' AS ind_entrada_saida, ");
            query.add("  a.codigo AS cod_situacao_tributaria, ");
            query.add("  '' AS cod_csosn, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_reducao_base, ");
            query.add("  'N' ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'S' AS ind_inativa ");
            query.add("FROM cst_ipi a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE a.tipo IN ('E', 'ES') ");
            query.add("AND b.tipo = 'IPI' ");
            query.add("AND b.cod_tributacao = " + codTributacao);
            query.add("ORDER BY 1 ");

        });
        return item;
    }

    public List<Tributacao> tributacaoList(String indEntradaSaida, String indTipoTributo, String filtro, Integer limit, Integer offset) {
        List<Tributacao> list = nativeFindAll(query -> {
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'ICMS' AS ind_tipo_tributo, ");
            query.add("  CASE WHEN a.cst_tributacao IN ('60') THEN 'O' ");
            query.add("       WHEN a.cst_tributacao IN ('40','41') THEN 'I' ");
            query.add("       WHEN a.cst_tributacao IN ('20','70') THEN 'R' ");
            query.add("       ELSE 'T' END AS ind_coluna_tributo, ");
            query.add("  '" + indEntradaSaida + "' AS ind_entrada_saida, ");
            query.add("  a.cst AS cod_situacao_tributaria, ");
            query.add("  a.csosn AS cod_csosn, ");
            query.add("  a.tributacao AS per_aliquota, ");
            query.add("  COALESCE(a.reducao_base,0) AS per_reducao_base, ");
            query.add("  CASE WHEN a.cst_tributacao IN ('60','70') THEN 'S' ELSE 'N' END AS ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'N' AS ind_inativa ");
            query.add("FROM tributacao a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE TRUE ");
            query.add("AND b.tipo = 'ICMS'  ");
            /*if (Strings.isNonEmpty(indEntradaSaida)) {
                if (Strings.equals(indEntradaSaida, "E+"))
                    query.add("AND COALESCE(a.indEntradaSaida, '') <> 'S' ");
                else if (Strings.equals(indEntradaSaida, "S+"))
                    query.add("AND COALESCE(a.indEntradaSaida, '') <> 'E' ");
                else query.add("AND a.indEntradaSaida = '" + indEntradaSaida + "' ");
            }*/
            if (Strings.isNonEmpty(indTipoTributo))
                query.add("AND '" + indTipoTributo + "' in ('ICMS','P') ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(b.cod_tributacao, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.cst) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(a.descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("UNION ALL ");
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'PIS' AS ind_tipo_tributo, ");
            query.add("  'T' ind_coluna_tributo, ");
            query.add("  '" + indEntradaSaida + "' AS ind_entrada_saida, ");
            query.add("  a.codigo AS cod_situacao_tributaria, ");
            query.add("  '' AS cod_csosn, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_reducao_base, ");
            query.add("  'N' ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'S' AS ind_inativa ");
            query.add("FROM cst_pis a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE a.tipo IN ('E', 'ES') ");
            query.add("AND b.tipo = 'PIS'  ");
            if (Strings.isNonEmpty(indTipoTributo))
                query.add("AND 'PIS' = '" + indTipoTributo + "' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(b.cod_tributacao, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.codigo) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(a.descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }

            query.add("UNION ALL ");
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'COFINS' AS ind_tipo_tributo, ");
            query.add("  'T' ind_coluna_tributo, ");
            query.add("  '" + indEntradaSaida + "' AS ind_entrada_saida, ");
            query.add("  a.codigo AS cod_situacao_tributaria, ");
            query.add("  '' AS cod_csosn, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_reducao_base, ");
            query.add("  'N' ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'S' AS ind_inativa ");
            query.add("FROM cst_cofins a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE a.tipo IN ('E', 'ES') ");
            query.add("AND b.tipo = 'COFINS'  ");
            if (Strings.isNonEmpty(indTipoTributo))
                query.add("AND 'COFINS' = '" + indTipoTributo + "' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(b.cod_tributacao, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.codigo) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(a.descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }

            query.add("UNION ALL ");
            query.add("SELECT DISTINCT ");
            query.add("  b.cod_tributacao AS cod_tributacao, ");
            query.add("  normali(a.descricao) AS des_tributacao, ");
            query.add("  'IPI' AS ind_tipo_tributo, ");
            query.add("  'T' ind_coluna_tributo, ");
            query.add("  '" + indEntradaSaida + "' AS ind_entrada_saida, ");
            query.add("  a.codigo AS cod_situacao_tributaria, ");
            query.add("  '' AS cod_csosn, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_reducao_base, ");
            query.add("  'N' ind_substituicao_tributaria, ");
            query.add("  'N' AS ind_ipi_integra_base, ");
            query.add("  'S' AS ind_inativa ");
            query.add("FROM cst_ipi a ");
            query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
            query.add("WHERE a.tipo IN ('E', 'ES') ");
            query.add("AND b.tipo = 'IPI'  ");
            if (Strings.isNonEmpty(indTipoTributo))
                query.add("AND '" + indTipoTributo + "' in ('I','IPI') ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(b.cod_tributacao, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.codigo) LIKE :filtro) OR ");
                query.add("  (LOWER(normali(a.descricao)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }

            query.add("ORDER BY 1, 2 ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }

    public Integer getIcmsNfe(Long codItem, Long codEmpresa, String natureza, String uf, String cst, double perAliquota, double perReducaoBc, String verificaClasseIcms) {
        Integer codIcms = null;
        if (Strings.equals(verificaClasseIcms, "S")) {
            codIcms = nativeFindValue(query -> {
                query.add("SELECT ");
                query.add("  CAST(d.cod_tributacao AS INTEGER) AS cod_tributacao");
                query.add("FROM produto a ");
                query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid) ");
                query.add("INNER JOIN nfast_tributacao d ON (d.codigo = b.tributacao) ");
                query.add("WHERE a.codigo = '" + codItem + "' ");
                query.add("AND b.de = '" + uf + "' ");
                query.add("AND d.tipo = 'ICMS'  ");
            });
        } else {
            if (Numbers.isAllNonEmpty(codItem, codEmpresa) && Strings.isAllNonEmpty(natureza, uf)) {
                codIcms = nativeFindValue(query -> {
                    query.add("SELECT ");
                    query.add("  CAST(b.cod_tributacao AS INTEGER) as cod_tributacao ");
                    query.add("FROM produto a ");
                    query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid) ");
                    query.add("INNER JOIN tributacao c ON (c.codigo = b.tributacao) ");
                    query.add("INNER JOIN nfast_tributacao d ON (d.codigo = c.codigo) ");
                    query.add("WHERE a.codigo = '" + codItem + "' ");
                    query.add("AND b.de = '" + uf + "' ");
                    query.add("AND c.cst = '" + cst + "' ");
                    query.add("AND c.tributacao = " + perAliquota);
                    query.add("AND c.reducao_base = " + perReducaoBc);
                    query.add("AND d.tipo = 'ICMS'  ");
                });
            }

            if (Numbers.isEmpty(codIcms)) {
                codIcms = nativeFindValue(query -> {
                    query.add("SELECT CAST(b.cod_tributacao AS INTEGER) AS cod_tributacao ");
                    query.add("FROM tributacao a ");
                    query.add("INNER JOIN nfast_tributacao b ON (a.codigo = b.codigo) ");
                    query.add("WHERE a.cst = '" + cst + "' ");
                    query.add("AND a.tributacao = " + perAliquota);
                    query.add("AND a.reducao_base = " + perReducaoBc);
                    query.add("AND b.tipo = 'ICMS'  ");
                });
            }
        }

        return codIcms;
    }

}
