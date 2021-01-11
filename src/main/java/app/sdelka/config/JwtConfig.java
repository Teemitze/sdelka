package app.sdelka.config;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@Data
public class JwtConfig {
    private String key;
    private Integer tokenExpirationAfterDays;

    public SecretKey getSecretKeyForSigning() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

}
