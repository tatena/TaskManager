package msda.taskmanager.model.dto;

import lombok.Data;
import msda.taskmanager.model.enums.WorkspaceStatus;

@Data
public class WorkspaceDto {
    private String title;
    private String description;
    private WorkspaceStatus status;
}
