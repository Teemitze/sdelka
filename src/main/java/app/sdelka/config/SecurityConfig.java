package app.sdelka.config;

import app.sdelka.security.jwt.JwtLoginAndPasswordAuthenticationFilter;
import app.sdelka.security.jwt.JwtTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsServiceImpl;

    private final JwtTokenVerifier jwtTokenVerifier;

    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtLoginAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(jwtTokenVerifier, JwtLoginAndPasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .authorizeRequests(authorize -> authorize
                        .mvcMatchers("/login", "/logout").permitAll()
                        .mvcMatchers("/api/v1/user/find/**", "/api/v1/user/save").permitAll()
                        .mvcMatchers("/api/v1/advert/find/**").permitAll()
                        .mvcMatchers("/api/v1/category/**").authenticated()
                        .anyRequest().denyAll()
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
