package br.com.nfast.api.repo.conhecimento;

import br.com.nfast.api.config.jpa.DataRepository;
import br.com.nfast.api.model.conhecimento.ConhecimentoResumo;
import br.com.nfast.api.repo.nfe.NFeResumoRepo;
import br.com.nfast.api.utils.Cast;
import br.com.nfast.api.utils.StringList;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ConhecimentoResumoRepo extends DataRepository<ConhecimentoResumo, Integer> {

    @Autowired
    private NFeResumoRepo nFeResumoRepo;

    public ConhecimentoResumoRepo() {
        super(ConhecimentoResumo.class);
    }

    public List<ConhecimentoResumo> listarConhecimento(String chaves, Integer codEmpresa) {
        if (Strings.isEmpty(chaves))
            return null;

        StringBuilder chavesTratada = new StringBuilder();
        for (String chave : Strings.split(chaves, ",")) {
            if (chavesTratada.length() > 0)
                chavesTratada.append(",");
            chavesTratada.append("'").append(chave).append("'");
        }

        StringList sql = new StringList();
        sql.add("SELECT a.seq_conhecimento ");
        sql.add("      ,a.seq_modal ");
        sql.add("      ,a.cod_modelo_documento ");
        sql.add("      ,a.cod_natureza_operacao ");
        sql.add("      ,a.cod_pessoa_transportadora ");
        sql.add("      ,a.cod_tipo_cobranca ");
        sql.add("      ,a.dta_emissao ");
        sql.add("      ,a.ind_tipofrete ");
        sql.add("      ,a.ind_credita_icms ");
        sql.add("      ,a.num_conhecimento ");
        sql.add("      ,a.num_placa_veiculo1 ");
        sql.add("      ,a.num_placa_veiculo2 ");
        sql.add("      ,a.num_placa_veiculo3 ");
        sql.add("      ,a.num_serie ");
        sql.add("      ,a.val_frete ");
        sql.add("      ,a.val_seguro ");
        sql.add("      ,a.val_base_icms ");
        sql.add("      ,a.val_icms ");
        sql.add("      ,a.val_icms_nao_tributado ");
        sql.add("      ,a.val_icms_outros ");
        sql.add("      ,a.val_pagamento_caixa ");
        sql.add("      ,a.val_pagamento_banco ");
        sql.add("      ,a.val_aliquota ");
        sql.add("      ,a.val_frete_peso_volume ");
        sql.add("      ,a.val_sec_cat ");
        sql.add("      ,a.val_despacho ");
        sql.add("      ,a.val_pedagio ");
        sql.add("      ,a.val_total_frete ");
        sql.add("      ,a.cod_empresa ");
        sql.add("      ,a.dta_entrada ");
        sql.add("      ,a.val_base_st ");
        sql.add("      ,a.val_st ");
        sql.add("      ,a.val_dif_aliquota ");
        sql.add("      ,a.des_observacao ");
        sql.add("      ,a.num_chave_nfe ");
        sql.add("      ,a.cod_tributacao_pis ");
        sql.add("      ,a.cod_tributacao_cofins ");
        sql.add("      ,a.val_pis ");
        sql.add("      ,a.val_cofins ");
        sql.add("      ,a.ind_nat_frete ");
        sql.add("      ,a.per_aliquota_icms_st ");
        sql.add("      ,a.ind_finalidade ");
        sql.add("      ,a.seq_despesa ");
        sql.add("FROM tab_conhecimento a ");
        sql.add("WHERE a.cod_empresa = " + codEmpresa);
        sql.add("AND a.num_chave_nfe IN (" + chavesTratada + ")");

        Query query = em.createNativeQuery(sql.toString(), ConhecimentoResumo.class);
        List<ConhecimentoResumo> list = Cast.of(query.getResultList());
        return list.isEmpty() ? null : list;
    }
}
