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
 * @author hechenyi
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

    public List<RecipeIngredient> updateIngredientByServeNumber(Integer id,String serveNumber);

    public List<RecipeIngredient> getIngredientByID(Integer id);

    public void setVIP(String username);

    public void updateRecipe(Recipe recipe);

    public void addRecipePreparationStep(PreparationStep preparationStep);

    public void updateRecipePreparationStep(Integer recipeID, List<PreparationStep> preparationSteps);

    public void deleteRecipe(Integer recipeID);
    }
