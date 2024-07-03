package dao.mappers;

import java.io.Serializable;

/**
 * Represents a Recipe entity with essential attributes.
 * This class encapsulates details such as recipe ID, name, serving amount,
 * preparation time, cooking time, and image URL.
 *
 * @author He Chenyi
 */
public class Recipe implements Serializable {

    private int recipeId;
    private String recipeName;
    private int serveAmount;
    private int preparationTime;
    private int cookingTime;
    private String imageUrl;

    /**
     * Default constructor for the Recipe class.
     */
    public Recipe() {
    }

    /**
     * Constructs a Recipe object with specified attributes.
     *
     * @param recipeId        The unique identifier for the recipe.
     * @param recipeName      The name or title of the recipe.
     * @param serveAmount     The number of servings the recipe yields.
     * @param cookingTime     The time required for cooking the recipe, in minutes.
     * @param preparationTime The time required for preparing the recipe, in minutes.
     * @param imageUrl        The URL or path to the image associated with the recipe.
     */
    public Recipe(int recipeId, String recipeName, int serveAmount, int cookingTime, int preparationTime, String imageUrl) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.serveAmount = serveAmount;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.imageUrl = imageUrl;
    }

    /**
     * Retrieves the recipe ID.
     *
     * @return The recipe ID.
     */
    public int getRecipeId() {
        return this.recipeId;
    }

    /**
     * Sets the recipe ID.
     *
     * @param recipeId The recipe ID to set.
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Retrieves the recipe name.
     *
     * @return The recipe name.
     */
    public String getRecipeName() {
        return this.recipeName;
    }

    /**
     * Sets the recipe name.
     *
     * @param recipeName The recipe name to set.
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * Retrieves the serving amount of the recipe.
     *
     * @return The serve amount.
     */
    public int getServeAmount() {
        return this.serveAmount;
    }

    /**
     * Sets the serving amount of the recipe.
     *
     * @param serveAmount The serve amount to set.
     */
    public void setServeAmount(int serveAmount) {
        this.serveAmount = serveAmount;
    }

    /**
     * Retrieves the preparation time of the recipe.
     *
     * @return The preparation time, in minutes.
     */
    public int getPreparationTime() {
        return this.preparationTime;
    }

    /**
     * Sets the preparation time of the recipe.
     *
     * @param preparationTime The preparation time to set, in minutes.
     */
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    /**
     * Retrieves the cooking time of the recipe.
     *
     * @return The cooking time, in minutes.
     */
    public int getCookingTime() {
        return this.cookingTime;
    }

    /**
     * Sets the cooking time of the recipe.
     *
     * @param cookingTime The cooking time to set, in minutes.
     */
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Retrieves the image URL associated with the recipe.
     *
     * @return The image URL.
     */
    public String getImageUrl() {
        return this.imageUrl;
    }

    /**
     * Returns a string representation of the Recipe object.
     *
     * @return A string containing all attributes of the Recipe.
     */
    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", serveAmount=" + serveAmount +
                ", preparationTime=" + preparationTime +
                ", cookingTime=" + cookingTime +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
