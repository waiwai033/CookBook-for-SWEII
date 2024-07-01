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

import java.util.function.Consumer;

public class VIPViewController implements EventHandler<ActionEvent> {

    private VIPView vipView;
    private Model model;
    private Consumer<Void> onBecomeVip;

    public VIPViewController(VIPView view,Consumer<Void> onBecomeVip) {
        this.vipView = view;
        this.model = new Model();
        this.onBecomeVip = onBecomeVip;
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == vipView.button1) {
            System.out.print(111);
            model.setVIP(SessionManager.getCurrentUserName());
            model.displayAlert(Alert.AlertType.INFORMATION,"Info.","Successfully join VIP!");
            vipView.close();
            if(onBecomeVip != null){
                onBecomeVip.accept(null);
            }

        }
        if (event.getSource() == vipView.button2) {
            System.out.print(111);
            vipView.close();

        }
    }
}

