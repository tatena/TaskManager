package msda.taskmanager.controller;



import msda.taskmanager.Service.WorkspaceService;
import msda.taskmanager.model.dto.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {


    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public void createWorkspace(@RequestBody NewWorkspaceRequest workspaceDto){
        workspaceService.createWorkspace(workspaceDto);
    }

    @PutMapping
    public void addMember(MembershipRequest membershipRequest){
        workspaceService.addMember(membershipRequest);
    }

    @DeleteMapping
    public void removeMember(WorkspaceMember membershipRequest){
        workspaceService.removeMember(membershipRequest);
    }

    @PutMapping("/roles")
    public void updateRoles(MembershipRequest membershipRequest){
        workspaceService.updateRoles(membershipRequest);
    }

    @DeleteMapping("/delete")
    public void deleteWorkspace(@RequestParam Long workspaceID){
        // TODO: check permission, then delete
    }
}
