package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import view.VIPView;
import config.SessionManager;
public class VIPViewController implements EventHandler<ActionEvent> {

    private VIPView vipView;
    private Model model;

    public VIPViewController(VIPView view) {
        this.vipView = view;
        this.model = new Model();
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == vipView.button1) {
            System.out.print(111);
            model.setVIP(SessionManager.getCurrentUserName());

        }
        if (event.getSource() == vipView.button2) {
            System.out.print(111);

        }
    }
}

