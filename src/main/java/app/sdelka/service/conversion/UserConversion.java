package app.sdelka.service.conversion;

import app.sdelka.controller.dto.UserDto;
import app.sdelka.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConversion {

    public User userDtoToUser(UserDto userDto) {
        final User user = new User();
        user.setName(userDto.getName());
        user.setCity(userDto.getCity());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }

    public UserDto userToUserDto(User user) {
        final UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }
}
