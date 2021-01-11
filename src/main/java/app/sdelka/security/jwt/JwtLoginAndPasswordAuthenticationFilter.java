package app.sdelka.security.jwt;

import app.sdelka.config.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
public class JwtLoginAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtConfig jwtConfig;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {

        final LoginAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                .readValue(request.getInputStream(), LoginAndPasswordAuthenticationRequest.class);

        final Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getLogin(), authenticationRequest.getPassword());

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        final String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(jwtConfig.getSecretKeyForSigning()).compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, token);
    }
}
