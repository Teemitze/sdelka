package app.sdelka.service.user;

import app.sdelka.controller.dto.UserDto;
import app.sdelka.model.entity.User;
import app.sdelka.repository.UserRepository;
import app.sdelka.service.conversion.UserConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceMapping implements UserService {

    private final UserRepository userRepository;
    private final UserConversion userConversion;

    @Override
    public void save(UserDto userDto) {
        userRepository.save(userConversion.userDtoToUser(userDto));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        return optUser.map(userConversion::userToUserDto).orElse(null);
    }
}
