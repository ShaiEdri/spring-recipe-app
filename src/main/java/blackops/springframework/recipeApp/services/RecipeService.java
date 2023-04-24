package blackops.springframework.recipeApp.services;

import blackops.springframework.recipeApp.models.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(long l);
}
