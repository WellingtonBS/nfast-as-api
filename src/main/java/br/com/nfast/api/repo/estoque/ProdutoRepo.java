package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.PrecoProduto;
import br.com.nfast.api.model.estoque.Produto;
import br.com.nfast.api.repo.nfe.NFeResumoRepo;
import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.StringList;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProdutoRepo extends DataRepository<Produto, Long> {

    @Autowired
    private NFeResumoRepo nFeResumoRepo;

    public ProdutoRepo() {
        super(Produto.class);
    }

    public Produto produto(Long codItem) {
        Produto item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_item, ");
            query.add("  a.nome AS des_item ");
            query.add("FROM produto a ");
            query.add("WHERE a.codigo = " + codItem);
        }, Produto.class);
        return item;
    }

    public List<Produto> produtoList(String filtro, Integer limit, Integer offset) {
        List<Produto> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_item, ");
            query.add("  a.nome AS des_item ");
            query.add("FROM produto a ");
            query.add("WHERE flag = 'A' ");

            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codigo, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.nome) LIKE :filtro) ");
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

    public Double getSaldoEstoque(Long codEmpresa, Long codItem, Long codAlmoxarifado, LocalDate data) {
        Double qtdSaldo = nativeFindValue("SELECT sp_obtem_saldo_item_qtde(" + codEmpresa + ", " + codItem + ", " + codAlmoxarifado + ", '" + Dates.format(data, "yyyy-MM-dd") + "', 'N') ");
        return qtdSaldo;
    }

    public Double getCustoProduto(Long codEmpresa, Long codItem, String tipo) {
        Double vlrCusto = 0.0;
        switch (tipo) {
            case "1": { //Custo Médio Geral
                Object[] saldo = nativeFindValue(query -> {
                    query.add("WITH entradas AS ( ");
                    query.add("SELECT  ");
                    query.add(" CAST(preco_custo_empresa_f(a.grid, b.grid, CURRENT_DATE) as DOUBLE PRECISION) as val_saldo, ");
                    query.add(" CAST(preco_custo_empresa_f(a.grid, b.grid, CURRENT_DATE) as DOUBLE PRECISION) as qtd_saldo ");
                    query.add("FROM produto a, ");
                    query.add("empresa b ");
                    query.add("WHERE a.codigo = " + codItem + " ");
                    query.add("AND b.codigo = " + codEmpresa + " ");
                    query.add(") ");

                    query.add("SELECT ");
                    query.add(" (a.val_saldo * a.qtd_saldo) AS val_saldo ");
                    query.add("  a.qtd_saldo AS qtd_saldo");
                    query.add("FROM entradas a  ");
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
                    query.add("SELECT CAST(CAST(a.preco_custo as NUMERIC(15, 6)) as DOUBLE PRECISION) ");
                    query.add("FROM produto_empresa a ");
                    query.add("INNER JOIN produto b ON (a.produto = b.grid) ");
                    query.add("INNER JOIN empresa c ON (a.empresa = c.grid) ");
                    query.add("WHERE CAST(b.codigo AS INTEGER) = " + codItem + " ");
                    query.add("AND CAST(c.codigo AS INTEGER) = " + codEmpresa + " ");
                });
            }
            break;
            default: { //Preço Unitário da Última NF
                vlrCusto = nativeFindValue(query -> {
                    query.add("SELECT CAST(CAST((a.valor / a.quantidade) as NUMERIC(15, 6)) as DOUBLE PRECISION) ");
                    query.add("FROM nota_fiscal_produto a ");
                    query.add("INNER JOIN nota_fiscal b ON(b.grid = a.nota_fiscal) ");
                    query.add("INNER JOIN produto c ON(c.grid = a.produto) ");
                    query.add("INNER JOIN empresa d ON(d.grid = b.empresa) ");
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

    public PrecoProduto getPrecoProduto(Long codEmpresa, Long codItem) {
        PrecoProduto precoProduto = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  (b.codigo||'-'||c.codigo) as id, ");
            query.add("  CAST(b.codigo AS INTEGER) AS cod_item, ");
            query.add("  CAST(c.codigo AS INTEGER) AS cod_empresa, ");
            query.add("  a.preco_unit as val_preco_venda, ");
            query.add("  a.margem_lucro_padrao as per_margem, ");
            query.add("  a.margem_lucro as per_markup ");
            query.add("FROM produto_empresa a ");
            query.add("INNER JOIN produto b ON (a.produto = b.grid) ");
            query.add("INNER JOIN empresa c ON (c.grid = a.empresa) ");
            query.add("WHERE CAST(b.codigo AS TEXT) = :codItem ");
            query.add("AND CAST(c.codigo AS TEXT)  = :codEmpresa ");
            query.set("codItem", codItem.toString());
            query.set("codEmpresa", codEmpresa.toString());
        }, PrecoProduto.class);
        return precoProduto;
    }

    public void vinculaProdutoFor(Long codItem, Integer codFornecedor, String codItemFornecedor, Integer codUnidadeAgrup, Double qtdUnidadeAgrup) {
        executeNative(q -> {
            if (Numbers.isEmpty(codFornecedor)) {
                q.add("INSERT INTO produto_unidade_medida ( ");
                q.add("  fiscal, ");
                q.add("  produto, ");
                q.add("  fator, ");
                q.add("  unid_med ");
                q.add(") values ( ");
                q.add("  TRUE, ");
                q.add("  (SELECT grid FROM produto WHERE codigo  = :codItem), ");
                q.add("  :qtdUnidadeAgrup, ");
                q.add("  (SELECT sgl_unidade FROM nfast_unidade WHERE cod_unidade = :codUnidadeAgrup ) ");
                q.add(" ) ");
                q.set("codItem", codItem);
                q.set("qtdUnidadeAgrup", qtdUnidadeAgrup);
                q.set("codUnidadeAgrup", codUnidadeAgrup);

            } else {
                q.add("INSERT INTO produto_fornec ( ");
                q.add("	unid_med, ");
                q.add("	produto, ");
                q.add("	fornecedor, ");
                q.add("	cst, ");
                q.add("	aliquota_icms, ");
                q.add("	codigo, ");
                q.add("	qtde_unid ");
                q.add(" ) VALUES ( ");
                q.add("	(SELECT sgl_unidade FROM nfast_unidade WHERE cod_unidade = :codUnidadeAgrup ), ");
                q.add("	(SELECT grid FROM produto WHERE codigo  = CAST(:codItem AS TEXT) ), ");
                q.add("	:codFornecedor, ");
                q.add("	(SELECT b.cst FROM produto A ");
                q.add("  INNER JOIN tributacao b ON  (a.tributacao = b.codigo) ");
                q.add("  WHERE a.codigo = CAST(:codItem AS TEXT) ), ");
                q.add("	(SELECT b.tributacao FROM produto A ");
                q.add("  INNER JOIN tributacao b ON  (a.tributacao = b.codigo) ");
                q.add("  WHERE a.codigo = CAST(:codItem AS TEXT) ) ,");
                q.add("	:codItemFornecedor, ");
                q.add("	:qtdUnidadeAgrup ");
                q.add(" )  ");
                q.set("codUnidadeAgrup", codUnidadeAgrup);
                q.set("codItem", codItem);
                q.set("codFornecedor", codFornecedor);
                q.set("codItemFornecedor", codItemFornecedor);
                q.set("qtdUnidadeAgrup", qtdUnidadeAgrup);

            }
        });
    }

    public void vinculaProdutoEan(Long codItem, String codBarra, Long codEmpresa) {

        StringList sql = new StringList();

        sql.clear();
        sql.add("INSERT INTO produto_codigo_barra (produto, codigo_barra) ");
        sql.add("  SELECT a.grid AS produto, ");
        sql.add("        '" + codBarra + "' AS codigo_barra ");
        sql.add("  FROM produto a ");
        sql.add("  WHERE a.codigo = '" + codItem + "'");
        sql.add("  AND a.codigo_barra <> '" + codBarra + "'");
        sql.add("  AND NOT EXISTS( ");
        sql.add("        SELECT 1 ");
        sql.add("        FROM produto_codigo_barra x ");
        sql.add("        WHERE x.codigo_barra = '" + codBarra + "'");

        Query q = em.createNativeQuery(sql.toString());

    }

    public void produtoValidadeNfe(Long seqNota, Long codItem, LocalDate dtaValidade, Double qtdItem, LocalDateTime dtaImportacao) {
        /*executeNative(query -> {
            query.add("insert into tab_nota_validade_produto(seq_nota, cod_item, dta_validade, qtd_item, dta_importacao) ");
            query.add("values(:seq_nota, ");
            query.add("       :cod_item, ");
            query.add("       '" + Dates.format(dtaValidade, "yyyy-MM-dd") + "', ");
            query.add("       :qtd_item, ");
            query.add("       '" + Dates.format(dtaImportacao, "yyyy-MM-dd") + "') ");
            query.set("seq_nota", seqNota);
            query.set("cod_item", codItem);
            query.set("qtd_item", qtdItem);
        });*/
    }

    public void excluiProdutoValidadeNfe(String chave, Long codEmpresa) {
        /*var listaNfeResumo = nFeResumoRepo.listaChave(codEmpresa, chave);

        for (NFeResumo nfe : listaNfeResumo)
            executeNative(query -> {
                query.add("DELETE FROM tab_nota_validade_produto WHERE seq_nota = :seq_nota");
                query.set("seq_nota", nfe.getSeqNota());
            });*/
    }

}
