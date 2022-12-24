package streamo.server.auth.bootstrap.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
    private String userName ;
    private String userMail;
    private String userPhoneNo;
    private String userPassword;

}
