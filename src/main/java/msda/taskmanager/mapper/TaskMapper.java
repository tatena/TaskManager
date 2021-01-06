package msda.taskmanager.mapper;

import com.sun.istack.NotNull;
import msda.taskmanager.TaskManagerApplication;
import msda.taskmanager.model.dto.TaskDto;
import msda.taskmanager.model.dto.TimezoneDto;
import msda.taskmanager.model.entity.Task;
import msda.taskmanager.model.entity.User;
import msda.taskmanager.model.entity.Workspace;
import msda.taskmanager.model.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static TaskDto toDto(Task task) {
        TaskDto res = new TaskDto();

        res.setId(task.getId());
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

    public static Task fromDto(String desc, User author, User receiver, Workspace workspace){
        Task task = new Task();
        task.setDescription(desc);
        task.setAuthor(author);
        task.setReceiver(receiver);
        task.setStatus(TaskStatus.TO_DO);
        task.setWorkspace(workspace);
        return task;
    }

    public static void setServiceDates(Integer userTZ, Integer workspaceTZ, TimezoneDto dates, Task task) {
        int timezone = TaskManagerApplication.TIME_ZONE;
        if(userTZ != null){
            timezone = userTZ.intValue();
        }else if(workspaceTZ != null){
            timezone = workspaceTZ.intValue();
        }
        SSDHelper(timezone, dates, task);
    }
    private static void SSDHelper(int timezone, TimezoneDto dates, Task task){
        LocalDateTime start = dates.getStartDate().minusHours(timezone);
        LocalDateTime end = dates.getEndDate().minusHours(timezone);
        task.setStartDate(start);
        task.setDeadline(end);
    }

    /**
     * Usage: After querying user, workspace and the task, this method
     * can generate given users actual date/time to display (by their timezone)
     *
     * @param userTimezone value returned by user.getTimezone() (this value can be null)
     * @param workspaceTimezone value returned by workspace.getTimezone() (this value can be null)
     * @param time value returned by task.get-X-Date() (X = start/end/deadline)
     * @return new LocalDateTime instance which can be displayed to the user
     */
    public static LocalDateTime getTaskDates(Integer userTimezone, Integer workspaceTimezone, @NotNull LocalDateTime time){
        int timezone = TaskManagerApplication.TIME_ZONE;
        if(userTimezone != null){
            timezone = userTimezone;
        }else if(workspaceTimezone != null){
            timezone = workspaceTimezone;
        }
        return time.minusHours(-timezone);
    }

}
