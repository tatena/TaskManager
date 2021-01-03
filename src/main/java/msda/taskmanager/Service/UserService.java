package msda.taskmanager.Service;


import msda.taskmanager.auth.CustomUserPrincipal;
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

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public ResponseEntity<List<WorkspaceDto>> getUserWorkspaces() {

        Authentication authentication = getAuthentication();
        String userName = (String) authentication.getPrincipal();
        User user = userRepository.findByUsername(userName).get();

        return null;
    }
}
