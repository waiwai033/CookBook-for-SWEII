package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Model;
import view.signupView;
public class SignPageController implements EventHandler<ActionEvent> {

    private signupView signupView;
    private Model model;
    public SignPageController(signupView signupView) {
        this.signupView = signupView;
        this.model = new Model();
    }
    public void handle(ActionEvent event) {
        if (event.getSource() == signupView.createUserButton) {
           TextField passwordtext = signupView.getNewPasswordTextField();
           TextField usernametext = signupView.getNewUserTextField();
//           System.out.println("Signup button pressed");

           String username = usernametext.getText();
           String password = passwordtext.getText();
           model.sign(username, password);


        }
    }
}

