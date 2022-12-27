package br.com.nfast.api.config.jpa;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedHashMap;
import java.util.Map;

@Lazy
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"br.com.nfast.api.data.repo"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class JpaConfig {
    @Autowired
    private JpaProperties properties;
    @Autowired
    private MultiTenantConnectionProvider provider;
    @Autowired
    private CurrentTenantIdentifierResolver resolver;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(Environment.STATEMENT_BATCH_SIZE, 20);
        params.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        params.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        params.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, provider);
        params.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, resolver);
        params.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        params.put("hibernate.show_sql", "true");

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaPropertyMap(params);
        emf.setPackagesToScan("br.com.nfast.api.model");

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactory().getObject());
        return jtm;
    }

}
