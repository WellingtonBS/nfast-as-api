package br.com.nfast.api.repo.adm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.adm.Despesa;
import br.com.nfast.api.utils.Numbers;
import org.springframework.stereotype.Repository;

@Repository
public class DespesaRepo extends DataRepository<Despesa, Integer> {

    public DespesaRepo() {
        super(Despesa.class);
    }

    @Override
    public <S extends Despesa> S save(S despesa) {
        if (Numbers.isEmpty(despesa.getSeqDespesa()))
            despesa.setSeqDespesa(nativeFindValue("SELECT CAST(nextval('gen_outras_despesas') as INTEGER)"));

        despesa.prepareToSave();
        return super.save(despesa);
    }
}
