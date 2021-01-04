package msda.taskmanager.model.dto;


import lombok.Data;

@Data
public class NewWorkspaceRequest {
    private String title;
    private String description;
}
