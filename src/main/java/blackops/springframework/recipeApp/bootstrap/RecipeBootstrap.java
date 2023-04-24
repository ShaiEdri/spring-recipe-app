package blackops.springframework.recipeApp.bootstrap;

import blackops.springframework.recipeApp.models.*;
import blackops.springframework.recipeApp.repositories.CategoryRepository;
import blackops.springframework.recipeApp.repositories.RecipeRepository;
import blackops.springframework.recipeApp.repositories.UnitOfMeasureRepository;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }
    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Optional<UnitOfMeasure>optionalUnitOfMeasure1 = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!optionalUnitOfMeasure1.isPresent()){
            throw new RuntimeException("UOM not found");
        }
        Optional<UnitOfMeasure>optionalUnitOfMeasure2 = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!optionalUnitOfMeasure2.isPresent()){
            throw new RuntimeException("UOM not found");
        }
        Optional<UnitOfMeasure>optionalUnitOfMeasure3 = unitOfMeasureRepository.findByDescription("Cup");
        if(!optionalUnitOfMeasure3.isPresent()){
            throw new RuntimeException("UOM not found");
        }
        Optional<UnitOfMeasure>optionalUnitOfMeasure4 = unitOfMeasureRepository.findByDescription("Pint");
        if(!optionalUnitOfMeasure4.isPresent()){
            throw new RuntimeException("UOM not found");
        }
        Optional<UnitOfMeasure>optionalUnitOfMeasure5 = unitOfMeasureRepository.findByDescription("Ounce");
        if(!optionalUnitOfMeasure5.isPresent()){
            throw new RuntimeException("UOM not found");
        }
        //Get UOM's
        UnitOfMeasure teaSpoon = optionalUnitOfMeasure1.get();
        UnitOfMeasure tableSpoon = optionalUnitOfMeasure2.get();
        UnitOfMeasure cup = optionalUnitOfMeasure3.get();
        UnitOfMeasure pint = optionalUnitOfMeasure4.get();
        UnitOfMeasure ounce = optionalUnitOfMeasure5.get();

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
        guacamoli.getIngredients().add(new Ingredient("avocados", guacamoli, new BigDecimal(2), pint));
        guacamoli.getIngredients().add(new Ingredient("salt", guacamoli, new BigDecimal(".5"), teaSpoon));
        guacamoli.getIngredients().add(new Ingredient("lime juice", guacamoli, new BigDecimal(2), tableSpoon));

        guacamoli.getCategories().add(americanCategory);
        guacamoli.getCategories().add(mexicanCategory);
        recipes.add(guacamoli);

        return recipes;
    }
}
