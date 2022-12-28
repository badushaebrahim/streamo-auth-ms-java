package streamo.server.auth.bootstrap.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private Map<String, String> tokenDetail;
}
