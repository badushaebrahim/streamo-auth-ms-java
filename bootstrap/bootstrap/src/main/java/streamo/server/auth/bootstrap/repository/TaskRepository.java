package streamo.server.auth.bootstrap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import streamo.server.auth.bootstrap.model.entity.TaskEntity;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<TaskEntity, String> {
    Optional<TaskEntity> findTaskByTaskName(String taskName);

    Optional<TaskEntity> findTaskByIdAndTaskName(String id,String taskName);
}
