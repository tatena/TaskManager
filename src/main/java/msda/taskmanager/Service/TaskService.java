package msda.taskmanager.Service;

import msda.taskmanager.model.dto.TaskAssignment;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.entity.Task;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.TaskStatus;
import msda.taskmanager.repository.TaskRepository;
import msda.taskmanager.repository.UserRepository;
import msda.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final WorkspaceRepository workspaceRepository;

    public TaskService(UserService userService, UserRepository userRepository, TaskRepository taskRepository, WorkspaceRepository workspaceRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.workspaceRepository = workspaceRepository;
    }

    public void assignTask(TaskAssignment taskDto){
        User receiver = userRepository.findById(taskDto.getReceiverID())
                    .orElseThrow(() -> new RuntimeException("No such receiver"));
        User author = userService.getAuthenticatedUser();

        Workspace workspace = workspaceRepository.findById(taskDto.getWorkspaceID())
                    .orElseThrow(() -> new RuntimeException("No such workspace !"));

        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setAuthor(author);
        task.setReceiver(receiver);
        task.setStatus(TaskStatus.TO_DO);
        task.setWorkspace(workspace);

        taskRepository.save(task);
    }

    public void updateTaskStatus(TaskDto taskDto){
        Task task = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("No such task id present in DB"));

        TaskStatus currentStatus = taskDto.getStatus();
        if(!task.getStatus().equals(currentStatus)){
            task.setStatus(currentStatus);
            taskRepository.save(task);
        }

    }

    public void cancelTask(TaskDto taskDto){
        Task task = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new RuntimeException("No such task id present in DB"));

        taskRepository.delete(task);
    }
}
