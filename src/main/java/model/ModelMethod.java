package model;

import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import dao.mappers.RecipeInstruction;

import java.util.List;

public interface ModelMethod {



    public void loadLoginPage();

    public void sign(int userID, String password);

    public void login(int userID, String password);

    void sign(String name, String password);

    void login(String name, String password);

    public void logout();

    public void loadRecipePage();

    public List<Recipe> updateRecipePageByName(String category, int recipeID);

    public List<Recipe> updateRecipePageByCategory(String category);

    public void loadAdvertisementPage();

    public boolean userIsVip(int userID);

    public void loadVipPage(int userID);

    public void signVip(int userID, String password);

    public List<RecipeIngredient> loadIngredientPage(int recipeID);

    public List<RecipeInstruction> loadInstructionPage(int recipeID);


}
