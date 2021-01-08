package msda.taskmanager.mapper;

import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.entity.Task;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDto toDto(Task task) {
        TaskDto res = new TaskDto();

        res.setId(task.getId());
        res.setAuthorUsername(task.getAuthor().getUsername());
        res.setReceiverUsername(task.getReceiver().getUsername());
        res.setWorkspaceName(task.getWorkspace().getTitle());
        res.setDescription(task.getDescription());
        res.setStatus(task.getStatus());

        return res;
    }

    public  static List<TaskDto> toDtoList(List<Task> tasks) {
        List<TaskDto> res = new ArrayList<>();

        for (Task task : tasks) {
            res.add(toDto(task));
        }

        return res;
    }

    public static Task fromDto(String desc, User author, User receiver, Workspace workspace){
        Task task = new Task();
        task.setDescription(desc);
        task.setAuthor(author);
        task.setReceiver(receiver);
        task.setStatus(TaskStatus.TO_DO);
        task.setWorkspace(workspace);
        return task;
    }
}
