package app.sdelka.service;

import app.sdelka.dto.CategoryDto;
import app.sdelka.model.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceDTO implements CategoryService<CategoryDto> {

    private final CategoryServiceDAO categoryServiceDAO;

    @Override
    public CategoryDto findAll() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Любая категория");
        return transformCategoryToCategoryDto(categoryServiceDAO.findAll(), categoryDto);
    }


    private CategoryDto transformCategoryToCategoryDto(Category category, CategoryDto rootCategoryDto) {
        if (category.getChildCategories().isEmpty()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            return categoryDto;
        }

        for (Category entity : category.getChildCategories()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(entity.getName());
            rootCategoryDto.getChildCategories().add(transformCategoryToCategoryDto(entity, categoryDto));
        }

        return rootCategoryDto;
    }
}
