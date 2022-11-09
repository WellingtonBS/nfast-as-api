package br.com.nfast.api.services;

import br.com.nfast.api.model.fiscal.*;
import br.com.nfast.api.repo.fiscal.*;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FiscalController implements FiscalApi {
    @Autowired
    private NopRepo nopRepo;
    @Autowired
    private TributacaoRepo tributacaoRepo;
    @Autowired
    private TributacaoNfeRepo tributacaoNfeRepo;
    @Autowired
    private AjusteIcmsRepo ajusteIcmsRepo;
    @Autowired
    private ModeloDocumentoRepo modeloDocumentoRepo;
    @Autowired
    private NcmRepo ncmRepo;
    @Autowired
    private NcmStRepo ncmStRepo;
    @Autowired
    private ClasseFiscalRepo classeFiscalRepo;

    @Override
    public ResponseEntity<Nop> nop(String token, String clientId, Integer codNaturezaOperacao) {
        Nop item = nopRepo.findById(codNaturezaOperacao).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Nop>> nopList(String token, String clientId, String indEntradaSaida, String indNopFrete, String indAtivoImobilizado, String indOrigemDestino, String filtro, Integer limit, Integer offset) {
        List<Nop> list = nopRepo.findAll(query -> {
            query.add("SELECT a FROM Nop a ");
            query.add("WHERE a.indDesativada = 'N' ");
            if (Strings.isNonEmpty(indEntradaSaida))
                query.add("AND a.indEntradaSaida = '" + indEntradaSaida + "' ");
            if (Strings.isNonEmpty(indNopFrete))
                query.add("AND a.indNopFrete = '" + indNopFrete + "' ");
            if (Strings.isNonEmpty(indAtivoImobilizado))
                query.add("AND a.indAtivoImobilizado = '" + indAtivoImobilizado + "' ");
            if (Strings.isNonEmpty(indOrigemDestino))
                query.add("AND a.indOrigemDestino = '" + indOrigemDestino + "' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(a.numCfop) LIKE :filtro) OR ");
                query.add("  (LOWER(a.desNaturezaOperacao) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.numCfop, a.desNaturezaOperacao ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Tributacao> tributacao(String token, String clientId, Integer codTributacao) {
        Tributacao item = tributacaoRepo.findById(codTributacao).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Tributacao>> tributacaoList(String token, String clientId, String indEntradaSaida, String indTipoTributo, String filtro, Integer limit, Integer offset) {
        List<Tributacao> list = tributacaoRepo.findAll(query -> {
            query.add("SELECT a FROM Tributacao a ");
            query.add("WHERE a.indInativa = 'N' ");
            if (Strings.isNonEmpty(indEntradaSaida)) {
                if (Strings.equals(indEntradaSaida, "E+"))
                    query.add("AND COALESCE(a.indEntradaSaida, '') <> 'S' ");
                else if (Strings.equals(indEntradaSaida, "S+"))
                    query.add("AND COALESCE(a.indEntradaSaida, '') <> 'E' ");
                else query.add("AND a.indEntradaSaida = '" + indEntradaSaida + "' ");
            }
            if (Strings.isNonEmpty(indTipoTributo))
                query.add("AND a.indTipoTributo = '" + indTipoTributo + "' ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (CONCAT(a.codTributacao, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.codSituacaoTributaria) LIKE :filtro) OR ");
                query.add("  (LOWER(a.desTributacao) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.codSituacaoTributaria, a.desTributacao ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TributacaoNfe> tributacaoNfe(String token, String clientId, Integer codItem, Integer codEmpresa, String natureza, String uf) {
        TributacaoNfe item = tributacaoNfeRepo.getTributacaoNfe(codItem, codEmpresa, natureza, uf);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> icmsNfe(String token, String clientId, Integer codItem, Integer codEmpresa, String natureza, String uf, String cst, Double perAliquota, Double perReducaoBc, String verificaClasseIcms) {
        Integer value = tributacaoRepo.getIcmsNfe(codItem, codEmpresa, natureza, uf, cst, perAliquota, perReducaoBc, verificaClasseIcms);
        return new ResponseEntity<>(Numbers.isEmpty(value) ? "0" : value.toString(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ModeloDocumento> modeloDocumento(String token, String clientId, Integer codModeloDocumento) {
        ModeloDocumento item = modeloDocumentoRepo.findById(codModeloDocumento).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ModeloDocumento>> modeloDocumentoList(String token, String clientId, Integer codEmpresa, String codModeloDocAnexo7, String indEntrada, String indSaida, String filtro, Integer limit, Integer offset) {
        List<ModeloDocumento> list = modeloDocumentoRepo.findAll(query -> {
            query.add("SELECT a FROM ModeloDocumento a ");
            query.add("WHERE a.indAtivo = 'S' ");
            if (Numbers.isNonEmpty(codEmpresa))
                query.add("AND a.codEmpresa = " + codEmpresa + " ");
            if (Strings.isNonEmpty(codModeloDocAnexo7)) {
                query.add("AND a.codModeloDocAnexo7 = :codModeloDocAnexo7 ");
                query.set("codModeloDocAnexo7", codModeloDocAnexo7);
            }
            if (Strings.isNonEmpty(indEntrada)) {
                query.add("AND a.indEntrada = :indEntrada ");
                query.set("indEntrada", indEntrada);
            }
            if (Strings.isNonEmpty(indSaida)) {
                query.add("AND a.indSaida = :indSaida ");
                query.set("indSaida", indSaida);
            }
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(a.desModeloDocumento) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.desModeloDocumento ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AjusteIcms> ajusteIcms(String token, String clientId, Integer seqAjApurIcms) {
        AjusteIcms item = ajusteIcmsRepo.findById(seqAjApurIcms).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AjusteIcms>> ajusteIcmsList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<AjusteIcms> list = ajusteIcmsRepo.findAll(query -> {
            query.add("SELECT a FROM AjusteIcms a ");
            query.add("WHERE a.indTipoAjuste IN('DF', 'AS') ");
            query.add("AND SUBSTRING(a.codIcms, 1, 1) IN('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z') ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (LOWER(a.codIcms) LIKE :filtro) OR ");
                query.add("  (LOWER(a.desIcms) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.codIcms, a.desIcms ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Ncm> ncm(String token, String clientId, Integer seqNcm) {
        Ncm item = ncmRepo.findById(seqNcm).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Ncm>> ncmList(String token, String clientId, String codNcm, String filtro, Integer limit, Integer offset) {
        List<Ncm> list = ncmRepo.findAll(query -> {
            query.add("SELECT a FROM Ncm a ");
            query.add("WHERE a.seqNcm > 0 ");
            if (Strings.isNonEmpty(codNcm)) {
                query.add("AND a.codNcm = :codNcm ");
                query.set("codNcm", codNcm);
            }
            if (Strings.isNonEmpty(filtro)) {
                query.add("AND ( ");
                query.add("  (a.codNcm LIKE :filtro) OR ");
                query.add("  (LOWER(a.desNcm) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.codNcm ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NcmSt> ncmSt(String token, String clientId, Integer codItem, String uf, LocalDate data) {
        NcmSt item = ncmStRepo.ncmSt(codItem, uf, data);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClasseFiscal> classeFiscal(String token, String clientId, Integer codClasseFiscal) {
        ClasseFiscal item = classeFiscalRepo.findById(codClasseFiscal).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClasseFiscal>> classeFiscalList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<ClasseFiscal> list = classeFiscalRepo.findAll(query -> {
            query.add("SELECT a FROM ClasseFiscal a ");
            if (Strings.isNonEmpty(filtro)) {
                query.add("WHERE ( ");
                query.add("  (CONCAT(a.codClasseFiscal, '') LIKE :filtro) OR ");
                query.add("  (LOWER(a.desClasseFiscal) LIKE :filtro) ");
                query.add(") ");
                query.set("filtro", "%" + filtro.toLowerCase() + "%");
            }
            query.add("ORDER BY a.desClasseFiscal ");
            if (Numbers.isNonEmpty(limit))
                query.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                query.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClasseFiscal> classeFiscalIcms(String token, String clientId, Integer codTributacao, String sglEstado, String indNatureza) {
        ClasseFiscal item = null;
        Integer codClasseFiscal = classeFiscalRepo.classeFiscalIcms(codTributacao, sglEstado, indNatureza);
        if (Numbers.isNonEmpty(codClasseFiscal))
            item = classeFiscalRepo.findById(codClasseFiscal).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
