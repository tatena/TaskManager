package msda.taskmanager.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import msda.taskmanager.model.enums.Role;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "task_manager")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE users SET deleted=true WHERE id=?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Membership> memberships;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Task> receivedTasks;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Task> createdTasks;

    @Column(name = "DELETED")
    private Boolean deleted;

    public List<Workspace> getWorkspaces() {
        List<Workspace> res = new ArrayList<>();

        for (Membership membership : memberships) {
            res.add(membership.getWorkspace());
        }

        return res;
    }
}
