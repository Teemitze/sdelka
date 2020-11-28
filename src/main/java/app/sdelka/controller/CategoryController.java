package app.sdelka.controller;

import app.sdelka.dto.CategoryDto;
import app.sdelka.model.Category;
import app.sdelka.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public void save(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id) {
        categoryService.deleteById(id);
    }
}
