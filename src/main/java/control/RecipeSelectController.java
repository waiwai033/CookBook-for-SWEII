package control;
import dao.mappers.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Button;
import view.*;
import config.SessionManager;

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

        for(Button button : recipeSelectView.buttonMap.keySet()){
            if (actionEvent.getSource() == button){

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
                        vipView = new VIPView();
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
            VIPView vipView = new VIPView();
            vipView.show();
        }
        else if(actionEvent.getSource() == recipeSelectView.searchButton){
            String recipeName = recipeSelectView.searchField.textProperty().getValue();
            recipeSelectView.update(model.updateImageUrls(recipeName), model.updateImageNames(recipeName));
//            System.out.println(recipeSelectView.imageUrls);

        }

    }


}
