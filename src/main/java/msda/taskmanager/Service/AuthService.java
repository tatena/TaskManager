package msda.taskmanager.Service;

import msda.taskmanager.mapper.UserMapper;
import msda.taskmanager.model.dto.SignUpRequest;
import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.enums.Role;
import msda.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto signUpUser(SignUpRequest signUpRequest) {
        User newUser = new User();
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRole(Role.ROLE_USER);
        newUser.setDeleted(false);
        newUser.setTimezone(signUpRequest.getTimezone());

        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw  new RuntimeException("User already exists");
        }

        return UserMapper.toDto(userRepository.save(newUser));
    }

}
