package app.sdelka.service.category;

import app.sdelka.controller.dto.CategoryDto;
import app.sdelka.repository.CategoryRepository;
import app.sdelka.service.conversion.CategoryConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceMapping implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConversion categoryConversion;

    @Override
    public CategoryDto findAll() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Любая категория");
        return categoryConversion.categoryToCategoryDtoConversionRecursive(categoryRepository.findAllChildById(0L), categoryDto);
    }
}
