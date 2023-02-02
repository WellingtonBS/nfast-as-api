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

    public NcmSt ncmSt(Long codItem, String uf, LocalDate data) {
        NcmSt item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  only_numbers(a.codigo) AS seq_ncm, ");
            query.add("  0 AS per_aliquota, ");
            query.add("  0 AS per_mark_up, ");
            query.add("  0 AS val_unitario, ");
            query.add("  0 AS val_preco ");
            query.add("FROM ncm a ");
            query.add("INNER JOIN produto b ON (a.codigo = b.codigo_ncm) ");
            query.add("WHERE b.codigo = ' " + codItem + "' ");

            //query.set("cod_item", codItem);
            //query.set("uf", uf);
            //query.set("data", data);
        });

        return item;
    }

}
