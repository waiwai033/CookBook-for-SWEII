package control;


import dao.mappers.RecipeIngredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import view.recipeCreateView;

import java.io.File;

public class recipeCreateController implements EventHandler<ActionEvent> {


    private recipeCreateView recipeCreateView;
    private Model model;
    private String currentImagePath;

    public recipeCreateController(recipeCreateView recipeCreateView) {
        this.recipeCreateView = recipeCreateView;
        this.model = new Model();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == recipeCreateView.uploadButton){
            String temp = imageChoose(recipeCreateView);
            if (temp != null){
                System.out.println(temp);
                recipeCreateView.updateImage(temp);
            }
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
        RecipeIngredient newIngredient = new RecipeIngredient(0,"name",0.0f,"unit","des"); // Assuming default constructor creates an empty ingredient
        recipeCreateView.tableView.getItems().add(newIngredient);
    }

    private void handleDeleteButtonAction() {
        if (!recipeCreateView.tableView.getItems().isEmpty()) {
            recipeCreateView.tableView.getItems().remove(0);
        } else {
            model.displayAlert(Alert.AlertType.INFORMATION,"No rows to delete", "There are no rows to delete from the table.");
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
