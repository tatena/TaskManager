package msda.taskmanager.mapper;

import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDto toDto(Task task) {
        TaskDto res = new TaskDto();

        res.setAuthorUsername(task.getAuthor().getUsername());
        res.setReceiverUsername(task.getReceiver().getUsername());
        res.setWorkspaceName(task.getWorkspace().getTitle());
        res.setDescription(task.getDescription());

        return res;
    }

    public  static List<TaskDto> toDtoList(List<Task> tasks) {
        List<TaskDto> res = new ArrayList<>();

        for (Task task : tasks) {
            res.add(toDto(task));
        }

        return res;
    }
}
