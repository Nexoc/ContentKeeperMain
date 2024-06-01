package at.davl.main.config.DatabaseConfiguration;


import at.davl.main.entities.entityContent.Content;
import at.davl.main.entities.entityContent.Folder;
import at.davl.main.entities.entityContent.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "contentEntityManagerFactory",
        transactionManagerRef = "contentTransactionManager",
        basePackageClasses = {Content.class, Folder.class, User.class})
@EntityScan("at/davl/main/entities/entityContent")
public class ContentJpaConfiguration {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean contentEntityManagerFactory(
            @Qualifier("contentDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Content.class, Folder.class, User.class)
                .build();
    }


    @Bean
    public PlatformTransactionManager contentTransactionManager(
            @Qualifier("contentEntityManagerFactory") LocalContainerEntityManagerFactoryBean contentEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(contentEntityManagerFactory.getObject()));
    }

}
