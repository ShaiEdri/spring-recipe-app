package blackops.springframework.recipeApp.repositories;

import blackops.springframework.recipeApp.models.Category;
import blackops.springframework.recipeApp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Set<Recipe> findByPrepTimeLessThan(Integer prepTime);
}
