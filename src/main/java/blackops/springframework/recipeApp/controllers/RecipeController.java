package blackops.springframework.recipeApp.controllers;

import blackops.springframework.recipeApp.commands.RecipeCommand;
import blackops.springframework.recipeApp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RequestMapping("/recipes")
@Controller
public class RecipeController {
    Logger logger = Logger.getLogger("rcontroller");
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/"})
    public String getIndex(Model model){
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

    @GetMapping("/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }
    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }
    @PostMapping("/create")
    public String createRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        logger.info("------>" + savedRecipeCommand.getId());
        return "redirect:/recipes/show/" + savedRecipeCommand.getId();
        // Spring will recognize the redirect and will exec it
    }

}
