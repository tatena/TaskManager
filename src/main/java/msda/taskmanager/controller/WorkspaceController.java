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
    public void createWorkspace(WorkspaceDto workspaceDto){

    }

    @PutMapping
    public void addMember(MembershipRequest membershipRequest){

    }

    @DeleteMapping
    public void removeMember(MembershipRequest membershipRequest){

    }

    @PutMapping("/roles")
    /* TODO: may need new DTO class */
    public void updateRoles(MembershipRequest membershipRequest){

    }

}
