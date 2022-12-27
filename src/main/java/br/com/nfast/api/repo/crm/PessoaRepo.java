package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.crm.Pessoa;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepo extends DataRepository<Pessoa, Integer> {

    public PessoaRepo() {
        super(Pessoa.class);
    }

    public Pessoa pessoa(Integer codPessoa, String numCnpjCpf) {

        List<Pessoa> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_pessoa, ");
            query.add("  only_numbers(a.cpf) AS num_cnpj_cpf, ");
            query.add("  normalize(a.nome) nom_pessoa, ");
            query.add("  CASE WHEN a.bloqueado = 'f' THEN 'N' ELSE 'S' END AS ind_bloqueado, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END ind_pessoa_ativa ");
            query.add("FROM pessoa a ");
            if (Numbers.isNonEmpty(codPessoa)) {
                query.add("WHERE a.codigo = :codPessoa ");
                query.set("codPessoa", codPessoa);
            } else if (Strings.isNonEmpty(numCnpjCpf)) {
                query.add("WHERE only_numbers(a.cpf) = :numCnpjCpf ");
                query.set("numCnpjCpf", numCnpjCpf);
            }
        });

        Pessoa pessoa = null;
        for (Pessoa item : list) {
            pessoa = item;
            if (Strings.equals(pessoa.getIndPessoaAtiva(), "S"))
                return pessoa;
            break;
        }
        return pessoa;

    }

    public List<Pessoa> pessoaList(String filtro, Integer limit, Integer offset) {

        List<Pessoa> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_pessoa, ");
            query.add("  only_numbers(a.cpf) AS num_cnpj_cpf, ");
            query.add("  normalize(a.nome) nom_pessoa, ");
            query.add("  CASE WHEN a.bloqueado = 'f' THEN 'N' ELSE 'S' END AS ind_bloqueado, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END ind_pessoa_ativa ");
            query.add("FROM pessoa a ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codigo, '') LIKE :filtro) OR ");
                query.add("  (LOWER(only_numbers(a.cpf)) LIKE :filtro) OR ");
                query.add("  (LOWER(normalize(a.nome)) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY normalize(a.nome)");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

    public List<Pessoa> pessoaListCnpjCpf(String cnpjCpf, Integer limit, Integer offset) {
        List<Pessoa> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_pessoa, ");
            query.add("  only_numbers(a.cpf) AS num_cnpj_cpf, ");
            query.add("  normalize(a.nome) nom_pessoa, ");
            query.add("  CASE WHEN a.bloqueado = 'f' THEN 'N' ELSE 'S' END AS ind_bloqueado, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END ind_pessoa_ativa ");
            query.add("FROM pessoa a ");
            query.add("WHERE a.flag = 'A'  ");
            if (Strings.isNonEmpty(cnpjCpf)) {
                query.add("AND only_numbers(a.cpf) = :cnpjCpf ");
                query.set("cnpjCpf", cnpjCpf);
            }
            query.add("ORDER BY normalize(a.nome)");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

}
