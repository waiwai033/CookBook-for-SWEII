package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Model;
import view.MainPageView;
import view.VIPView;
import view.recipeSelectView;
public class RecipeSelectController implements EventHandler<ActionEvent> {
    private recipeSelectView recipeSelectView;
    private MainPageView mainPageView;
    private Model model;
    private VIPView vipView;
    public RecipeSelectController(recipeSelectView recipeSelectView) {
        this.recipeSelectView = recipeSelectView;
        this.model = new Model();

    }
    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == recipeSelectView.vipButton) {
            VIPView vipView = new VIPView();
            vipView.show();
        }

    }


}
