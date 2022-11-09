package br.com.nfast.api.config.jpa;

import br.com.nfast.api.config.Config;
import br.com.nfast.api.config.auth.AuthHandler;
import br.com.nfast.api.config.auth.ClientDatabaseConfig;
import br.com.nfast.api.utils.Assert;
import br.com.nfast.api.utils.Strings;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Lazy
@Component
public class TenantProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final Logger log = LoggerFactory.getLogger(TenantProvider.class);
    private final Map<String, DataSource> map = new ConcurrentHashMap<>();

    @Autowired
    private AuthHandler authHandler;

    @Override
    protected DataSource selectAnyDataSource() {
        throw new RuntimeException("Missing clientId");
    }

    @Override
    protected DataSource selectDataSource(String clientId) {
        DataSource dataSource = map.get(clientId);
        if (dataSource == null) {
            dataSource = createDataSource(clientId);
            map.put(clientId, dataSource);
        }
        return dataSource;
    }

    private DataSource createDataSource(String clientId) {
        Assert.nonEmpty(clientId, "Missing clientId");
        log.info("Starting DataSource for clientId " + clientId);

        ClientDatabaseConfig db = authHandler.getClientDatabaseConfig(clientId);
        Assert.nonNull(db, "clientId " + clientId + " not found");

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://" + db.getHost() + ":" + db.getPort() + "/" + db.getDatabase());
        config.setUsername(db.getUser());
        config.setPassword(db.getPassword());

        config.setIdleTimeout(15 * 60 * 1000); //15min
        config.setMinimumIdle(0);
        config.setMaximumPoolSize(db.getMaxPoolSize());
        config.setConnectionTestQuery("SELECT 1");

        if (Strings.isNonEmpty(Config.APPLICATION_NAME)) {
            config.addDataSourceProperty("ApplicationName", Config.APPLICATION_NAME);
        } else {
            config.addDataSourceProperty("ApplicationName", "nfast-api-" + Config.INSTANCE_ID.toLowerCase());
        }

        log.info("Creating DataSource [ " + config.getJdbcUrl() + " ]");
        return new HikariDataSource(config);
    }

}