package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.crm.PessoaFull;
import br.com.nfast.api.utils.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class PessoaFullRepo extends DataRepository<PessoaFull, Integer> {

    private PessoaFull pessoa;
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
            pessoa.setCodPessoa(nativeFindValue("SELECT COALESCE(MAX(codigo), 0) + 1 FROM pessoa"));
        if (pessoa.getDtaCadastro() == null)
            pessoa.setDtaCadastro(LocalDate.now());
        if (Strings.isEmpty(pessoa.getHraCadastro()))
            pessoa.setHraCadastro(Dates.format(LocalTime.now(), "HH:mm:ss"));

        if (Strings.equals(pessoa.getIndCliente(), "S"))
            pessoa.setIndSacado("S");
        if (Strings.equals(pessoa.getIndFornecedor(), "S"))
            pessoa.setIndSacador("S");

        StringList sql = new StringList();
        sql.add("INSERT INTO pessoa ( ");
        sql.add(" fone, ");
        sql.add(" ult_usuario, ");
        sql.add(" complemento, ");
        sql.add(" fax, ");
        sql.add(" nome_reduzido, ");
        sql.add(" cep, ");
        sql.add(" municipio, ");
        sql.add(" tipo, ");
        sql.add(" bairro, ");
        sql.add(" logradouro, ");
        sql.add(" tipo_pessoa, ");
        sql.add(" codigo, ");
        sql.add(" cnae, ");
        sql.add(" obs, ");
        sql.add(" pais, ");
        sql.add(" nome, ");
        sql.add(" versao, ");
        sql.add(" flag, ");
        sql.add(" uf, ");
        sql.add(" cidade, ");
        sql.add(" download_xml_manifestacao, ");
        sql.add(" numero, ");
        sql.add(" ciencia_manifestacao_auto, ");
        sql.add(" inscr_est, ");
        sql.add(" estado, ");
        sql.add(" cpf, ");
        sql.add(" data_nasc, ");
        sql.add(" endereco ");
        sql.add(" ) VALUES ( ");
        sql.add(pessoa.getNumTelefone1() + ", ");
        sql.add(" '" + pessoa.getNomUsuarioCadastro() + "', ");
        sql.add(" '" + pessoa.getDesComplemento() + "', ");
        sql.add(" '" + pessoa.getNumTelefone2() + "', ");
        sql.add(" '" + pessoa.getNomFantasia() + "', ");
        sql.add(pessoa.getNumCep() + ", ");
        sql.add(pessoa.getCodCidade() + ", ");
        sql.add(" 'F', "); //tipo
        sql.add(" '" + pessoa.getNomBairro() + "', ");
        sql.add(" '" + pessoa.getDesLogradouro() + "', ");
        sql.add(" 'F', "); //tipo_pessoa
        sql.add(pessoa.getCodPessoa() + ", ");
        sql.add(" '', "); //cnae
        sql.add(" '', "); //obs
        sql.add(" 1058, "); //pais
        sql.add(" '" + pessoa.getNomPessoa() + "', ");
        sql.add(" '2.0', "); //versao
        sql.add(" 'A', "); //flag
        sql.add(" (SELECT uf FROM municipio_view WHERE codigo = " + pessoa.getCodCidade() + "), ");
        sql.add(" (SELECT municipio FROM municipio_view WHERE codigo = " + pessoa.getCodCidade() + "), ");
        sql.add(" TRUE, "); //download_xml_manifestacao
        sql.add(" 0,"); //numero
        sql.add(" FALSE, "); //ciencia_manifestacao_auto
        sql.add(" '" + pessoa.getNumIeRg() + "', ");
        sql.add(" (SELECT uf FROM municipio_view WHERE codigo = " + pessoa.getCodCidade() + "), ");
        sql.add(pessoa.getNumCnpjCpf() + ", ");
        sql.add(" CURRENT_DATE , ");
        sql.add(" '" + pessoa.getDesLogradouro() + "'");
        sql.add(") RETURNING codigo");

        Query q = em.createNativeQuery(sql.toString());
        Integer codPessoa = Cast.of(q.getSingleResult());


        PessoaFull pessoaNew = consultaPessoa(codPessoa);
        return pessoaNew;
    }

    public PessoaFull consultaPessoa(Integer codPessoa) {
        StringList sql = new StringList();
        sql.add("SELECT  ");
        sql.add(" CAST(a.codigo AS INTEGER) AS cod_pessoa,  ");
        sql.add(" only_numbers(a.cpf) AS num_cnpj_cpf,  ");
        sql.add(" normali(a.nome) AS nom_pessoa,  ");
        sql.add(" normali(a.nome_reduzido) AS nom_fantasia,  ");
        sql.add(" CASE WHEN length(only_numbers(a.cpf)) > 11 THEN 'J' ELSE 'F' END AS ind_natureza,  ");
        sql.add(" a.bloqueado AS ind_bloqueado,  ");
        sql.add(" a.flag AS ind_pessoa_ativa,  ");
        sql.add(" a.inscr_est AS num_ie_rg,  ");
        sql.add(" '' AS des_org_exp_rg,  ");
        sql.add(" a.data_nasc AS dta_nascimento,  ");
        sql.add(" a.endereco AS des_logradouro,  ");
        sql.add(" a.complemento AS des_complemento,  ");
        sql.add(" CAST(a.municipio AS INTEGER) AS cod_cidade,  ");
        sql.add(" a.bairro AS nom_bairro,  ");
        sql.add(" a.cep AS num_cep,  ");
        sql.add(" '' AS num_caixa_postal,  ");
        sql.add(" a.fone AS num_telefone_1,  ");
        sql.add(" '' AS num_telefone_2,  ");
        sql.add(" '' AS num_telefone_3,  ");
        sql.add(" '' AS num_ramal,  ");
        sql.add(" a.contato AS nom_contato,  ");
        sql.add(" a.obs AS des_observacao,  ");
        sql.add(" '' AS des_banco,  ");
        sql.add(" '' AS num_agencia,  ");
        sql.add(" '' AS num_conta,  ");
        sql.add(" a.data_cad AS dta_cadastro,  ");
        sql.add(" 'N' AS ind_cliente,  ");
        sql.add(" 'S' AS ind_fornecedor,  ");
        sql.add(" 'N' AS ind_banco,  ");
        sql.add(" 'N' AS ind_funcionario,  ");
        sql.add(" 'N' AS ind_sacado,  ");
        sql.add(" 'S' AS ind_sacador,  ");
        sql.add(" 'N' AS ind_representante,  ");
        sql.add(" 'N' AS ind_supervisor_venda,  ");
        sql.add(" 'N' AS ind_gerente_venda,  ");
        sql.add(" 'N' AS ind_administradora_cartao,  ");
        sql.add(" 'N' AS ind_transportadora,  ");
        sql.add(" 'N' AS ind_bloqueio_automatico,  ");
        sql.add(" '' AS des_email_1,  ");
        sql.add(" '' AS des_email_2,  ");
        sql.add(" '' AS des_home_page,  ");
        sql.add(" '' AS des_extra_1,  ");
        sql.add(" '' AS des_extra_2,  ");
        sql.add(" '' AS des_extra_3,  ");
        sql.add(" '' AS des_extra_4,  ");
        sql.add(" '' AS des_extra_5,  ");
        sql.add(" CURRENT_DATE AS dta_alteracao_status,  ");
        sql.add(" '' AS hra_alteracao_status,  ");
        sql.add(" '' AS nom_usu_alt_statu,  ");
        sql.add(" '' AS nom_usuario_alteracao,  ");
        sql.add(" 'N' AS ind_produtor_rural,  ");
        sql.add(" 'N' AS ind_motorista,  ");
        sql.add(" 'N' AS ind_conta_poupanca,  ");
        sql.add(" 'N' AS ind_escritorio_cobranca,  ");
        sql.add(" 'N' AS ind_pre_cadastro,  ");
        sql.add(" '' AS num_ie_st,  ");
        sql.add(" '' AS num_inscricao_municipal,  ");
        sql.add(" 'N' AS ind_cartao_parceiro,  ");
        sql.add(" 'N' AS ind_tipo_convenio,  ");
        sql.add(" null AS cod_cartao_parceiro,  ");
        sql.add(" 'N' AS ind_contador,  ");
        sql.add(" null AS qtd_avisos_pessoa,  ");
        sql.add(" 'N' AS ind_montadora,  ");
        sql.add(" null AS cod_grupo,  ");
        sql.add(" 'N' AS ind_prestador,  ");
        sql.add(" 'N' AS ind_comprador,  ");
        sql.add(" null AS cod_ultimo_atendente,  ");
        sql.add(" 'N' AS ind_sincronizado_v4,  ");
        sql.add(" CURRENT_DATE AS dta_alteracao,  ");
        sql.add(" '' AS nom_usuario_inativacao,  ");
        sql.add(" '' AS nom_usuario_cadastro,  ");
        sql.add(" null AS cod_classe_agregado,  ");
        sql.add(" 'N' AS ind_proprietario,  ");
        sql.add(" null AS cod_empresa_cadastro,  ");
        sql.add(" 'N' AS ind_responsavel_obra,  ");
        sql.add(" 'N' AS ind_atividade_economica,  ");
        sql.add(" 'N' AS ind_fabricante,  ");
        sql.add(" 'N' AS ind_locatario,  ");
        sql.add(" 'N' AS ind_fiador,  ");
        sql.add(" '' AS hra_cadastro,  ");
        sql.add(" 'N' AS ind_agregado,  ");
        sql.add(" '' AS des_email_3,  ");
        sql.add(" '' AS des_email_4,  ");
        sql.add(" '' AS des_email_5,  ");
        sql.add(" 'N' AS ind_pedagio_iv,  ");
        sql.add(" 'N' AS ind_expedidor,  ");
        sql.add(" 'N' AS ind_recebedor,  ");
        sql.add(" 'N' AS ind_terceiro,  ");
        sql.add(" 'N' AS ind_cprb ");
        sql.add("FROM pessoa a  ");
        sql.add("WHERE a.codigo =  " + codPessoa);

        Query query = em.createNativeQuery(sql.toString(), PessoaFull.class);
        List<PessoaFull> list = Cast.of(query.getResultList());
        return list.isEmpty() ? null : list.get(0);

    }

}
