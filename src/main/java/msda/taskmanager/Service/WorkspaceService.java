package msda.taskmanager.Service;

import msda.taskmanager.mapper.MembershipMapper;
import msda.taskmanager.mapper.WorkspaceMapper;
import msda.taskmanager.model.dto.MembershipRequest;
import msda.taskmanager.model.dto.NewWorkspaceRequest;
import msda.taskmanager.model.dto.WorkspaceMember;
import msda.taskmanager.model.entity.Membership;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.Role;
import msda.taskmanager.repository.MembershipRepository;
import msda.taskmanager.repository.TaskRepository;
import msda.taskmanager.repository.UserRepository;
import msda.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final MembershipRepository membershipRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, MembershipRepository membershipRepository, UserService userService, UserRepository userRepository, TaskRepository taskRepository) {
        this.workspaceRepository = workspaceRepository;
        this.membershipRepository = membershipRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public void createWorkspace(NewWorkspaceRequest workspaceDto){
        Workspace workspace = WorkspaceMapper.fromDto(workspaceDto);
        workspaceRepository.save(workspace);
        User user = userService.getAuthenticatedUser();

        Membership membership = MembershipMapper.createMembership(workspace, user, Role.ROLE_ADMIN);

        membershipRepository.save(membership);
    }

    public void addMember(MembershipRequest membershipRequest){
        WorkspaceMember workspaceMember = membershipRequest.getWorkspaceMember();
        User user = userRepository.findById(workspaceMember.getUserID())
                    .orElseThrow(() -> new RuntimeException("User does not exist"));

        Workspace workspace = workspaceRepository.getById(workspaceMember.getWorkspaceID())
                    .orElseThrow(() -> new RuntimeException("Workspace doesn't exist"));

        Membership membership = MembershipMapper.createMembership(
                workspace, user, membershipRequest.getRole());

        membershipRepository.save(membership);
    }

    public void removeMember(WorkspaceMember workspaceMember){
        Membership membership = membershipRepository.findByWorkspaceIdAndUserId
                (workspaceMember.getWorkspaceID(), workspaceMember.getUserID())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        membershipRepository.delete(membership);
    }

    public void deleteWorkspace(Long workspaceID){
        User user = userService.getAuthenticatedUser();
        Membership membership = membershipRepository.findByWorkspaceIdAndUserId(workspaceID, user.getId())
                .orElseThrow(() -> new RuntimeException("no such user in this workspace"));
        if(membership.getRole().equals(Role.ROLE_ADMIN)){
            Workspace workspace = workspaceRepository.findById(workspaceID)
                    .orElseThrow(() -> new RuntimeException("no such workspace"));

            membershipRepository.deleteMembershipsByWorkspaceId(workspaceID);
            taskRepository.deleteTaskByWorkspaceId(workspaceID);
            workspaceRepository.delete(workspace);
        }
    }

    public void updateRoles(MembershipRequest membershipRequest){
        WorkspaceMember workspaceMember = membershipRequest.getWorkspaceMember();
        Role currentRole = membershipRequest.getRole();
        Membership membership = membershipRepository.findByWorkspaceIdAndUserId
                (workspaceMember.getWorkspaceID(), workspaceMember.getUserID())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        if(!membership.getRole().equals(currentRole)){
            membership.setRole(membershipRequest.getRole());
            membershipRepository.save(membership);
        }
    }
}
