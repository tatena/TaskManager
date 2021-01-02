package msda.taskmanager.controller;

import msda.taskmanager.Service.AuthService;
import msda.taskmanager.model.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public void signUpUser(@RequestBody SignUpRequest signUpRequest) {
        authService.signUpUser(signUpRequest);
    }




}
