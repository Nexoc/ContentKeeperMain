package at.davl.main.repositories.repositoryContent;

import at.davl.main.entities.entityContent.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
}
