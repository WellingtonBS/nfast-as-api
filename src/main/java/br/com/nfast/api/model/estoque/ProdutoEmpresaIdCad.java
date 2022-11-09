package br.com.nfast.api.model.estoque;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoEmpresaIdCad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cod_item", insertable = false, updatable = false)
    private Integer codItem;
    @Column(name = "cod_empresa", insertable = false, updatable = false)
    private Integer codEmpresa;

    public ProdutoEmpresaIdCad() {

    }

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public Integer getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(Integer codEmpresa) {
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
