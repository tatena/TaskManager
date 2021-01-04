package msda.taskmanager.repository;

import msda.taskmanager.model.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    //@Query(value="select m from workspace_users m where m.workspace_id = :workspaceID and m.user_id = :userID")
    Optional<Membership> findByWorkspaceIdAndUserId(Long workspaceID, Long userID);
}
