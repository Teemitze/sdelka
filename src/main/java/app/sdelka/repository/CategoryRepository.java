package app.sdelka.repository;

import app.sdelka.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findAllChildById(Long parentId);
}
