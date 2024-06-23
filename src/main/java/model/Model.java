package model;

import dao.mappers.*;
import javafx.scene.control.Alert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import view.*;

public class Model implements ModelMethod{
    private UserMapper userMapper;
    private RecipeMapper recipeMapper;
    private RecipeIngredientMapper recipeIngredientMapper;
    private PreparationStepMapper preparationStepMapper;
    private SqlSession sqlSession;


    public Model(){
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            userMapper = sqlSession.getMapper(UserMapper.class);
            recipeMapper = sqlSession.getMapper(RecipeMapper.class);
            recipeIngredientMapper = sqlSession.getMapper(RecipeIngredientMapper.class);
            preparationStepMapper = sqlSession.getMapper(PreparationStepMapper.class);
            // 处理 inputStream
        } catch (IOException e) {
            // 处理异常，或者将异常向上层抛出
            e.printStackTrace();
        }

    }

    public void loadLoginPage() {

    }




    @Override
    public boolean sign(String name, String password) {
        if(userMapper.getUserByName(name)!=null){
            System.out.print("User already exist");
            return false;
        }
        else {
            User user = new User();
            user.setUser(name, password);
            userMapper.addUser(user);
            sqlSession.commit();
            return true;
        }

    }


    @Override
    public boolean login(String name, String password) {
        User user = userMapper.getUserByName(name);
        if(user == null){
            displayAlert(Alert.AlertType.ERROR,"error","Please input username!");
            System.out.print(1111);
            sqlSession.rollback();
            return false;

        }
        else if(!user.getPassword().equals(password)){
            displayAlert(Alert.AlertType.ERROR,"error","password error");
            System.out.print("password error");
            return false;
        }
        else if(user.getPassword().equals(password)){
            System.out.println("successful");
            sqlSession.commit();
            return true;
        }
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public void loadRecipePage() {

//        需要变化的标签
//       recipeNameLabel.setText("");
//       cookingTimeLabel.setText("");
//       preparationTimeLabel.setText("");
//       serveNumberTextField.setText("");
    }


    public ArrayList<Recipe> updateRecipePageByName(String name) {
        ArrayList<Recipe> recipes = recipeMapper.getAllRecipes();
        String regex = ".*" + name + ".*";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        ArrayList<Recipe> matches = new ArrayList<>();
        for(Recipe recipe : recipes){
            Matcher matcher = pattern.matcher(recipe.getRecipeName());
            if(matcher.matches()){
                matches.add(recipe);
            }
        }
        return matches;

    }

    @Override
    public ArrayList<Recipe> updateRecipePageByCategory(String category) {
        ArrayList<Recipe> recipeList = recipeMapper.getRecipeByCategory(category);
        return recipeList;
    }

    @Override
    public void loadAdvertisementPage() {

    }

    @Override
    public boolean userIsVip(int userID) {
        User user = userMapper.getUserById(userID);
        return user.isVip();
    }

    @Override
    public void loadVipPage(int userID) {

    }

    @Override
    public void signVip(int userID, String password) {
        User user = userMapper.getUserById(userID);
        user.setVip();

    }

    @Override
    public List<RecipeIngredient> loadIngredientPage(int recipeID) {
        List<RecipeIngredient> ingredients = recipeIngredientMapper.getRecipeIngredientsByRecipeId(recipeID);
        return ingredients;
    }

    public List<PreparationStep> loadInstructionPage(int recipeID) {
        List<PreparationStep> instructions = preparationStepMapper.getPreparationStepsByRecipeId(recipeID);
        return instructions;
    }


    public  ArrayList<String>  updateImageUrls(String recipeName){
        ArrayList<Recipe> recipes = recipeMapper.getRecipeByName(recipeName);
        ArrayList<String> imageUrls = new ArrayList<>();
        for(Recipe recipe : recipes){

            imageUrls.add(recipe.getImageUrl());
        }
        System.out.println(imageUrls);
        return imageUrls;
    }
    public  ArrayList<String>  updateImageNames(String recipeName){
        ArrayList<Recipe> recipes = recipeMapper.getRecipeByName(recipeName);
        ArrayList<String> imageNames = new ArrayList<>();
        for(Recipe recipe : recipes){

            imageNames.add(recipe.getRecipeName());
        }

        return imageNames;
    }
    public ArrayList<String> getImageUrls(){
        ArrayList<String> imageUrls = new ArrayList<>();
        ArrayList<Recipe> recipes = recipeMapper.getAllRecipes();
        for(Recipe recipe : recipes){
            imageUrls.add(recipe.getImageUrl());
        }
        return imageUrls;
    }
    public ArrayList<String> getImageNames() {
        ArrayList<String> imageNames = new ArrayList<>();
        ArrayList<Recipe> recipes = recipeMapper.getAllRecipes();
        for (Recipe recipe : recipes) {
            imageNames.add(recipe.getRecipeName());
        }
        return imageNames;
    }
    public static void displayAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
