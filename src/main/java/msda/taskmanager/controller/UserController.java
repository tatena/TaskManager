package msda.taskmanager.controller;

import msda.taskmanager.Service.UserService;
import msda.taskmanager.auth.CustomUserPrincipal;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.Workspace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/workspaces")
    public ResponseEntity<List<WorkspaceDto>> getUserWorkspaces(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserWorkspaces());
    }

    @GetMapping("/tasks/received")
    public ResponseEntity<List<TaskDto>> getUserReceivedTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserReceivedTasks());
    }

    @GetMapping("/tasks/created")
    public ResponseEntity<List<TaskDto>> getUserCreatedTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserCreatedTasks());
    }
}
