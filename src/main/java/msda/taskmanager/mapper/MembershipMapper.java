package msda.taskmanager.mapper;

import msda.taskmanager.model.entity.Membership;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.Role;

public class MembershipMapper {
    public static Membership createMembership(Workspace workspace, User user, Role role){
        Membership membership = new Membership();

        membership.setWorkspace(workspace);
        membership.setUser(user);
        membership.setRole(Role.ROLE_ADMIN);

        return membership;
    }
}
