package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.enums.TaskStatus;

@Data
public class TaskStatusUpdate {
    private Long taskID;
    private TaskStatus status;
}
