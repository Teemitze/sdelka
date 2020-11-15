package app.sdelka.service.converter;

import app.sdelka.dto.UserDto;
import app.sdelka.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverterService {
    public User convertUserDtoInUserEntity (UserDto userDto) {
        final User user = new User();
        user.setName(userDto.getName());
        user.setCity(userDto.getCity());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return user;
    }
}
