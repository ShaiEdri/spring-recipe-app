package blackops.springframework.recipeApp.controllers;

import blackops.springframework.recipeApp.commands.RecipeCommand;
import blackops.springframework.recipeApp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class IngredientControllerTest {
  @Mock
  RecipeService recipeService;
  IngredientController ingredientController;
  MockMvc mockMvc;
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ingredientController = new IngredientController(recipeService);
    mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
  }
  @Test
  public void testListIngredients() throws Exception {
    //given
    RecipeCommand recipeCommand = new RecipeCommand();
    Mockito.when(recipeService.findCommandById(Mockito.anyLong()))
            .thenReturn(recipeCommand);
    //when
    mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/recipe/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    //then
    Mockito.verify(recipeService, Mockito.times(1)).findCommandById(Mockito.anyLong());
  }
}
