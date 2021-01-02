package app.sdelka.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[A-z А-я]+$")
    private String name;
    private String city;
    @Size(min = 12, max = 12)
    @Pattern(regexp = "^\\+79[0-9]+$")
    private String phone;
    @Email
    private String email;
}
