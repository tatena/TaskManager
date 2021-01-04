package msda.taskmanager.controller;


import msda.taskmanager.Service.TaskService;
import msda.taskmanager.model.dto.TaskAssignment;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.TaskStatusUpdate;
import msda.taskmanager.model.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> assignTask(@RequestBody TaskAssignment taskDto){
        taskService.assignTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateTaskStatus(@RequestBody TaskStatusUpdate taskDto){
        taskService.updateTaskStatus(taskDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelTask(@RequestParam Long taskID){
        taskService.cancelTask(taskID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
