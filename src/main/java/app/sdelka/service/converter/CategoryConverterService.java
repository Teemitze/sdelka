package app.sdelka.service.converter;

import app.sdelka.dto.CategoryDto;
import app.sdelka.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryConverterService {
    public Category convertCategoryDtoInCategoryEntity(CategoryDto categoryDto) {
        final Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }
}
