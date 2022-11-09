package br.com.nfast.api.services;

import br.com.nfast.api.config.Config;
import br.com.nfast.api.config.auth.*;
import br.com.nfast.api.utils.Crypt;
import br.com.nfast.api.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController implements AuthApi {
    @Autowired
    private AuthHandler authHandler;

    @Override
    public ResponseEntity<Bearer> token(String clientId, String user, String pass) {
        if (Strings.isEmpty(clientId))
            throw new RuntimeException("Missing clientId");
        ApiAuthUser apiAuthUser = authHandler.getApiAuthUser(user);
        if (apiAuthUser == null)
            throw new RuntimeException("Invalid username or password");
        if (Strings.diff(apiAuthUser.getHashedPassword(), Crypt.md5(pass)))
            throw new RuntimeException("Invalid username or password");

        Bearer bearer = Bearer.create(user, clientId);
        return new ResponseEntity<>(bearer, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientDatabaseConfig> config(String clientId) {
        ClientDatabaseConfig item = authHandler.getClientDatabaseConfig(clientId);
        if (item != null)
            item.setPassword("");
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiInfo> apiInfo() {
        ApiInfo item = new ApiInfo();
        item.setApiVersion(Config.API_VERSION);
        item.setInstanceId(Config.INSTANCE_ID);
        item.setStartTime(Config.START_TIME);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
