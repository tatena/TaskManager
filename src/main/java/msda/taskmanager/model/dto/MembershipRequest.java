package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.Role;

@Data
public class MembershipRequest {
    private Workspace workspace;
    private String username;
    private Role role;
}
