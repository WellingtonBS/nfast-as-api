package br.com.nfast.api.config.auth;

import br.com.nfast.api.config.Config;
import br.com.nfast.api.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@Configuration
public class AuthHandler {
    @Value("${auth_database.host:}")
    private String host;
    @Value("${auth_database.port:}")
    private String port;
    @Value("${auth_database.username:}")
    private String username;
    @Value("${auth_database.password:}")
    private String password;

    public ApiAuthUser getApiAuthUser(String username) {
        if (Strings.equals(Config.AUTH_MODE, "file")) {
            Properties props = Files.loadProperties(Config.SERVER_FILE);
            String pass = props.getProperty("user." + username);
            if (Strings.isEmpty(pass))
                return null;

            ApiAuthUser item = new ApiAuthUser();
            item.setUsername(username);
            item.setHashedPassword(Crypt.md5(pass));

            return item;
        } else {
            Connection c = connect();
            try {
                PreparedStatement stm = c.prepareStatement("SELECT * FROM api_auth_users WHERE username = ?");
                stm.setString(1, username);

                ResultSet rs = stm.executeQuery();
                if (!rs.next())
                    return null;

                ApiAuthUser item = new ApiAuthUser();
                item.setUsername(rs.getString("username"));
                item.setHashedPassword(rs.getString("hashed_password"));

                stm.close();
                return item;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                close(c);
            }
        }
    }

    public ClientDatabaseConfig getClientDatabaseConfig(String clientId) {
        if (Strings.equals(Config.AUTH_MODE, "file")) {
            Properties props = Files.loadProperties(Config.SERVER_FILE);
            String host = props.getProperty(clientId + ".host");
            if (Strings.isEmpty(host))
                return null;

            ClientDatabaseConfig item = new ClientDatabaseConfig();
            item.setClientId(clientId);
            item.setHost(host);
            item.setDatabase(clientId);
            item.setPort(Numbers.asInteger(props.getProperty(clientId + ".port"), 5432));
            item.setUser(props.getProperty(clientId + ".user"));
            item.setPassword(props.getProperty(clientId + ".pass"));
            item.setMaxPoolSize(Numbers.asInteger(props.getProperty(clientId + ".maxpoolsize"), 5));
            return item;
        } else {
            Connection c = connect();
            try {
                PreparedStatement stm = c.prepareStatement("SELECT * FROM client_database_configurations WHERE client_id = ?");
                stm.setString(1, clientId);

                ResultSet rs = stm.executeQuery();
                if (!rs.next())
                    return null;

                ClientDatabaseConfig item = new ClientDatabaseConfig();
                item.setClientId(rs.getString("client_id"));
                item.setHost(rs.getString("host"));
                item.setDatabase(rs.getString("database"));
                item.setPort(rs.getInt("port"));
                item.setUser(rs.getString("user"));
                item.setPassword(rs.getString("password"));
                item.setMaxPoolSize(rs.getInt("max"));

                stm.close();
                return item;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                close(c);
            }
        }
    }

    private Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            if (Strings.isNonEmpty(Config.SERVER_FILE)) {
                return DriverManager.getConnection("jdbc:postgresql://" + Config.AUTH_HOST + ":" + Config.AUTH_PORT + "/" + Strings.firstNonEmpty(Config.AUTH_NAME, "auth_database") + "?ApplicationName=nfast-api", Config.AUTH_USER, Config.AUTH_PASS);
            } else {
                return DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/auth_database?ApplicationName=nfast-api", username, password);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void close(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception e) {

            }
        }
    }

    public <T> T find(Connection c, String sql, T def, Object... params) {
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            int index = 0;
            for (Object item : params) {
                index++;
                stm.setObject(index, item);
            }

            T result = null;
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                result = Cast.of(rs.getObject(1));
            }

            if (result == null)
                result = def;

            stm.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(Connection c, String sql, Object... params) {
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            int index = 0;
            for (Object item : params) {
                index++;
                stm.setObject(index, item);
            }

            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}