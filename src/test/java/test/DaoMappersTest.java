package test;
import static org.junit.jupiter.api.Assertions.*;
import dao.mappers.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

/**
 * The class is used to test the Dao.Mappers class
 *
 * @author He Chenyi
 */
public class DaoMappersTest {
    private RecipeMapper recipeMapper;
    private PreparationStepMapper preparationStepMapper;
    private RecipeIngredientMapper recipeIngredientMapper;
    private UserMapper userMapper;
    private SqlSession  sqlSession;
    @BeforeEach
    public void setUp() {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            recipeMapper = sqlSession.getMapper(RecipeMapper.class);
            userMapper = sqlSession.getMapper(UserMapper.class);
            recipeIngredientMapper = sqlSession.getMapper(RecipeIngredientMapper.class);
            preparationStepMapper = sqlSession.getMapper(PreparationStepMapper.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Tests the getUserById method of UserMapper.
     * Verifies that the user with the specified ID is correctly retrieved from the database.
     */
    @Test
    public void testGetUserById() {
        int userId = 117930;
        User expectedUser = new User(userId, "hcyhcy", "111",true);
        User actualUser = userMapper.getUserById(userId);
        System.out.println(actualUser);
        assertEquals(expectedUser.getUserName(), actualUser.getUserName());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());

    }

    /**
     * Tests the getRecipeById method of RecipeMapper.
     * Verifies that the recipe with the specified ID is correctly retrieved from the database.
     */
    @Test
    public void testGetRecipeById() {
        int recipeId = 85;
        Recipe expectedrecipe = new Recipe(recipeId, "hongshaorou",1,20,40);
        Recipe actualrecipe = recipeMapper.getRecipeById(recipeId);
        assertEquals(expectedrecipe.getRecipeName(), actualrecipe.getRecipeName());
        assertEquals(expectedrecipe.getServeAmount(), actualrecipe.getServeAmount());
        assertEquals(expectedrecipe.getCookingTime(), actualrecipe.getCookingTime());
        assertEquals(expectedrecipe.getPreparationTime(), actualrecipe.getPreparationTime());
    }


    /**
     * Tests the getRecipeIngredientsByRecipeId method of RecipeIngredientMapper.
     * Verifies that the correct number of ingredients for a recipe is retrieved from the database.
     */
    @Test
    public void testGetIngredientById() {
        int recipeId = 85;
        int expectedIngredientNumbers = 5;
        int actualIngredientNumbers = recipeIngredientMapper.getRecipeIngredientsByRecipeId(recipeId).size();
        assertEquals(expectedIngredientNumbers, actualIngredientNumbers);
    }

    /**
     * Tests the getPreparationStepsByRecipeId method of PreparationStepMapper.
     * Verifies that the correct number of preparation steps for a recipe is retrieved from the database.
     */
    @Test
    public void testGetPreparationStepById() {
        int recipeId = 85;
        int expectedPreparationNumbers = 9;
        int actualPreparationNumbers = preparationStepMapper.getPreparationStepsByRecipeId(recipeId).size();
        assertEquals(expectedPreparationNumbers, actualPreparationNumbers);
    }


}
