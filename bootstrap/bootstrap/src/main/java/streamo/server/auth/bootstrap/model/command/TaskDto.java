package streamo.server.auth.bootstrap.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;
    private String taskName;

    private Boolean isTaskActive;
    private HashMap<String, Object> taskData;

    private LocalDateTime taskEndTime;
    private LocalDateTime taskCreatedTime;
    private String taskCreatedUserName;
    private String taskAssignedUserName;
    private  String taskStatus;
}
