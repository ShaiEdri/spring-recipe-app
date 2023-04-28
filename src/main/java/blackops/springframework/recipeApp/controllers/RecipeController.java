package blackops.springframework.recipeApp.controllers;

import blackops.springframework.recipeApp.commands.RecipeCommand;
import blackops.springframework.recipeApp.models.Recipe;
import blackops.springframework.recipeApp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RequestMapping("/recipes")
@Controller
public class RecipeController {
    Logger logger = Logger.getLogger("Rcontroller");
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
    @PostMapping("/save")
    public String createRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipes/show/" + savedRecipeCommand.getId();
        // Spring will recognize the redirect and will exec it
    }

    @RequestMapping("/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeForm" ;
    }
    @RequestMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable String id, Model model){
        recipeService.deleteById(Long.valueOf(id));
        logger.info("Deleted " + id);
        return "redirect:/" ;
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        recipeService.deleteAll();
        return "redirect:/" ;
    }
    @GetMapping("/ptime")
    public String findByptimeless(Model model){
        recipeService.findByPrepTimeLessThan(9).stream().forEach(e-> System.out.println(e));
        return "redirect:/";
    }
}
