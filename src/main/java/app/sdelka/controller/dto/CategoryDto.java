package app.sdelka.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private String name;
    private List<CategoryDto> childCategories = new ArrayList<>();
}
