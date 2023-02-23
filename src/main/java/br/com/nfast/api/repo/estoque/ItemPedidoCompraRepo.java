package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ItemPedidoCompra;
import br.com.nfast.api.model.estoque.ItemPedidoCompraId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemPedidoCompraRepo extends DataRepository<ItemPedidoCompra, ItemPedidoCompraId> {

    public ItemPedidoCompraRepo() {
        super(ItemPedidoCompra.class);
    }

    public ItemPedidoCompra itemPedidoCompra(Integer seqPedido, Integer seqItem) {
        /*ItemPedidoCompra item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.seq_pedido, ");
            query.add("  a.seq_item, ");
            query.add("  a.cod_item, ");
            query.add("  a.qtd_item, ");
            query.add("  a.qtd_item_convertido, ");
            query.add("  a.val_unitario, ");
            query.add("  a.val_desconto, ");
            query.add("  a.val_total, ");
            query.add("  a.qtd_atendido, ");
            query.add("  a.qtd_cancelado, ");
            query.add("  (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) as qtd_restante, ");
            query.add("  a.cod_unidade, ");
            query.add("  c.sgl_unidade, ");
            query.add("  a.cod_almoxarifado, ");
            query.add("  d.des_almoxarifado, ");
            query.add("  a.cod_natureza_operacao, ");
            query.add("  (e.num_cfop||' - '||e.des_natureza_operacao) as des_natureza_operacao, ");
            query.add("  a.cod_tipo_despesa, ");
            query.add("  a.cod_centro_custo, ");
            query.add("  a.des_observacao, ");
            query.add("  a.dta_previsao_entrega, ");
            query.add("  b.dta_emissao, ");
            query.add("  b.nom_usuario, ");
            query.add("  b.des_observacao as des_observacao_pc ");
            query.add("FROM tab_item_pedido_compra a ");
            query.add("INNER JOIN tab_pedido_compra b ON(b.seq_pedido = a.seq_pedido) ");
            query.add("INNER JOIN tab_unidade c ON(c.cod_unidade = a.cod_unidade) ");
            query.add("INNER JOIN tab_almoxarifado d ON(d.cod_almoxarifado = a.cod_almoxarifado) ");
            query.add("LEFT  JOIN tab_natureza_operacao e ON(e.cod_natureza_operacao = a.cod_natureza_operacao) ");
            query.add("WHERE a.seq_pedido = " + seqPedido);
            query.add("AND a.seq_item = " + seqItem);
        });

        return item;*/
        return null;
    }

    public ItemPedidoCompra itemPedidoCompraNfe(String cnpjCpfFor, String cnpjEmpresa, Long codItem, Double qtdTotal, String verificaQtdPedido, String permiteQtdMenorPedido) {
        /*ItemPedidoCompra item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.seq_pedido, ");
            query.add("  a.seq_item, ");
            query.add("  a.cod_item, ");
            query.add("  a.qtd_item, ");
            query.add("  a.qtd_item_convertido, ");
            query.add("  a.val_unitario, ");
            query.add("  a.val_desconto, ");
            query.add("  a.val_total, ");
            query.add("  a.qtd_atendido, ");
            query.add("  a.qtd_cancelado, ");
            query.add("  (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) as qtd_restante, ");
            query.add("  a.cod_unidade, ");
            query.add("  c.sgl_unidade, ");
            query.add("  a.cod_almoxarifado, ");
            query.add("  d.des_almoxarifado, ");
            query.add("  a.cod_natureza_operacao, ");
            query.add("  (e.num_cfop||' - '||e.des_natureza_operacao) as des_natureza_operacao, ");
            query.add("  a.cod_tipo_despesa, ");
            query.add("  a.cod_centro_custo, ");
            query.add("  a.des_observacao, ");
            query.add("  a.dta_previsao_entrega, ");
            query.add("  b.dta_emissao, ");
            query.add("  b.nom_usuario, ");
            query.add("  b.des_observacao as des_observacao_pc ");
            query.add("FROM tab_item_pedido_compra a ");
            query.add("INNER JOIN tab_pedido_compra b ON(b.seq_pedido = a.seq_pedido) ");
            query.add("INNER JOIN tab_unidade c ON(c.cod_unidade = a.cod_unidade) ");
            query.add("INNER JOIN tab_almoxarifado d ON(d.cod_almoxarifado = a.cod_almoxarifado) ");
            query.add("LEFT  JOIN tab_natureza_operacao e ON(e.cod_natureza_operacao = a.cod_natureza_operacao) ");
            query.add("INNER JOIN tab_pessoa f ON(f.cod_pessoa = b.cod_pessoa_fornecedor) ");
            query.add("INNER JOIN tab_empresa g ON(g.cod_empresa = b.cod_empresa) ");
            query.add("WHERE f.num_cnpj_cpf LIKE :cnpjCpfFor ");
            query.add("AND g.num_cnpj = :cnpjEmpresa ");
            query.add("AND a.cod_item = " + codItem);
            query.add("AND (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) > 0.0 ");

            if (Strings.equals(verificaQtdPedido, "S")) {
                if (Strings.equals(permiteQtdMenorPedido, "S")) {
                    query.add("AND (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) >= :qtdTotal");
                } else {
                    query.add("AND (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) = :qtdTotal");
                }
                query.set("qtdTotal", qtdTotal);
            }

            query.add("AND COALESCE(b.ind_status, 'P') <> 'C' ");
            query.add("AND ( ");
            query.add("  CASE WHEN EXISTS(SELECT 1 FROM tab_parametro_empresa x WHERE x.cod_empresa = b.cod_empresa AND x.cod_parametro = 168 AND x.val_parametro = 'S') ");
            query.add("    THEN b.ind_status_aprovacao_alcada = 'AP' ");
            query.add("    ELSE b.seq_log_autorizacao > 0 ");
            query.add("  END");
            query.add(") ");
            query.add("ORDER BY b.dta_emissao, a.seq_pedido ");
            query.add("LIMIT 1 ");

            query.set("cnpjCpfFor", cnpjCpfFor + "%");
            query.set("cnpjEmpresa", cnpjEmpresa);
        });

        return item; */
        return null;
    }

    public List<ItemPedidoCompra> itemPedidoCompraList(String cnpjCpfFor, String cnpjEmpresa, Long codItem, String somenteAutorizado, String filtro, Integer limit, Integer offset) {
        /*List<ItemPedidoCompra> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.seq_pedido, ");
            query.add("  a.seq_item, ");
            query.add("  a.cod_item, ");
            query.add("  a.qtd_item, ");
            query.add("  a.qtd_item_convertido, ");
            query.add("  a.val_unitario, ");
            query.add("  a.val_desconto, ");
            query.add("  a.val_total, ");
            query.add("  a.qtd_atendido, ");
            query.add("  a.qtd_cancelado, ");
            query.add("  (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) as qtd_restante, ");
            query.add("  a.cod_unidade, ");
            query.add("  c.sgl_unidade, ");
            query.add("  a.cod_almoxarifado, ");
            query.add("  d.des_almoxarifado, ");
            query.add("  a.cod_natureza_operacao, ");
            query.add("  (e.num_cfop||' - '||e.des_natureza_operacao) as des_natureza_operacao, ");
            query.add("  a.cod_tipo_despesa, ");
            query.add("  a.cod_centro_custo, ");
            query.add("  a.des_observacao, ");
            query.add("  a.dta_previsao_entrega, ");
            query.add("  b.dta_emissao, ");
            query.add("  b.nom_usuario, ");
            query.add("  b.des_observacao as des_observacao_pc ");
            query.add("FROM tab_item_pedido_compra a ");
            query.add("INNER JOIN tab_pedido_compra b ON(b.seq_pedido = a.seq_pedido) ");
            query.add("INNER JOIN tab_unidade c ON(c.cod_unidade = a.cod_unidade) ");
            query.add("INNER JOIN tab_almoxarifado d ON(d.cod_almoxarifado = a.cod_almoxarifado) ");
            query.add("LEFT  JOIN tab_natureza_operacao e ON(e.cod_natureza_operacao = a.cod_natureza_operacao) ");
            query.add("INNER JOIN tab_pessoa f ON(f.cod_pessoa = b.cod_pessoa_fornecedor) ");
            query.add("INNER JOIN tab_empresa g ON(g.cod_empresa = b.cod_empresa) ");
            query.add("WHERE f.num_cnpj_cpf LIKE :cnpjCpfFor ");
            query.add("AND g.num_cnpj = :cnpjEmpresa ");
            query.add("AND a.cod_item = " + codItem);
            query.add("AND (a.qtd_item_convertido - a.qtd_atendido - a.qtd_cancelado) > 0.0 ");

            if (Strings.equals(somenteAutorizado, "S")) {
                query.add("AND COALESCE(b.ind_status, 'P') <> 'C' ");
                query.add("AND ( ");
                query.add("  CASE WHEN EXISTS(SELECT 1 FROM tab_parametro_empresa x WHERE x.cod_empresa = b.cod_empresa AND x.cod_parametro = 168 AND x.val_parametro = 'S') ");
                query.add("    THEN b.ind_status_aprovacao_alcada = 'AP' ");
                query.add("    ELSE b.seq_log_autorizacao >= 0 ");
                query.add("  END");
                query.add(") ");
            }

            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (b.nom_usuario ILIKE :filtro) OR ");
                query.add("  (a.des_observacao ILIKE :filtro) OR ");
                query.add("  (b.des_observacao ILIKE :filtro) OR ");
                query.add("  (CAST(a.seq_pedido as TEXT) ILIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY b.dta_emissao, a.seq_pedido");

            query.set("cnpjEmpresa", cnpjEmpresa);
            query.set("cnpjCpfFor", cnpjCpfFor + "%");

            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;*/
        return null;
    }

}
