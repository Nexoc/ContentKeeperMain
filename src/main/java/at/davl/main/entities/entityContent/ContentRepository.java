package at.davl.main.entities.entityContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
}
