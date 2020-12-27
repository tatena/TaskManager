package msda.taskmanager.repository;

import msda.taskmanager.model.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Membership, Long> {
}
