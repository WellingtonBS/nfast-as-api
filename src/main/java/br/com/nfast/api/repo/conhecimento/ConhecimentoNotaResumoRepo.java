package br.com.nfast.api.repo.conhecimento;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.conhecimento.ConhecimentoNotaResumo;
import br.com.nfast.api.repo.nfe.NFeResumoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class ConhecimentoNotaResumoRepo extends DataRepository<ConhecimentoNotaResumo, Integer> {

    @Autowired
    private NFeResumoRepo nFeResumoRepo;

    public ConhecimentoNotaResumoRepo() {
        super(ConhecimentoNotaResumo.class);
    }

    public BigInteger obtemQtdConhecimentoNota(Long seqConhecimento) {
        return nativeFindValue(query -> {
            query.add("SELECT count(*) ");
            query.add("FROM tab_conhecimento_nota a ");
            query.add("WHERE a.seq_conhecimento = :seq_conhecimento");
            query.set("seq_conhecimento", seqConhecimento);
        });

    }
}
