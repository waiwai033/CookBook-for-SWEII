package dao.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Recipe mapper.
 * Manage the mapper between Recipe and xml.
 *
 * @author Chenyi He
 */
public interface RecipeInstructionMapper {
    /**
     * Add user boolean.
     *
     * @param recipeInstruction
     * @return the boolean
     */
    public boolean addRecipeInstruction(@Param("recipeInstruction") RecipeInstruction recipeInstruction);


    /**
     * Delete user boolean.
     *
     * @param recipeInstruction
     * @return the boolean
     */
    public boolean deleteRecipeInstruction(@Param("recipeInstruction") RecipeInstruction recipeInstruction);

    /**
     * Update recipe boolean.
     *
     * @param recipeInstruction
     * @return the boolean
     */
    public boolean updateRecipeInstruction(@Param("recipeInstruction") RecipeInstruction recipeInstruction);

    /**
     * Gets recipe ingredient by recipe id and ingredient id.
     *
     * @param recipeId   the ID of the recipe
     * @return the recipe ingredient matching the provided IDs
     */
    public List<RecipeInstruction> getRecipeInstructionById(@Param("recipeId") int recipeId);




}
