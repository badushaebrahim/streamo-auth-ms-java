package streamo.server.auth.bootstrap.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "login-details")
public class AuthEntity {
    @Id
    private String id;
    private String userName;
    private String name;
    private Integer userAge;
    private String userGender;
    private String userMail;
    private String userPhoneNo;
    private String userPassword;
    private String userCountry;
    private String uuid;
    private String createdBy;
    private LocalDateTime createdTime;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedTime;
}