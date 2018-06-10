package my.vips.learning.aws.recipies_teller.dao;

import my.vips.learning.aws.recipies_teller.Recipe;

/**
 * Created by vipinsharma on 09/06/18.
 */
public interface RecipeDAO {
    String TABLE_NAME = "Recipes";

    Recipe getRecipeWithCode(String recipeCode);

}
