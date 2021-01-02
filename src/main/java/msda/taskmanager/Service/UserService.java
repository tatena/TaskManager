package msda.taskmanager.Service;


import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserInfo(User user) {
        UserDto res = new UserDto();
        res.setUsername(user.getUsername());

        return res;
    }
}
