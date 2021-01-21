package app.sdelka.service.conversion;

import app.sdelka.controller.dto.UserDto;
import app.sdelka.entity.User;
import app.sdelka.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserConversion {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User userDtoToUser(UserDto userDto) {
        final User user = new User();
        user.setName(userDto.getName());
        user.setCity(userDto.getCity());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setEnabled(userDto.isEnabled());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        return user;
    }

    public UserDto userToUserDto(User user) {
        final UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setEnabled(user.isEnabled());
        return userDto;
    }
}
