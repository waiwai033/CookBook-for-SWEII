package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Model;
import view.signinView;
import view.signupView;
public class LoginPageController implements EventHandler<ActionEvent> {

    private signinView signinView;
    private Model model;
    public LoginPageController(signinView signinView) {
        this.signinView = signinView;
        this.model = new Model();
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == signinView.signupButton) {
            // 创建新的 signView 实例并显示
            signupView signupView = new signupView();
            signupView.show();
        }
        if (event.getSource() == signinView.signinButton) {
            TextField passwordtext = signinView.getNewPasswordTextField();
            TextField usernametext = signinView.getNewUserTextField();
           System.out.println("Signup button pressed");

            String username = usernametext.getText();
            String password = passwordtext.getText();
            model.login(username, password);
        }
    }
}

