package model;
import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import dao.mappers.PreparationStep;
import java.nio.file.Path;
import java.util.List;
import java.util.HashMap;

/**
 * The interface ModelMethod.
 * This interface defines the methods for managing user authentication,
 * retrieving and updating recipes, and handling image processing.
 * It also includes methods for user sign-up, login, VIP status check,
 * and various operations related to recipes and ingredients.
 *
 * @author He Chenyi
 */
public interface ModelMethod {

    /**
     * Signs up a new user with the given name and password.
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @return true if the sign-up was successful, false otherwise
     */
    public boolean sign(String name, String password);

    /**
     * Logs in a user with the given name and password.
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @return true if the login was successful, false otherwise
     */
    public boolean login(String name, String password);

    /**
     * Checks if the user with the given name is a VIP.
     *
     * @param userName the name of the user
     * @return true if the user is a VIP, false otherwise
     */
    public boolean userIsVip(String userName);

    /**
     * Updates and retrieves the image names associated with the given recipe name.
     *
     * @param recipeName the name of the recipe
     * @return a list of image names associated with the recipe
     */
    public List<String> updateImageNames(String recipeName);

    /**
     * Updates and retrieves the image URLs associated with the given recipe name.
     *
     * @param recipeName the name of the recipe
     * @return a hash map with image IDs as keys and image URLs as values
     */
    public HashMap<Integer, String> updateImageUrls(String recipeName);

    /**
     * Retrieves a recipe by its ID.
     *
     * @param id the ID of the recipe
     * @return the recipe with the specified ID
     */
    public Recipe getRecipeByID(Integer id);

    /**
     * Retrieves the preparation steps for a recipe by its ID.
     *
     * @param id the ID of the recipe
     * @return a list of preparation steps for the recipe
     */
    public List<PreparationStep> getRecipePreparationSteps(Integer id);

    /**
     * Adds a new recipe to the database.
     *
     * @param recipe the recipe to be added
     * @return the ID of the newly added recipe
     */
    public Integer addRecipe(Recipe recipe);

    /**
     * Adds a new ingredient for a recipe to the database.
     *
     * @param recipeIngredient the ingredient to be added
     */
    public void addRecipeIngredient(RecipeIngredient recipeIngredient);

    /**
     * Updates the ingredients of a recipe with the given ID.
     *
     * @param recipeID          the ID of the recipe
     * @param recipeIngredients the list of ingredients to update
     */
    public void updateRecipeIngredient(Integer recipeID, List<RecipeIngredient> recipeIngredients);

    /**
     * Retrieves all recipes from the database.
     *
     * @return a list of all recipes
     */
    public List<Recipe> getAllRecipes();

    /**
     * Duplicates an image from the given URL and returns the path to the duplicated image.
     *
     * @param imageURL the URL of the image to duplicate
     * @return the path to the duplicated image
     */
    public Path duplicateImage(String imageURL);

    /**
     * Checks if a given string represents an integer.
     *
     * @param str the string to check
     * @return true if the string is an integer, false otherwise
     */
    public boolean serveNumberIsInteger(String str);

    /**
     * Updates the ingredients of a recipe based on the number of servings.
     *
     * @param id          the ID of the recipe
     * @param serveNumber the number of servings to update the ingredients for
     * @return a list of updated ingredients based on the number of servings
     */
    public List<RecipeIngredient> updateIngredientByServeNumber(Integer id, String serveNumber);

    /**
     * Retrieves the ingredients of a recipe by its ID.
     *
     * @param id the ID of the recipe
     * @return a list of ingredients for the recipe
     */
    public List<RecipeIngredient> getIngredientByID(Integer id);

    /**
     * Sets a user as VIP based on their username.
     *
     * @param username the username of the user to set as VIP
     */
    public void setVIP(String username);


    /**
     * Updates an existing recipe in the database.
     *
     * @param recipe the recipe with updated information
     */
    public void updateRecipe(Recipe recipe);

    /**
     * Adds a new preparation step for a recipe to the database.
     *
     * @param preparationStep the preparation step to be added
     */
    public void addRecipePreparationStep(PreparationStep preparationStep);

    /**
     * Updates the preparation steps of a recipe with the given ID.
     *
     * @param recipeID          the ID of the recipe
     * @param preparationSteps the list of preparation steps to update
     */
    public void updateRecipePreparationStep(Integer recipeID, List<PreparationStep> preparationSteps);

    /**
     * Deletes a recipe from the database based on its ID.
     *
     * @param recipeID the ID of the recipe to delete
     */
    public void deleteRecipe(Integer recipeID);

    /**
     * Validates the given recipe details.
     *
     * @param recipeName      the name of the recipe
     * @param cookingTime     the cooking time of the recipe
     * @param preparationTime the preparation time of the recipe
     * @param recipeImage     the image associated with the recipe
     * @return true if the recipe details are valid, false otherwise
     */
    public boolean validateRecipe(String recipeName, String cookingTime, String preparationTime, String recipeImage);


    /**
     * Validates the given recipe ingredient details.
     *
     * @param recipeName the name of the recipe
     * @param quantity   the quantity of the ingredient
     * @param unit       the unit of the ingredient
     * @return true if the recipe ingredient details are valid, false otherwise
     */
    public boolean validateRecipeIngredient(String recipeName, Float quantity, String unit);

}
