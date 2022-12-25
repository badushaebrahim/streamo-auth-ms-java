package streamo.server.auth.bootstrap.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
    private String userName;
    private String userPassword;
    private String newUserPassword;
}
