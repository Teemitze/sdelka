package app.sdelka.security.jwt;

import app.sdelka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        final Optional<app.sdelka.model.entity.User> optUser = userRepository.findByEmailOrPhone(login, login);

        User.UserBuilder builder;
        if (optUser.isPresent()) {
            app.sdelka.model.entity.User user = optUser.get();
            builder = User.withUsername(user.getPhone());
            builder.password(user.getPassword());
            builder.roles(user.getRole().getName());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
