package streamo.server.auth.bootstrap.model.command;

import lombok.Data;
import streamo.server.auth.bootstrap.configurations.properties.CustomProperties;
import streamo.server.auth.bootstrap.model.common.CommonHeaders;
import streamo.server.auth.bootstrap.model.request.SignInRequest;

@Data
public class SignInCommand extends CommonHeaders {
    private SignInRequest signInRequest;
}
