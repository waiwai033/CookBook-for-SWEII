package control;
import dao.mappers.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Button;
import view.*;
import config.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
        List<Recipe> allRecipes = model.getAllRecipes();
        recipeSelectView.imageUrls =  new LinkedHashMap<>();;
        recipeSelectView.imageNames = new ArrayList<>();
        for(Recipe recipe : allRecipes) {
            recipeSelectView.imageNames.add(recipe.getRecipeName());

            recipeSelectView.imageUrls.put( recipe.getRecipeId(),recipe.getImageUrl());
            System.out.println(recipe.getRecipeName());
            System.out.println(recipe.getImageUrl());
        }
//        recipeSelectView.imageUrls = model.getImageUrls();
//        recipeSelectView.imageNames = model.getImageNames();

    }
    @Override
    public void handle(ActionEvent actionEvent) {

        for(Button button : recipeSelectView.buttonMap.keySet()){
            if (actionEvent.getSource() == button){
                recipeSelectView.close();
//                recipeDisplayView view = new recipeDisplayView(recipeSelectView.buttonMap.get(button));
                Integer recipeNumber = recipeSelectView.buttonMap.get(button);
                AdvertiseView advertiseView = new AdvertiseView();
                advertiseView.setOnEndOfMedia(() -> {
                    advertiseView.close();
                    // 展示 RecipeDisplayView
                    recipeDisplayView view = new recipeDisplayView(recipeNumber);
                    view.show();
                });

                // 设置跳过按钮事件处理
                advertiseView.setOnSkipButton(event -> {
                    if(!model.userIsVip(SessionManager.getCurrentUserName())){
                        vipView = new VIPView(v -> {
                            advertiseView.mediaPlayer.stop();
                            advertiseView.close();
                            recipeDisplayView view = new recipeDisplayView(recipeNumber);
                            view.show();
                        });
                        vipView.show();
                    }else{
                    advertiseView.mediaPlayer.stop();
                    advertiseView.close();
                    // 展示 RecipeDisplayView
                    recipeDisplayView view = new recipeDisplayView(recipeNumber);
                    view.show();
                    }
                });

                advertiseView.show();
//                System.out.println(recipeNumber);
//                view.show();
            }
        }
        if (actionEvent.getSource() == recipeSelectView.vipButton) {
            if(!model.userIsVip(SessionManager.getCurrentUserName())){
                VIPView vipView = new VIPView(v ->{
                    if (model.userIsVip(SessionManager.getCurrentUserName())){
                        model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are now vip");
                    }
                });
                vipView.show();
            }else {
                model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are already vip");
            }

        }
        else if(actionEvent.getSource() == recipeSelectView.searchButton){
            String recipeName = recipeSelectView.searchField.textProperty().getValue();
            recipeSelectView.update(model.updateImageUrls(recipeName), model.updateImageNames(recipeName));
//            System.out.println(recipeSelectView.imageUrls);

        }
        else if(actionEvent.getSource() == recipeSelectView.backButton){
            recipeSelectView.close();
        }

    }


}
