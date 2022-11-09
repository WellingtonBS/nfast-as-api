package br.com.nfast.api.repo.financeiro;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.financeiro.EspecieCaixa;
import org.springframework.stereotype.Repository;

@Repository
public class EspecieCaixaRepo extends DataRepository<EspecieCaixa, Integer> {

    public EspecieCaixaRepo() {
        super(EspecieCaixa.class);
    }

    public Double getSaldoCaixa(Integer codEmpresa, Integer codEspecieCaixa) {
        Double vlrSaldo = nativeFindValue("SELECT sp_obtem_saldo_caixa(" + codEmpresa + ", " + codEspecieCaixa + ") ");
        return vlrSaldo;
    }

}
