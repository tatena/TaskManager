package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.enums.Role;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Boolean deleted;
    private Integer timezone;

}
