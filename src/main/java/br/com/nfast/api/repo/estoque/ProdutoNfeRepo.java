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
            query.add("  a.cod_item, ");
            query.add("  a.des_item, ");
            query.add("  a.cod_barra, ");
            query.add("  b.ind_item_ativo, ");
            query.add("  a.cod_unidade, ");
            query.add("  a.cod_unidade_agrupamento_1, ");
            query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
            query.add("  a.qtd_unidade_agrupamento_1, ");
            query.add("  a.cod_unidade_agrupamento_2, ");
            query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
            query.add("  a.qtd_unidade_agrupamento_2, ");
            query.add("  b.cod_tipo_despesa, ");
            query.add("  c.cod_ncm, ");
            query.add("  b.cod_nop_de_ent, ");
            query.add("  b.cod_nop_fe_ent, ");
            query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
            query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
            query.add("  b.cod_tributacao_ipi as cod_ipi, ");
            query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
            query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
            query.add("FROM tab_item a ");
            query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
            query.add("LEFT  JOIN tab_ncm c ON(c.seq_ncm = a.seq_ncm) ");
            query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
            query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
            query.add("WHERE a.cod_item = " + codItem + " ");
            query.add("AND b.cod_empresa = " + codEmpresa + " ");
        });

        return item;
    }

    public List<ProdutoNfe> produtoNfeList(Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ProdutoNfe> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.cod_item, ");
            query.add("  a.des_item, ");
            query.add("  a.cod_barra, ");
            query.add("  b.ind_item_ativo, ");
            query.add("  a.cod_unidade, ");
            query.add("  a.cod_unidade_agrupamento_1, ");
            query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
            query.add("  a.qtd_unidade_agrupamento_1, ");
            query.add("  a.cod_unidade_agrupamento_2, ");
            query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
            query.add("  a.qtd_unidade_agrupamento_2, ");
            query.add("  b.cod_tipo_despesa, ");
            query.add("  c.cod_ncm, ");
            query.add("  b.cod_nop_de_ent, ");
            query.add("  b.cod_nop_fe_ent, ");
            query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
            query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
            query.add("  b.cod_tributacao_ipi as cod_ipi, ");
            query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
            query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
            query.add("FROM tab_item a ");
            query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
            query.add("LEFT  JOIN tab_ncm c ON(c.seq_ncm = a.seq_ncm) ");
            query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
            query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
            query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
            query.add("AND b.ind_item_ativo = 'S' ");

            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (a.des_item ILIKE :filtro) OR ");
                query.add("  (a.cod_barra ILIKE :filtro) OR ");
                query.add("  (c.cod_ncm ILIKE :filtro) OR ");
                query.add("  (CAST(a.cod_item as TEXT) ILIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.des_item");

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
                query.add("SELECT ");
                query.add("  a.cod_item, ");
                query.add("  a.des_item, ");
                query.add("  a.cod_barra, ");
                query.add("  b.ind_item_ativo, ");
                query.add("  a.cod_unidade, ");
                query.add("  a.cod_unidade_agrupamento_1, ");
                query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
                query.add("  a.qtd_unidade_agrupamento_1, ");
                query.add("  a.cod_unidade_agrupamento_2, ");
                query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
                query.add("  a.qtd_unidade_agrupamento_2, ");
                query.add("  b.cod_tipo_despesa, ");
                query.add("  d.cod_ncm, ");
                query.add("  b.cod_nop_de_ent, ");
                query.add("  b.cod_nop_fe_ent, ");
                query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
                query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
                query.add("  b.cod_tributacao_ipi as cod_ipi, ");
                query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
                query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
                query.add("FROM tab_item a ");
                query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
                query.add("INNER JOIN tab_item_fornecedor c ON(c.cod_item = a.cod_item) ");
                query.add("LEFT  JOIN tab_ncm d ON(d.seq_ncm = a.seq_ncm) ");
                query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
                query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
                query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
                query.add("AND b.ind_item_ativo = 'S' ");
                query.add("AND c.cod_pessoa_fornecedor = " + codFornecedor + " ");
                //query.add("AND REPLACE(c.cod_item_fornecedor, ' ', '') = '" + codigo + "' ");
                query.add("AND TRIM(LEADING '0' FROM REPLACE(c.cod_item_fornecedor, ' ', '')) = TRIM(LEADING '0' FROM '" + codigo + "') ");
                query.add("LIMIT 1 ");
            });

            if (produto == null) {
                produto = nativeFind(query -> {
                    query.add("SELECT ");
                    query.add("  a.cod_item, ");
                    query.add("  a.des_item, ");
                    query.add("  a.cod_barra, ");
                    query.add("  b.ind_item_ativo, ");
                    query.add("  a.cod_unidade, ");
                    query.add("  a.cod_unidade_agrupamento_1, ");
                    query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
                    query.add("  a.qtd_unidade_agrupamento_1, ");
                    query.add("  a.cod_unidade_agrupamento_2, ");
                    query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
                    query.add("  a.qtd_unidade_agrupamento_2, ");
                    query.add("  b.cod_tipo_despesa, ");
                    query.add("  d.cod_ncm, ");
                    query.add("  b.cod_nop_de_ent, ");
                    query.add("  b.cod_nop_fe_ent, ");
                    query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
                    query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
                    query.add("  b.cod_tributacao_ipi as cod_ipi, ");
                    query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
                    query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
                    query.add("FROM tab_item a ");
                    query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
                    query.add("LEFT  JOIN tab_ncm d ON(d.seq_ncm = a.seq_ncm) ");
                    query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
                    query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
                    query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
                    query.add("AND b.ind_item_ativo = 'S' ");
                    query.add("AND a.cod_pessoa_fornecedor = " + codFornecedor + " ");
                    //query.add("AND REPLACE(a.cod_referencia, ' ', '') = '" + codigo + "' ");
                    query.add("AND TRIM(LEADING '0' FROM REPLACE(a.cod_referencia, ' ', '')) = TRIM(LEADING '0' FROM '" + codigo + "') ");
                });
            }
        }

        if ((produto == null) && Strings.isNonEmpty(eanTrib)) {
            produto = nativeFind(query -> {
                query.add("SELECT ");
                query.add("  a.cod_item, ");
                query.add("  a.des_item, ");
                query.add("  a.cod_barra, ");
                query.add("  b.ind_item_ativo, ");
                query.add("  a.cod_unidade, ");
                query.add("  a.cod_unidade_agrupamento_1, ");
                query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
                query.add("  a.qtd_unidade_agrupamento_1, ");
                query.add("  a.cod_unidade_agrupamento_2, ");
                query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
                query.add("  a.qtd_unidade_agrupamento_2, ");
                query.add("  b.cod_tipo_despesa, ");
                query.add("  d.cod_ncm, ");
                query.add("  b.cod_nop_de_ent, ");
                query.add("  b.cod_nop_fe_ent, ");
                query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
                query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
                query.add("  b.cod_tributacao_ipi as cod_ipi, ");
                query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
                query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
                query.add("FROM tab_item a ");
                query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
                query.add("LEFT  JOIN tab_ncm d ON(d.seq_ncm = a.seq_ncm) ");
                query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
                query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
                query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
                query.add("AND b.ind_item_ativo = 'S' ");
                query.add("AND a.cod_barra = '" + eanTrib + "' ");
            });

            if (produto == null) {
                produto = nativeFind(query -> {
                    query.add("SELECT ");
                    query.add("  a.cod_item, ");
                    query.add("  a.des_item, ");
                    query.add("  a.cod_barra, ");
                    query.add("  b.ind_item_ativo, ");
                    query.add("  a.cod_unidade, ");
                    query.add("  a.cod_unidade_agrupamento_1, ");
                    query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
                    query.add("  a.qtd_unidade_agrupamento_1, ");
                    query.add("  a.cod_unidade_agrupamento_2, ");
                    query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
                    query.add("  a.qtd_unidade_agrupamento_2, ");
                    query.add("  b.cod_tipo_despesa, ");
                    query.add("  d.cod_ncm, ");
                    query.add("  b.cod_nop_de_ent, ");
                    query.add("  b.cod_nop_fe_ent, ");
                    query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
                    query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
                    query.add("  b.cod_tributacao_ipi as cod_ipi, ");
                    query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
                    query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
                    query.add("FROM tab_item a ");
                    query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
                    query.add("INNER JOIN tab_item_embalagem c ON(c.cod_item = a.cod_item) ");
                    query.add("LEFT  JOIN tab_ncm d ON(d.seq_ncm = a.seq_ncm) ");
                    query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
                    query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
                    query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
                    query.add("AND b.ind_item_ativo = 'S' ");
                    query.add("AND c.cod_barra = '" + eanTrib + "' ");
                    query.add("AND c.ind_embalagem = 'N' ");
                });
            }
        }

        if ((produto == null) && Strings.isNonEmpty(ean)) {
            produto = nativeFind(query -> {
                query.add("SELECT ");
                query.add("  a.cod_item, ");
                query.add("  a.des_item, ");
                query.add("  a.cod_barra, ");
                query.add("  b.ind_item_ativo, ");
                query.add("  a.cod_unidade, ");
                query.add("  a.cod_unidade_agrupamento_1, ");
                query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
                query.add("  a.qtd_unidade_agrupamento_1, ");
                query.add("  a.cod_unidade_agrupamento_2, ");
                query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
                query.add("  a.qtd_unidade_agrupamento_2, ");
                query.add("  b.cod_tipo_despesa, ");
                query.add("  d.cod_ncm, ");
                query.add("  b.cod_nop_de_ent, ");
                query.add("  b.cod_nop_fe_ent, ");
                query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
                query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
                query.add("  b.cod_tributacao_ipi as cod_ipi, ");
                query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
                query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
                query.add("FROM tab_item a ");
                query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
                query.add("LEFT  JOIN tab_ncm d ON(d.seq_ncm = a.seq_ncm) ");
                query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
                query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
                query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
                query.add("AND b.ind_item_ativo = 'S' ");
                query.add("AND a.cod_barra = '" + ean + "' ");
            });

            if (produto == null) {
                produto = nativeFind(query -> {
                    query.add("SELECT ");
                    query.add("  a.cod_item, ");
                    query.add("  a.des_item, ");
                    query.add("  a.cod_barra, ");
                    query.add("  b.ind_item_ativo, ");
                    query.add("  a.cod_unidade, ");
                    query.add("  a.cod_unidade_agrupamento_1, ");
                    query.add("  g1.sgl_unidade as sgl_unidade_agrupamento_1, ");
                    query.add("  a.qtd_unidade_agrupamento_1, ");
                    query.add("  a.cod_unidade_agrupamento_2, ");
                    query.add("  g2.sgl_unidade as sgl_unidade_agrupamento_2, ");
                    query.add("  a.qtd_unidade_agrupamento_2, ");
                    query.add("  b.cod_tipo_despesa, ");
                    query.add("  d.cod_ncm, ");
                    query.add("  b.cod_nop_de_ent, ");
                    query.add("  b.cod_nop_fe_ent, ");
                    query.add("  a.cod_nop_transf_entrada_de as cod_nop_de_tra, ");
                    query.add("  a.cod_nop_transf_entrada_fe as cod_nop_fe_tra, ");
                    query.add("  b.cod_tributacao_ipi as cod_ipi, ");
                    query.add("  b.cod_tributacao_pis_entrada as cod_pis, ");
                    query.add("  b.cod_tributacao_cofins_entrada as cod_cofins ");
                    query.add("FROM tab_item a ");
                    query.add("INNER JOIN tab_item_empresa b ON(b.cod_item = a.cod_item) ");
                    query.add("INNER JOIN tab_item_embalagem c ON(c.cod_item = a.cod_item) ");
                    query.add("LEFT  JOIN tab_ncm d ON(d.seq_ncm = a.seq_ncm) ");
                    query.add("LEFT  JOIN tab_unidade g1 ON(g1.cod_unidade = a.cod_unidade_agrupamento_1) ");
                    query.add("LEFT  JOIN tab_unidade g2 ON(g2.cod_unidade = a.cod_unidade_agrupamento_2) ");
                    query.add("WHERE b.cod_empresa = " + codEmpresa + " ");
                    query.add("AND b.ind_item_ativo = 'S' ");
                    query.add("AND c.cod_barra = '" + ean + "' ");
                    query.add("AND c.ind_embalagem = 'N' ");
                });
            }
        }

        return produto;
    }

    public ProdutoFor getProdutoFor(Integer codItem, Integer codFornecedor, String codItemFornecedor) {
        ProdutoFor produto = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.cod_item, ");
            query.add("  a.cod_unidade_agrupamento, ");
            query.add("  a.qtd_unidade_agrupamento ");
            query.add("FROM tab_item_fornecedor a ");
            query.add("INNER JOIN tab_unidade b ON(b.cod_unidade = a.cod_unidade_agrupamento) ");
            query.add("WHERE a.cod_item = " + codItem + " ");
            query.add("AND a.cod_pessoa_fornecedor = " + codFornecedor + " ");
            query.add("AND a.cod_item_fornecedor = '" + codItemFornecedor + "' ");
            query.add("AND a.cod_unidade_agrupamento IS NOT NULL ");
            query.add("AND a.cod_unidade_agrupamento > 0 ");
        }, ProdutoFor.class);

        if (produto == null) {
            produto = nativeFind(query -> {
                query.add("SELECT ");
                query.add("  a.cod_item, ");
                query.add("  a.cod_unidade_agrupamento, ");
                query.add("  a.qtd_unidade_agrupamento ");
                query.add("FROM tab_item_fornecedor a ");
                query.add("INNER JOIN tab_unidade b ON(b.cod_unidade = a.cod_unidade_agrupamento) ");
                query.add("WHERE a.cod_item = " + codItem + " ");
                query.add("AND a.cod_pessoa_fornecedor = " + codFornecedor + " ");
                query.add("AND TRIM(LEADING '0' FROM a.cod_item_fornecedor) = TRIM(LEADING '0' FROM '" + codItemFornecedor + "') ");
                query.add("AND a.cod_unidade_agrupamento IS NOT NULL ");
                query.add("AND a.cod_unidade_agrupamento > 0 ");
            }, ProdutoFor.class);
        }

        return produto;
    }

}
