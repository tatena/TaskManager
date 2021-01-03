package msda.taskmanager.mapper;

import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.Workspace;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceMapper {

    public static WorkspaceDto toDto(Workspace workspace) {
        WorkspaceDto res = new WorkspaceDto();

        res.setDescription(workspace.getDescription());
        res.setStatus(workspace.getStatus());
        res.setTitle(workspace.getTitle());

        return res;
    }

    public static List<WorkspaceDto> toDtoList(List<Workspace> workspaces) {
        List<WorkspaceDto> res = new ArrayList<>();

        for (Workspace workspace : workspaces) {
            res.add(toDto(workspace));
        }

        return res;
    }

}
