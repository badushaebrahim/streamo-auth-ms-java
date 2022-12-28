package streamo.server.auth.bootstrap.configurations.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("config.rest.services")
public class CustomProperties {
    private String encryptionKey;
}
