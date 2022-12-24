package streamo.server.auth.bootstrap.model.command;

import lombok.Data;
import streamo.server.auth.bootstrap.model.common.CommonHeaders;
import streamo.server.auth.bootstrap.model.request.SignupRequest;

@Data
public class SignUpCommand extends CommonHeaders {
    private SignupRequest signupRequest;

}
