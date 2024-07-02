package control;


import dao.mappers.Recipe;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class recipeCreateController implements EventHandler<ActionEvent> {

    private recipeCreateView recipeCreateView;
    private Model model;
    private String currentImagePath;
    public static String imageUrl = "";
    public recipeCreateController(recipeCreateView recipeCreateView) {
        this.recipeCreateView = recipeCreateView;
        this.model = new Model();
    }
    private static String getFileExtension(String filename) {
        if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return ""; // 没有扩展名的情况
        }
    }
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == recipeCreateView.uploadButton){
            String temp = imageChoose(recipeCreateView);
            String projectPath = System.getProperty("user.dir");
            String targetPath = projectPath + "/src/images/dishes";
            System.out.println(targetPath);
            imageUrl = temp;
            String fileExtension = getFileExtension(temp);
            System.out.println(fileExtension);

            long timestamp = System.currentTimeMillis();
            System.out.println(timestamp);
            String newFileName = timestamp + "." + fileExtension;
//            Path fullPath = Paths.get(targetPath+"/005.jpeg");
            Path fullPath = Paths.get(targetPath).resolve(newFileName);
            System.out.println(fullPath.toString());
            Path imagePath = Paths.get(imageUrl);
            try {
                // 使用 Files.copy 方法复制文件
                Files.copy(imagePath,fullPath);
                System.out.println("文件复制成功");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("复制文件时发生错误: " + e.getMessage());
            }

            if (fullPath.toString() != null){
//                System.out.println(temp);
                recipeCreateView.updateImage(fullPath.toString());
            }
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
        RecipeIngredient newIngredient = new RecipeIngredient(0,"",new Float(0.0),"",""); // Assuming default constructor creates an empty ingredient
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
