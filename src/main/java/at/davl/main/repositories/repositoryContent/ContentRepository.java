package at.davl.main.repositories.repositoryContent;
import at.davl.main.entities.entityContent.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;



@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>{

}
