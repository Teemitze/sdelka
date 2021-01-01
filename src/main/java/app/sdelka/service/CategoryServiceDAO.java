package app.sdelka.service;

import app.sdelka.model.entity.Category;
import app.sdelka.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceDAO implements CategoryService<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findAll() {
        return categoryRepository.findAllChildById(0L);
    }
}
