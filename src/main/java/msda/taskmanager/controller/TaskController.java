package msda.taskmanager.controller;


import msda.taskmanager.Service.TaskService;
import msda.taskmanager.model.dto.TaskAssignment;
import msda.taskmanager.model.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public void assignTask(TaskAssignment taskDto){
        taskService.assignTask(taskDto);
    }

    @PutMapping
    public void updateTaskStatus(TaskDto taskDto){
        taskService.updateTaskStatus(taskDto);
    }

    @DeleteMapping
    public void cancelTask(TaskDto taskDto){
        taskService.cancelTask(taskDto);
    }

}
