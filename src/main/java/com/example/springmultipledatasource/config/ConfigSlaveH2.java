package com.example.springmultipledatasource.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EntityScan("com.example.springmultipledatasource.slave")
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryH2",
        transactionManagerRef = "transactionManagerH2",
        basePackages = { "com.example.springmultipledatasource.slave.repository" }
)
public class ConfigSlaveH2 {

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "h2spring.datasource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("h2spring.datasource.url"));
        dataSource.setDriverClassName(env.getProperty("h2spring.datasource.driver-class-name"));
        dataSource.setUsername(env.getProperty("h2spring.datasource.username"));
        dataSource.setPassword(env.getProperty("h2spring.datasource.password"));
        return dataSource;

    }

    @Bean(name = "entityManagerFactoryH2")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource())
                .packages("com.example.springmultipledatasource.slave.entity")
                .persistenceUnit("productSlav")
                .build();
    }


    @Bean(name = "entityManagerFactoryH2")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryH2") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
