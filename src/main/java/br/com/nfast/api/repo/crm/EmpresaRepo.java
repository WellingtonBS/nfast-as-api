package br.com.nfast.api.repo.crm;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.crm.Empresa;
import br.com.nfast.api.model.crm.LimiteRetroativo;
import br.com.nfast.api.utils.Dates;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class EmpresaRepo extends DataRepository<Empresa, Integer> {

    public EmpresaRepo() {
        super(Empresa.class);
    }

    public Empresa empresa(Integer codEmpresa) {
        Empresa item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_empresa, ");
            query.add("  only_numbers(a.cpf) AS num_cnpj, ");
            query.add("  a.nome AS nom_razao_social, ");
            query.add("  a.nome_reduzido AS nom_fantasia, ");
            query.add("  a.uf AS sgl_estado, ");
            query.add("  CASE a.flag WHEN 't' THEN 'S' ELSE 'N' END AS ind_ativo, ");
            query.add("  CASE WHEN c.valor not in ('None') ");
            query.add("  		THEN CAST(c.valor AS INTEGER) ");
            query.add("        ELSE (SELECT distinct bb.deposito ");
            query.add("  			  FROM empresa_conta aa ");
            query.add("              INNER JOIN caixa_conf bb ON (bb.conta = aa.conta) ");
            query.add("              WHERE aa.empresa = a.grid ) ");
            query.add("        END AS cod_almoxarifado_venda, ");
            query.add("  'N' AS ind_verificar_saldo_caixa, ");
            query.add("  'S' AS ind_contribuinte_icms, ");
            query.add("  b.contribuinte_ipi AS ind_contribuinte_ipi, ");
            query.add("  CASE a.regime_apuracao WHEN '1' THEN 'N' ELSE 'S' END AS ind_regime_deb_cred_icms, ");
            query.add("  b.contribuinte_ipi AS ind_regime_deb_cred_ipi, ");
            query.add("  'N' AS ind_regime_deb_cred_iss, ");
            query.add("  CASE a.regime_apuracao WHEN '1' THEN 'N' ELSE 'S' END AS ind_regime_deb_cred_piscof, ");
            query.add("  'N' AS ind_nfe_pedido ");
            query.add("FROM pessoa a ");
            query.add("INNER JOIN empresa_fiscal b ON (b.empresa = a.grid) ");
            query.add("LEFT JOIN config c ON (c.empresa=a.grid AND c.chave='deposito_padrao') ");
            query.add("WHERE a.codigo = " + codEmpresa);
            query.add("ORDER BY a.codigo ");
        });
        return item;
    }

    public List<Empresa> empresas(String filtro, Integer limit, Integer offset) {
        List<Empresa> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_empresa, ");
            query.add("  only_numbers(a.cpf) AS num_cnpj, ");
            query.add("  a.nome AS nom_razao_social, ");
            query.add("  a.nome_reduzido AS nom_fantasia, ");
            query.add("  a.uf AS sgl_estado, ");
            query.add("  CASE a.flag WHEN 't' THEN 'S' ELSE 'N' END AS ind_ativo, ");
            query.add("  CASE WHEN c.valor not in ('None') ");
            query.add("  		THEN CAST(c.valor AS INTEGER) ");
            query.add("        ELSE (SELECT distinct bb.deposito ");
            query.add("  			  FROM empresa_conta aa ");
            query.add("              INNER JOIN caixa_conf bb ON (bb.conta = aa.conta) ");
            query.add("              WHERE aa.empresa = a.grid ) ");
            query.add("        END AS cod_almoxarifado_venda, ");
            query.add("  'N' AS ind_verificar_saldo_caixa, ");
            query.add("  'S' AS ind_contribuinte_icms, ");
            query.add("  b.contribuinte_ipi AS ind_contribuinte_ipi, ");
            query.add("  CASE a.regime_apuracao WHEN '1' THEN 'N' ELSE 'S' END AS ind_regime_deb_cred_icms, ");
            query.add("  b.contribuinte_ipi AS ind_regime_deb_cred_ipi, ");
            query.add("  'N' AS ind_regime_deb_cred_iss, ");
            query.add("  CASE a.regime_apuracao WHEN '1' THEN 'N' ELSE 'S' END AS ind_regime_deb_cred_piscof, ");
            query.add("  'N' AS ind_nfe_pedido ");
            query.add("FROM pessoa a ");
            query.add("INNER JOIN empresa_fiscal b ON (b.empresa = a.grid) ");
            query.add("LEFT JOIN config c ON (c.empresa=a.grid AND c.chave='deposito_padrao') ");
            query.add("WHERE a.tipo ILIKE '%E%' ");
            query.add("AND CASE a.flag WHEN 'A' THEN 'S' ELSE 'N' END = 'S' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CAST(a.codigo as TEXT) ILIKE :filtro) OR "); //CodEmpresa
                query.add("  (a.cpf ILIKE :filtro) OR "); //CNPJ
                query.add("  (a.nome ILIKE :filtro) OR ");//Razao Social
                query.add("  (a.nome_reduzido ILIKE :filtro) "); //Nome Fantasia
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.codigo  ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
    }

    public String parametroSistema(Integer codParametro) {
        String item = nativeFindValue("SELECT " +
                "CAST(codigo as TEXT) AS val_parametro " +
                "FROM motivo_movto " +
                "WHERE CASE WHEN " + codParametro + " = 21 THEN codigo = 1 " +
                "           WHEN " + codParametro + " = 425 THEN codigo = 64 " +
                "      END ");
        return item;
    }

    public String parametroEmpresa(Integer codParametro) {
        String item = nativeFindValue("SELECT " +
                "CASE WHEN " + codParametro + " = 208 THEN '' " +
                "     WHEN " + codParametro + " = 324 THEN '' " +
                "END ");
        return item;
    }


    public boolean validaDataLimite(Integer codEmpresa, LocalDate data) {
        if (data == null)
            return true;

        LocalDate hoje = LocalDate.now();
        if (data.isEqual(hoje) || data.isAfter(hoje))
            return true;

        LimiteRetroativo item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.cod_empresa, ");
            query.add("  a.ind_tipo_limite_retroativo, ");
            query.add("  CAST(a.dta_limite_retroativo as DATE) as dta_limite_retroativo, ");
            query.add("  COALESCE(a.qtd_dias_limite_retroacao, 0) as qtd_dias_limite_retroacao ");
            query.add("FROM tab_empresa a ");
            query.add("WHERE a.cod_empresa = " + codEmpresa);
        }, LimiteRetroativo.class);

        if (item != null) {
            if (Strings.equals(item.getIndTipoLimiteRetroativo(), "DA")) {
                return (item.getDtaLimiteRetroativo() == null) || data.isAfter(item.getDtaLimiteRetroativo()) || data.isEqual(item.getDtaLimiteRetroativo());
            } else {
                int dias = Dates.daysSince(data);
                return item.getQtdDiasLimiteRetroacao() >= dias;
            }
        }

        return true;
    }

}
