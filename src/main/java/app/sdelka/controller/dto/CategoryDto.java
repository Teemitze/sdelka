package app.sdelka.controller.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[A-z А-я]+$")
    private String name;
    private List<CategoryDto> childCategories = new ArrayList<>();
}
