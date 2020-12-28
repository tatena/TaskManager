package msda.taskmanager.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import msda.taskmanager.model.enums.TaskStatus;

import javax.persistence.*;

@Entity
@Table(name = "TASKS", schema = "TASK_MANAGER")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "RECEIVER_ID", referencedColumnName = "ID")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "WORKSPACE_ID", referencedColumnName = "ID")
    private Workspace workspace;

    @Column(name = "DESCRIPTION", columnDefinition = "LONGTEXT")
    @Lob
    private String description;

    @Column(name = "STATUS_ID")
    private TaskStatus status;

}
