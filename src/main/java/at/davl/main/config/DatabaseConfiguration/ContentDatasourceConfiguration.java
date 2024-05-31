package at.davl.main.config.DatabaseConfiguration;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Configuration
public class ContentDatasourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.content")
    public DataSourceProperties contentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.content.hikari")
    public DataSource contentDataSource() {
        return contentDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
