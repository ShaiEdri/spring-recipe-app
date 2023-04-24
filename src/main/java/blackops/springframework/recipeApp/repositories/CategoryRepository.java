package blackops.springframework.recipeApp.repositories;

import blackops.springframework.recipeApp.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category>findByDescription(String description);
}
