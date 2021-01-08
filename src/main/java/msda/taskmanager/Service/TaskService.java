package msda.taskmanager.Service;

import msda.taskmanager.TaskManagerApplication;
import msda.taskmanager.mapper.TaskMapper;
import msda.taskmanager.model.dto.TaskAssignment;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.TaskStatusUpdate;
import msda.taskmanager.model.entity.Membership;
import msda.taskmanager.model.entity.Task;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.TaskStatus;
import msda.taskmanager.repository.MembershipRepository;
import msda.taskmanager.repository.TaskRepository;
import msda.taskmanager.repository.UserRepository;
import msda.taskmanager.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class TaskService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final WorkspaceRepository workspaceRepository;
    private final MembershipRepository membershipRepository;

    public TaskService(UserService userService, UserRepository userRepository, TaskRepository taskRepository, WorkspaceRepository workspaceRepository, MembershipRepository membershipRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.workspaceRepository = workspaceRepository;
        this.membershipRepository = membershipRepository;
    }

    public TaskDto assignTask(TaskAssignment taskDto){
        User receiver = userRepository.findById(taskDto.getReceiverID())
                    .orElseThrow(() -> new RuntimeException("No such receiver"));
        User author = userService.getAuthenticatedUser().orElseThrow(() -> new RuntimeException("User not found"));

        Workspace workspace = workspaceRepository.findById(taskDto.getWorkspaceID())
                    .orElseThrow(() -> new RuntimeException("No such workspace !"));

        if(!checkPresence(receiver, workspace.getId())){
            throw new RuntimeException("Task receiver \"" + receiver.getUsername() + " \"not present in workspace");
        }

        if(!checkPresence(author, workspace.getId())){
            throw new RuntimeException("Can not assign tasks while not in the workspace");
        }

        Task task = TaskMapper.fromDto(taskDto.getDescription(), author, receiver, workspace);
        TaskMapper.setServiceDates(author.getTimezone(), workspace.getTimezone(),taskDto.getDates(), task);

        return TaskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto updateTaskStatus(TaskStatusUpdate taskDto){
        Task task = taskRepository.findById(taskDto.getTaskID())
                .orElseThrow(() -> new RuntimeException("No such task id present in DB"));

        User activeUser = userService.getAuthenticatedUser().orElseThrow(() -> new RuntimeException("User not found"));
        if(!task.getReceiver().getId().equals(activeUser.getId())){
            throw new RuntimeException("Only task receiver can alter the status");
        }

        TaskStatus currentStatus = taskDto.getStatus();
        if(!task.getStatus().equals(currentStatus)){
            if(currentStatus.equals(TaskStatus.DONE)){
                task.setEndDate(LocalDateTime.now().minusHours(TaskManagerApplication.TIME_ZONE));
            }
            task.setStatus(currentStatus);
            task = taskRepository.save(task);
        }

        return TaskMapper.toDto(task);
    }

    public void cancelTask(Long taskID){
        Task task = taskRepository.findById(taskID)
                .orElseThrow(() -> new RuntimeException("No such task id present in DB"));

        User activeUser = userService.getAuthenticatedUser().orElseThrow(() -> new RuntimeException("User not found"));
        if(!task.getAuthor().getId().equals(activeUser.getId())){
            throw new RuntimeException("Only task author can delete the task");
        }
        taskRepository.delete(task);
    }


    private boolean checkPresence(User user, Long id){
        for(Workspace workspace : user.getWorkspaces()){
            if(workspace.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public TaskDto getById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        Task task = taskOptional.orElseThrow(() -> new RuntimeException("Task not found"));

        return TaskMapper.toDto(task);
    }

    public List<TaskDto> getAll() {
        return TaskMapper.toDtoList(taskRepository.findAll());
    }

    public TaskDto doTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such task id present in DB"));

        User activeUser = userService.getAuthenticatedUser().orElseThrow(() -> new RuntimeException("User not found"));
        if(!task.getReceiver().getId().equals(activeUser.getId())){
            throw new RuntimeException("Only task receiver can do the task");
        }

        task.setStatus(TaskStatus.DONE);

        return TaskMapper.toDto(taskRepository.save(task));
    }
}
