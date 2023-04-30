package streamo.server.auth.bootstrap.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import streamo.server.auth.bootstrap.enums.GenderEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String newUsername;
    private String name;
    private Integer userAge;
    private GenderEnum userGender;
    private String userMail;
    private String userPhoneNo;

}
