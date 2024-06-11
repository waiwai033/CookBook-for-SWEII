package dao.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Recipe mapper.
 * Manage the mapper between Recipe and xml.
 *
 * @author Chenyi He
 */
public interface RecipeMapper {
    /**
     * Add user boolean.
     *
     * @param recipe
     * @return the boolean
     */
    public boolean addRecipe(@Param("recipe") Recipe recipe);


    /**
     * Delete user boolean.
     *
     * @param recipe
     * @return the boolean
     */
    public boolean deleteRecipe(@Param("recipe") Recipe recipe);

    /**
     * Update recipe boolean.
     *
     * @param recipe
     * @return the boolean
     */
    public boolean updateRecipe(@Param("recipe") Recipe recipe);

    /**
     * Gets recipe by id.
     *
     * @param recipeId
     * @return the user by id
     */
    public Recipe getRecipeById(@Param("recipeId") int recipeId);

    /**
     * Gets recipe by name.
     *
     * @param recipeName
     * @return the user by name
     */
    public Recipe getRecipeByName(@Param("recipeName") String recipeName);


    public List<Recipe> getAllRecipes();
    /**
     * Gets recipe list by category.
     *
     * @param category
     * @return the recipe list by category
     */
    public List<Recipe> getRecipeByCategory(@Param("category") String category);
}
