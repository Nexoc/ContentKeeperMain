package at.davl.main.config.DatabaseConfiguration;


import java.util.HashMap;
import java.util.Objects;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


/*
### Configure and Use Multiple DataSources in Spring Boot
https://www.baeldung.com/spring-boot-configure-multiple-datasources

https://www.baeldung.com/spring-data-jpa-multiple-databases

https://github.com/eugenp/tutorials/tree/master/persistence-modules/spring-data-jdbc

*/

@Configuration
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(
        basePackages = { "at.davl.main.repositories.repositoryUsers" },
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
public class UserDatasourceConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "userEntityManager")
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(new String[] { "at/davl/main/entities/entityUsers" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl.auto", env.getProperty("spring.jpa.hibernate.ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.database-platform"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.users")
    public DataSource userDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.users.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.users.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.users.user"));
        dataSource.setPassword(env.getProperty("spring.datasource.users.pass"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( userEntityManager().getObject());
        return transactionManager;
    }
}
