package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.PrecoProduto;
import br.com.nfast.api.model.estoque.Produto;
import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.utils.Numbers;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class ProdutoRepo extends DataRepository<Produto, Integer> {

    public ProdutoRepo() {
        super(Produto.class);
    }

    public Double getSaldoEstoque(Integer codEmpresa, Integer codItem, Integer codAlmoxarifado, LocalDate data) {
        Double qtdSaldo = nativeFindValue("SELECT sp_obtem_saldo_item_qtde(" + codEmpresa + ", " + codItem + ", " + codAlmoxarifado + ", '" + Dates.format(data, "yyyy-MM-dd") + "', 'N') ");
        return qtdSaldo;
    }

    public Double getCustoProduto(Integer codEmpresa, Integer codItem, String tipo) {
        Double vlrCusto = 0.0;
        switch (tipo) {
            case "1": { //Custo Médio Geral
                Object[] saldo = nativeFindValue(query -> {
                    query.add("SELECT ");
                    query.add("  CAST(CAST(SUM(a.val_entrada - a.val_saida) as NUMERIC(15, 6)) as DOUBLE PRECISION) as val_saldo, ");
                    query.add("  CAST(CAST(SUM(a.qtd_entrada - a.qtd_saida) as NUMERIC(15, 6)) as DOUBLE PRECISION) as qtd_saldo ");
                    query.add("FROM tab_movto_acum_estoque a ");
                    query.add("WHERE a.cod_item = " + codItem + " ");
                    query.add("AND a.cod_empresa = " + codEmpresa + " ");
                });

                Double valSaldo = 0.0, qtdSaldo = 0.0;
                if (saldo != null) {
                    valSaldo = Numbers.asDouble(saldo[0], 0.0);
                    qtdSaldo = Numbers.asDouble(saldo[1], 0.0);
                }

                if (qtdSaldo > 0.0)
                    vlrCusto = Numbers.round(valSaldo / qtdSaldo, 6);

                if (vlrCusto <= 0.0)
                    vlrCusto = getCustoProduto(codEmpresa, codItem, "3");
            }
            break;
            case "2": { //Custo Unitário da Última NF
                vlrCusto = nativeFindValue(query -> {
                    query.add("SELECT CAST(CAST(a.val_custo_unitario as NUMERIC(15, 6)) as DOUBLE PRECISION) ");
                    query.add("FROM tab_item_empresa a ");
                    query.add("WHERE a.cod_item = " + codItem + " ");
                    query.add("AND a.cod_empresa = " + codEmpresa + " ");
                });
            }
            break;
            default: { //Preço Unitário da Última NF
                vlrCusto = nativeFindValue(query -> {
                    query.add("SELECT CAST(CAST((a.valor / a.quantidade) as NUMERIC(15, 6)) as DOUBLE PRECISION) ");
                    query.add("FROM nota_fiscal_produto a ");
                    query.add("INNER JOIN nota_fiscal b ON(b.grid = a.nota_fiscal) ");
                    query.add("INNER JOIN produto c ON(c.grid = a.produto) ");
                    query.add("INNER JOIN empresa d ON(d.grid = b.empresa)  ");
                    query.add("WHERE c.codigo = '" + codItem + "' ");
                    query.add("AND d.codigo = " + codEmpresa + " ");
                    query.add("AND a.cfop not in ('1201','1202','1203','1204','1208','1209','1410','1411','2201','2202','2203','2204','2208','2209','2410','2411','5201','5202','5203'," +
                            "'5204','5208','5209','5410','5411','6201','6202','6203','6204','6208','6209','6410','6411')"); // ind_devolução
                    query.add("AND a.quantidade > 0.0 ");
                    query.add("ORDER BY b.data_emissao DESC ");
                    query.add("LIMIT 1 ");
                });
            }
            break;
        }

        vlrCusto = Numbers.round(Numbers.ifNull(vlrCusto, 0.0), 6);
        return vlrCusto;
    }

    public PrecoProduto getPrecoProduto(Integer codEmpresa, Integer codItem) {
        PrecoProduto precoProduto = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  (a.cod_item||'-'||a.cod_empresa) as id, ");
            query.add("  a.cod_item, ");
            query.add("  a.cod_empresa, ");
            query.add("  COALESCE(( ");
            query.add("    SELECT x.val_preco_venda_a ");
            query.add("    FROM tab_preco_venda x ");
            query.add("    WHERE x.cod_item = a.cod_item ");
            query.add("    AND x.cod_empresa = a.cod_empresa ");
            query.add("    AND x.cod_pessoa IS NULL ");
            query.add("    AND x.cod_condicao_pagamento IS NULL ");
            query.add("    AND x.dta_inicio <= CURRENT_DATE ");
            query.add("    ORDER BY x.dta_inicio DESC ");
            query.add("    LIMIT 1 ");
            query.add("  ), 0.0) as val_preco_venda, ");
            query.add("  a.per_margem_desejada as per_margem, ");
            query.add("  a.per_margem as per_markup ");
            query.add("FROM tab_item_empresa a ");
            query.add("WHERE a.cod_item = :codItem ");
            query.add("AND a.cod_empresa = :codEmpresa ");
            query.set("codItem", codItem);
            query.set("codEmpresa", codEmpresa);
        }, PrecoProduto.class);
        return precoProduto;
    }

    public void vinculaProdutoFor(Integer codItem, Integer codFornecedor, String codItemFornecedor, Integer codUnidadeAgrup, Double qtdUnidadeAgrup) {
        executeNative(q -> {
            if (Numbers.isEmpty(codUnidadeAgrup)) {
                q.add("INSERT INTO tab_item_fornecedor(cod_item, cod_pessoa_fornecedor, cod_item_fornecedor, qtd_unidade_agrupamento) ");
                q.add("SELECT :codItem, :codFornecedor, :codItemFornecedor, :qtdUnidadeAgrup ");
                q.add("WHERE NOT EXISTS( ");
                q.add("  SELECT 1 ");
                q.add("  FROM tab_item_fornecedor x ");
                q.add("  WHERE x.cod_item = :codItem ");
                q.add("  AND x.cod_pessoa_fornecedor = :codFornecedor ");
                q.add("  AND x.cod_item_fornecedor = :codItemFornecedor ");
                q.add(") ");
                q.set("codItem", codItem);
                q.set("codFornecedor", codFornecedor);
                q.set("codItemFornecedor", codItemFornecedor);
                q.set("qtdUnidadeAgrup", qtdUnidadeAgrup);
            } else {
                q.add("INSERT INTO tab_item_fornecedor(cod_item, cod_pessoa_fornecedor, cod_item_fornecedor, cod_unidade_agrupamento, qtd_unidade_agrupamento) ");
                q.add("SELECT :codItem, :codFornecedor, :codItemFornecedor, :codUnidadeAgrup, :qtdUnidadeAgrup ");
                q.add("WHERE NOT EXISTS( ");
                q.add("  SELECT 1 ");
                q.add("  FROM tab_item_fornecedor x ");
                q.add("  WHERE x.cod_item = :codItem ");
                q.add("  AND x.cod_pessoa_fornecedor = :codFornecedor ");
                q.add("  AND x.cod_item_fornecedor = :codItemFornecedor ");
                q.add(") ");
                q.set("codItem", codItem);
                q.set("codFornecedor", codFornecedor);
                q.set("codItemFornecedor", codItemFornecedor);
                q.set("codUnidadeAgrup", codUnidadeAgrup);
                q.set("qtdUnidadeAgrup", qtdUnidadeAgrup);
            }
        });
    }

    public void vinculaProdutoEan(Integer codItem, String codBarra, Integer codEmpresa) {
        executeNative(q -> {
            q.add("INSERT INTO tab_item_embalagem(cod_item, cod_barra, ind_embalagem, des_item_resumido) ");
            q.add("SELECT a.cod_item, :codBarra, 'N', CAST(a.des_item as VARCHAR(30)) ");
            q.add("FROM tab_item a ");
            q.add("WHERE a.cod_item = :codItem ");
            q.add("AND a.cod_barra <> :codBarra ");
            q.add("AND NOT EXISTS( ");
            q.add("  SELECT 1 ");
            q.add("  FROM tab_item_embalagem x ");
            q.add("  WHERE x.cod_barra = :codBarra ");
            //q.add("  AND x.cod_item = a.cod_item ");
            //q.add("  AND x.ind_embalagem = 'N' ");
            q.add(") ");
            q.set("codItem", codItem);
            q.set("codBarra", codBarra);
        });

        executeNative(q -> {
            q.add("INSERT INTO tab_item_embalagem_empresa(cod_barra, cod_empresa, qtd_item_embalagem, val_preco_unitario, ind_sincronizado, ind_solicita_vendedor) ");
            q.add("SELECT a.cod_barra, :codEmpresa, 1.0, 0.0, 'N', 'N' ");
            q.add("FROM tab_item_embalagem a ");
            q.add("WHERE a.cod_barra = :codBarra ");
            q.add("AND NOT EXISTS( ");
            q.add("  SELECT 1 ");
            q.add("  FROM tab_item_embalagem_empresa x ");
            q.add("  WHERE x.cod_barra = a.cod_barra ");
            q.add("  AND x.cod_empresa = :codEmpresa ");
            q.add(") ");
            q.set("codBarra", codBarra);
            q.set("codEmpresa", codEmpresa);
        });
    }

}
