
package msda.taskmanager.controller;


import msda.taskmanager.Service.TaskService;
import msda.taskmanager.model.dto.TaskAssignment;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.TaskStatusUpdate;
import msda.taskmanager.model.entity.Task;
import msda.taskmanager.model.enums.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> assignTask(@RequestBody TaskAssignment taskDto, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.assignTask(taskDto));
    }

    @PutMapping("/status")
    public ResponseEntity<TaskDto> updateTaskStatus(@RequestBody TaskStatusUpdate taskDto, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskStatus(taskDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelTask(@RequestParam Long taskID, HttpServletRequest request){
        taskService.cancelTask(taskID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<TaskDto> getTaskById(@RequestParam Long id, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAll());
    }

    @PutMapping("/status/done")
    public ResponseEntity<TaskDto> doTask(@RequestParam Long id, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.doTask(id));
    }

}