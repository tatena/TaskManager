package msda.taskmanager.controller;

import msda.taskmanager.Service.AuthService;
import msda.taskmanager.model.dto.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Void> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        authService.signUpUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
