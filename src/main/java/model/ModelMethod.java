package model;

import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import dao.mappers.PreparationStep;

import java.util.List;

public interface ModelMethod {



    public void loadLoginPage();



    public boolean sign(String name, String password);

    public boolean login(String name, String password);

    public void logout();

    public void loadRecipePage();


    public List<Recipe> updateRecipePageByCategory(String category);

    public void loadAdvertisementPage();

    public boolean userIsVip(int userID);

    public void loadVipPage(int userID);

    public void signVip(int userID, String password);

    public List<RecipeIngredient> loadIngredientPage(int recipeID);

    public List<PreparationStep> loadInstructionPage(int recipeID);

    public List<String> getImageUrls();
    public List<String> getImageNames();
    public  List<String>  updateImageNames(String recipeName);
    public List<String> updateImageUrls(String recipeName);
}
