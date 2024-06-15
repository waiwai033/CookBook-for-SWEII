package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Model;
import view.signupView;
import view.MainPageView;
import view.VIPView;
public class MainPageController implements EventHandler<ActionEvent> {

    private MainPageView mainPageView;
    private Model model;
    public MainPageController(MainPageView mainPageView) {
        this.mainPageView = mainPageView;
        this.model = new Model();
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == mainPageView.getVIP) {
            VIPView vipView = new VIPView();
            vipView.show();


        }
    }
}

