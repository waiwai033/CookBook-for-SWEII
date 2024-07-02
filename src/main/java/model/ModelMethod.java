package model;

import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import dao.mappers.PreparationStep;

import java.nio.file.Path;
import java.util.List;
import java.util.HashMap;

public interface ModelMethod {

    public void loadLoginPage();

    public boolean sign(String name, String password);

    public boolean login(String name, String password);

    public void logout();

    public void loadRecipePage();

    public List<Recipe> updateRecipePageByCategory(String category);

    public void loadAdvertisementPage();

    public boolean userIsVip(String userName);

    public void loadVipPage(int userID);

    public void signVip(int userID, String password);

    public List<RecipeIngredient> loadIngredientPage(int recipeID);

    public List<PreparationStep> loadInstructionPage(int recipeID);

    public HashMap<String, Integer>  getImageUrls();

    public List<String> getImageNames();

    public  List<String>  updateImageNames(String recipeName);

    public HashMap<Integer,String >updateImageUrls(String recipeName);

    public Recipe getRecipeByID(Integer id);
    public List<PreparationStep> getRecipeInstruction(Integer id);

    public Integer addRecipe(Recipe recipe);

    public void addRecipeIngredient(RecipeIngredient recipeIngredient);

    public void updateRecipeIngredient(Integer recipeID,List<RecipeIngredient> recipeIngredients);
    public List<Recipe> getAllRecipes();
    public Path duplicateImage(String imageURL);
}
