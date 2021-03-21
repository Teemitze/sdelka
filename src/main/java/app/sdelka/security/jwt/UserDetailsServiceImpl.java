package app.sdelka.security.jwt;

import app.sdelka.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        final Optional<app.sdelka.entity.User> optUser = userRepository.findByEmailOrPhone(login, login);

        User.UserBuilder builder;
        if (optUser.isPresent()) {
            app.sdelka.entity.User user = optUser.get();
            builder = User.withUsername(user.getPhone());
            builder.password(user.getPassword());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}
