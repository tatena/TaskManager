package msda.taskmanager.model.dto;

import lombok.Data;


@Data
public class SignUpRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer timezone;
}
