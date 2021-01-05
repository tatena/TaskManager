package msda.taskmanager.Service;

import msda.taskmanager.mapper.UserMapper;
import msda.taskmanager.model.dto.CreateUserRequest;
import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.repository.UserRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
    }

    public void updateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        User user = userOptional.orElseThrow(() -> new RuntimeException("User Does not exist"));

        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getRole() != null) {
            user.setRole(userDto.getRole());
        }

        userRepository.save(user);
    }

    public void createUser(CreateUserRequest createUserRequest) {
        User newUser = new User();
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        newUser.setFirstName(createUserRequest.getFirstName());
        newUser.setLastName(createUserRequest.getLastName());
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setRole(createUserRequest.getRole());

        if (userRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw  new RuntimeException("User already exists");
        }

        userRepository.save(newUser);
    }

    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.orElseThrow(() -> new RuntimeException("User with id does not exist"));

        return UserMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return UserMapper.toDtoList(userRepository.findAll());
    }

    public void removeUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        User user = userOptional.orElseThrow(() -> new RuntimeException("User with id does not exist"));

        if(user.getDeleted()) {
            throw  new RuntimeException("User already deleted");
        }

        userRepository.delete(user);
    }

    public List<UserDto> getUsers(Boolean isDeleted) {
        List<User> users = userRepository.findAllByDeleted(isDeleted);
        return UserMapper.toDtoList(users);
    }

    public UserDto getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new RuntimeException("User with id does not exist"));

        return UserMapper.toDto(user);
    }
}
