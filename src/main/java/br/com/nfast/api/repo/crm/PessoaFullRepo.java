package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.crm.PessoaFull;
import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public class PessoaFullRepo extends DataRepository<PessoaFull, Integer> {

    public PessoaFullRepo() {
        super(PessoaFull.class);
    }

    public PessoaFull gravaPessoa(final PessoaFull pessoa) {
        pessoa.setNumCnpjCpf(Strings.onlyNumbers(pessoa.getNumCnpjCpf()));
        if (Strings.isEmpty(pessoa.getNumCnpjCpf()))
            throw new RuntimeException("CNPJ/CPF não informado");
        if ((pessoa.getNumCnpjCpf().length() != 11) && (pessoa.getNumCnpjCpf().length() != 14))
            throw new RuntimeException("Tamanho do CNPJ/CPF inválido");
        if (Strings.isEmpty(pessoa.getIndNatureza())) {
            if (pessoa.getNumCnpjCpf().length() == 11)
                pessoa.setIndNatureza("F");
            if (pessoa.getNumCnpjCpf().length() == 14)
                pessoa.setIndNatureza("J");
        }

        if (Numbers.isEmpty(pessoa.getCodCidade()))
            throw new RuntimeException("Cidade não informada");

        if (Numbers.isEmpty(pessoa.getCodPessoa()))
            pessoa.setCodPessoa(nativeFindValue("SELECT COALESCE(MAX(cod_pessoa), 0) + 1 FROM tab_pessoa"));
        if (pessoa.getDtaCadastro() == null)
            pessoa.setDtaCadastro(LocalDate.now());
        if (Strings.isEmpty(pessoa.getHraCadastro()))
            pessoa.setHraCadastro(Dates.format(LocalTime.now(), "HH:mm:ss"));

        if (Strings.equals(pessoa.getIndCliente(), "S"))
            pessoa.setIndSacado("S");
        if (Strings.equals(pessoa.getIndFornecedor(), "S"))
            pessoa.setIndSacador("S");

        PessoaFull pessoaNew = save(pessoa);
        return pessoaNew;
    }

}
