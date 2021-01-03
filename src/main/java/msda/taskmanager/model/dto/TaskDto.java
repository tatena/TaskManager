package msda.taskmanager.model.dto;

import lombok.Data;

@Data
public class TaskDto {

    String description;
    String authorUsername;
    String receiverUsername;
    String workspaceName;

}
