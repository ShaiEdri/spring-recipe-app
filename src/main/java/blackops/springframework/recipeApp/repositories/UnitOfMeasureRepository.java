package blackops.springframework.recipeApp.repositories;

import blackops.springframework.recipeApp.models.Category;
import blackops.springframework.recipeApp.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure>findByDescription(String description);
}
