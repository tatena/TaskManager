package msda.taskmanager.controller;

import msda.taskmanager.Service.UserService;
import msda.taskmanager.auth.CustomUserPrincipal;
import msda.taskmanager.model.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getUserInfo(Principal userPrincipal) {
        CustomUserPrincipal principal = (CustomUserPrincipal) userPrincipal;
        return userService.getUserInfo(principal.user);
    }
}
