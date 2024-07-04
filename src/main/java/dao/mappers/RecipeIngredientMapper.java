package dao.mappers;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * The interface Recipe ingredient mapper.
 * Manage the mapper between RecipeIngredient and XML.
 *
 * @author He Chenyi
 */
public interface RecipeIngredientMapper {

    /**
     * Add recipe ingredient.
     *
     * @param recipeIngredient the recipe ingredient to add
     * @return true if the ingredient was successfully added, false otherwise
     */
    boolean addRecipeIngredient(@Param("recipeIngredient") RecipeIngredient recipeIngredient);

    /**
     * Delete recipe ingredient.

     * @return true if the ingredient was successfully deleted, false otherwise
     */
    boolean deleteRecipeIngredient(@Param("recipeID") Integer recipeID);

    /**
     * Update recipe ingredient.
     *
     * @param recipeIngredient the recipe ingredient to update
     * @return true if the ingredient was successfully updated, false otherwise
     */
    boolean updateRecipeIngredient(@Param("recipeIngredient") RecipeIngredient recipeIngredient);

    /**
     * Get recipe ingredients by recipe id.
     *
     * @param recipeId the ID of the recipe
     * @return a list of recipe ingredients matching the provided recipe ID
     */
    List<RecipeIngredient> getRecipeIngredientsByRecipeId(@Param("recipeId") int recipeId);
}
