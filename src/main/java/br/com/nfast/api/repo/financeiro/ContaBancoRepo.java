package br.com.nfast.api.repo.financeiro;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.financeiro.ContaBanco;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContaBancoRepo extends DataRepository<ContaBanco, Integer> {

    public ContaBancoRepo() {
        super(ContaBanco.class);
    }

    public Double getSaldoBanco(Long codEmpresa, String numMnemonico) {
        Double vlrSaldo = nativeFindValue("SELECT sp_obtem_saldo_banco(" + codEmpresa + ", '" + numMnemonico + "') ");
        return vlrSaldo;
    }

    public ContaBanco contaBanco(String numMnemonico) {
        ContaBanco item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS seq_conta_banco, ");
            query.add("  CAST(a.nr_conta || '-' || a.digito AS VARCHAR(10)) AS num_mnemonico, ");
            query.add("  CAST(trim(LEADING '0' FROM a.nr_conta)|| '-' ||a.digito AS VARCHAR(10)) as num_conta, ");
            query.add("  a.agencia as num_agencia, ");
            query.add("  b.codigo AS num_banco, ");
            query.add("  b.nome AS nom_banco, ");
            query.add("  'S' as ind_ativo, ");
            query.add("  CAST(d.codigo AS INTEGER) AS cod_empresa ");
            query.add("FROM conta_corrente a ");
            query.add("INNER JOIN banco b on (b.codigo = a.banco) ");
            query.add("INNER JOIN empresa d ON (d.grid = a.empresa) ");
            query.add("WHERE CAST(a.nr_conta || '-' || a.digito AS VARCHAR(10)) = '" + numMnemonico + "' ");
        });
        return item;
    }

    public List<ContaBanco> contasBanco(Long codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ContaBanco> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS seq_conta_banco, ");
            query.add("  CAST(a.nr_conta || '-' || a.digito AS VARCHAR(10)) AS num_mnemonico, ");
            query.add("  CAST(trim(LEADING '0' FROM a.nr_conta)|| '-' ||a.digito AS VARCHAR(10)) as num_conta, ");
            query.add("  a.agencia as num_agencia, ");
            query.add("  b.codigo AS num_banco, ");
            query.add("  b.nome AS nom_banco, ");
            query.add("  'S' as ind_ativo, ");
            query.add("  CAST(d.codigo AS INTEGER) AS cod_empresa ");
            query.add("FROM conta_corrente a ");
            query.add("INNER JOIN banco b on (b.codigo = a.banco) ");
            query.add("INNER JOIN empresa d ON (d.grid = a.empresa) ");
            if (Numbers.isNonEmpty(codEmpresa))
                query.add("AND CAST(d.codigo AS INTEGER) = " + codEmpresa + " ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CAST(a.nr_conta || '-' || a.digito AS VARCHAR(10)) ILIKE :filtro) OR "); // num_mnemonico
                query.add("  (CAST(trim(LEADING '0' FROM a.nr_conta)|| '-' ||a.digito AS VARCHAR(10)) ILIKE :filtro) OR ");// num_conta
                query.add("  (a.agencia ILIKE :filtro) OR ");// num_agencia
                query.add("  (b.nome ILIKE :filtro) "); // banco
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY CAST(a.nr_conta || '-' || a.digito AS VARCHAR(10)) ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }

}
