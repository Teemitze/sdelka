package app.sdelka.security.jwt;

import app.sdelka.model.entity.Role;
import app.sdelka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        final Optional<app.sdelka.model.entity.User> optUser = userRepository.findByEmailOrPhone(login, login);

        User.UserBuilder builder;
        if (optUser.isPresent()) {
            app.sdelka.model.entity.User user = optUser.get();
            builder = User.withUsername(user.getPhone());
            builder.password(user.getPassword());
            builder.roles(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(", ")));
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
