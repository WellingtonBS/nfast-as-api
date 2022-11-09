package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoCompraId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "seq_pedido", insertable = false, updatable = false)
    private Integer seqPedido;
    @Column(name = "seq_item", insertable = false, updatable = false)
    private Integer seqItem;

    public ItemPedidoCompraId() {

    }

    public Integer getSeqPedido() {
        return seqPedido;
    }

    public void setSeqPedido(Integer seqPedido) {
        this.seqPedido = seqPedido;
    }

    public Integer getSeqItem() {
        return seqItem;
    }

    public void setSeqItem(Integer seqItem) {
        this.seqItem = seqItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqPedido, seqItem);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final ItemPedidoCompraId other = (ItemPedidoCompraId) obj;
        return Objects.equals(seqPedido, other.seqPedido) && Objects.equals(seqItem, other.seqItem);
    }

}
