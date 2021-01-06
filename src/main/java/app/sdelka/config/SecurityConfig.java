package app.sdelka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .mvcMatchers("/login", "/logout").permitAll()
                        .mvcMatchers("/user/find/**", "/user/save").permitAll()
                        .mvcMatchers("/advert/find/**").permitAll()
                        .mvcMatchers("/category/**").permitAll()
                        .anyRequest().denyAll()
                ).csrf().disable();
    }
}
