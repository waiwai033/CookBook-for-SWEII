package control;
import dao.mappers.PreparationStep;
import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import view.RecipeCreateView;
import view.RecipeSelectView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling actions on the Recipe Create page.
 *
 * @author He Chenyi, Yan Yi
 */

public class RecipeCreateController implements EventHandler<ActionEvent> {

    private int preparationStepNumber = 0;
    private final RecipeCreateView recipeCreateView;
    private final Model model;
    public  String imageUrl = "";

    /**
     * Constructs a RecipeCreateController with a RecipeCreateView instance.
     *
     * @param recipeCreateView The RecipeCreateView associated with this controller.
     */
    public RecipeCreateController(RecipeCreateView recipeCreateView) {
        this.recipeCreateView = recipeCreateView;
        this.model = new Model();
        // Initialize preparation step number based on existing steps for edited recipes
        List<PreparationStep> preparationSteps = model.getRecipePreparationSteps(recipeCreateView.editedRecipeId);
        for(PreparationStep preparationStep : preparationSteps){
            preparationStepNumber = Math.max(preparationStepNumber,preparationStep.getStep());
        }
        preparationStepNumber++;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == recipeCreateView.uploadButton){
            imageUrl = imageChoose(recipeCreateView);
            if(imageUrl != null) {
                recipeCreateView.updateImage(model.duplicateImage(imageUrl).toString());
            }

        }
        // Handle back button click
        if(event.getSource() == recipeCreateView.backButton){
            recipeCreateView.close();
        }
        // Handle add button click
        if (event.getSource() == recipeCreateView.addButton){
            handleAddButtonAction();
        }
        // Handle delete button click
        if (event.getSource() == recipeCreateView.deleteButton){
            handleDeleteButtonAction();
        }
        // Handle clear button click
        if (event.getSource() == recipeCreateView.clearButton) {
            handleClearButtonAction();
        }
        // Handle submit button click
        if(event.getSource() == recipeCreateView.submitButton){
            // Retrieve input values
            String recipeName = recipeCreateView.recipeNameTextField.getText();
            String cookingTime = recipeCreateView.cookingTimeTextField.getText();
            String preparation = recipeCreateView.preparationTextField.getText();

            // Validate input
            if (recipeName.isEmpty() || cookingTime.isEmpty() || preparation.isEmpty()) {
                Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please complete form！");
                return;
            }
            if (!cookingTime.matches("\\d+") || !preparation.matches("\\d+")) {
                Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please input number！");
                return;
            }
            if (recipeCreateView.recipeImage.getImage() == null) {
                Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please upload image!");
                return;
            }
            Recipe recipe;
            Integer recipeId = 0;
            // Create or update recipe based on editing status
            if(!recipeCreateView.isEdited) {
                recipe = new Recipe(0,recipeCreateView.recipeNameTextField.getText(),1,Integer.parseInt(recipeCreateView.cookingTimeTextField.getText()),Integer.parseInt(recipeCreateView.preparationTextField.getText()),recipeCreateView.recipeImage.getImage().getUrl().replace("file:", ""));
                recipeId = model.addRecipe(recipe);
            }
            else{
                recipe = new Recipe(recipeCreateView.editedRecipeId,recipeCreateView.recipeNameTextField.getText(),1,Integer.parseInt(recipeCreateView.cookingTimeTextField.getText()),Integer.parseInt(recipeCreateView.preparationTextField.getText()),recipeCreateView.recipeImage.getImage().getUrl().replace("file:", ""));
                recipeId = recipeCreateView.editedRecipeId;
                model.updateRecipe(recipe);
            }
            // Prepare updated recipe ingredients
            List<RecipeIngredient> updatedRecipeIngredients = new ArrayList<>();
            for(RecipeIngredient recipeIngredient: recipeCreateView.tableView.getItems()){
                recipeIngredient.setRecipeId(recipeId);
                // Validate ingredient fields
                if(recipeIngredient.getName().isEmpty()){
                    Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please input ingredient name！");
                    if(!recipeCreateView.isEdited) {
                        model.deleteRecipe(recipeId);
                    }
                    return;
                }
                if(recipeIngredient.getQuantity() == null || recipeIngredient.getQuantity() == 0.0){
                    Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please input ingredient quantity ！");
                    if(!recipeCreateView.isEdited) {
                        model.deleteRecipe(recipeId);
                    }
                    return;
                }
                if(recipeIngredient.getUnit().isEmpty()){
                    Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please ingredient unit！");
                    if(!recipeCreateView.isEdited) {
                        model.deleteRecipe(recipeId);
                    }
                    return;
                }
                if(recipeIngredient.getDescription().isEmpty()){
                    Model.displayAlert(Alert.AlertType.WARNING, "Warn", "Please input ingredient description！");
                    if(!recipeCreateView.isEdited) {
                        model.deleteRecipe(recipeId);
                    }
                    return;
                }

                // Directly add recipe ingredient when creating recipe
                if(!recipeCreateView.isEdited) {
                    model.addRecipeIngredient(recipeIngredient);
                }
                // Temporarily store recipe ingredient to List when editing recipe
                else {
                    updatedRecipeIngredients.add(recipeIngredient);

                }

            }
            // Update recipe ingredients if editing
            if(recipeCreateView.isEdited){
                model.updateRecipeIngredient(recipeId,updatedRecipeIngredients);
            }

            // Prepare updated preparation steps
            List<PreparationStep> updatedPreparationSteps = new ArrayList<>();
            for(PreparationStep preparationStep : recipeCreateView.instructionTableView.getItems()){
                preparationStep.setRecipeId(recipeId);
                // Directly add recipe preparation step when creating recipe
                if(!recipeCreateView.isEdited) {
                    model.addRecipePreparationStep(preparationStep);
                }
                // Temporarily store recipe preparation step to List when editing recipe
                else{
                    updatedPreparationSteps.add(preparationStep);

                }
            }
            // Update preparation steps if editing
            if(recipeCreateView.isEdited){
                model.updateRecipePreparationStep(recipeId, updatedPreparationSteps);
            }
            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Recipe and ingredients added successfully!");
            successAlert.showAndWait();

            // Close the stage
            recipeCreateView.close();
            RecipeSelectView recipeSelectView = new RecipeSelectView();
            recipeSelectView.show();

        }


    }

    /**
     * Opens a file chooser dialog for selecting an image file.
     *
     * @param stage The stage to show the file chooser dialog on.
     * @return The absolute path of the selected image file, or null if no file selected.
     */
    public String imageChoose(Stage stage){
        FileChooser fileChooser = new FileChooser();
        stage.setOpacity(0);
        Stage tempWindow = new Stage();
        File temp = fileChooser.showOpenDialog(tempWindow);
        stage.setOpacity(1);
        if (imageStore(temp)) {
            return temp.getAbsolutePath();
        }
        return null;
    }

    private boolean imageStore(File file) {
        return file != null && file.exists();
    }

    /**
     * Handles adding new rows to the table based on the currently selected tab.
     */
    private void handleAddButtonAction() {

        Tab selectedTab = recipeCreateView.tabPane.getSelectionModel().getSelectedItem();
        if(selectedTab.equals(recipeCreateView.ingredientsTab)){
            RecipeIngredient newIngredient = new RecipeIngredient(0,"",new Float(0.0),"",""); // Assuming default constructor creates an empty ingredient
            recipeCreateView.tableView.getItems().add(newIngredient);
        }
        else if(selectedTab.equals(recipeCreateView.instructionTab)){
            PreparationStep newInstruction = new PreparationStep(0, preparationStepNumber++,"");
            recipeCreateView.instructionTableView.getItems().add(newInstruction);
        }
    }

    /**
     * Handles deleting selected rows from the table based on the currently selected tab.
     */
    private void handleDeleteButtonAction() {
        Tab selectedTab = recipeCreateView.tabPane.getSelectionModel().getSelectedItem();

        if (selectedTab.equals(recipeCreateView.ingredientsTab)) {
            int selectedIndex = recipeCreateView.tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                recipeCreateView.tableView.getItems().remove(selectedIndex);
            } else {
                Model.displayAlert(Alert.AlertType.INFORMATION, "No rows selected", "Please select a row to delete.");
            }
        } else if (selectedTab.equals(recipeCreateView.instructionTab)) {
            int selectedIndex = recipeCreateView.instructionTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                recipeCreateView.instructionTableView.getItems().remove(selectedIndex);
            } else {
                Model.displayAlert(Alert.AlertType.INFORMATION, "No rows selected", "Please select a row to delete.");
            }
        }
    }

    /**
     * Handles clearing all input fields and tables.
     */
    private void handleClearButtonAction() {
        recipeCreateView.recipeNameTextField.clear();
        recipeCreateView.preparationTextField.clear();
        recipeCreateView.cookingTimeTextField.clear();
        recipeCreateView.tableView.getItems().clear();
        recipeCreateView.instructionTableView.getItems().clear();
        recipeCreateView.recipeImage.setImage(null);
    }
}
