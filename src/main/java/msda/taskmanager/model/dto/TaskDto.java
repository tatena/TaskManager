package msda.taskmanager.model.dto;

import lombok.Data;

@Data
public class TaskDto {

    private Long id;
    private String description;
    private String authorUsername;
    private String receiverUsername;
    private String workspaceName;

}
