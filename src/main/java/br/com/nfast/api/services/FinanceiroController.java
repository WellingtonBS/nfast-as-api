package br.com.nfast.api.services;

import br.com.nfast.api.model.financeiro.*;
import br.com.nfast.api.repo.financeiro.ContaBancoRepo;
import br.com.nfast.api.repo.financeiro.EspecieCaixaRepo;
import br.com.nfast.api.repo.financeiro.TipoCobrancaRepo;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
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
        EspecieCaixa item = especieCaixaRepo.findById(codEspecieCaixa).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EspecieCaixa>> especieCaixaList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<EspecieCaixa> list = especieCaixaRepo.findAll(query -> {
            query.add("SELECT a FROM EspecieCaixa a ");
            query.add("WHERE a.indStatus = 'A' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codEspecieCaixa, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.desEspecieCaixa) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.codEspecieCaixa ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContaBanco> contaBanco(String token, String clientId, String numMnemonico) {
        ContaBanco item = contaBancoRepo.contaBanco(numMnemonico);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContaBanco>> contaBancoList(String token, String clientId, Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ContaBanco> list = contaBancoRepo.contasBanco(codEmpresa, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoCobranca> tipoCobranca(String token, String clientId, Integer codTipoCobranca) {
        TipoCobranca item = tipoCobrancaRepo.findById(codTipoCobranca).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TipoCobranca>> tipoCobrancaList(String token, String clientId, Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<TipoCobranca> list = tipoCobrancaRepo.findAll(query -> {
            query.add("SELECT a FROM TipoCobranca a ");
            query.add("WHERE a.indStatus = 'A' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codTipoCobranca, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.desTipoCobranca) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("AND EXISTS( ");
            query.add("  SELECT b FROM TipoCobrancaEmpresa b ");
            query.add("  WHERE b.codTipoCobranca = a.codTipoCobranca ");
            query.add("  AND b.codEmpresa = " + codEmpresa + " ");
            query.add("  AND b.indTipo IN('P', 'T') ");
            query.add(") ");
            query.add("ORDER BY a.codTipoCobranca ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaldoCaixa> saldoCaixa(String token, String clientId, Integer codEmpresa, Integer codEspecieCaixa) {
        SaldoCaixa saldoCaixa = new SaldoCaixa();
        saldoCaixa.setCodEmpresa(codEmpresa);
        saldoCaixa.setCodEspecieCaixa(codEspecieCaixa);
        saldoCaixa.setVlrSaldo(especieCaixaRepo.getSaldoCaixa(codEmpresa, codEspecieCaixa));
        return new ResponseEntity<>(saldoCaixa, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaldoBanco> saldoBanco(String token, String clientId, Integer codEmpresa, String numMnemonico) {
        SaldoBanco saldoBanco = new SaldoBanco();
        saldoBanco.setCodEmpresa(codEmpresa);
        saldoBanco.setNumMnemonico(numMnemonico);
        saldoBanco.setVlrSaldo(contaBancoRepo.getSaldoBanco(codEmpresa, numMnemonico));
        return new ResponseEntity<>(saldoBanco, HttpStatus.OK);
    }

}
