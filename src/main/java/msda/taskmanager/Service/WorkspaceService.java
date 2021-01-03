package msda.taskmanager.Service;

import msda.taskmanager.mapper.WorkspaceMapper;
import msda.taskmanager.model.dto.MembershipRequest;
import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.Membership;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.Role;
import msda.taskmanager.repository.MembershipRepository;
import msda.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MembershipRepository membershipRepository;
    private final UserService userService;

    public WorkspaceService(WorkspaceRepository workspaceRepository, MembershipRepository membershipRepository, UserService userService) {
        this.workspaceRepository = workspaceRepository;
        this.membershipRepository = membershipRepository;
        this.userService = userService;
    }

    public void createWorkspace(WorkspaceDto workspaceDto){
        Workspace workspace = WorkspaceMapper.fromDto(workspaceDto);
        workspace = workspaceRepository.save(workspace);
        User user = userService.getAuthenticatedUser();
//        workspace.setId(10L);
        Membership membership = new Membership();
        membership.setWorkspace(workspace);
        membership.setUser(user);
        membership.setRole(Role.ROLE_ADMIN);

        membershipRepository.save(membership);
        workspace.getMemberships().add(membership);

    }

    public void addMember(MembershipRequest membershipRequest){

    }

    public void removeMember(MembershipRequest membershipRequest){

    }

    /* TODO: may need new DTO class */
    public void updateRoles(MembershipRequest membershipRequest){

    }
}
