package streamo.server.auth.bootstrap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task-details")
public class TaskEntity {
    @Id
    private String id;
    private String taskName;
    private String taskCreatedUserName;
    private String taskAssignedUserName;
    private  String taskStatus;

    private Boolean TaskActive;
    private HashMap<String, Object> taskData;

    private LocalDateTime taskEndTime;
    private LocalDateTime taskCreatedTime;
    private LocalDateTime taskCompletedTime;
}
