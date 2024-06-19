package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
           System.out.print(username);
           if(username == "" || password == ""){
               System.out.println("Username and password are null!");
           }
           else if(model.sign(username, password)){
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("Sign Up");
               alert.setHeaderText(null);
               alert.setContentText("Successfully signed up!");

               alert.showAndWait().ifPresent(response -> {
                   // 获取当前的Stage并关闭
                   Stage stage = (Stage) signupView.getStage();
                   stage.close();
               });
           }
           else{
               Model.displayAlert(Alert.AlertType.ERROR,"error","Already have same name!");
           }


        }
    }
}

