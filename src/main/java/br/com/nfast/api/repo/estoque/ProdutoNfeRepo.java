package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoFor;
import br.com.nfast.api.model.estoque.ProdutoNfe;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoNfeRepo extends DataRepository<ProdutoNfe, Integer> {

    public ProdutoNfeRepo() {
        super(ProdutoNfe.class);
    }

    public ProdutoNfe produtoNfe(Integer codItem, Integer codEmpresa) {
        ProdutoNfe item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_item, ");
            query.add("  a.nome AS des_item, ");
            query.add("  a.codigo_barra AS cod_barra, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo, ");
            query.add("  b.cod_unidade, ");
            query.add("  b.cod_unidade AS cod_unidade_agrupamento_1, ");
            query.add("  a.unid_med as sgl_unidade_agrupamento_1, ");
            query.add("  1 AS qtd_unidade_agrupamento_1, ");
            query.add("  b.cod_unidade AS cod_unidade_agrupamento_2, ");
            query.add("  a.unid_med as sgl_unidade_agrupamento_2, ");
            query.add("  1 AS qtd_unidade_agrupamento_2, ");
            query.add("  a.conta_usoconsumo AS cod_tipo_despesa, ");
            query.add("  a.codigo_ncm AS cod_ncm, ");
            query.add("  null AS cod_nop_de_ent, ");
            query.add("  null AS cod_nop_fe_ent, ");
            query.add("  null AS cod_nop_de_tra, ");
            query.add("  null AS cod_nop_fe_tra, ");
            query.add("  null AS cod_ipi, ");
            query.add("  null AS cod_pis, ");
            query.add("  null AS cod_cofins ");
            query.add("FROM produto a ");
            query.add("INNER JOIN nfast_unidade b ON (b.sgl_unidade = a.unid_med) ");
            query.add("WHERE a.codigo = '" + codItem + "' ");
        });

        return item;
    }

    public List<ProdutoNfe> produtoNfeList(Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ProdutoNfe> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_item, ");
            query.add("  a.nome AS des_item, ");
            query.add("  a.codigo_barra AS cod_barra, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo, ");
            query.add("  b.cod_unidade, ");
            query.add("  b.cod_unidade AS cod_unidade_agrupamento_1, ");
            query.add("  a.unid_med as sgl_unidade_agrupamento_1, ");
            query.add("  1 AS qtd_unidade_agrupamento_1, ");
            query.add("  b.cod_unidade AS cod_unidade_agrupamento_2, ");
            query.add("  a.unid_med as sgl_unidade_agrupamento_2, ");
            query.add("  1 AS qtd_unidade_agrupamento_2, ");
            query.add("  a.conta_usoconsumo AS cod_tipo_despesa, ");
            query.add("  a.codigo_ncm AS cod_ncm, ");
            query.add("  null AS cod_nop_de_ent, ");
            query.add("  null AS cod_nop_fe_ent, ");
            query.add("  null AS cod_nop_de_tra, ");
            query.add("  null AS cod_nop_fe_tra, ");
            query.add("  null AS cod_ipi, ");
            query.add("  null AS cod_pis, ");
            query.add("  null AS cod_cofins ");
            query.add("FROM produto a ");
            query.add("INNER JOIN nfast_unidade b ON (b.sgl_unidade = a.unid_med) ");
            query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S' ");

            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (a.nome ILIKE :filtro) OR ");
                query.add("  (a.codigo_barra ILIKE :filtro) OR ");
                query.add("  (a.codigo_ncm ILIKE :filtro) OR ");
                query.add("  (CAST(a.codigo as TEXT) ILIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.nome");

            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

    public ProdutoNfe produtoNfeCodigo(String cprod, String cean, String ceanTrib, Integer codFornecedor, Integer codEmpresa) {
        final String codigo = Strings.trim(cprod);
        final String ean = Strings.diff(cean, "SEM GTIN") ? cean : null;
        final String eanTrib = Strings.diff(ceanTrib, "SEM GTIN") ? ceanTrib : null;
        ProdutoNfe produto = null;

        if (Numbers.isNonEmpty(codFornecedor)) {
            produto = nativeFind(query -> {
                query.add("SELECT  ");
                query.add("  a.codigo AS cod_item,  ");
                query.add("  normalize(a.nome) AS des_item,  ");
                query.add("  a.codigo_barra AS cod_barra,  ");
                query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo,  ");
                query.add("  h.cod_unidade AS cod_unidade,  ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento_1,  ");
                query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_1,  ");
                query.add("  1 AS qtd_unidade_agrupamento_1,  ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento_2,  ");
                query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_2,  ");
                query.add("  1 AS qtd_unidade_agrupamento_2,  ");
                query.add("  a.conta_usoconsumo AS cod_tipo_despesa,  ");
                query.add("  a.codigo_ncm AS cod_ncm,  ");
                query.add("  null AS cod_nop_de_ent,  ");
                query.add("  null AS cod_nop_fe_ent,  ");
                query.add("  null AS cod_nop_de_tra,  ");
                query.add("  null AS cod_nop_fe_tra,  ");
                query.add("  null AS cod_ipi,  ");
                query.add("  null AS cod_pis,  ");
                query.add("  null AS cod_cofins  ");
                query.add("FROM produto a  ");
                query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid)  ");
                query.add("INNER JOIN produto_fornec c ON (c.produto = a.grid) ");
                query.add("INNER JOIN pessoa d ON (d.grid = c.fornecedor) ");
                query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = a.unid_med) ");
                query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'  ");
                query.add("AND d.codigo = " + codFornecedor + "  ");
                query.add("AND TRIM(LEADING '0' FROM REPLACE(c.codigo, ' ', '')) = TRIM(LEADING '0' FROM '" + codigo + "')");
                query.add("LIMIT 1  ");

            });

        }

        if ((produto == null) && Strings.isNonEmpty(eanTrib)) {
            produto = nativeFind(query -> {
                query.add("SELECT  ");
                query.add("  a.codigo AS cod_item,  ");
                query.add("  normalize(a.nome) AS des_item,  ");
                query.add("  a.codigo_barra AS cod_barra,  ");
                query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo,  ");
                query.add("  h.cod_unidade AS cod_unidade,  ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento_1,  ");
                query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_1,  ");
                query.add("  1 AS qtd_unidade_agrupamento_1,  ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento_2,  ");
                query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_2,  ");
                query.add("  1 AS qtd_unidade_agrupamento_2,  ");
                query.add("  a.conta_usoconsumo AS cod_tipo_despesa,  ");
                query.add("  a.codigo_ncm AS cod_ncm,  ");
                query.add("  null AS cod_nop_de_ent,  ");
                query.add("  null AS cod_nop_fe_ent,  ");
                query.add("  null AS cod_nop_de_tra,  ");
                query.add("  null AS cod_nop_fe_tra,  ");
                query.add("  null AS cod_ipi,  ");
                query.add("  null AS cod_pis,  ");
                query.add("  null AS cod_cofins  ");
                query.add("FROM produto a  ");
                query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid)  ");
                query.add("INNER JOIN produto_fornec c ON (c.produto = a.grid) ");
                query.add("INNER JOIN pessoa d on (d.grid = c.fornecedor) ");
                query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = a.unid_med) ");
                query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'  ");
                query.add("AND a.codigo_barra = '" + eanTrib + "'  ");
                query.add("LIMIT 1  ");
            });

            if (produto == null) {
                produto = nativeFind(query -> {
                    query.add("SELECT  ");
                    query.add("  a.codigo AS cod_item,  ");
                    query.add("  normalize(a.nome) AS des_item,  ");
                    query.add("  a.codigo_barra AS cod_barra,  ");
                    query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo,  ");
                    query.add("  h.cod_unidade AS cod_unidade,  ");
                    query.add("  h.cod_unidade AS cod_unidade_agrupamento_1,  ");
                    query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_1,  ");
                    query.add("  1 AS qtd_unidade_agrupamento_1,  ");
                    query.add("  h.cod_unidade AS cod_unidade_agrupamento_2,  ");
                    query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_2,  ");
                    query.add("  1 AS qtd_unidade_agrupamento_2,  ");
                    query.add("  a.conta_usoconsumo AS cod_tipo_despesa,  ");
                    query.add("  a.codigo_ncm AS cod_ncm,  ");
                    query.add("  null AS cod_nop_de_ent,  ");
                    query.add("  null AS cod_nop_fe_ent,  ");
                    query.add("  null AS cod_nop_de_tra,  ");
                    query.add("  null AS cod_nop_fe_tra,  ");
                    query.add("  null AS cod_ipi,  ");
                    query.add("  null AS cod_pis,  ");
                    query.add("  null AS cod_cofins  ");
                    query.add("FROM produto a  ");
                    query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid)  ");
                    query.add("INNER JOIN produto_fornec c ON (c.produto = a.grid) ");
                    query.add("INNER JOIN pessoa d on (d.grid = c.fornecedor) ");
                    query.add("INNER JOIN produto_codigo_barra e ON (e.produto = a.grid) ");
                    query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = a.unid_med) ");
                    query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'  ");
                    query.add("AND e.codigo_barra = '" + eanTrib + "'  ");
                    query.add("LIMIT 1  ");
                });
            }
        }

        if ((produto == null) && Strings.isNonEmpty(ean)) {
            produto = nativeFind(query -> {
                query.add("SELECT  ");
                query.add("  a.codigo AS cod_item,  ");
                query.add("  normalize(a.nome) AS des_item,  ");
                query.add("  a.codigo_barra AS cod_barra,  ");
                query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo,  ");
                query.add("  h.cod_unidade AS cod_unidade,  ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento_1,  ");
                query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_1,  ");
                query.add("  1 AS qtd_unidade_agrupamento_1,  ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento_2,  ");
                query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_2,  ");
                query.add("  1 AS qtd_unidade_agrupamento_2,  ");
                query.add("  a.conta_usoconsumo AS cod_tipo_despesa,  ");
                query.add("  a.codigo_ncm AS cod_ncm,  ");
                query.add("  null AS cod_nop_de_ent,  ");
                query.add("  null AS cod_nop_fe_ent,  ");
                query.add("  null AS cod_nop_de_tra,  ");
                query.add("  null AS cod_nop_fe_tra,  ");
                query.add("  null AS cod_ipi,  ");
                query.add("  null AS cod_pis,  ");
                query.add("  null AS cod_cofins  ");
                query.add("FROM produto a  ");
                query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid)  ");
                query.add("INNER JOIN produto_fornec c ON (c.produto = a.grid) ");
                query.add("INNER JOIN pessoa d on (d.grid = c.fornecedor) ");
                query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = a.unid_med) ");
                query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'  ");
                query.add("AND a.codigo_barra = '" + ean + "' ");
            });

            if (produto == null) {
                produto = nativeFind(query -> {
                    query.add("SELECT  ");
                    query.add("  a.codigo AS cod_item,  ");
                    query.add("  normalize(a.nome) AS des_item,  ");
                    query.add("  a.codigo_barra AS cod_barra,  ");
                    query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_item_ativo,  ");
                    query.add("  h.cod_unidade AS cod_unidade,  ");
                    query.add("  h.cod_unidade AS cod_unidade_agrupamento_1,  ");
                    query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_1,  ");
                    query.add("  1 AS qtd_unidade_agrupamento_1,  ");
                    query.add("  h.cod_unidade AS cod_unidade_agrupamento_2,  ");
                    query.add("  a.unid_med_entrada AS sgl_unidade_agrupamento_2,  ");
                    query.add("  1 AS qtd_unidade_agrupamento_2,  ");
                    query.add("  a.conta_usoconsumo AS cod_tipo_despesa,  ");
                    query.add("  a.codigo_ncm AS cod_ncm,  ");
                    query.add("  null AS cod_nop_de_ent,  ");
                    query.add("  null AS cod_nop_fe_ent,  ");
                    query.add("  null AS cod_nop_de_tra,  ");
                    query.add("  null AS cod_nop_fe_tra,  ");
                    query.add("  null AS cod_ipi,  ");
                    query.add("  null AS cod_pis,  ");
                    query.add("  null AS cod_cofins  ");
                    query.add("FROM produto a  ");
                    query.add("INNER JOIN produto_tributacao b ON (b.produto = a.grid)  ");
                    query.add("INNER JOIN produto_fornec c ON (c.produto = a.grid) ");
                    query.add("INNER JOIN pessoa d on (d.grid = c.fornecedor) ");
                    query.add("INNER JOIN produto_codigo_barra e ON (e.produto = a.grid) ");
                    query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = a.unid_med) ");
                    query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'  ");
                    query.add("AND e.codigo_barra = '" + ean + "' ");
                });
            }
        }

        return produto;
    }

    public ProdutoFor getProdutoFor(Integer codItem, Integer codFornecedor, String codItemFornecedor) {
        ProdutoFor produto = nativeFind(query -> {
            query.add("SELECT   ");
            query.add("  a.codigo AS cod_item,   ");
            query.add("  h.cod_unidade AS cod_unidade_agrupamento, ");
            query.add("  b.qtde_unid AS qtd_unidade_agrupamento");
            query.add("FROM produto a    ");
            query.add("INNER JOIN produto_fornec b ON (b.produto = a.grid)  ");
            query.add("INNER JOIN pessoa d on (d.grid = b.fornecedor)  ");
            query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = b.unid_med)  ");
            query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'   ");
            query.add("AND a.codigo = '" + codItem + "'   ");
            query.add("AND d.codigo = " + codFornecedor + " ");
            query.add("AND b.codigo = '" + codItemFornecedor + "' ");
        }, ProdutoFor.class);

        if (produto == null) {
            produto = nativeFind(query -> {
                query.add("SELECT   ");
                query.add("  a.codigo AS cod_item,   ");
                query.add("  h.cod_unidade AS cod_unidade_agrupamento, ");
                query.add("  b.qtde_unid AS qtd_unidade_agrupamento");
                query.add("FROM produto a    ");
                query.add("INNER JOIN produto_fornec b ON (b.produto = a.grid)  ");
                query.add("INNER JOIN pessoa d on (d.grid = b.fornecedor)  ");
                query.add("INNER JOIN nfast_unidade h ON (h.sgl_unidade = b.unid_med)  ");
                query.add("WHERE CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END = 'S'   ");
                query.add("AND a.codigo = '" + codItem + "'   ");
                query.add("AND d.codigo = " + codFornecedor + " ");
                query.add("AND TRIM(LEADING '0' FROM b.codigo) = TRIM(LEADING '0' FROM '" + codItemFornecedor + "') ");
            }, ProdutoFor.class);
        }

        return produto;
    }

}
