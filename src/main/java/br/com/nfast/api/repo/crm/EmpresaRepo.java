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
            query.add("  a.cod_empresa, ");
            query.add("  a.num_cnpj, ");
            query.add("  a.nom_razao_social, ");
            query.add("  a.nom_fantasia, ");
            query.add("  c.sgl_estado, ");
            query.add("  a.ind_ativo, ");
            query.add("  a.cod_almoxarifado_venda, ");
            query.add("  a.ind_verificar_saldo_caixa, ");
            query.add("  a.ind_contribuinte_icms, ");
            query.add("  a.ind_contribuinte_ipi, ");
            query.add("  a.ind_regime_deb_cred_icms, ");
            query.add("  a.ind_regime_deb_cred_ipi, ");
            query.add("  a.ind_regime_deb_cred_iss, ");
            query.add("  a.ind_regime_deb_cred_piscof, ");
            query.add("  a.ind_nfe_pedido ");
            query.add("FROM tab_empresa a ");
            query.add("INNER JOIN tab_cidade b ON(b.cod_cidade = a.cod_cidade) ");
            query.add("INNER JOIN tab_estado c ON(c.cod_estado = b.cod_estado) ");
            query.add("WHERE a.cod_empresa = " + codEmpresa);
            query.add("ORDER BY a.cod_empresa ");
        });
        return item;
    }

    public List<Empresa> empresas(String filtro, Integer limit, Integer offset) {
        List<Empresa> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.cod_empresa, ");
            query.add("  a.num_cnpj, ");
            query.add("  a.nom_razao_social, ");
            query.add("  a.nom_fantasia, ");
            query.add("  c.sgl_estado, ");
            query.add("  a.ind_ativo, ");
            query.add("  a.cod_almoxarifado_venda, ");
            query.add("  a.ind_verificar_saldo_caixa, ");
            query.add("  a.ind_contribuinte_icms, ");
            query.add("  a.ind_contribuinte_ipi, ");
            query.add("  a.ind_regime_deb_cred_icms, ");
            query.add("  a.ind_regime_deb_cred_ipi, ");
            query.add("  a.ind_regime_deb_cred_iss, ");
            query.add("  a.ind_regime_deb_cred_piscof, ");
            query.add("  a.ind_nfe_pedido ");
            query.add("FROM tab_empresa a ");
            query.add("INNER JOIN tab_cidade b ON(b.cod_cidade = a.cod_cidade) ");
            query.add("INNER JOIN tab_estado c ON(c.cod_estado = b.cod_estado) ");
            query.add("WHERE a.ind_ativo = 'S' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CAST(a.cod_empresa as TEXT) ILIKE :filtro) OR ");
                query.add("  (a.num_cnpj ILIKE :filtro) OR ");
                query.add("  (a.nom_razao_social ILIKE :filtro) OR ");
                query.add("  (a.nom_fantasia ILIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.cod_empresa ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return list;
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
