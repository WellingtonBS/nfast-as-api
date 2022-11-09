package br.com.nfast.api.repo.fiscal;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.fiscal.Tributacao;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.stereotype.Repository;

@Repository
public class TributacaoRepo extends DataRepository<Tributacao, Integer> {

    public TributacaoRepo() {
        super(Tributacao.class);
    }

    public Integer getIcmsNfe(Integer codItem, Integer codEmpresa, String natureza, String uf, String cst, double perAliquota, double perReducaoBc, String verificaClasseIcms) {
        Integer codIcms = null;
        if (Strings.equals(verificaClasseIcms, "S")) {
            codIcms = nativeFindValue(query -> {
                query.add("SELECT d.cod_tributacao ");
                query.add("FROM tab_item_empresa a ");
                query.add("INNER JOIN tab_tributacao_classe_fiscal b ON(b.cod_classe_fiscal = a.cod_classe_fiscal) ");
                query.add("INNER JOIN tab_estado c ON(c.cod_estado = b.cod_estado) ");
                query.add("INNER JOIN tab_tributacao d ON(d.cod_tributacao = b.cod_tributacao_entrada_" + Strings.evalDef(natureza, "pj", "F", "pf", "J", "pj") + ") ");
                query.add("WHERE a.cod_item = " + codItem);
                query.add("AND a.cod_empresa = " + codEmpresa);
                query.add("AND c.sgl_estado = '" + uf + "' ");
            });
        } else {
            if (Numbers.isAllNonEmpty(codItem, codEmpresa) && Strings.isAllNonEmpty(natureza, uf)) {
                codIcms = nativeFindValue(query -> {
                    query.add("SELECT d.cod_tributacao ");
                    query.add("FROM tab_item_empresa a ");
                    query.add("INNER JOIN tab_tributacao_classe_fiscal b ON(b.cod_classe_fiscal = a.cod_classe_fiscal) ");
                    query.add("INNER JOIN tab_estado c ON(c.cod_estado = b.cod_estado) ");
                    query.add("INNER JOIN tab_tributacao d ON(d.cod_tributacao = b.cod_tributacao_entrada_" + Strings.evalDef(natureza, "pj", "F", "pf", "J", "pj") + ") ");
                    query.add("WHERE a.cod_item = " + codItem);
                    query.add("AND a.cod_empresa = " + codEmpresa);
                    query.add("AND c.sgl_estado = '" + uf + "' ");
                    query.add("AND d.cod_situacao_tributaria = '" + cst + "' ");
                    query.add("AND d.per_aliquota = " + perAliquota);
                    query.add("AND d.per_reducao_base = " + perReducaoBc);
                });
            }

            if (Numbers.isEmpty(codIcms)) {
                codIcms = nativeFindValue(query -> {
                    query.add("SELECT a.cod_tributacao ");
                    query.add("FROM tab_tributacao a ");
                    query.add("WHERE a.ind_inativa = 'N' ");
                    query.add("AND a.ind_tipo_tributo = 'P' ");
                    query.add("AND a.cod_situacao_tributaria = '" + cst + "' ");
                    query.add("AND a.per_aliquota = " + perAliquota);
                    query.add("AND a.per_reducao_base = " + perReducaoBc);
                });
            }
        }

        return codIcms;
    }

}
