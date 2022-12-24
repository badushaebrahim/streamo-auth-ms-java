package streamo.server.auth.bootstrap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import streamo.server.auth.bootstrap.model.entity.AuthEntity;

@Repository
public interface AuthRepository extends MongoRepository<AuthEntity, String> {
    //retrieve by username
    public AuthEntity getByUserName(String userName);

    //delete by username
    public void deleteByUserName(String userName);
}