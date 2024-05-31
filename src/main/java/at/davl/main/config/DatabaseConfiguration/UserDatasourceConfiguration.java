package at.davl.main.config.DatabaseConfiguration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/*
### Configure and Use Multiple DataSources in Spring Boot
https://www.baeldung.com/spring-boot-configure-multiple-datasources

https://www.baeldung.com/spring-data-jpa-multiple-databases

https://github.com/eugenp/tutorials/tree/master/persistence-modules/spring-data-jdbc

*/

@Configuration
public class UserDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.users")
    public DataSourceProperties usersDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource usersDataSource() {
        return usersDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }



}
