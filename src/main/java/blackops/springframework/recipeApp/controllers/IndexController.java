package blackops.springframework.recipeApp.controllers;

import blackops.springframework.recipeApp.repositories.CategoryRepository;
import blackops.springframework.recipeApp.repositories.UnitOfMeasureRepository;
import blackops.springframework.recipeApp.services.RecipeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    Logger logger = LogManager.getLogger(this.getClass());
    //private final CategoryRepository categoryRepository;
    //private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
