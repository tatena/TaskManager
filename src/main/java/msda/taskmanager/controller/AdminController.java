package msda.taskmanager.controller;

import msda.taskmanager.Service.AdminService;
import msda.taskmanager.model.dto.CreateUserRequest;
import msda.taskmanager.model.dto.SignUpRequest;
import msda.taskmanager.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto){
        adminService.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(CreateUserRequest createUserRequest){
        adminService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUserById(id));
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
    }

    @GetMapping("/users/deleted")
    public ResponseEntity<List<UserDto>> getDeletedUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers(true));
    }

    @GetMapping("/users/active")
    public ResponseEntity<List<UserDto>> getActiveUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getUsers(false));
    }

    @DeleteMapping
    public ResponseEntity<Void> removeUser(@RequestParam Long id){
        adminService.removeUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/filter")
    public void searchUsers(UserDto userDto){

    }
}
