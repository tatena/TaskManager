package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.enums.Role;

@Data
public class MembershipRequest {
    private WorkspaceMember workspaceMember;
    private Role role;
}
