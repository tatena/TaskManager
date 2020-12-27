package msda.taskmanager.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import msda.taskmanager.model.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS", schema = "TASK_MANAGER")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
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

    @Column(name = "ROLE_ID")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Membership> workspaces;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    List<Task> receivedTasks;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    List<Task> createdTasks;
}
