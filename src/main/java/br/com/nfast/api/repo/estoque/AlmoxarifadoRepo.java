package br.com.nfast.api.repo.estoque;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.estoque.Almoxarifado;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlmoxarifadoRepo extends DataRepository<Almoxarifado, Integer> {

    public AlmoxarifadoRepo() {
        super(Almoxarifado.class);
    }

    public Almoxarifado almoxarifado(Long codAlmoxarifado) {
        Almoxarifado item = nativeFind(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_almoxarifado, ");
            query.add("  a.nome AS des_almoxarifado, ");
            query.add("  b.codigo AS cod_empresa, ");
            query.add("  c.codigo AS cod_item_tanque, ");
            query.add("  a.capacidade AS qtd_capacidade_tanque, ");
            query.add("  a.lastro AS qtd_lastro_tanque, ");
            query.add("  CASE WHEN a.produto IS NULL THEN 'N' ELSE 'S' END AS ind_tanque, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_desativado ");
            query.add("FROM deposito a ");
            query.add("INNER JOIN empresa b ON (a.empresa = b.grid) ");
            query.add("LEFT JOIN produto c ON (c.grid = a.empresa) ");
            query.add("WHERE TRUE = 't' ");
            query.add("AND a.codigo = :codAlmoxarifado");
            query.set("codAlmoxarifado", codAlmoxarifado);
        }, Almoxarifado.class);
        return item;
    }

    public List<Almoxarifado> almoxarifadoList(Long codEmpresa, String indTanque, Long codItemTanque, String filtro, Integer limit, Integer offset) {
        List<Almoxarifado> list = nativeFindAll(query -> {
            query.add("SELECT ");
            query.add("  a.codigo AS cod_almoxarifado, ");
            query.add("  a.nome AS des_almoxarifado, ");
            query.add("  b.codigo AS cod_empresa, ");
            query.add("  c.codigo AS cod_item_tanque, ");
            query.add("  a.capacidade AS qtd_capacidade_tanque, ");
            query.add("  a.lastro AS qtd_lastro_tanque, ");
            query.add("  CASE WHEN a.produto IS NULL THEN 'N' ELSE 'S' END AS ind_tanque, ");
            query.add("  CASE WHEN a.flag = 'A' THEN 'S' ELSE 'N' END AS ind_desativado ");
            query.add("FROM deposito a ");
            query.add("INNER JOIN empresa b ON (a.empresa = b.grid) ");
            query.add("LEFT JOIN produto c ON (c.grid = a.empresa) ");
            query.add("WHERE TRUE = 't' ");
            query.add("AND a.flag = 'A' ");

            if (Numbers.isNonEmpty(codEmpresa))
                query.add("AND b.codigo = '" + codEmpresa + "' ");
            if (Strings.isNonEmpty(indTanque)) {
                query.add("AND CASE WHEN a.produto IS NULL THEN 'N' ELSE 'S' END = :indTanque ");
                query.set("indTanque", indTanque);
            }
            if (Numbers.isNonEmpty(codItemTanque))
                query.add("AND c.codigo = '" + codItemTanque + "' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codigo, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.nome) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.nome");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });

        return list;
    }

}
