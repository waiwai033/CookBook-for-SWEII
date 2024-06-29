package control;
import config.SessionManager;
import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Button;
import view.*;
import config.SessionManager;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class RecipeDisplayController implements EventHandler<ActionEvent> {
    private recipeDisplayView recipeDisplayView;
    private MainPageView mainPageView;
    private Model model;
    private VIPView vipView;
    private recipeCreateView recipeCreateView;
    private Recipe selectedRecipe;
    private List<RecipeIngredient> selectedIngredient;
    private List<String> selectedInstructions;
    public RecipeDisplayController(recipeDisplayView recipeDisplayView) {
        Integer selectedRecipeNumber = recipeDisplayView.selectedRecipeNumber;
        this.recipeDisplayView = recipeDisplayView;
        this.model = new Model();
        selectedRecipe = model.getRecipeByID(selectedRecipeNumber);
        selectedIngredient = model.getIngredientByID(selectedRecipeNumber);
        selectedInstructions = model.getRecipeInstruction(selectedRecipeNumber);
        // 在构造函数中添加 TextField 的监听器

    }
    public void initializeData() {
        recipeDisplayView.serveNumberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                recipeDisplayView.selectedIngredients.clear();
                List<RecipeIngredient> updatedRecipeIngredients = model.updateIngredientByServeNumber(selectedRecipe.getRecipeId(), recipeDisplayView.serveNumberTextField.getText());
                for(RecipeIngredient updatedRecipeIngredient: updatedRecipeIngredients){
                    recipeDisplayView.selectedIngredients.add(updatedRecipeIngredient);
                }
            }
        });
        System.out.println("Initializing RecipeSelectController");
        recipeDisplayView.recipeNameLabel.setText( selectedRecipe.getRecipeName());
        recipeDisplayView.imageurl = "file:"+selectedRecipe.getImageUrl();
        for(RecipeIngredient recipeIngredient : selectedIngredient){
            recipeDisplayView.selectedIngredients.add(recipeIngredient);
        }
        recipeDisplayView.serveNumberTextField.setText(String.valueOf(selectedRecipe.getServeAmount()));
        recipeDisplayView.cookingTimeLabel.setText("Cooking Time: "+selectedRecipe.getCookingTime()+"min");
        recipeDisplayView.preparationTimeLabel.setText("Preparation Time: "+selectedRecipe.getPreparationTime()+"min");
        for(String instruction : selectedInstructions){
            recipeDisplayView.instructionsTextArea.appendText(instruction+"\n");
        }

    }
    public void handle(ActionEvent event) {
        if (event.getSource() == recipeDisplayView.VIPbutton) {
            if(!model.userIsVip(SessionManager.getCurrentUserName())){
                VIPView vipView1 = new VIPView();
                vipView1.show();
            }else {
                model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are already vip");
            }

        } else if (event.getSource() == recipeDisplayView.deleteRecipeButton) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this recipe?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    model.displayAlert(Alert.AlertType.INFORMATION,"Info","Successfully deleted this recipe");
//                    deleterecipe();
                }
            });
        }else if (event.getSource() == recipeDisplayView.editRecipeButton) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to edit this recipe?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    //            recipeDisplayView.close();
                    recipeCreateView = new recipeCreateView();
                    recipeCreateView.recipeNameTextField.setText(selectedRecipe.getRecipeName());
                    recipeCreateView.cookingTimeTextField.setText(String.valueOf(selectedRecipe.getCookingTime()));
                    recipeCreateView.preparationTextField.setText(String.valueOf(selectedRecipe.getPreparationTime()));
                    recipeCreateView.recipeImage.setImage(new Image("file:"+selectedRecipe.getImageUrl()));
                    for(RecipeIngredient recipeIngredient : selectedIngredient){

                    }
                    for(String instruction : selectedInstructions){
                        recipeDisplayView.instructionsTextArea.appendText(instruction+"\n");
                    }
                    recipeCreateView.show();
                }
            });
        }else if (event.getSource() == recipeDisplayView.backButton) {
            recipeDisplayView.close();
        }
    }
}
