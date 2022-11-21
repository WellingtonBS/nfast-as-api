package br.com.nfast.api.model.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PessoaFull {
    @Id
    @Column(name = "cod_pessoa")
    private Integer codPessoa;
    @Column(name = "num_cnpj_cpf")
    private String numCnpjCpf;
    @Column(name = "nom_pessoa")
    private String nomPessoa;
    @Column(name = "nom_fantasia")
    private String nomFantasia;
    @Column(name = "ind_natureza")
    private String indNatureza;
    @Column(name = "ind_bloqueado")
    private String indBloqueado = "N";
    @Column(name = "ind_pessoa_ativa")
    private String indPessoaAtiva = "S";
    @Column(name = "num_ie_rg")
    private String numIeRg = "";
    @Column(name = "des_org_exp_rg")
    private String desOrgExpRg = "";
    @Column(name = "dta_nascimento")
    private LocalDate dtaNascimento;
    @Column(name = "des_logradouro")
    private String desLogradouro = "";
    @Column(name = "des_complemento")
    private String desComplemento = "";
    @Column(name = "cod_cidade")
    private Integer codCidade;
    @Column(name = "nom_bairro")
    private String nomBairro = "";
    @Column(name = "num_cep")
    private String numCep = "";
    @Column(name = "num_caixa_postal")
    private String numCaixaPostal = "";
    @Column(name = "num_telefone_1")
    private String numTelefone1 = "";
    @Column(name = "num_telefone_2")
    private String numTelefone2 = "";
    @Column(name = "num_telefone_3")
    private String numTelefone3 = "";
    @Column(name = "num_ramal")
    private String numRamal = "";
    @Column(name = "nom_contato")
    private String nomContato = "";
    @Column(name = "des_observacao")
    private String desObservacao = "";
    @Column(name = "des_banco")
    private String desBanco = "";
    @Column(name = "num_agencia")
    private String numAgencia = "";
    @Column(name = "num_conta")
    private String numConta = "";
    @Column(name = "dta_cadastro")
    private LocalDate dtaCadastro;
    @Column(name = "ind_cliente")
    private String indCliente = "N";
    @Column(name = "ind_fornecedor")
    private String indFornecedor = "N";
    @Column(name = "ind_banco")
    private String indBanco = "N";
    @Column(name = "ind_funcionario")
    private String indFuncionario = "N";
    @Column(name = "ind_sacado")
    private String indSacado = "N";
    @Column(name = "ind_sacador")
    private String indSacador = "N";
    @Column(name = "ind_representante")
    private String indRepresentante = "N";
    @Column(name = "ind_supervisor_venda")
    private String indSupervisorVenda = "N";
    @Column(name = "ind_gerente_venda")
    private String indGerenteVenda = "N";
    @Column(name = "ind_administradora_cartao")
    private String indAdministradoraCartao = "N";
    @Column(name = "ind_transportadora")
    private String indTransportadora = "N";
    @Column(name = "ind_bloqueio_automatico")
    private String indBloqueioAutomatico = "N";
    @Column(name = "des_email_1")
    private String desEmail1 = "";
    @Column(name = "des_email_2")
    private String desEmail2 = "";
    @Column(name = "des_home_page")
    private String desHomePage = "";
    @Column(name = "des_extra_1")
    private String desExtra1 = "";
    @Column(name = "des_extra_2")
    private String desExtra2 = "";
    @Column(name = "des_extra_3")
    private String desExtra3 = "";
    @Column(name = "des_extra_4")
    private String desExtra4 = "";
    @Column(name = "des_extra_5")
    private String desExtra5 = "";
    @Column(name = "dta_alteracao_status")
    private LocalDate dtaAlteracaoStatus;
    @Column(name = "hra_alteracao_status")
    private String hraAlteracaoStatus;
    @Column(name = "nom_usu_alt_statu")
    private String nomUsuAltStatu;
    @Column(name = "nom_usuario_alteracao")
    private String nomUsuarioAlteracao;
    @Column(name = "ind_produtor_rural")
    private String indProdutorRural = "N";
    @Column(name = "ind_motorista")
    private String indMotorista = "N";
    @Column(name = "ind_conta_poupanca")
    private String indContaPoupanca = "N";
    @Column(name = "ind_escritorio_cobranca")
    private String indEscritorioCobranca = "N";
    @Column(name = "ind_pre_cadastro")
    private String indPreCadastro = "N";
    @Column(name = "num_ie_st")
    private String numIeSt = "";
    @Column(name = "num_inscricao_municipal")
    private String numInscricaoMunicipal = "";
    @Column(name = "ind_cartao_parceiro")
    private String indCartaoParceiro;
    @Column(name = "ind_tipo_convenio")
    private String indTipoConvenio = "P";
    @Column(name = "cod_cartao_parceiro")
    private Integer codCartaoParceiro;
    @Column(name = "ind_contador")
    private String indContador = "N";
    @Column(name = "qtd_avisos_pessoa")
    private Integer qtdAvisosPessoa = 0;
    @Column(name = "ind_montadora")
    private String indMontadora = "N";
    @Column(name = "cod_grupo")
    private Integer codGrupo;
    @Column(name = "ind_prestador")
    private String indPrestador = "N";
    @Column(name = "ind_comprador")
    private String indComprador = "N";
    @Column(name = "cod_ultimo_atendente")
    private Integer codUltimoAtendente;
    @Column(name = "ind_sincronizado_v4")
    private String indSincronizadoV4 = "N";
    @Column(name = "dta_alteracao")
    private LocalDateTime dtaAlteracao;
    @Column(name = "nom_usuario_inativacao")
    private String nomUsuarioInativacao;
    @Column(name = "nom_usuario_cadastro")
    private String nomUsuarioCadastro;
    @Column(name = "cod_classe_agregado")
    private Integer codClasseAgregado;
    @Column(name = "ind_proprietario")
    private String indProprietario = "N";
    @Column(name = "cod_empresa_cadastro")
    private Integer codEmpresaCadastro;
    @Column(name = "ind_responsavel_obra")
    private String indResponsavelObra = "N";
    @Column(name = "ind_atividade_economica")
    private String indAtividadeEconomica;
    @Column(name = "ind_fabricante")
    private String indFabricante = "N";
    @Column(name = "ind_locatario")
    private String indLocatario = "N";
    @Column(name = "ind_fiador")
    private String indFiador = "N";
    @Column(name = "hra_cadastro")
    private String hraCadastro;
    @Column(name = "ind_agregado")
    private String indAgregado = "N";
    @Column(name = "des_email_3")
    private String desEmail3 = "";
    @Column(name = "des_email_4")
    private String desEmail4 = "";
    @Column(name = "des_email_5")
    private String desEmail5 = "";
    @Column(name = "ind_pedagio_iv")
    private String indPedagioIv = "N";
    @Column(name = "ind_expedidor")
    private String indExpedidor = "N";
    @Column(name = "ind_recebedor")
    private String indRecebedor = "N";
    @Column(name = "ind_terceiro")
    private String indTerceiro = "N";
    @Column(name = "ind_cprb")
    private String indCpbr = "N";

    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNumCnpjCpf() {
        return numCnpjCpf;
    }

    public void setNumCnpjCpf(String numCnpjCpf) {
        this.numCnpjCpf = numCnpjCpf;
    }

    public String getNomPessoa() {
        return nomPessoa;
    }

    public void setNomPessoa(String nomPessoa) {
        this.nomPessoa = nomPessoa;
    }

    public String getNomFantasia() {
        return nomFantasia;
    }

    public void setNomFantasia(String nomFantasia) {
        this.nomFantasia = nomFantasia;
    }

    public String getIndBloqueado() {
        return indBloqueado;
    }

    public void setIndBloqueado(String indBloqueado) {
        this.indBloqueado = indBloqueado;
    }

    public String getIndPessoaAtiva() {
        return indPessoaAtiva;
    }

    public void setIndPessoaAtiva(String indPessoaAtiva) {
        this.indPessoaAtiva = indPessoaAtiva;
    }

    public String getNumIeRg() {
        return numIeRg;
    }

    public void setNumIeRg(String numIeRg) {
        this.numIeRg = numIeRg;
    }

    public String getDesOrgExpRg() {
        return desOrgExpRg;
    }

    public void setDesOrgExpRg(String desOrgExpRg) {
        this.desOrgExpRg = desOrgExpRg;
    }

    public LocalDate getDtaNascimento() {
        return dtaNascimento;
    }

    public void setDtaNascimento(LocalDate dtaNascimento) {
        this.dtaNascimento = dtaNascimento;
    }

    public String getIndNatureza() {
        return indNatureza;
    }

    public void setIndNatureza(String indNatureza) {
        this.indNatureza = indNatureza;
    }

    public String getDesLogradouro() {
        return desLogradouro;
    }

    public void setDesLogradouro(String desLogradouro) {
        this.desLogradouro = desLogradouro;
    }

    public String getDesComplemento() {
        return desComplemento;
    }

    public void setDesComplemento(String desComplemento) {
        this.desComplemento = desComplemento;
    }

    public Integer getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(Integer codCidade) {
        this.codCidade = codCidade;
    }

    public String getNomBairro() {
        return nomBairro;
    }

    public void setNomBairro(String nomBairro) {
        this.nomBairro = nomBairro;
    }

    public String getNumCep() {
        return numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }

    public String getNumCaixaPostal() {
        return numCaixaPostal;
    }

    public void setNumCaixaPostal(String numCaixaPostal) {
        this.numCaixaPostal = numCaixaPostal;
    }

    public String getNumTelefone1() {
        return numTelefone1;
    }

    public void setNumTelefone1(String numTelefone1) {
        this.numTelefone1 = numTelefone1;
    }

    public String getNumTelefone2() {
        return numTelefone2;
    }

    public void setNumTelefone2(String numTelefone2) {
        this.numTelefone2 = numTelefone2;
    }

    public String getNumTelefone3() {
        return numTelefone3;
    }

    public void setNumTelefone3(String numTelefone3) {
        this.numTelefone3 = numTelefone3;
    }

    public String getNumRamal() {
        return numRamal;
    }

    public void setNumRamal(String numRamal) {
        this.numRamal = numRamal;
    }

    public String getNomContato() {
        return nomContato;
    }

    public void setNomContato(String nomContato) {
        this.nomContato = nomContato;
    }

    public String getDesObservacao() {
        return desObservacao;
    }

    public void setDesObservacao(String desObservacao) {
        this.desObservacao = desObservacao;
    }

    public String getDesBanco() {
        return desBanco;
    }

    public void setDesBanco(String desBanco) {
        this.desBanco = desBanco;
    }

    public String getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(String numAgencia) {
        this.numAgencia = numAgencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public LocalDate getDtaCadastro() {
        return dtaCadastro;
    }

    public void setDtaCadastro(LocalDate dtaCadastro) {
        this.dtaCadastro = dtaCadastro;
    }

    public String getIndCliente() {
        return indCliente;
    }

    public void setIndCliente(String indCliente) {
        this.indCliente = indCliente;
    }

    public String getIndFornecedor() {
        return indFornecedor;
    }

    public void setIndFornecedor(String indFornecedor) {
        this.indFornecedor = indFornecedor;
    }

    public String getIndBanco() {
        return indBanco;
    }

    public void setIndBanco(String indBanco) {
        this.indBanco = indBanco;
    }

    public String getIndFuncionario() {
        return indFuncionario;
    }

    public void setIndFuncionario(String indFuncionario) {
        this.indFuncionario = indFuncionario;
    }

    public String getIndSacado() {
        return indSacado;
    }

    public void setIndSacado(String indSacado) {
        this.indSacado = indSacado;
    }

    public String getIndSacador() {
        return indSacador;
    }

    public void setIndSacador(String indSacador) {
        this.indSacador = indSacador;
    }

    public String getIndRepresentante() {
        return indRepresentante;
    }

    public void setIndRepresentante(String indRepresentante) {
        this.indRepresentante = indRepresentante;
    }

    public String getIndSupervisorVenda() {
        return indSupervisorVenda;
    }

    public void setIndSupervisorVenda(String indSupervisorVenda) {
        this.indSupervisorVenda = indSupervisorVenda;
    }

    public String getIndGerenteVenda() {
        return indGerenteVenda;
    }

    public void setIndGerenteVenda(String indGerenteVenda) {
        this.indGerenteVenda = indGerenteVenda;
    }

    public String getIndAdministradoraCartao() {
        return indAdministradoraCartao;
    }

    public void setIndAdministradoraCartao(String indAdministradoraCartao) {
        this.indAdministradoraCartao = indAdministradoraCartao;
    }

    public String getIndTransportadora() {
        return indTransportadora;
    }

    public void setIndTransportadora(String indTransportadora) {
        this.indTransportadora = indTransportadora;
    }

    public String getIndBloqueioAutomatico() {
        return indBloqueioAutomatico;
    }

    public void setIndBloqueioAutomatico(String indBloqueioAutomatico) {
        this.indBloqueioAutomatico = indBloqueioAutomatico;
    }

    public String getDesEmail1() {
        return desEmail1;
    }

    public void setDesEmail1(String desEmail1) {
        this.desEmail1 = desEmail1;
    }

    public String getDesEmail2() {
        return desEmail2;
    }

    public void setDesEmail2(String desEmail2) {
        this.desEmail2 = desEmail2;
    }

    public String getDesHomePage() {
        return desHomePage;
    }

    public void setDesHomePage(String desHomePage) {
        this.desHomePage = desHomePage;
    }

    public String getDesExtra1() {
        return desExtra1;
    }

    public void setDesExtra1(String desExtra1) {
        this.desExtra1 = desExtra1;
    }

    public String getDesExtra2() {
        return desExtra2;
    }

    public void setDesExtra2(String desExtra2) {
        this.desExtra2 = desExtra2;
    }

    public String getDesExtra3() {
        return desExtra3;
    }

    public void setDesExtra3(String desExtra3) {
        this.desExtra3 = desExtra3;
    }

    public String getDesExtra4() {
        return desExtra4;
    }

    public void setDesExtra4(String desExtra4) {
        this.desExtra4 = desExtra4;
    }

    public String getDesExtra5() {
        return desExtra5;
    }

    public void setDesExtra5(String desExtra5) {
        this.desExtra5 = desExtra5;
    }

    public LocalDate getDtaAlteracaoStatus() {
        return dtaAlteracaoStatus;
    }

    public void setDtaAlteracaoStatus(LocalDate dtaAlteracaoStatus) {
        this.dtaAlteracaoStatus = dtaAlteracaoStatus;
    }

    public String getHraAlteracaoStatus() {
        return hraAlteracaoStatus;
    }

    public void setHraAlteracaoStatus(String hraAlteracaoStatus) {
        this.hraAlteracaoStatus = hraAlteracaoStatus;
    }

    public String getNomUsuAltStatu() {
        return nomUsuAltStatu;
    }

    public void setNomUsuAltStatu(String nomUsuAltStatu) {
        this.nomUsuAltStatu = nomUsuAltStatu;
    }

    public String getNomUsuarioAlteracao() {
        return nomUsuarioAlteracao;
    }

    public void setNomUsuarioAlteracao(String nomUsuarioAlteracao) {
        this.nomUsuarioAlteracao = nomUsuarioAlteracao;
    }

    public String getIndProdutorRural() {
        return indProdutorRural;
    }

    public void setIndProdutorRural(String indProdutorRural) {
        this.indProdutorRural = indProdutorRural;
    }

    public String getIndMotorista() {
        return indMotorista;
    }

    public void setIndMotorista(String indMotorista) {
        this.indMotorista = indMotorista;
    }

    public String getIndContaPoupanca() {
        return indContaPoupanca;
    }

    public void setIndContaPoupanca(String indContaPoupanca) {
        this.indContaPoupanca = indContaPoupanca;
    }

    public String getIndEscritorioCobranca() {
        return indEscritorioCobranca;
    }

    public void setIndEscritorioCobranca(String indEscritorioCobranca) {
        this.indEscritorioCobranca = indEscritorioCobranca;
    }

    public String getIndPreCadastro() {
        return indPreCadastro;
    }

    public void setIndPreCadastro(String indPreCadastro) {
        this.indPreCadastro = indPreCadastro;
    }

    public String getNumIeSt() {
        return numIeSt;
    }

    public void setNumIeSt(String numIeSt) {
        this.numIeSt = numIeSt;
    }

    public String getNumInscricaoMunicipal() {
        return numInscricaoMunicipal;
    }

    public void setNumInscricaoMunicipal(String numInscricaoMunicipal) {
        this.numInscricaoMunicipal = numInscricaoMunicipal;
    }

    public String getIndCartaoParceiro() {
        return indCartaoParceiro;
    }

    public void setIndCartaoParceiro(String indCartaoParceiro) {
        this.indCartaoParceiro = indCartaoParceiro;
    }

    public String getIndTipoConvenio() {
        return indTipoConvenio;
    }

    public void setIndTipoConvenio(String indTipoConvenio) {
        this.indTipoConvenio = indTipoConvenio;
    }

    public Integer getCodCartaoParceiro() {
        return codCartaoParceiro;
    }

    public void setCodCartaoParceiro(Integer codCartaoParceiro) {
        this.codCartaoParceiro = codCartaoParceiro;
    }

    public String getIndContador() {
        return indContador;
    }

    public void setIndContador(String indContador) {
        this.indContador = indContador;
    }

    public Integer getQtdAvisosPessoa() {
        return qtdAvisosPessoa;
    }

    public void setQtdAvisosPessoa(Integer qtdAvisosPessoa) {
        this.qtdAvisosPessoa = qtdAvisosPessoa;
    }

    public String getIndMontadora() {
        return indMontadora;
    }

    public void setIndMontadora(String indMontadora) {
        this.indMontadora = indMontadora;
    }

    public Integer getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(Integer codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getIndPrestador() {
        return indPrestador;
    }

    public void setIndPrestador(String indPrestador) {
        this.indPrestador = indPrestador;
    }

    public String getIndComprador() {
        return indComprador;
    }

    public void setIndComprador(String indComprador) {
        this.indComprador = indComprador;
    }

    public Integer getCodUltimoAtendente() {
        return codUltimoAtendente;
    }

    public void setCodUltimoAtendente(Integer codUltimoAtendente) {
        this.codUltimoAtendente = codUltimoAtendente;
    }

    public String getIndSincronizadoV4() {
        return indSincronizadoV4;
    }

    public void setIndSincronizadoV4(String indSincronizadoV4) {
        this.indSincronizadoV4 = indSincronizadoV4;
    }

    public LocalDateTime getDtaAlteracao() {
        return dtaAlteracao;
    }

    public void setDtaAlteracao(LocalDateTime dtaAlteracao) {
        this.dtaAlteracao = dtaAlteracao;
    }

    public String getNomUsuarioInativacao() {
        return nomUsuarioInativacao;
    }

    public void setNomUsuarioInativacao(String nomUsuarioInativacao) {
        this.nomUsuarioInativacao = nomUsuarioInativacao;
    }

    public String getNomUsuarioCadastro() {
        return nomUsuarioCadastro;
    }

    public void setNomUsuarioCadastro(String nomUsuarioCadastro) {
        this.nomUsuarioCadastro = nomUsuarioCadastro;
    }

    public Integer getCodClasseAgregado() {
        return codClasseAgregado;
    }

    public void setCodClasseAgregado(Integer codClasseAgregado) {
        this.codClasseAgregado = codClasseAgregado;
    }

    public String getIndProprietario() {
        return indProprietario;
    }

    public void setIndProprietario(String indProprietario) {
        this.indProprietario = indProprietario;
    }

    public Integer getCodEmpresaCadastro() {
        return codEmpresaCadastro;
    }

    public void setCodEmpresaCadastro(Integer codEmpresaCadastro) {
        this.codEmpresaCadastro = codEmpresaCadastro;
    }

    public String getIndResponsavelObra() {
        return indResponsavelObra;
    }

    public void setIndResponsavelObra(String indResponsavelObra) {
        this.indResponsavelObra = indResponsavelObra;
    }

    public String getIndAtividadeEconomica() {
        return indAtividadeEconomica;
    }

    public void setIndAtividadeEconomica(String indAtividadeEconomica) {
        this.indAtividadeEconomica = indAtividadeEconomica;
    }

    public String getIndFabricante() {
        return indFabricante;
    }

    public void setIndFabricante(String indFabricante) {
        this.indFabricante = indFabricante;
    }

    public String getIndLocatario() {
        return indLocatario;
    }

    public void setIndLocatario(String indLocatario) {
        this.indLocatario = indLocatario;
    }

    public String getIndFiador() {
        return indFiador;
    }

    public void setIndFiador(String indFiador) {
        this.indFiador = indFiador;
    }

    public String getHraCadastro() {
        return hraCadastro;
    }

    public void setHraCadastro(String hraCadastro) {
        this.hraCadastro = hraCadastro;
    }

    public String getIndAgregado() {
        return indAgregado;
    }

    public void setIndAgregado(String indAgregado) {
        this.indAgregado = indAgregado;
    }

    public String getDesEmail3() {
        return desEmail3;
    }

    public void setDesEmail3(String desEmail3) {
        this.desEmail3 = desEmail3;
    }

    public String getDesEmail4() {
        return desEmail4;
    }

    public void setDesEmail4(String desEmail4) {
        this.desEmail4 = desEmail4;
    }

    public String getDesEmail5() {
        return desEmail5;
    }

    public void setDesEmail5(String desEmail5) {
        this.desEmail5 = desEmail5;
    }

    public String getIndPedagioIv() {
        return indPedagioIv;
    }

    public void setIndPedagioIv(String indPedagioIv) {
        this.indPedagioIv = indPedagioIv;
    }

    public String getIndExpedidor() {
        return indExpedidor;
    }

    public void setIndExpedidor(String indExpedidor) {
        this.indExpedidor = indExpedidor;
    }

    public String getIndRecebedor() {
        return indRecebedor;
    }

    public void setIndRecebedor(String indRecebedor) {
        this.indRecebedor = indRecebedor;
    }

    public String getIndTerceiro() {
        return indTerceiro;
    }

    public void setIndTerceiro(String indTerceiro) {
        this.indTerceiro = indTerceiro;
    }

    public String getIndCpbr() {
        return indCpbr;
    }

    public void setIndCpbr(String indCpbr) {
        this.indCpbr = indCpbr;
    }
}
