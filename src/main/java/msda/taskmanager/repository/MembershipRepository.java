package msda.taskmanager.repository;

import msda.taskmanager.model.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByWorkspaceIdAndUserId(Long workspaceID, Long userID);

    @Transactional
    void deleteMembershipsByWorkspaceId(Long workspaceID);
}
