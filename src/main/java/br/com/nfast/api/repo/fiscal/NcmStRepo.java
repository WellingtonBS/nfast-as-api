package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.NcmSt;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class NcmStRepo extends DataRepository<NcmSt, Integer> {

    public NcmStRepo() {
        super(NcmSt.class);
    }

    public NcmSt ncmSt(Integer codItem, String uf, LocalDate data) {
        NcmSt item = nativeFind(query -> {
            query.add("SELECT a.seq_ncm, a.per_aliquota, a.per_mark_up, a.val_unitario, a.val_preco ");
            query.add("FROM tab_ncm_st a ");
            query.add("INNER JOIN tab_item b ON(b.seq_ncm = a.seq_ncm) ");
            query.add("INNER JOIN tab_estado c ON(c.cod_estado = a.cod_estado) ");
            query.add("WHERE b.cod_item = :cod_item ");
            query.add("AND c.sgl_estado = :uf ");
            query.add("AND a.ind_operacao = 'E' ");
            query.add("AND a.dta_inicio_validade <= :data ");
            query.add("AND ((a.dta_fim_validade IS NULL) OR (a.dta_fim_validade >= :data)) ");
            query.add("ORDER BY a.dta_inicio_validade DESC LIMIT 1 ");

            query.set("cod_item", codItem);
            query.set("uf", uf);
            query.set("data", data);
        });

        return item;
    }

}
