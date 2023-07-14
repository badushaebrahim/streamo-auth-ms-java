package streamo.server.auth.bootstrap.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchRequest {

    private String taskName;
    private String taskCreatedUserName;
    private String taskAssignedUserName;
    private  String taskStatus;

    private Boolean TaskActive;


    private LocalDateTime taskFromEndTime;
    private LocalDateTime taskToEndTime;

}
