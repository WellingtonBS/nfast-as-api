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

    public Double getSaldoBanco(Integer codEmpresa, String numMnemonico) {
        Double vlrSaldo = nativeFindValue("SELECT sp_obtem_saldo_banco(" + codEmpresa + ", '" + numMnemonico + "') ");
        return vlrSaldo;
    }

    public ContaBanco contaBanco(String numMnemonico) {
        ContaBanco item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.seq_conta_banco, ");
            query.add("  a.num_mnemonico, ");
            query.add("  a.num_conta, ");
            query.add("  a.num_agencia, ");
            query.add("  a.num_banco, ");
            query.add("  b.nom_pessoa as nom_banco, ");
            query.add("  a.ind_ativo, ");
            query.add("  a.cod_empresa ");
            query.add("FROM tab_conta_banco a ");
            query.add("INNER JOIN tab_pessoa b ON(b.cod_pessoa = a.cod_pessoa_banco) ");
            query.add("WHERE a.num_mnemonico = '" + numMnemonico + "' ");
        });
        return item;
    }

    public List<ContaBanco> contasBanco(Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<ContaBanco> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.seq_conta_banco, ");
            query.add("  a.num_mnemonico, ");
            query.add("  a.num_conta, ");
            query.add("  a.num_agencia, ");
            query.add("  a.num_banco, ");
            query.add("  b.nom_pessoa as nom_banco, ");
            query.add("  a.ind_ativo, ");
            query.add("  a.cod_empresa ");
            query.add("FROM tab_conta_banco a ");
            query.add("INNER JOIN tab_pessoa b ON(b.cod_pessoa = a.cod_pessoa_banco) ");
            query.add("WHERE a.ind_ativo = 'S' ");
            if (Numbers.isNonEmpty(codEmpresa))
                query.add("AND a.cod_empresa = " + codEmpresa + " ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (a.num_mnemonico ILIKE :filtro) OR ");
                query.add("  (a.num_conta ILIKE :filtro) OR ");
                query.add("  (a.num_agencia ILIKE :filtro) OR ");
                query.add("  (b.nom_pessoa ILIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.num_mnemonico ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }

}
