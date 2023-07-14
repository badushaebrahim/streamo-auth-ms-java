package streamo.server.auth.bootstrap.model.command;

import lombok.Data;
import streamo.server.auth.bootstrap.model.common.CommonHeaders;
import streamo.server.auth.bootstrap.model.request.UpdateProfileRequest;

@Data
public class UpdateProfileCommand extends CommonHeaders {
    private String userToken;
    private UpdateProfileRequest updateProfileRequest;
}