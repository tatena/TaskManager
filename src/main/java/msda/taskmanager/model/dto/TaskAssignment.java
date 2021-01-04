package msda.taskmanager.model.dto;

import lombok.Data;

@Data
public class TaskAssignment {

    private String description;
    private Long receiverID;
    private Long workspaceID;

}
