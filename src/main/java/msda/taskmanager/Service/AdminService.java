package msda.taskmanager.Service;

import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;

    private final UserService userService;

    public AdminService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void updateUser(UserDto userDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User Does nto exist"));

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getRole() != null) {
            System.out.println("here");
            user.setRole(userDto.getRole());
        }

        userRepository.save(user);
    }
}
