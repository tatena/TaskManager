package msda.taskmanager.Service;


import msda.taskmanager.auth.CustomUserPrincipal;
import msda.taskmanager.mapper.TaskMapper;
import msda.taskmanager.mapper.WorkspaceMapper;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.UserDto;
import msda.taskmanager.model.dto.WorkspaceDto;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.repository.UserRepository;
import org.bouncycastle.tsp.TSPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserInfo(User user) {
        UserDto res = new UserDto();
        res.setUsername(user.getUsername());

        return res;
    }

    public List<WorkspaceDto> getUserWorkspaces() {
        User user = getAuthenticatedUser();
        return WorkspaceMapper.toDtoList(user.getWorkspaces());
    }

    public List<TaskDto> getUserReceivedTasks() {
        User user = getAuthenticatedUser();
        return TaskMapper.toDtoList(user.getReceivedTasks());
    }

    public List<TaskDto> getUserCreatedTasks() {
        User user = getAuthenticatedUser();
        return TaskMapper.toDtoList(user.getCreatedTasks());
    }

        public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();
        User user = userRepository.findByUsername(userName).get();
        return user;
    }

}
