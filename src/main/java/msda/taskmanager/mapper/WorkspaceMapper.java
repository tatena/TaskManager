package msda.taskmanager.mapper;

import msda.taskmanager.model.dto.NewWorkspaceRequest;
import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.WorkspaceStatus;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceMapper {

    public static WorkspaceDto toDto(Workspace workspace) {
        WorkspaceDto res = new WorkspaceDto();

        res.setId(workspace.getId());
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

    public static Workspace fromDto(WorkspaceDto workspaceDto){
        Workspace workspace = new Workspace();

        workspace.setId(workspaceDto.getId());
        workspace.setTitle(workspaceDto.getTitle());
        workspace.setDescription(workspaceDto.getDescription());
        workspace.setStatus(workspaceDto.getStatus());
        workspace.setMemberships(new ArrayList<>());

        return workspace;
    }

    public static Workspace fromDto(NewWorkspaceRequest workspaceDto){
        Workspace workspace = new Workspace();

        workspace.setTitle(workspaceDto.getTitle());
        workspace.setDescription(workspaceDto.getDescription());
        workspace.setStatus(WorkspaceStatus.ACTIVE);
        workspace.setMemberships(new ArrayList<>());

        return workspace;
    }



}
