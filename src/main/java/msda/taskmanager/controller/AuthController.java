package msda.taskmanager.controller;

import msda.taskmanager.Service.AuthService;
import msda.taskmanager.model.dto.SignUpRequest;
import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.utils.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RequestService requestService;

    public AuthController(AuthService authService, RequestService requestService) {
        this.authService = authService;
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<UserDto> signUpUser(@RequestBody SignUpRequest signUpRequest, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUpUser(signUpRequest));
    }

}
