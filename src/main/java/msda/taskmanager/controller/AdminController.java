package msda.taskmanager.controller;


import msda.taskmanager.Service.AdminService;
import msda.taskmanager.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping
    public void updateUser(){

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
