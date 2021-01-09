package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.enums.TaskStatus;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private Long id;
    private String description;
    private String authorUsername;
    private String receiverUsername;
    private String workspaceName;
    private TaskStatus status;
    private LocalDateTime startDate;
    private LocalDateTime deadline;

}
