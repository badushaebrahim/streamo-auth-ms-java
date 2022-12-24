package streamo.server.auth.bootstrap.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import streamo.server.auth.bootstrap.enums.GenderEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String userName ;
    private GenderEnum userGender;
    private String userMail;
    private String userPhoneNo;
    private String userPassword;

}
