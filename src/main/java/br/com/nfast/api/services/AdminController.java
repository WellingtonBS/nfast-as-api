package br.com.nfast.api.services;

import br.com.nfast.api.model.adm.CentroCusto;
import br.com.nfast.api.model.adm.Despesa;
import br.com.nfast.api.model.adm.RateioPadrao;
import br.com.nfast.api.model.adm.TipoDespesa;
import br.com.nfast.api.model.crm.Empresa;
import br.com.nfast.api.repo.adm.CentroCustoRepo;
import br.com.nfast.api.repo.adm.DespesaRepo;
import br.com.nfast.api.repo.adm.RateioPadraoRepo;
import br.com.nfast.api.repo.adm.TipoDespesaRepo;
import br.com.nfast.api.repo.crm.EmpresaRepo;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminController implements AdminApi {
    @Autowired
    private EmpresaRepo empresaRepo;
    @Autowired
    private DespesaRepo despesaRepo;
    @Autowired
    private TipoDespesaRepo tipoDespesaRepo;
    @Autowired
    private CentroCustoRepo centroCustoRepo;
    @Autowired
    private RateioPadraoRepo rateioPadraoRepo;

    @Override
    public ResponseEntity<Empresa> empresa(String token, String clientId, Integer codEmpresa) {
        Empresa item = empresaRepo.empresa(codEmpresa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Empresa>> empresaList(String token, String clientId, String filtro, Integer limit, Integer offset) {
        List<Empresa> list = empresaRepo.empresas(filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TipoDespesa> tipoDespesa(String token, String clientId, Long codTipoDespesa) {
        TipoDespesa item = tipoDespesaRepo.tipoDespesa(codTipoDespesa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TipoDespesa>> tipoDespesaList(String token, String clientId, String indTipo, String filtro, Integer limit, Integer offset) {
        List<TipoDespesa> list = tipoDespesaRepo.tipoDespesaList(indTipo, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CentroCusto> centroCusto(String token, String clientId, Integer codCentroCusto) {
        CentroCusto item = centroCustoRepo.centroCusto(codCentroCusto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CentroCusto>> centroCustoList(String token, String clientId, Integer codEmpresa, String filtro, Integer limit, Integer offset) {
        List<CentroCusto> list = centroCustoRepo.centroCustoList(codEmpresa, filtro, limit, offset);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> parametroSistema(String token, String clientId, Integer codParametro) {
        String value = empresaRepo.nativeFindValue("SELECT val_parametro FROM tab_parametro_sistema WHERE cod_parametro = " + codParametro);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> parametroEmpresa(String token, String clientId, Integer codParametro, Integer codEmpresa) {
        String value = empresaRepo.nativeFindValue("SELECT val_parametro FROM tab_parametro_empresa WHERE cod_parametro = " + codParametro + " AND cod_empresa = " + codEmpresa);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Despesa> despesa(String token, String clientId, Integer seqDespesa) {
        Despesa item = despesaRepo.findById(seqDespesa).orElse(null);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Despesa> gravaDespesa(String token, String clientId, Despesa despesa) {
        Despesa item = despesaRepo.save(despesa);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RateioPadrao>> rateioPadraoDespesa(String token, String clientId, Integer codTipoDespesa, Integer codEmpresa) {
        List<RateioPadrao> list = rateioPadraoRepo.findAll(query -> {
            query.add("SELECT a FROM RateioPadrao a ");
            query.add("WHERE a.seqOrigem = " + codTipoDespesa);
            query.add("AND a.indOrigem = 'D' ");
            query.add("AND EXISTS( ");
            query.add("  SELECT b ");
            query.add("  FROM CentroCusto b ");
            query.add("  WHERE b.codCentroCusto = a.codCentroCusto ");
            query.add("  AND b.codEmpresa = " + codEmpresa);
            query.add(") ");
            query.add("ORDER BY a.seqRateio");
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
