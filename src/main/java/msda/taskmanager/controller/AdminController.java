package msda.taskmanager.controller;

import msda.taskmanager.Service.AdminService;
import msda.taskmanager.model.dto.CreateUserRequest;
import msda.taskmanager.model.dto.SignUpRequest;
import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.utils.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final RequestService requestService;

    public AdminController(AdminService adminService, RequestService requestService) {
        this.adminService = adminService;
        this.requestService = requestService;
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, HttpServletRequest request){
        adminService.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest createUserRequest, HttpServletRequest request){
        adminService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
