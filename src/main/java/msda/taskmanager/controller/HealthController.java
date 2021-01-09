package msda.taskmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HealthController {
    @GetMapping("/check")
    public ResponseEntity<Void> check(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
