package msda.taskmanager.controller;

import msda.taskmanager.Service.AdminService;
import msda.taskmanager.model.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, @RequestParam Long userId){
        adminService.updateUser(userDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public void createUser(){

    }

    @GetMapping
    public void getUser(@RequestParam long id){

    }

    @DeleteMapping
    public void removeUser(@RequestParam long id){

    }

    @GetMapping("/filter")
    public void searchUsers(UserDto userDto){

    }
}
