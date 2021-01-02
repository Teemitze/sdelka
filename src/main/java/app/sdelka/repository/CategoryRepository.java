package app.sdelka.repository;

import app.sdelka.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findAllChildById(Long parentId);

    Optional<Category> findByName(String name);
}
