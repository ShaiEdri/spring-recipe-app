package blackops.springframework.recipeApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeAppApplication {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger();
		SpringApplication.run(RecipeAppApplication.class, args);
	}

}
