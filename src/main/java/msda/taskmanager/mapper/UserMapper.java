package msda.taskmanager.mapper;

import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto res = new UserDto();

        res.setUsername(user.getUsername());
        res.setPassword(user.getPassword());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setRole(user.getRole());
        res.setId(user.getId());
        res.setDeleted(user.getDeleted());

        return res;
    }

    public static List<UserDto> toDtoList(List<User> users) {
        List<UserDto> res = new ArrayList<>();

        for (User user : users) {
            res.add(toDto(user));
        }

        return res;
    }
}
