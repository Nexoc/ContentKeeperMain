package at.davl.main.repositories.repositoryContent;

import at.davl.main.entities.entityContent.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
