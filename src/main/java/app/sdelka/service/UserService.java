package app.sdelka.service;

import app.sdelka.controller.dto.UserDto;

public interface UserService {
    void save(UserDto userDto);

    void deleteById(Long id);

    UserDto findById(Long id);
}
