package com.filemarket.readwritedatabase.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.filemarket.readwritedatabase.ReadWriteDatabaseApplication.MODEL_PACKAGE;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryRead",
        transactionManagerRef = "transactionManagerRead",
        basePackages = {"com.filemarket.readwritedatabase.repository.readrepository"}
)
public class DataSourceConfigRead {

    @Bean(name = "readDataSourceProperties")
    @ConfigurationProperties("spring.datasource.read")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @ConfigurationProperties("spring.jpa.read")
    @Bean(name = "readJpaProperties")
    public JpaProperties jpaProp () {
        return new JpaProperties();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties("spring.datasource.read.configuration")
    public DataSource dataSource(@Qualifier("readDataSourceProperties") DataSourceProperties readDataSourceProperties) {
        return readDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .driverClassName(readDataSourceProperties.getDriverClassName())
                .url(readDataSourceProperties.getUrl())
                .username(readDataSourceProperties.getUsername())
                .password(readDataSourceProperties.getPassword())
                .build();
    }

    @Bean(name = "entityManagerFactoryRead")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("readDataSource") DataSource readDataSource) {
        return builder
                .dataSource(readDataSource)
                .packages(MODEL_PACKAGE)
                .persistenceUnit("readPersistenceUnit")
                .properties(jpaProp().getProperties())
                .build();
    }

    @Bean(name = "transactionManagerRead")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryRead") EntityManagerFactory readEntityManagerFactory) {
        return new JpaTransactionManager(readEntityManagerFactory);
    }

}
