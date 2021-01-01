package app.sdelka.service;

import app.sdelka.dto.UserDto;
import app.sdelka.model.entity.User;
import app.sdelka.repository.UserRepository;
import app.sdelka.service.converter.UserConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverterService userConverterService;

    public void save(UserDto userDto) {
        userRepository.save(
                userConverterService.convertUserDtoInUserEntity(userDto)
        );
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
