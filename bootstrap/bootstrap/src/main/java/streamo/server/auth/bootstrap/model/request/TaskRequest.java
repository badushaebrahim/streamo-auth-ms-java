package streamo.server.auth.bootstrap.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequest {
    private String taskName;

    private Boolean TaskActive;
    private HashMap<String, Object> taskData;

    private LocalDateTime taskEndTime;

//    private String taskCreatedUserName;
//    private String taskAssignedUserName;
    private  String taskStatus;
}
