package blackops.springframework.recipeApp.services;

import blackops.springframework.recipeApp.commands.RecipeCommand;
import blackops.springframework.recipeApp.models.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    Recipe findById(long l);
}
