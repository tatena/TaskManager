package msda.taskmanager.controller;



import msda.taskmanager.Service.WorkspaceService;
import msda.taskmanager.model.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {


    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<Void> createWorkspace(@RequestBody NewWorkspaceRequest workspaceDto, HttpServletRequest request){
        workspaceService.createWorkspace(workspaceDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> addMember(@RequestBody MembershipRequest membershipRequest, HttpServletRequest request){
        workspaceService.addMember(membershipRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeMember(@RequestBody WorkspaceMember membershipRequest, HttpServletRequest request){
        workspaceService.removeMember(membershipRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/roles")
    public ResponseEntity<Void> updateRoles(@RequestBody MembershipRequest membershipRequest, HttpServletRequest request){
        workspaceService.updateRoles(membershipRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteWorkspace(@RequestParam Long workspaceID, HttpServletRequest request){
        workspaceService.deleteWorkspace(workspaceID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}