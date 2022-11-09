package br.com.nfast.api.repo.nfe;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.nfe.CustoNFe;
import br.com.nfast.api.model.nfe.NFeResumo;
import br.com.nfast.api.utils.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class NFeResumoRepo extends DataRepository<NFeResumo, Integer> {

    public NFeResumoRepo() {
        super(NFeResumo.class);
    }

    public List<NFeResumo> listagem(LocalDate dataIni, LocalDate dataFim, String codEmpresas, String nomUsuario) {
        StringList sql = new StringList();
        sql.add("SELECT ");
        sql.add("  a.seq_nota, ");
        sql.add("  a.num_nota, ");
        sql.add("  a.dta_emissao, ");
        sql.add("  a.dta_entrada, ");
        sql.add("  a.val_total_nota, ");
        sql.add("  a.nom_usuario, ");
        sql.add("  a.dta_digitacao, ");
        sql.add("  a.hra_digitacao, ");
        sql.add("  a.cod_pessoa_fornecedor, ");
        sql.add("  c.nom_pessoa as nom_pessoa_fornecedor, ");
        sql.add("  a.cod_empresa, ");
        sql.add("  b.nom_fantasia, ");
        sql.add("  a.num_chave_nfe ");
        sql.add("FROM tab_nota_fiscal_entrada a ");
        sql.add("INNER JOIN tab_empresa b ON(b.cod_empresa = a.cod_empresa) ");
        sql.add("INNER JOIN tab_pessoa c ON(c.cod_pessoa = a.cod_pessoa_fornecedor) ");
        sql.add("WHERE TRUE ");
        if (dataIni != null)
            sql.add("AND a.dta_entrada >= '" + Dates.format(dataIni, "yyyy-MM-dd") + "' ");
        if (dataFim != null)
            sql.add("AND a.dta_entrada <= '" + Dates.format(dataFim, "yyyy-MM-dd") + "' ");
        if (Strings.isNonEmpty(codEmpresas))
            sql.add("AND a.cod_empresa IN(" + codEmpresas + ") ");
        if (Strings.isNonEmpty(nomUsuario))
            sql.add("AND LOWER(a.nom_usuario) = '" + nomUsuario.toLowerCase() + "' ");
        sql.add("ORDER BY a.dta_entrada, a.seq_nota ");

        Query query = em.createNativeQuery(sql.toString(), NFeResumo.class);
        return Cast.of(query.getResultList());
    }

    public List<NFeResumo> listaChave(Integer codEmpresa, String chaves) {
        StringBuilder items = new StringBuilder();
        for (String chave : Strings.split(chaves, ",")) {
            if (items.length() > 0)
                items.append(",");
            items.append("'").append(chave).append("'");
        }

        StringList sql = new StringList();
        sql.add("SELECT ");
        sql.add("  a.seq_nota, ");
        sql.add("  a.num_nota, ");
        sql.add("  a.dta_emissao, ");
        sql.add("  a.dta_entrada, ");
        sql.add("  a.val_total_nota, ");
        sql.add("  a.nom_usuario, ");
        sql.add("  a.dta_digitacao, ");
        sql.add("  a.hra_digitacao, ");
        sql.add("  a.cod_pessoa_fornecedor, ");
        sql.add("  c.nom_pessoa as nom_pessoa_fornecedor, ");
        sql.add("  a.cod_empresa, ");
        sql.add("  b.nom_fantasia, ");
        sql.add("  a.num_chave_nfe ");
        sql.add("FROM tab_nota_fiscal_entrada a ");
        sql.add("INNER JOIN tab_empresa b ON(b.cod_empresa = a.cod_empresa) ");
        sql.add("INNER JOIN tab_pessoa c ON(c.cod_pessoa = a.cod_pessoa_fornecedor) ");
        sql.add("WHERE a.cod_empresa = " + codEmpresa + " ");
        sql.add("AND a.num_chave_nfe IN(" + items.toString() + ") ");
        sql.add("ORDER BY a.seq_nota ");

        Query query = em.createNativeQuery(sql.toString(), NFeResumo.class);
        return Cast.of(query.getResultList());
    }

    public List<CustoNFe> auditoria(LocalDate dataIni, LocalDate dataFim, String codEmpresas, Integer codFornecedor, Double perVariacaoUp, Double perVariacaoDown, String custoAntZero, String filtroProduto) {
        if (perVariacaoUp == null)
            perVariacaoUp = 0.0;
        if (perVariacaoDown == null)
            perVariacaoDown = 0.0;

        StringList sql = new StringList();
        sql.add("WITH entradas AS ( ");
        sql.add("  SELECT ");
        sql.add("    a.seq_movimento_estoque as seq_movimento, ");
        sql.add("    a.cod_item, ");
        sql.add("    c.des_item, ");
        sql.add("    b.cod_empresa, ");
        sql.add("    b.dta_entrada, ");
        sql.add("    a.qtd_item as qtd_bruto, ");
        sql.add("    a.qtd_item_convertido as qtd_total, ");
        sql.add("    f.sgl_unidade, ");
        sql.add("    sp_obtem_custo_medio_item(b.cod_empresa, a.cod_almoxarifado, a.cod_item, b.dta_entrada - CAST('1 DAY' as INTERVAL), 1.0) as val_custo_ant, ");
        sql.add("    (a.val_custo / a.qtd_item_convertido) as val_custo, ");
        sql.add("    a.seq_nota, ");
        sql.add("    b.num_nota, ");
        sql.add("    (d.cod_pessoa||' - '||d.nom_pessoa) as nom_fornecedor, ");
        sql.add("    b.nom_usuario, ");
        sql.add("    (e.cod_empresa||' - '||e.nom_fantasia) as nom_fantasia, ");
        sql.add("    a.cod_almoxarifado, ");
        sql.add("    g.des_almoxarifado ");
        sql.add("  FROM tab_item_nfe a ");
        sql.add("  INNER JOIN tab_nota_fiscal_entrada b ON(b.seq_nota = a.seq_nota) ");
        sql.add("  INNER JOIN tab_item c ON(c.cod_item = a.cod_item) ");
        sql.add("  INNER JOIN tab_pessoa d ON(d.cod_pessoa = b.cod_pessoa_fornecedor) ");
        sql.add("  INNER JOIN tab_empresa e ON(e.cod_empresa = b.cod_empresa) ");
        sql.add("  INNER JOIN tab_unidade f ON(f.cod_unidade = a.cod_unidade_compra) ");
        sql.add("  INNER JOIN tab_almoxarifado g ON(g.cod_almoxarifado = a.cod_almoxarifado) ");
        sql.add("  WHERE a.seq_movimento_estoque IS NOT NULL ");
        if (dataIni != null)
            sql.add("  AND b.dta_entrada >= '" + Dates.format(dataIni, "yyyy-MM-dd") + "' ");
        if (dataFim != null)
            sql.add("  AND b.dta_entrada <= '" + Dates.format(dataFim, "yyyy-MM-dd") + "' ");
        if (Strings.isNonEmpty(codEmpresas))
            sql.add("  AND b.cod_empresa IN(" + codEmpresas + ") ");
        if (Numbers.isNonEmpty(codFornecedor))
            sql.add("  AND b.cod_pessoa_fornecedor = " + codFornecedor + " ");
        if (Strings.isNonEmpty(filtroProduto)) {
            Integer codItem = Numbers.asInteger(filtroProduto, 0);
            sql.add("  AND ( ");
            if (codItem > 0)
                sql.add("    (a.cod_item = " + codItem + ") OR ");
            sql.add("    (c.cod_barra = '" + filtroProduto + "') OR ");
            sql.add("    (c.des_item ILIKE '%" + filtroProduto + "%') ");
            sql.add("  ) ");
        }
        sql.add(") ");
        sql.add("SELECT ");
        sql.add("  a.seq_movimento, ");
        sql.add("  a.cod_item, ");
        sql.add("  a.des_item, ");
        sql.add("  a.cod_empresa, ");
        sql.add("  a.dta_entrada, ");
        sql.add("  a.qtd_bruto, ");
        sql.add("  a.qtd_total, ");
        sql.add("  a.sgl_unidade, ");
        sql.add("  a.val_custo_ant, ");
        sql.add("  a.val_custo, ");
        sql.add("  CAST((CASE WHEN (a.val_custo > 0.0) THEN ((1.0 - (a.val_custo_ant / a.val_custo)) * 100.0) ELSE 0.0 END) as NUMERIC(15, 2)) as per_variacao, ");
        sql.add("  a.seq_nota, ");
        sql.add("  a.num_nota, ");
        sql.add("  a.nom_fornecedor, ");
        sql.add("  a.nom_usuario, ");
        sql.add("  a.nom_fantasia, ");
        sql.add("  a.cod_almoxarifado, ");
        sql.add("  a.des_almoxarifado ");
        sql.add("FROM entradas a ");
        sql.add("WHERE TRUE ");
        if (Strings.equals(custoAntZero, "N"))
            sql.add("AND a.val_custo_ant > 0.0 ");
        if ((perVariacaoUp > 0.0) || (perVariacaoDown > 0.0)) {
            sql.add("AND ( ");
            if (perVariacaoUp > 0.0) {
                sql.add("CAST((CASE WHEN (a.val_custo > 0.0) THEN ((1.0 - (a.val_custo_ant / a.val_custo)) * 100.0) ELSE 0.0 END) as NUMERIC(15, 2)) >= " + perVariacaoUp + " ");
                if (perVariacaoDown > 0.0)
                    sql.add("OR ");
            }
            if (perVariacaoDown > 0.0) {
                sql.add("CAST((CASE WHEN (a.val_custo > 0.0) THEN ((1.0 - (a.val_custo_ant / a.val_custo)) * 100.0) ELSE 0.0 END) as NUMERIC(15, 2)) <= " + (perVariacaoDown * -1) + " ");
            }
            sql.add(") ");
        }

        sql.add("ORDER BY a.cod_item, a.dta_entrada, a.seq_movimento ");

        Query query = em.createNativeQuery(sql.toString(), CustoNFe.class);
        return Cast.of(query.getResultList());
    }

}
