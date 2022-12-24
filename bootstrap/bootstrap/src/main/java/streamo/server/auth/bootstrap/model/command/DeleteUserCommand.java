package streamo.server.auth.bootstrap.model.command;

import lombok.Data;
import streamo.server.auth.bootstrap.model.common.CommonHeaders;
import streamo.server.auth.bootstrap.model.request.DeleteUserRequest;

@Data
public class DeleteUserCommand extends CommonHeaders {
    private DeleteUserRequest deleteUserRequest;
}
