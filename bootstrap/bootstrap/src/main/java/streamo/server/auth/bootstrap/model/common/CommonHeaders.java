package streamo.server.auth.bootstrap.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import streamo.server.auth.bootstrap.enums.CountryCodeEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonHeaders {
    private CountryCodeEnum userCountry;
    private String uuid;
}
