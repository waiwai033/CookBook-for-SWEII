package CookBookView;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Yanyi
 * used for checking result of each view
 * **/
public class App extends Application {

    public void start(Stage primaryStage) {
//        signinView signin = new signinView();
//        signin.show();
        signupView signup = new signupView();
        signup.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}