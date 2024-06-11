package dao.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Recipe mapper.
 * Manage the mapper between Recipe and xml.
 *
 * @author Chenyi He
 */
public interface RecipeIngredientMapper {
    /**
     * Add user boolean.
     *
     * @param recipeIngredient
     * @return the boolean
     */
    public boolean addRecipeIngredient(@Param("recipeIngredient") RecipeIngredient recipeIngredient);


    /**
     * Delete user boolean.
     *
     * @param recipeIngredient
     * @return the boolean
     */
    public boolean deleteRecipe(@Param("recipe") RecipeIngredient recipeIngredient);

    /**
     * Update recipe boolean.
     *
     * @param recipeIngredient
     * @return the boolean
     */
    public boolean updateRecipe(@Param("recipeIngredient") Recipe recipeIngredient);

    /**
     * Gets recipe ingredient by recipe id and ingredient id.
     *
     * @param recipeId    the ID of the recipe
     * @return the recipe ingredient matching the provided IDs
     */
    public List<RecipeIngredient> getRecipeById(@Param("recipeId") int recipeId);




}
