package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.ProdutoCad;
import br.com.nfast.api.model.estoque.ProdutoEmpresaCad;
import br.com.nfast.api.model.estoque.ProdutoEmpresaIdCad;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.StringList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class ProdutoCadRepo extends DataRepository<ProdutoCad, Long> {
    @Autowired
    ProdutoRepo produtoRepo;
    @Autowired
    ProdutoEmpresaCadRepo produtoEmpresaRepo;

    public ProdutoCadRepo() {
        super(ProdutoCad.class);
    }

    public ProdutoCad gravaProduto(ProdutoCad produto) {
        Integer cod =(nativeFindValue("SELECT COALESCE(MAX(CAST(codigo as INT)), 0) + 1 FROM produto WHERE tipo NOT IN ('K')"));

        produto.setCodItem(Long.valueOf(cod));

        StringList sql = new StringList();

        sql.clear();
        sql.add("insert into produto (          ");
        sql.add("	codigo_ncm,                 ");
        sql.add("	ult_alteracao,              ");
        sql.add("	data_cad,                   ");
        sql.add("	ult_usuario,                ");
        sql.add("	cst_cofins_entrada,         ");
        sql.add("	subgrupo,                   ");
        sql.add("	unid_med_entrada,           ");
        sql.add("	exporta_checkapp,           ");
        sql.add("	exporta_inforlub,           ");
        sql.add("	aplicacao,                  ");
        sql.add("	codigo_ncm_ex,              ");
        sql.add("	codigo_barra,               ");
        sql.add("	escala_cnpj_fabricante,     ");
        sql.add("	cst_cofins,                 ");
        sql.add("	cst_pis,                    ");
        sql.add("	ippt,                       ");
        sql.add("	unid_med,                   ");
        sql.add("	margem_lucro_sobre,         ");
        sql.add("	nome,                       ");
        sql.add("	iat,                        ");
        sql.add("	controlar_estoque,          ");
        sql.add("	margem_lucro,               ");
        sql.add("	reajusta_preco_auto,        ");
        sql.add("	cst_pis_entrada,            ");
        sql.add("	codigo,                     ");
        sql.add("	cfop_fora,                  ");
        sql.add("	trocar_ponto,               ");
        sql.add("	reajusta_preco_menor_auto,  ");
        sql.add("	tributacao,					");
        sql.add("	grupo,                      ");
        sql.add("	escala_nao_relevante        ");
        sql.add(") values (                     ");
        sql.add("	:cod_ncm,               ");
        sql.add("	CURRENT_TIMESTAMP,      ");
        sql.add("	CURRENT_DATE,               ");
        sql.add("	'lzt',                      ");
        sql.add("	:cst_cofins_entrada,                       ");
        sql.add("	:subgrupo,                  ");
        sql.add("	'',                         ");
        sql.add("	FALSE,                      ");
        sql.add("	FALSE,                      ");
        sql.add("	'',                         ");
        sql.add("	NULL,                       ");
        sql.add("	:cod_barra,                   ");
        sql.add("	'',                         ");
        sql.add("	:cst_cofins,                       ");
        sql.add("	:cst_pis,                       ");
        sql.add("	'T',                        ");
        sql.add("	:unid_med,                       ");
        sql.add("	0,                          ");
        sql.add("	:nome,              ");
        sql.add("	'T',                        ");
        sql.add("	TRUE,                       ");
        sql.add("	NULL,                       ");
        sql.add("	2,                          ");
        sql.add("	:cst_pis_entrada,                       ");
        sql.add("	:codigo,                   ");
        sql.add("	NULL,                       ");
        sql.add("	FALSE,                      ");
        sql.add("	FALSE,                      ");
        sql.add("	:tributacao,                      ");
        sql.add("	(SELECT grupo FROM subgrupo_produto WHERE grid = :grupo), ");
        sql.add("	FALSE                       ");
        sql.add(" )                           ");

        Query q = em.createNativeQuery(sql.toString());
        q.setParameter("cod_ncm", produto.getCodNcm());
        q.setParameter("cod_barra", produto.getCodBarra());
        q.setParameter("cst_cofins_entrada",produto.getEmpresas().get(0).getCodTributacaoCofinsEntrada());
        q.setParameter("subgrupo", produto.getCodSubgrupoItem());
        q.setParameter("cod_barra", produto.getCodBarra());
        q.setParameter("cst_cofins",produto.getEmpresas().get(0).getCodTributacaoCofins());
        q.setParameter("cst_pis",produto.getEmpresas().get(0).getCodTributacaoPis());
        q.setParameter("unid_med",produto.getCodUnidade());
        q.setParameter("nome",produto.getDesItem());
        q.setParameter("cst_pis_entrada",produto.getEmpresas().get(0).getCodTributacaoPisEntrada());
        q.setParameter("codigo",produto.getCodItem());
        q.setParameter("tributacao",produto.getEmpresas().get(0).getCodClasseFiscal());
        q.setParameter("grupo",produto.getCodSubgrupoItem());

        if (Numbers.isNonEmpty(produto.getCodPessoaFornecedor())) {
            produtoRepo.vinculaProdutoFor(produto.getCodItem(), produto.getCodPessoaFornecedor(), produto.getCodReferencia(), null, 0.0);
        }

        ProdutoCad result = save(produto);

        return result;
    }



}
