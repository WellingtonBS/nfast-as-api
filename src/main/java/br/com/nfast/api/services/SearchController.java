package br.com.nfast.api.services;

import br.com.nfast.api.model.crm.Item;
import br.com.nfast.api.repo.crm.ItemRepo;
import br.com.nfast.api.utils.Numbers;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SearchController implements SearchApi {
    @Autowired
    private ItemRepo repo;

    @Override
    public ResponseEntity<String> searchValue(String token, String clientId, String query) {
        if (Strings.isEmpty(query))
            throw new RuntimeException("Missing Query");

        String value = repo.nativeFindValue(query);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Item>> searchList(String token, String clientId, String query, Integer limit, Integer offset) {
        if (Strings.isEmpty(query))
            throw new RuntimeException("Missing Query");

        List<Item> list = repo.nativeFindAll(q -> {
            q.add(query);
            if (Numbers.isNonEmpty(limit))
                q.setLimit(limit);
            if (Numbers.isNonEmpty(offset))
                q.setOffset(offset);
        });
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
