package blackops.springframework.recipeApp.services;

import blackops.springframework.recipeApp.commands.RecipeCommand;
import blackops.springframework.recipeApp.converters.RecipeCommandToRecipe;
import blackops.springframework.recipeApp.converters.RecipeToRecipeCommand;
import blackops.springframework.recipeApp.models.Recipe;
import blackops.springframework.recipeApp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(e->recipeSet.add(e));
        return recipeSet;
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedR = recipeRepository.save(recipe);
        return recipeToRecipeCommand.convert(savedR);
    }

    @Override
    public Recipe findById(long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);
        if (!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }
        return recipeOptional.get();
    }
}
