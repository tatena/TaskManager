package msda.taskmanager.Service;

import msda.taskmanager.mapper.MembershipMapper;
import msda.taskmanager.mapper.WorkspaceMapper;
import msda.taskmanager.model.dto.MembershipRequest;
import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.Membership;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.Role;
import msda.taskmanager.repository.MembershipRepository;
import msda.taskmanager.repository.UserRepository;
import msda.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MembershipRepository membershipRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, MembershipRepository membershipRepository, UserService userService, UserRepository userRepository) {
        this.workspaceRepository = workspaceRepository;
        this.membershipRepository = membershipRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void createWorkspace(WorkspaceDto workspaceDto){
        Workspace workspace = WorkspaceMapper.fromDto(workspaceDto);
        workspace = workspaceRepository.save(workspace);
        User user = userService.getAuthenticatedUser();

        Membership membership = MembershipMapper.createMembership(workspace, user, Role.ROLE_ADMIN);

        membershipRepository.save(membership);
        workspace.getMemberships().add(membership);

    }

    public void addMember(MembershipRequest membershipRequest){
        User user = userRepository.findByUsername(membershipRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User does not exist"));

        Membership membership = MembershipMapper.createMembership(
                membershipRequest.getWorkspace(),
                user,
                membershipRequest.getRole());

        membershipRepository.save(membership);
    }

    public void removeMember(MembershipRequest membershipRequest){
        User user = userRepository.findByUsername(membershipRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        Membership membership = MembershipMapper.createMembership(
                membershipRequest.getWorkspace(),
                user,
                membershipRequest.getRole());

        membershipRepository.delete(membership);
    }

    /* TODO: may need new DTO class */
    public void updateRoles(MembershipRequest membershipRequest){

    }
}
