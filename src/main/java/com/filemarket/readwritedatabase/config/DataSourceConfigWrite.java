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
        entityManagerFactoryRef = "entityManagerFactoryWrite",
        transactionManagerRef = "transactionManagerWrite",
        basePackages = {"com.filemarket.readwritedatabase.repository.writerepository"}
)
public class DataSourceConfigWrite {

    @Primary
    @Bean(name = "writeDataSourceProperties")
    @ConfigurationProperties("spring.datasource.write")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "writeJpaProperties")
    @ConfigurationProperties("spring.jpa.write")
    public JpaProperties jpaProp () {
        return new JpaProperties();
    }

    @Primary
    @Bean(name = "writeDataSource")
    @ConfigurationProperties("spring.datasource.write.configuration")
    public DataSource dataSource(@Qualifier("writeDataSourceProperties") DataSourceProperties writeDataSourceProperties) {
        return writeDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .driverClassName(writeDataSourceProperties.getDriverClassName())
                .url(writeDataSourceProperties.getUrl())
                .username(writeDataSourceProperties.getUsername())
                .password(writeDataSourceProperties.getPassword())
                .build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryWrite")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("writeDataSource") DataSource writeDataSource) {
        return builder
                .dataSource(writeDataSource)
                .packages(MODEL_PACKAGE)
                .persistenceUnit("writePersistenceUnit")
                .properties(jpaProp().getProperties())
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerWrite")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryWrite") EntityManagerFactory writeEntityManagerFactory) {
        return new JpaTransactionManager(writeEntityManagerFactory);
    }
}
