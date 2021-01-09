package msda.taskmanager.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msda.taskmanager.model.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS", schema = "TASK_MANAGER")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
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

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "START_DATE", columnDefinition = "DATETIME")
    private LocalDateTime startDate;

    @Column(name = "END_DATE", columnDefinition = "DATETIME")
    private LocalDateTime endDate;

    @Column(name = "DEADLINE", columnDefinition = "DATETIME")
    private LocalDateTime deadline;

}
