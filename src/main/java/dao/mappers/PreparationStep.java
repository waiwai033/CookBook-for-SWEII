package dao.mappers;

/**
 * Represents a preparation step for a recipe.
 * Each step consists of a recipe ID, step number, and description of the step.
 *
 * @author He Chenyi
 */
public class PreparationStep {

    private int recipeId;
    private int step;
    private String description;

    /**
     * Constructs a PreparationStep object with the specified recipe ID, step number, and description.
     *
     * @param recipeId    The ID of the recipe to which this preparation step belongs.
     * @param step        The step number in the sequence of preparation steps.
     * @param description The description of the preparation step.
     */
    public PreparationStep(int recipeId, int step, String description) {
        this.recipeId = recipeId;
        this.step = step;
        this.description = description;
    }

    /**
     * Retrieves the recipe ID associated with this preparation step.
     *
     * @return The recipe ID.
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Sets the recipe ID associated with this preparation step.
     *
     * @param recipeId The recipe ID to set.
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Retrieves the step number of this preparation step.
     *
     * @return The step number.
     */
    public int getStep() {
        return step;
    }

    /**
     * Sets the step number of this preparation step.
     *
     * @param step The step number to set.
     */
    public void setStep(int step) {
        this.step = step;
    }

    /**
     * Retrieves the description of this preparation step.
     *
     * @return The description of the preparation step.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this preparation step.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the PreparationStep object.
     *
     * @return A string representation including recipe ID, step number, and description.
     */
    @Override
    public String toString() {
        return "PreparationStep{" +
                "recipeId=" + recipeId +
                ", step=" + step +
                ", description='" + description + '\'' +
                '}';
    }
}
