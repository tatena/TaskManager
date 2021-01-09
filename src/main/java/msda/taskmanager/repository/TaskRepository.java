package msda.taskmanager.repository;

import msda.taskmanager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    void deleteTaskByWorkspaceId(Long workspaceID);
}
