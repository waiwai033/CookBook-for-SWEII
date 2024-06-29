package Enter;

import javafx.stage.Stage;
import view.*;

/**
 * The type Application entrance.
 * Load a JavaFX stage.
 */
public class ApplicationEntrance extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws Exception {
        signinView signinView = new signinView();
        signinView.show();

//          recipeDisplayView view = new recipeDisplayView();
//          view.show();
//        MainPageView mainPageView = new MainPageView();
//        mainPageView.show();
//        VIPView vipView = new VIPView();
//        vipView.show();
    }
}
