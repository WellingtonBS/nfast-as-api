package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoEmpresaIdCad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cod_item", insertable = false, updatable = false)
    private Long codItem;
    @Column(name = "cod_empresa", insertable = false, updatable = false)
    private Long codEmpresa;

    public ProdutoEmpresaIdCad() {

    }

    public Long getCodItem() {
        return codItem;
    }

    public void setCodItem(Long codItem) {
        this.codItem = codItem;
    }

    public Long getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Long codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codItem, codEmpresa);
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass()))
            return false;

        final ProdutoEmpresaIdCad other = (ProdutoEmpresaIdCad) obj;
        return Objects.equals(codItem, other.codItem) && Objects.equals(codEmpresa, other.codEmpresa);
    }

}
