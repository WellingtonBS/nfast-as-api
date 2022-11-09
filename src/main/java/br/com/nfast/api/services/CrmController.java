package br.com.nfast.api.services;

import br.com.nfast.api.model.crm.Cidade;
import br.com.nfast.api.model.crm.Pessoa;
import br.com.nfast.api.model.crm.PessoaFull;
import br.com.nfast.api.repo.crm.CidadeRepo;
import br.com.nfast.api.repo.crm.PessoaFullRepo;
import br.com.nfast.api.repo.crm.PessoaRepo;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CrmController implements CrmApi {
    @Autowired
    private PessoaRepo pessoaRepo;
    @Autowired
    private PessoaFullRepo pessoaFullRepo;
    @Autowired
    private CidadeRepo cidadeRepo;

    @Override
    public ResponseEntity<Pessoa> pessoa(String token, String clientId, Integer codPessoa, String numCnpjCpf) {
        List<Pessoa> list = pessoaRepo.findAll(query -> {
            query.add("SELECT a FROM Pessoa a ");
            if (Numbers.isNonEmpty(codPessoa)) {
                query.add("WHERE a.codPessoa = :codPessoa ");
                query.set("codPessoa", codPessoa);
            } else if (Strings.isNonEmpty(numCnpjCpf)) {
                query.add("WHERE a.numCnpjCpf = :numCnpjCpf ");
                query.set("numCnpjCpf", numCnpjCpf);
            }
        });

        Pessoa pessoa = null;
        for (Pessoa item : list) {
            pessoa = item;
            if (Strings.equals(pessoa.getIndPessoaAtiva(), "S"))
                break;
        }

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Pessoa>> pessoaList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<Pessoa> list = pessoaRepo.findAll(query -> {
            query.add("SELECT a FROM Pessoa a ");
            query.add("WHERE a.indPessoaAtiva = 'S' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codPessoa, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.numCnpjCpf) LIKE :filtro) OR ");
                query.add("  (LOWER(a.nomPessoa) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.nomPessoa");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Pessoa>> pessoaListCnpjCpf(String token, String clientId, String cnpjCpf, Integer limit, Integer offset) {
        List<Pessoa> list = pessoaRepo.findAll(query -> {
            query.add("SELECT a FROM Pessoa a ");
            query.add("WHERE a.indPessoaAtiva = 'S' ");
            if (Strings.isNonEmpty(cnpjCpf)) {
                query.add("AND a.numCnpjCpf = :cnpjCpf ");
                query.set("cnpjCpf", cnpjCpf);
            }
            query.add("ORDER BY a.nomPessoa");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PessoaFull> gravaPessoa(String token, String clientId, PessoaFull pessoa) {
        PessoaFull item = pessoaFullRepo.gravaPessoa(pessoa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cidade> obtemCidade(String token, String clientId, Long codCidade, String codIbge, String nomCidade, String sglEstado) {
        Cidade item = cidadeRepo.obtemCidade(codCidade, codIbge, nomCidade, sglEstado);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
