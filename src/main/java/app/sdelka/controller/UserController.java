package app.sdelka.controller;

import app.sdelka.dto.UserDto;
import app.sdelka.model.entity.User;
import app.sdelka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public void save(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable long id) {
        Optional<User> optUser = userService.findById(id);
        return optUser.orElse(null);
    }
}
