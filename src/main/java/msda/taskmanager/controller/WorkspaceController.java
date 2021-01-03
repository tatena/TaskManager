package msda.taskmanager.controller;



import msda.taskmanager.Service.WorkspaceService;
import msda.taskmanager.model.dto.MembershipRequest;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.WorkspaceDto;
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
    public void createWorkspace(@RequestBody WorkspaceDto workspaceDto){
        workspaceService.createWorkspace(workspaceDto);
    }

    @PutMapping
    public void addMember(MembershipRequest membershipRequest){
        workspaceService.addMember(membershipRequest);
    }

    @DeleteMapping
    public void removeMember(MembershipRequest membershipRequest){
        workspaceService.removeMember(membershipRequest);
    }

    @PutMapping("/roles")
    public void updateRoles(MembershipRequest membershipRequest){
        /* TODO: may need new DTO class */
    }

}
