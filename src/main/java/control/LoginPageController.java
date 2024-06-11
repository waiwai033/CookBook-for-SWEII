package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import model.Model;
import view.signinView;
import view.signupView;
public class LoginPageController implements EventHandler<ActionEvent> {

    private signinView signinView;
    public LoginPageController(signinView signinView) {
        this.signinView = signinView;
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == signinView.signupButton) {
            // 创建新的 signView 实例并显示
            signupView signupView = new signupView();
            signupView.show();
        }
    }
}

