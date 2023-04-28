package blackops.springframework.recipeApp.services;

import blackops.springframework.recipeApp.commands.RecipeCommand;
import blackops.springframework.recipeApp.models.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand findCommandById(Long l);
    void deleteById(Long l);
    void deleteAll();
    Recipe findById(Long l);
    Set<Recipe> findByPrepTimeLessThan(Integer prepTime);
}
