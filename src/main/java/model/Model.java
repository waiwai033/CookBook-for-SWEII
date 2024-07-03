package model;
import config.SessionManager;
import dao.mappers.*;
import javafx.scene.control.Alert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The Model Class is used to implement ModelMethod Interface
 *
 * @author He Chenyi
 */
public class Model implements ModelMethod{
    private UserMapper userMapper;
    private RecipeMapper recipeMapper;
    private RecipeIngredientMapper recipeIngredientMapper;
    private PreparationStepMapper preparationStepMapper;
    private SqlSession sqlSession;

    /**
     * Constructor that initializes MyBatis and connects to the database.
     */
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean sign(String name, String password) {
        if (userMapper.getUserByName(name) != null) {
            return false;
        } else {
            try {
                User user = new User();
                user.setUser(name, password);
                userMapper.addUser(user);
                sqlSession.commit();
                return true;
            } catch (Exception e) {
                sqlSession.rollback();
                return false;
            }
        }

    }

    @Override
    public boolean login(String name, String password) {
        try {
            User user = userMapper.getUserByName(name);
            if (user == null) {
                displayAlert(Alert.AlertType.ERROR, "Error", "Please input username!");
                return false;
            } else if (!user.getPassword().equals(password)) {
                displayAlert(Alert.AlertType.ERROR, "Error", "Password error");
                return false;
            } else {
                SessionManager.setCurrentUserName(name);
                return true;
            }
        } catch (Exception e) {
            sqlSession.rollback();
            return false;
        }
    }


    @Override
    public boolean userIsVip(String userName) {
        try {
            User user = userMapper.getUserByName(userName);
            return user.isVip();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public  LinkedHashMap< Integer,String>   updateImageUrls(String recipeName){
        ArrayList<Recipe> recipes = recipeMapper.getRecipeByName(recipeName);
        LinkedHashMap< Integer,String> imageHashMap = new LinkedHashMap<>();
        for(Recipe recipe : recipes){

            imageHashMap.put(recipe.getRecipeId(),recipe.getImageUrl());
        }

        return imageHashMap;
    }
    @Override
    public  ArrayList<String>  updateImageNames(String recipeName){
        ArrayList<Recipe> recipes = recipeMapper.getRecipeByName(recipeName);
        ArrayList<String> imageNames = new ArrayList<>();
        for(Recipe recipe : recipes){

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

    @Override
    public Recipe getRecipeByID(Integer id){
        return recipeMapper.getRecipeById(id);
    }

    @Override
    public List<RecipeIngredient> getIngredientByID(Integer id){
        return recipeIngredientMapper.getRecipeIngredientsByRecipeId(id);
    }

    @Override
    public List<PreparationStep> getRecipePreparationSteps(Integer id){
        return preparationStepMapper.getPreparationStepsByRecipeId(id);
    }

    @Override
    public List<RecipeIngredient> updateIngredientByServeNumber(Integer id,String serveNumber){
        Float serveNumberInt = Float.parseFloat(serveNumber);
        List<RecipeIngredient> updatedIngredients = new ArrayList<>();
        List<RecipeIngredient> ingredients = recipeIngredientMapper.getRecipeIngredientsByRecipeId(id);
        for(RecipeIngredient ingredient : ingredients){
            updatedIngredients.add(new RecipeIngredient(ingredient));
        }
        for(RecipeIngredient ingredient : updatedIngredients){
            ingredient.setQuantity(serveNumberInt * ingredient.getQuantity());
        }
        return updatedIngredients;
    }

    @Override
    public void setVIP(String username) {
        try {
            userMapper.setVIP(username);
            sqlSession.commit();
        } catch (Exception e) {
           sqlSession.rollback();
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        try {
            recipeMapper.updateRecipe(recipe);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }
    }

    @Override
    public Integer addRecipe(Recipe recipe){

        try {
            recipeMapper.addRecipe(recipe);
            sqlSession.commit();
            Recipe newRecipe = recipeMapper.getNewRecipe();
            return newRecipe.getRecipeId();
        } catch (Exception e) {
            sqlSession.rollback();
            return 0;
        }

   }

    @Override
    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        try {
            recipeIngredientMapper.addRecipeIngredient(recipeIngredient);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }

    }

    @Override
    public void updateRecipeIngredient(Integer recipeID,List<RecipeIngredient> recipeIngredients){
        try {
            recipeIngredientMapper.deleteRecipeIngredient(recipeID);
            for (RecipeIngredient recipeIngredient : recipeIngredients) {
                recipeIngredientMapper.addRecipeIngredient(recipeIngredient);
            }
            sqlSession.commit();
        } catch (Exception e) {
           sqlSession.rollback();
        }
    }

    @Override
    public List<Recipe> getAllRecipes(){
        return recipeMapper.getAllRecipes();
    }

    @Override
    public void addRecipePreparationStep(PreparationStep preparationStep) {
        try {
            preparationStepMapper.addPreparationStep(preparationStep);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }
    }

    @Override
    public void updateRecipePreparationStep(Integer recipeID, List<PreparationStep> preparationSteps){
        try {
            preparationStepMapper.deletePreparationStep(recipeID);
            for (PreparationStep preparationStep : preparationSteps) {
                preparationStepMapper.addPreparationStep(preparationStep);
            }
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }
    }

    private static String getFileExtension(String filename) {
        if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    @Override
    public Path duplicateImage(String imageUrl){
        String projectPath = System.getProperty("user.dir");
        String targetPath = projectPath + "/src/images/dishes";
        String fileExtension = getFileExtension(imageUrl);
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String newFileName = timestamp + "." + fileExtension;
        Path fullPath = Paths.get(targetPath).resolve(newFileName);
        Path imagePath = Paths.get(imageUrl);
        try {
            Files.copy(imagePath,fullPath);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        return  fullPath;

    }

    @Override
    public void deleteRecipe(Integer recipeID){
        try {
            recipeMapper.deleteRecipe(recipeID);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }   }

    @Override
    public boolean serveNumberIsInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}

