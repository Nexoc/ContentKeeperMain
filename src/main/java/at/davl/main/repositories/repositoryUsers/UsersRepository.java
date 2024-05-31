package at.davl.main.repositories.repositoryUsers;

import at.davl.main.entities.entityUsers.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
