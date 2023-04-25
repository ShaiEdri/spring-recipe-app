package blackops.springframework.recipeApp.bootstrap;

import blackops.springframework.recipeApp.models.*;
import blackops.springframework.recipeApp.repositories.CategoryRepository;
import blackops.springframework.recipeApp.repositories.RecipeRepository;
import blackops.springframework.recipeApp.repositories.UnitOfMeasureRepository;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        Optional<Category> american = categoryRepository.findByDescription("American");
        if(!american.isPresent()){
            throw new RuntimeException("Category not found");
        }
        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
        if(!mexican.isPresent()){
            throw new RuntimeException("Category not found");
        }
        //Get categories
        Category americanCategory = american.get();
        Category mexicanCategory = mexican.get();
        Recipe guacamoli = new Recipe();
        guacamoli.setDescription("Delicious guacamoli");
        guacamoli.setPrepTime(10);
        guacamoli.setCookTime(0);
        guacamoli.setDifficulty(Difficulty.EASY);
        guacamoli.setDirections("Slice three ripe avocados in half, remove the pit and scoop them into a mixing bowl. " + "\n" +
                "Then use a fork to gently mash them to your desired level of chunky or smooth. " + "\n" +
                "Add the onions, tomatoes, cilantro, jalapeno pepper, garlic, lime juice and salt and stir everything together. " + "\n" +
                "That’s it. The most delicious, easy guacamole.");
        Notes guacNotes = new Notes();
        guacNotes.setNotes("The most common guacamole preservation hack is placing plastic wrap " +"\n" +
                "directly on the guacamole to prevent oxidation. " +"\n" +
                "And this may work for a day or two. " +"\n" +
                "But I’m striving to reduce my plastic consumption and just don’t love this method");
        guacNotes.setRecipe(guacamoli);
        guacamoli.setNotes(guacNotes);
        guacamoli.getIngredients().add(new Ingredient("avocados", guacamoli, new BigDecimal(2), pintUom));
        guacamoli.getIngredients().add(new Ingredient("salt", guacamoli, new BigDecimal(".5"), teapoonUom));
        guacamoli.getIngredients().add(new Ingredient("lime juice", guacamoli, new BigDecimal(2), tableSpoonUom));

        guacamoli.getCategories().add(americanCategory);
        guacamoli.getCategories().add(mexicanCategory);
        recipes.add(guacamoli);

        return recipes;
    }
}
