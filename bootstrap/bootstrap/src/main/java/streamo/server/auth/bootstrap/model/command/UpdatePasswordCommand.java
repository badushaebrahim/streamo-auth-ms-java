package streamo.server.auth.bootstrap.model.command;

import lombok.Data;
import streamo.server.auth.bootstrap.model.common.CommonHeaders;
import streamo.server.auth.bootstrap.model.request.UpdatePasswordRequest;

@Data
public class UpdatePasswordCommand extends CommonHeaders {
    private UpdatePasswordRequest updatePasswordRequest;
}
