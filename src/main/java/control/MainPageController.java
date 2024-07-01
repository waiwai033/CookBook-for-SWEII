package control;
import config.SessionManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Model;
import view.*;

public class MainPageController implements EventHandler<ActionEvent> {

    private MainPageView mainPageView;
    private Model model;
    public MainPageController(MainPageView mainPageView) {
        this.mainPageView = mainPageView;
        this.model = new Model();
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == mainPageView.getVIP) {
            if(!model.userIsVip(SessionManager.getCurrentUserName())){
                VIPView vipView = new VIPView(v ->{
                    if(model.userIsVip(SessionManager.getCurrentUserName())){
                        model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are now vip");
                    }
                });
                vipView.show();
            }else {
                model.displayAlert(Alert.AlertType.INFORMATION,"Info.","You are already vip");
            }

        }
        else if(event.getSource() == mainPageView.chooseRecipe){
            recipeSelectView recipeSelectView = new recipeSelectView();
            recipeSelectView.show();
        }
        else if(event.getSource() == mainPageView.addRecipe){
            recipeCreateView recipeCreateView = new recipeCreateView();
            recipeCreateView.show();
        }

        }

}

