package br.com.nfast.api.repo.nfe;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.nfe.CustoNFe;
import br.com.nfast.api.model.nfe.NFeResumo;
import br.com.nfast.api.utils.*;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
public class NFeResumoRepo extends DataRepository<NFeResumo, Long> {

    public NFeResumoRepo() {
        super(NFeResumo.class);
    }

    public NFeResumo obtem(BigInteger notaFiscal) {
        StringList sql = new StringList();
        sql.add("SELECT ");
        sql.add("  a.grid AS seq_nota, ");
        sql.add("  a.numero_nota AS num_nota, ");
        sql.add("  a.data_emissao AS dta_emissao, ");
        sql.add("  a.data_saida AS dta_entrada, ");
        sql.add("  a.valor_nota AS val_total_nota, ");
        sql.add("  g.usuario AS nom_usuario, ");
        sql.add("  g.data AS dta_digitacao, ");
        sql.add("  '' AS hra_digitacao, ");
        sql.add("  d.codigo AS cod_pessoa_fornecedor, ");
        sql.add("  d.nome_reduzido AS nom_pessoa_fornecedor, ");
        sql.add("  e.codigo AS cod_empresa, ");
        sql.add("  e.nome_reduzido AS nom_fantasia, ");
        sql.add("  f.chave_acesso AS num_chave_nfe ");
        sql.add("FROM nota_fiscal a ");
        sql.add("INNER JOIN nota_fiscal_pessoa b ON (b.grid = a.emitente) ");
        sql.add("INNER JOIN nota_fiscal_pessoa c ON (c.grid = a.destinatario) ");
        sql.add("INNER JOIN pessoa d ON (d.grid = b.pessoa) ");
        sql.add("INNER JOIN pessoa e ON (e.grid = b.pessoa) ");
        sql.add("INNER JOIN nfe f ON (f.nota_fiscal = a.grid) ");
        sql.add("INNER JOIN lancto g on (g.mlid = a.mlid) ");
        sql.add("WHERE a.grid = " + notaFiscal);

        Query query = em.createNativeQuery(sql.toString(), NFeResumo.class);
        List<NFeResumo> list = Cast.of(query.getResultList());
        return list.isEmpty() ? null : list.get(0);
    }

    public List<NFeResumo> listagem(LocalDate dataIni, LocalDate dataFim, String codEmpresas, String nomUsuario) {
        StringList sql = new StringList();
        sql.add("SELECT DISTINCT ");
        sql.add("  a.grid AS seq_nota,  ");
        sql.add("  a.numero_nota AS num_nota,  ");
        sql.add("  a.data_emissao AS dta_emissao,  ");
        sql.add("  a.data_emissao AS dta_entrada,  ");
        sql.add("  a.valor_nota AS val_total_nota,  ");
        sql.add("  d.usuario AS nom_usuario,  ");
        sql.add("  d.data_doc AS dta_digitacao,  ");
        sql.add("  SUBSTRING(CAST(d.hora AS TEXT) from 12 for 5) AS hra_digitacao,  ");
        sql.add("  c.codigo AS cod_pessoa_fornecedor,  ");
        sql.add("  normalize(c.nome) AS nom_pessoa_fornecedor,  ");
        sql.add("  b.codigo AS cod_empresa,  ");
        sql.add("  b.nome_reduzido AS nom_fantasia,  ");
        sql.add("  e.chave_acesso AS num_chave_nfe  ");
        sql.add("FROM nota_fiscal a ");
        sql.add("INNER JOIN empresa b ON (b.grid = a.empresa)  ");
        sql.add("INNER JOIN pessoa c ON (c.grid = a.pessoa)  ");
        sql.add("INNER JOIN lancto d ON (a.mlid = a.mlid) ");
        sql.add("INNER JOIN nfe e ON (e.nota_fiscal = a.grid) ");
        sql.add("WHERE TRUE   ");
        if (dataIni != null)
            sql.add("AND a.data_emissao >= '" + Dates.format(dataIni, "yyyy-MM-dd") + "' ");
        if (dataFim != null)
            sql.add("AND a.data_emissao <= '" + Dates.format(dataFim, "yyyy-MM-dd") + "' ");
        if (Strings.isNonEmpty(codEmpresas))
            sql.add("AND b.codigo IN(" + codEmpresas + ") ");
        if (Strings.isNonEmpty(nomUsuario))
            sql.add("AND LOWER(d.usuario) = '" + nomUsuario.toLowerCase() + "' ");
        sql.add("ORDER BY a.data_emissao, a.grid ");

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
        sql.add("SELECT DISTINCT ");
        sql.add("  a.grid AS seq_nota,  ");
        sql.add("  a.numero_nota AS num_nota,  ");
        sql.add("  a.data_emissao AS dta_emissao,  ");
        sql.add("  a.data_emissao AS dta_entrada,  ");
        sql.add("  a.valor_nota AS val_total_nota,  ");
        sql.add("  d.usuario AS nom_usuario,  ");
        sql.add("  d.data_doc AS dta_digitacao,  ");
        sql.add("  SUBSTRING(CAST(d.hora AS TEXT) from 12 for 5) AS hra_digitacao,  ");
        sql.add("  c.codigo AS cod_pessoa_fornecedor,  ");
        sql.add("  normalize(c.nome) AS nom_pessoa_fornecedor,  ");
        sql.add("  b.codigo AS cod_empresa,  ");
        sql.add("  b.nome_reduzido AS nom_fantasia,  ");
        sql.add("  e.chave_acesso AS num_chave_nfe  ");
        sql.add("FROM nota_fiscal a ");
        sql.add("INNER JOIN empresa b ON (b.grid = a.empresa)  ");
        sql.add("INNER JOIN pessoa c ON (c.grid = a.pessoa)  ");
        sql.add("INNER JOIN lancto d ON (d.mlid = a.mlid) ");
        sql.add("INNER JOIN nfe e ON (e.nota_fiscal = a.grid) ");
        sql.add("WHERE b.codigo = " + codEmpresa + " ");
        sql.add("AND e.chave_acesso IN(" + items.toString() + ") ");
        sql.add("ORDER BY a.grid ");

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
