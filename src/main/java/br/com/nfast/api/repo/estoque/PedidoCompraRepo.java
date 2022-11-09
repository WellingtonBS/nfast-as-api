package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.PedidoCompra;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoCompraRepo extends DataRepository<PedidoCompra, Integer> {

    public PedidoCompraRepo() {
        super(PedidoCompra.class);
    }

    public PedidoCompra pedidoCompra(Integer seqPedido) {
        PedidoCompra item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.seq_pedido, ");
            query.add("  a.cod_empresa, ");
            query.add("  a.cod_pessoa_fornecedor, ");
            query.add("  a.num_pedido_fornecedor, ");
            query.add("  a.dta_emissao, ");
            query.add("  a.val_pedido, ");
            query.add("  a.ind_pendente, ");
            query.add("  a.des_observacao, ");
            query.add("  a.nom_usuario, ");
            query.add("  a.seq_log_autorizacao, ");
            query.add("  a.ind_status_aprovacao_alcada ");
            query.add("FROM tab_pedido_compra a ");
            query.add("WHERE a.seq_pedido = " + seqPedido);
        });

        return item;
    }

    public List<PedidoCompra> pedidoCompraList(String cnpjCpfFor, String cnpjEmpresa, Integer codItem, String filtro, Integer limit, Integer offset) {
        List<PedidoCompra> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.seq_pedido, ");
            query.add("  a.cod_empresa, ");
            query.add("  a.cod_pessoa_fornecedor, ");
            query.add("  a.num_pedido_fornecedor, ");
            query.add("  a.dta_emissao, ");
            query.add("  a.val_pedido, ");
            query.add("  a.ind_pendente, ");
            query.add("  a.des_observacao, ");
            query.add("  a.nom_usuario, ");
            query.add("  a.seq_log_autorizacao, ");
            query.add("  a.ind_status_aprovacao_alcada ");
            query.add("FROM tab_pedido_compra a ");
            query.add("INNER JOIN tab_pessoa b ON(b.cod_pessoa = a.cod_pessoa_fornecedor) ");
            query.add("INNER JOIN tab_empresa c ON(c.cod_empresa = a.cod_empresa) ");
            query.add("WHERE b.num_cnpj_cpf LIKE :cnpjCpfFor ");
            query.set("cnpjCpfFor", cnpjCpfFor + "%");
            if (Strings.isNonEmpty(cnpjEmpresa)) {
                query.add("AND c.num_cnpj = :cnpjEmpresa ");
                query.set("cnpjEmpresa", cnpjEmpresa);
            }

            if (Numbers.isNonEmpty(codItem)) {
                query.add("AND EXISTS( ");
                query.add("  SELECT 1 ");
                query.add("  FROM tab_item_pedido_compra x ");
                query.add("  WHERE x.seq_pedido = a.seq_pedido ");
                query.add("  AND x.cod_item = " + codItem);
                query.add(") ");
            }

            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (a.nom_usuario ILIKE :filtro) OR ");
                query.add("  (a.des_observacao ILIKE :filtro) OR ");
                query.add("  (CAST(a.seq_pedido as TEXT) ILIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.dta_emissao, a.seq_pedido");

            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

}
