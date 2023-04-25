package blackops.springframework.recipeApp.services;

import blackops.springframework.recipeApp.models.Recipe;
import blackops.springframework.recipeApp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class RecipeServiceImplTest {
    @Mock
    private RecipeRepository recipeRepository;
    private RecipeService recipeService;

    @BeforeEach
    public void setUp(){
        //Manual: recipeRepository = Mockito.mock(recipeRepository.getClass());
        //Auto :
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }
    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet<>();
        recipesData.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();

    }
    @Test
    public void getRecipeBIdTest(){
       Recipe recipe = new Recipe();
       recipe.setId(1L);
       Optional<Recipe> recipeOptional = Optional.of(recipe);
       Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
       Recipe recipeReturned = recipeService.findById(1L);
       assertNotNull(recipeReturned);
       Mockito.verify(recipeRepository, Mockito.times(1))
               .findById(Mockito.anyLong());
    }








}