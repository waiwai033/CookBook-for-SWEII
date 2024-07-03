package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * The interface Preparation step mapper.
 * Manage the mapper between PreparationStep and XML.
 *
 * @author He Chenyi
 */
public interface PreparationStepMapper {

    /**
     * Add preparation step.
     *
     * @param preparationStep the preparation step to add
     * @return true if the step was successfully added, false otherwise
     */
    boolean addPreparationStep(@Param("preparationStep") PreparationStep preparationStep);

    /**
     * Delete preparation step.
     *
     * @param recipeID the recipe's preparation steps to delete
     * @return true if the step was successfully deleted, false otherwise
     */
    boolean deletePreparationStep(@Param("recipeID") Integer recipeID);;

    /**
     * Update preparation step.
     *
     * @param preparationStep the preparation step to update
     * @return true if the step was successfully updated, false otherwise
     */
    boolean updatePreparationStep(@Param("preparationStep") PreparationStep preparationStep);

    /**
     * Get preparation steps by recipe id.
     *
     * @param recipeId the ID of the recipe
     * @return a list of preparation steps matching the provided recipe ID
     */
    List<PreparationStep> getPreparationStepsByRecipeId(@Param("recipeId") Integer recipeId);
}
