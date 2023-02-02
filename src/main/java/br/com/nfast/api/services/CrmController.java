package br.com.nfast.api.services;

import br.com.nfast.api.model.crm.Cidade;
import br.com.nfast.api.model.crm.Pessoa;
import br.com.nfast.api.model.crm.PessoaFull;
import br.com.nfast.api.repo.crm.CidadeRepo;
import br.com.nfast.api.repo.crm.PessoaFullRepo;
import br.com.nfast.api.repo.crm.PessoaRepo;
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
        Pessoa pessoa = pessoaRepo.pessoa(codPessoa, numCnpjCpf);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Pessoa>> pessoaList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<Pessoa> list = pessoaRepo.pessoaList(filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Pessoa>> pessoaListCnpjCpf(String token, String clientId, String cnpjCpf, Integer limit, Integer offset) {
        List<Pessoa> list = pessoaRepo.pessoaListCnpjCpf(cnpjCpf, limit, offset);
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
