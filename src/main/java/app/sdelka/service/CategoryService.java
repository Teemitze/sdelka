package app.sdelka.service;

import app.sdelka.dto.CategoryDto;
import app.sdelka.model.Category;
import app.sdelka.repository.CategoryRepository;
import app.sdelka.service.converter.CategoryConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverterService categoryConverterService;

    public void save(CategoryDto categoryDto) {
        categoryRepository.save(
                categoryConverterService.convertCategoryDtoInCategoryEntity(categoryDto)
        );
    }

    public void deleteById(long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
