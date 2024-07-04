package test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javafx.application.Platform;
import org.apache.ibatis.session.SqlSession;
import model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationExtension;
import dao.mappers.UserMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The class is used to test the sign-up function
 *
 * @author He Chenyi
 */
// use ApplicationExtension to manage JavaFX environment
@ExtendWith(ApplicationExtension.class)
public class SignTest {

    private UserMapper userMapper;
    private SqlSession sqlSession;
    private Model model;

    @BeforeEach
    public void setUp() {
        userMapper = mock(UserMapper.class);
        sqlSession = mock(SqlSession.class);
        model = new Model();
    }
    /**
     * Test whether signed users are duplicated
     */
    @Test
    public void testSignUserAlreadyExists() throws InterruptedException, ExecutionException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            model.sign("hcyhcyhcyhcyhcy", "password123");
            boolean result = model.sign("hcyhcyhcyhcyhcy", "password123");
            System.out.println(model.getAllRecipes());
            System.out.println(result);
            future.complete(result);
        });

        assertFalse(future.get());
    }

    /**
     * Test whether username is pure Integer
     */
    @Test
    public void testSignUserNameIsPureInteger()throws InterruptedException, ExecutionException {

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            boolean result = model.sign("123", "password123");
            future.complete(result);
        });
        assertFalse(future.get());
    }

    /**
     * Test whether password is pure integer
     */
    @Test
    public void testSignPasswordIsPureInteger()throws InterruptedException, ExecutionException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            boolean result = model.sign("ddd", "123");
            future.complete(result);
        });
        assertFalse(future.get());
    }

    /**
     * Test whether sign up successfully
     */
    @Test
    public void testSignSuccess() throws InterruptedException, ExecutionException {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            boolean result = model.sign("hcyhcyhcyhcyhcyhcyhcy", "password123");
            System.out.println(model.getAllRecipes());
            System.out.println(result);
            future.complete(result);
        });

        assertTrue(future.get());
    }


}