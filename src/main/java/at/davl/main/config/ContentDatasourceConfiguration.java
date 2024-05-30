package at.davl.main.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Primary
@Configuration
public class ContentDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.content")
    public DataSourceProperties contentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource contentDataSource() {
        return contentDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public JdbcTemplate contentJdbcTemplate(@Qualifier("contentDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
