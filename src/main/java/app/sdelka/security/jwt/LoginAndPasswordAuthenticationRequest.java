package app.sdelka.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginAndPasswordAuthenticationRequest {
    private String login;
    private String password;
}
