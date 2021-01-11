package app.sdelka.security.jwt;

import app.sdelka.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import joptsimple.internal.Strings;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {

        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!Strings.isNullOrEmpty(token)) {
            final Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(jwtConfig.getSecretKeyForSigning()).build().parseClaimsJws(token);

            final List<Map<String, String>> authorities = (List<Map<String, String>>) claimsJws.getBody().get("authorities");

            final Set<SimpleGrantedAuthority> authority = authorities.stream().map(a -> new SimpleGrantedAuthority(a.get("authority"))).collect(Collectors.toSet());

            final Authentication authentication = new UsernamePasswordAuthenticationToken(
                    claimsJws.getBody().getSubject(),
                    null,
                    authority
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }
}
