package app.sdelka.service.conversion;

import app.sdelka.controller.dto.CategoryDto;
import app.sdelka.model.entity.Category;
import app.sdelka.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConversion {

    private final CategoryRepository categoryRepository;

    public Category categoryNameToCategoryConversionRecursive(String categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }

    public CategoryDto categoryToCategoryDtoConversionRecursive(Category category, CategoryDto rootCategoryDto) {
        if (category.getChildCategories().isEmpty()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            return categoryDto;
        }

        for (Category entity : category.getChildCategories()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(entity.getName());
            rootCategoryDto.getChildCategories().add(categoryToCategoryDtoConversionRecursive(entity, categoryDto));
        }

        return rootCategoryDto;
    }
}
