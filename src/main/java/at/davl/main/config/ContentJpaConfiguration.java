package at.davl.main.config;


import at.davl.main.entities.entityContent.Content;
import at.davl.main.entities.entityContent.Folder;
import at.davl.main.entities.entityContent.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = {Content.class, Folder.class, User.class},
        entityManagerFactoryRef = "contentEntityManagerFactory",
        transactionManagerRef = "contentTransactionManager"
)
public class ContentJpaConfiguration {

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
