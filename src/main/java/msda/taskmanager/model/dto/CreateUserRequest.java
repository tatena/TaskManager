package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.enums.Role;

@Data
public class CreateUserRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Integer timezone;
}
