package br.com.nfast.api.repo.conhecimento;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.nfe.ConhecimentoNota;
import br.com.nfast.api.repo.nfe.NFeResumoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConhecimentoNotaRepo extends DataRepository<ConhecimentoNota, Integer> {

    @Autowired
    private NFeResumoRepo nFeResumoRepo;

    public ConhecimentoNotaRepo() {
        super(ConhecimentoNota.class);
    }

    public void excluiConhecimentoNota(Long seqNotaEntrada) {
        executeNative("DELETE FROM tab_conhecimento_nota WHERE seq_nota = " + seqNotaEntrada);
    }

    public void gravaConhecimentoNota(Integer seqNota, Integer seqConhecimento, String indEntradaSaida) {
        executeNative(query -> {
            query.add("INSERT INTO tab_conhecimento_nota (seq_nota, seq_conhecimento, ind_entrada_saida) ");
            query.add("VALUES (:seq_nota, :seq_conhecimento, :ind_entrada_saida) ");
            query.set("seq_nota", seqNota);
            query.set("seq_conhecimento", seqConhecimento);
            query.set("ind_entrada_saida", indEntradaSaida);
        });
    }
}
