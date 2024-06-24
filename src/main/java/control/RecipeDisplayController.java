package control;
import dao.mappers.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Button;
import view.MainPageView;
import view.VIPView;
import view.recipeDisplayView;
import view.recipeSelectView;
public class RecipeDisplayController  {
    private recipeDisplayView recipeDisplayView;
    private MainPageView mainPageView;
    private Model model;
    private VIPView vipView;
    private Recipe selectedRecipe;
    public RecipeDisplayController(recipeDisplayView recipeDisplayView) {
        Integer selectedRecipeNumber = recipeDisplayView.selectedRecipeNumber;
        this.recipeDisplayView = recipeDisplayView;
        this.model = new Model();
        selectedRecipe = model.getRecipeByID(selectedRecipeNumber);


    }
    public void initializeData() {
        System.out.println("Initializing RecipeSelectController");
        recipeDisplayView.recipeNameLabel.setText( selectedRecipe.getRecipeName());
        recipeDisplayView.imageurl = "file:"+selectedRecipe.getImageUrl();

    }


}
