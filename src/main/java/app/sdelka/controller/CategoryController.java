package app.sdelka.controller;

import app.sdelka.controller.dto.CategoryDto;
import app.sdelka.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryServiceMapping;

    @GetMapping("/all")
    public CategoryDto allCategories() {
        return categoryServiceMapping.findAll();
    }
}
