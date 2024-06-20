package control;
import dao.mappers.Recipe;
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
    public void initializeData() {
        System.out.println("Initializing RecipeSelectController");
        recipeSelectView.imageUrls = model.getImageUrls();
        recipeSelectView.imageNames = model.getImageNames();
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(222222);
        if (actionEvent.getSource() == recipeSelectView.vipButton) {
            VIPView vipView = new VIPView();
            vipView.show();
        }
        else if(actionEvent.getSource() == recipeSelectView.searchButton){
            String recipeName = recipeSelectView.searchField.textProperty().getValue();
            recipeSelectView.update(model.updateImageUrls(recipeName),model.updateImageNames(recipeName));
            System.out.println(recipeSelectView.imageUrls);

        }

    }


}
