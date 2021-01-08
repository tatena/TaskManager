
package msda.taskmanager.model.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class TimezoneDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}