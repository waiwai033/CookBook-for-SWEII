package dao.mappers;
import org.apache.ibatis.annotations.Param;
import java.util.ArrayList;

/**
 * The interface Recipe mapper.
 * Manage the mapper between Recipe and xml.
 *
 * @author He Chenyi
 */
public interface RecipeMapper {
    /**
     * Add recipe boolean.
     *
     * @param recipe the recipe
     * @return the boolean
     */
    boolean addRecipe(@Param("recipe") Recipe recipe);

    /**
     * Delete recipe boolean.
     *
     * @param recipeId the recipe id
     * @return the boolean
     */
    boolean deleteRecipe(@Param("recipeID") Integer recipeID);

    /**
     * Update recipe boolean.
     *
     * @param recipe the recipe
     * @return the boolean
     */
    boolean updateRecipe(@Param("recipe") Recipe recipe);

    /**
     * Gets recipe by id.
     *
     * @param recipeId the recipe id
     * @return the recipe by id
     */
    Recipe getRecipeById(@Param("recipeId") int recipeId);

    /**
     * Gets the newest recipe;
     *
     * @return the recipe
     */
    Recipe getNewRecipe();

    /**
     * Gets recipe by name.
     *
     * @param recipeName the recipe name
     * @return the recipe by name
     */
    ArrayList<Recipe> getRecipeByName(@Param("recipeName") String recipeName);

    /**
     * Gets all recipes.
     *
     * @return the list of all recipes
     */
    ArrayList<Recipe> getAllRecipes();

    /**
     * Gets recipe list by category.
     *
     * @param category the category
     * @return the recipe list by category
     */
    ArrayList<Recipe> getRecipeByCategory(@Param("category") String category);


}
