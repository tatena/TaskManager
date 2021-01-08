package msda.taskmanager.Service;


import msda.taskmanager.mapper.TaskMapper;
import msda.taskmanager.mapper.WorkspaceMapper;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<WorkspaceDto> getUserWorkspaces() {
        User user = getAuthenticatedUser();
        return WorkspaceMapper.toDtoList(user.getWorkspaces());
    }

    public List<TaskDto> getUserReceivedTasks() {
        User user = getAuthenticatedUser();
        return TaskMapper.toDtoList(user, user.getReceivedTasks());
    }

    public List<TaskDto> getUserCreatedTasks() {
        User user = getAuthenticatedUser();
        return TaskMapper.toDtoList(user, user.getCreatedTasks());
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();

        return userRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("Not authorized"));
    }

}
