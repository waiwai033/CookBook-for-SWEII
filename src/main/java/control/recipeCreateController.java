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
import view.recipeCreateView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class recipeCreateController implements EventHandler<ActionEvent> {

    private int preparationStepNumber = 0;
    private recipeCreateView recipeCreateView;
    private Model model;
    private String currentImagePath;
    public static String imageUrl = "";
    public recipeCreateController(recipeCreateView recipeCreateView) {
        this.recipeCreateView = recipeCreateView;
        this.model = new Model();
        System.out.println(recipeCreateView.editedRecipeId);
        System.out.println(recipeCreateView.isEdited);
        List<PreparationStep> preparationSteps = model.getRecipeInstruction(recipeCreateView.editedRecipeId);
        System.out.println(preparationSteps);
        for(PreparationStep preparationStep : preparationSteps){
            System.out.println(preparationStep.getStep());
            System.out.println(preparationStep.getDescription());
            preparationStepNumber = Math.max(preparationStepNumber,preparationStep.getStep());
        }
        preparationStepNumber++;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == recipeCreateView.uploadButton){
            String temp = imageChoose(recipeCreateView);
            imageUrl = temp;
            recipeCreateView.updateImage(model.duplicateImage(imageUrl).toString());

        }
        if(event.getSource() == recipeCreateView.backButton){
            recipeCreateView.close();
        }
        if (event.getSource() == recipeCreateView.addButton){
            handleAddButtonAction();
        }
        if (event.getSource() == recipeCreateView.deleteButton){
            handleDeleteButtonAction();
        }
        if (event.getSource() == recipeCreateView.clearButton) {
            handleClearButtonAction();
        }
        if(event.getSource() == recipeCreateView.submitButton){
            String recipeName = recipeCreateView.recipeNameTextField.getText();
            String cookingTime = recipeCreateView.cookingTimeTextField.getText();
            String preparation = recipeCreateView.preparationTextField.getText();

            if (recipeName.isEmpty() || cookingTime.isEmpty() || preparation.isEmpty()) {
                AlertUtils.showWarning("Warn","Please complete form！");
                return;
            }
            if (!cookingTime.matches("\\d+") || !preparation.matches("\\d+")) {
                AlertUtils.showWarning("Warn","Please input number！");
                return;
            }
            if (recipeCreateView.recipeImage.getImage() == null) {
                AlertUtils.showWarning("Warn", "Please upload image!");
                return;
            }
            Recipe recipe;
            Integer recipeId = 0;
            if(recipeCreateView.isEdited == false) {
                System.out.println("create");
                recipe = new Recipe(0,recipeCreateView.recipeNameTextField.getText(),1,Integer.parseInt(recipeCreateView.cookingTimeTextField.getText()),Integer.parseInt(recipeCreateView.preparationTextField.getText()),recipeCreateView.recipeImage.getImage().getUrl().replace("file:", ""));

                recipeId = model.addRecipe(recipe);
            }
            else if(recipeCreateView.isEdited == true){
                System.out.println("edit");
                recipe = new Recipe(recipeCreateView.editedRecipeId,recipeCreateView.recipeNameTextField.getText(),1,Integer.parseInt(recipeCreateView.cookingTimeTextField.getText()),Integer.parseInt(recipeCreateView.preparationTextField.getText()),recipeCreateView.recipeImage.getImage().getUrl().replace("file:", ""));
                recipeId = recipeCreateView.editedRecipeId;
                model.updateRecipe(recipe);
            }
            List<RecipeIngredient> updatedRecipeIngredients = new ArrayList<>();
            List<PreparationStep> updatedPreparationSteps = new ArrayList<>();
            for(RecipeIngredient recipeIngredient: recipeCreateView.tableView.getItems()){
                recipeIngredient.setRecipeId(recipeId);
                if(recipeIngredient.getName().isEmpty()){
                    AlertUtils.showWarning("Warn","Please input ingredient name！");
                    return;
                }
                if(recipeIngredient.getQuantity() == null || recipeIngredient.getQuantity() == 0.0){
                    AlertUtils.showWarning("Warn","Please input ingredient quantity ！");
                    return;
                }
                if(recipeIngredient.getUnit().isEmpty()){
                    AlertUtils.showWarning("Warn","Please ingredient unit！");
                    return;
                }
                if(recipeIngredient.getDescription().isEmpty()){
                    AlertUtils.showWarning("Warn","Please input ingredient description！");
                    return;
                }


                if(recipeCreateView.isEdited == false) {
                    model.addRecipeIngredient(recipeIngredient);
                }
                else if(recipeCreateView.isEdited == true){
                    updatedRecipeIngredients.add(recipeIngredient);

                }

            }
            if(recipeCreateView.isEdited == true){
                model.updateRecipeIngredient(recipeId,updatedRecipeIngredients);
            }


            for(PreparationStep preparationStep : recipeCreateView.instructionTableView.getItems()){
                preparationStep.setRecipeId(recipeId);

                if(recipeCreateView.isEdited == false) {
                    model.addRecipePreparationStep(preparationStep);
                }
                else if(recipeCreateView.isEdited == true){
                    updatedPreparationSteps.add(preparationStep);

                }
            }
            if(recipeCreateView.isEdited == true){
                model.updateRecipePreparationStep(recipeId, updatedPreparationSteps);
            }

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Recipe and ingredients added successfully!");
            successAlert.showAndWait();

        }


    }

    public class AlertUtils {
        public static void showWarning(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
    public String imageChoose(Stage stage){
        FileChooser fileChooser = new FileChooser();
        stage.setOpacity(0);
        Stage tempWindow = new Stage();
        File temp = fileChooser.showOpenDialog(tempWindow);
        stage.setOpacity(1);
        if (imageStore(temp)) {
            currentImagePath = temp.getAbsolutePath();
            return temp.getAbsolutePath();
        }
        return null;
    }

    private boolean imageStore(File file) {
        // Implement the logic to store the image if needed
        // Return true if the image was stored successfully, otherwise return false
        return file != null && file.exists();
    }
    private void handleAddButtonAction() {

        Tab selectedTab = recipeCreateView.tabPane.getSelectionModel().getSelectedItem();
        if(selectedTab.equals(recipeCreateView.ingredientsTab)){
            RecipeIngredient newIngredient = new RecipeIngredient(0,"",new Float(0.0),"",""); // Assuming default constructor creates an empty ingredient
            recipeCreateView.tableView.getItems().add(newIngredient);
        }
        else if(selectedTab.equals(recipeCreateView.instructionTab)){
//
            PreparationStep newInstruction = new PreparationStep(0, preparationStepNumber++,"");
            recipeCreateView.instructionTableView.getItems().add(newInstruction);
        }
    }

    private void handleDeleteButtonAction() {
        Tab selectedTab = recipeCreateView.tabPane.getSelectionModel().getSelectedItem();

        if (selectedTab.equals(recipeCreateView.ingredientsTab)) {
            int selectedIndex = recipeCreateView.tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                recipeCreateView.tableView.getItems().remove(selectedIndex);
            } else {
                model.displayAlert(Alert.AlertType.INFORMATION, "No rows selected", "Please select a row to delete.");
            }
        } else if (selectedTab.equals(recipeCreateView.instructionTab)) {
            int selectedIndex = recipeCreateView.instructionTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                recipeCreateView.instructionTableView.getItems().remove(selectedIndex);
            } else {
                model.displayAlert(Alert.AlertType.INFORMATION, "No rows selected", "Please select a row to delete.");
            }
        }
    }

    private void handleClearButtonAction() {
        recipeCreateView.recipeNameTextField.clear();
        recipeCreateView.preparationTextField.clear();
        recipeCreateView.cookingTimeTextField.clear();
        recipeCreateView.instructionTextArea.clear();
        recipeCreateView.tableView.getItems().clear();
        recipeCreateView.recipeImage.setImage(null);
    }
}
