package msda.taskmanager.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msda.taskmanager.model.enums.WorkspaceStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WORKSPACES", schema = "TASK_MANAGER")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"memberships", "tasks"})
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    @Lob
    private String description;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private WorkspaceStatus status;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    private List<Membership> memberships;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @Column(name = "TIMEZONE")
    private Integer timezone;
}
