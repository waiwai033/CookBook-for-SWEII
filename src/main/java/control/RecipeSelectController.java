package control;
import dao.mappers.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.Model;
import javafx.scene.control.Button;
import view.*;
import config.SessionManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Controller for handling actions on the Recipe Select page.
 *
 * @author He Chenyi, Yan Yi
 */
public class RecipeSelectController implements EventHandler<ActionEvent> {
    private final RecipeSelectView recipeSelectView;
    private final Model model;
    private VIPView vipView;

    /**
     * Constructor to initialize the controller with the given RecipeSelectView.
     *
     * @param recipeSelectView The view instance to be associated with this controller.
     */
    public RecipeSelectController(RecipeSelectView recipeSelectView) {
        this.recipeSelectView = recipeSelectView;
        this.model = new Model();

    }


    /**
     * Initializes data to be displayed in the RecipeSelectView.
     * Retrieves all recipes from the model and populates imageUrls and imageNames in the view.
     */
    public void initializeData() {
        List<Recipe> allRecipes = model.getAllRecipes();
        recipeSelectView.imageUrls =  new LinkedHashMap<>();;
        recipeSelectView.imageNames = new ArrayList<>();
        for(Recipe recipe : allRecipes) {
            recipeSelectView.imageNames.add(recipe.getRecipeName());

            recipeSelectView.imageUrls.put( recipe.getRecipeId(),recipe.getImageUrl());
        }

    }

    /**
     * Handles actions performed in the RecipeSelectView.
     *
     * @param actionEvent The ActionEvent representing the user's action.
     */
    @Override
    public void handle(ActionEvent actionEvent) {

        for(Button button : recipeSelectView.buttonMap.keySet()){
            // Handle recipe button clicks
            if (actionEvent.getSource() == button){
                recipeSelectView.close();
                // Play advertisement before displaying RecipeDisplayView
                Integer recipeNumber = recipeSelectView.buttonMap.get(button);
                AdvertiseView advertiseView = new AdvertiseView();
                // Handle end of media event to close AdvertiseView and show RecipeDisplayView
                advertiseView.setOnEndOfMedia(() -> {
                    advertiseView.close();
                    // Show RecipeDisplayView
                    RecipeDisplayView view = new RecipeDisplayView(recipeNumber);
                    view.show();
                });

                // Handle skip button event
                advertiseView.setOnSkipButton(event -> {
                    if(!model.userIsVip(SessionManager.getCurrentUserName())){
                        vipView = new VIPView(v -> {
                            advertiseView.mediaPlayer.stop();
                            advertiseView.close();
                            RecipeDisplayView view = new RecipeDisplayView(recipeNumber);
                            view.show();
                        });
                        vipView.show();
                    }else{
                        // Close AdvertiseView and show RecipeDisplayView if user is VIP
                        advertiseView.mediaPlayer.stop();
                        advertiseView.close();
                        RecipeDisplayView view = new RecipeDisplayView(recipeNumber);
                        view.show();
                    }
                });

                advertiseView.show();
            }
        }
        // Handle VIP button click
        if (actionEvent.getSource() == recipeSelectView.vipButton) {
            if(!model.userIsVip(SessionManager.getCurrentUserName())){
                // Show VIPView if user is not VIP
                VIPView vipView = new VIPView(v ->{
                    if (model.userIsVip(SessionManager.getCurrentUserName())){
                        Model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are now vip");
                    }
                });
                vipView.show();
            }else {
                // Display information message if user is already VIP
                Model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are already vip");
            }

        }
        // Handle search button click
        else if(actionEvent.getSource() == recipeSelectView.searchButton){
            String recipeName = recipeSelectView.searchField.textProperty().getValue();
            recipeSelectView.update(model.updateImageUrls(recipeName), model.updateImageNames(recipeName));
        }
        // Handle back button click
        else if(actionEvent.getSource() == recipeSelectView.backButton){
            recipeSelectView.close();
        }

    }


}
