package control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import view.SignUpView;

/**
 * Controller for handling actions on the sign-up page.
 *
 * @author He Chenyi, Yan Yi
 */
public class SignPageController implements EventHandler<ActionEvent> {

    private final SignUpView signupView;
    private final Model model;
    /**
     * Constructor to initialize the controller with the SignUpView.
     *
     * @param signupView The SignUpView instance to be associated with this controller.
     */
    public SignPageController(SignUpView signupView) {
        this.signupView = signupView;
        this.model = new Model();
    }
    /**
     * Handles actions performed in the SignUpView.
     *
     * @param event The ActionEvent representing the user's action.
     */
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == signupView.createUserButton) {
           TextField passwordText = signupView.getNewPasswordTextField();
           TextField usernameText = signupView.getNewUserTextField();
           String username = usernameText.getText();
           String password = passwordText.getText();
           // Check if username or password fields are empty
           if(username == "" ){
               Model.displayAlert(Alert.AlertType.ERROR,"error","Please input username!");
           }
           else if(password == "" ){
               Model.displayAlert(Alert.AlertType.ERROR,"error","Please input password!");
           }
           else if(model.sign(username, password)){
               // Successful sign up
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("Sign Up");
               alert.setHeaderText(null);
               alert.setContentText("Successfully signed up!");
               // Close the SignUpView upon successful sign up
               alert.showAndWait().ifPresent(response -> {
                   Stage stage = (Stage) signupView.getStage();
                   stage.close();
               });
           }
           else{
               passwordText.clear();
               usernameText.clear();

           }
        } else if (event.getSource() == signupView.createBackButton) {
            // Handle back button click
            signupView.close();
        }
    }
}

