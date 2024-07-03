package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.Model;
import view.VIPView;
import config.SessionManager;
import java.util.function.Consumer;

/**
 * Controller for handling actions on the vip page.
 *
 * @author He Chenyi, Yan Yi
 */
public class VIPViewController implements EventHandler<ActionEvent> {

    private final VIPView vipView;
    private final Model model;
    private final Consumer<Void> onBecomeVip;

    /**
     * Constructor to initialize the controller with the VIPView and callback function.
     *
     * @param view The VIPView instance to be associated with this controller.
     * @param onBecomeVip Callback function to execute upon becoming a VIP.
     */
    public VIPViewController(VIPView view,Consumer<Void> onBecomeVip) {
        this.vipView = view;
        this.model = new Model();
        this.onBecomeVip = onBecomeVip;
    }
    /**
     * Handles button click events on the VIPView.
     *
     * @param event The ActionEvent representing the user's action.
     */
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == vipView.button1) {
            System.out.print(111);
            model.setVIP(SessionManager.getCurrentUserName());
            Model.displayAlert(Alert.AlertType.INFORMATION,"Info.","Successfully join VIP!");
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

