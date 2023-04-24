package blackops.springframework.recipeApp.controllers;

import blackops.springframework.recipeApp.models.Recipe;
import blackops.springframework.recipeApp.repositories.RecipeRepository;
import blackops.springframework.recipeApp.services.RecipeService;
import blackops.springframework.recipeApp.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    IndexController indexController;
    @BeforeEach
    public void setUp(){
        //Manual: recipeRepository = Mockito.mock(recipeRepository.getClass());
        //Auto :
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndex() {
        //given
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor
                = ArgumentCaptor.forClass(Set.class);
        //when
        String vName = indexController.getIndex(model);

        //then
        assertEquals(vName, "index");
        Mockito.verify(model, Mockito.times(1))
                .addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
        Set<Recipe> fromController = argumentCaptor.getValue();
        assertEquals(2, fromController.size());
    }
}