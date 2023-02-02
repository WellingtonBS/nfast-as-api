package br.com.nfast.api.services;

import br.com.nfast.api.model.financeiro.*;
import br.com.nfast.api.repo.financeiro.ContaBancoRepo;
import br.com.nfast.api.repo.financeiro.EspecieCaixaRepo;
import br.com.nfast.api.repo.financeiro.TipoCobrancaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FinanceiroController implements FinanceiroApi {
    @Autowired
    private EspecieCaixaRepo especieCaixaRepo;
    @Autowired
    private ContaBancoRepo contaBancoRepo;
    @Autowired
    private TipoCobrancaRepo tipoCobrancaRepo;

    @Override
    public ResponseEntity<EspecieCaixa> especieCaixa(String token, String clientId, Integer codEspecieCaixa) {
        EspecieCaixa item = especieCaixaRepo.especieCaixa(codEspecieCaixa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EspecieCaixa>> especieCaixaList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<EspecieCaixa> list = especieCaixaRepo.especieCaixaList(filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContaBanco> contaBanco(String token, String clientId, String numMnemonico) {
        ContaBanco item = contaBancoRepo.contaBanco(numMnemonico);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContaBanco>> contaBancoList(String token, String clientId, Long codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ContaBanco> list = contaBancoRepo.contasBanco(codEmpresa, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoCobranca> tipoCobranca(String token, String clientId, Integer codTipoCobranca) {
        TipoCobranca item = tipoCobrancaRepo.tipoCobranca(codTipoCobranca);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TipoCobranca>> tipoCobrancaList(String token, String clientId, Long codEmpresa, String filtro, Integer limit, Integer offset) {
        List<TipoCobranca> list = tipoCobrancaRepo.tipoCobrancaList(codEmpresa, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaldoCaixa> saldoCaixa(String token, String clientId, Long codEmpresa, Integer codEspecieCaixa) {
        SaldoCaixa saldoCaixa = new SaldoCaixa();
        saldoCaixa.setCodEmpresa(codEmpresa);
        saldoCaixa.setCodEspecieCaixa(codEspecieCaixa);
        saldoCaixa.setVlrSaldo(especieCaixaRepo.getSaldoCaixa(codEmpresa, codEspecieCaixa));
        return new ResponseEntity<>(saldoCaixa, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaldoBanco> saldoBanco(String token, String clientId, Long codEmpresa, String numMnemonico) {
        SaldoBanco saldoBanco = new SaldoBanco();
        saldoBanco.setCodEmpresa(codEmpresa);
        saldoBanco.setNumMnemonico(numMnemonico);
        saldoBanco.setVlrSaldo(contaBancoRepo.getSaldoBanco(codEmpresa, numMnemonico));
        return new ResponseEntity<>(saldoBanco, HttpStatus.OK);
    }

}
