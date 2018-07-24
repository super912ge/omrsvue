package com.proship.omrs.config;



import com.proship.omrs.base.repository.CustomizedRepository;
import com.zaxxer.hikari.HikariDataSource;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.proship.omrs",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",basePackageClasses = CustomizedRepository.class)
@EnableTransactionManagement
public class JpaConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Autowired
    private Environment environment;

//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

    @Bean
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = dataSourceProperties();
        HikariDataSource dataSource =
                org.springframework.boot.jdbc.DataSourceBuilder
                        .create(dataSourceProperties.getClassLoader())

                        .driverClassName(dataSourceProperties.getDriverClassName())
                        .url(dataSourceProperties.getUrl())
                        .username(dataSourceProperties.getUsername())
                        .password(dataSourceProperties.getPassword())
                        .type(HikariDataSource.class)
                        .build();
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
//            throws NamingException {
//        LocalContainerEntityManagerFactoryBean factoryBean = new
//                LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        factoryBean.setJpaProperties(jpaProperties());
//        return factoryBean;
//    }

//    @Bean
//    public SessionFactory getSessionFactory() throws NamingException {
//        return entityManagerFactory.unwrap(SessionFactory.class);
//    }


    /*
     * Provider specific adapter.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

        return  new HibernateJpaVendorAdapter();
    }

    /*
     * Here you can specify any provider specific properties.
     */
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",
                environment.getRequiredProperty
                        ("spring.jpa.properties.hibernate.dialect")
        );
        return properties;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager
    transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

}
