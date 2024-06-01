package at.davl.main.config.DatabaseConfiguration;

import at.davl.main.entities.entityUsers.Users;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackageClasses = Users.class,
        entityManagerFactoryRef = "usersEntityManagerFactory",
        transactionManagerRef = "usersTransactionManager"
)
@EntityScan("at/davl/main/entities/entityUsers")
public class UserJpaConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean usersEntityManagerFactory(
            @Qualifier("usersDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages(Users.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager usersTransactionManager(
            @Qualifier("usersEntityManagerFactory") LocalContainerEntityManagerFactoryBean usersEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(usersEntityManagerFactory.getObject()));
    }


}
