package app.sdelka.controller;

import app.sdelka.controller.dto.UserDto;
import app.sdelka.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userServiceMapping;

    @PostMapping("/save")
    public void save(@RequestBody UserDto userDto) {
        userServiceMapping.save(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userServiceMapping.deleteById(id);
    }

    @GetMapping("/find/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userServiceMapping.findById(id);
    }
}
