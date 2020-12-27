package msda.taskmanager.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import msda.taskmanager.model.enums.WorkspaceStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WORKSPACES", schema = "TASK_MANAGER")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS_ID")
    private WorkspaceStatus status;


    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    List<Membership> users;

    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    List<Task> tasks;
}
