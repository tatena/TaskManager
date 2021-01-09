package msda.taskmanager.controller;

import msda.taskmanager.Service.AdminService;
import msda.taskmanager.model.dto.CreateUserRequest;
import msda.taskmanager.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole(\"ROLE_ADMIN\")")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateUser(userDto));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createUser(createUserRequest));
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam Long id, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUserById(id));
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
    }

    @GetMapping("/users/deleted")
    public ResponseEntity<List<UserDto>> getDeletedUsers(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers(true));
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<UserDto>> getActiveUsers(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers(false));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeUser(@RequestParam Long id, HttpServletRequest request){
        adminService.removeUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/username")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUserByUsername(username));
    }
}
