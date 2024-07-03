package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import model.Model;
import view.SignInView;
import view.SignUpView;
import view.MainPageView;

/**
 * Controller for handling actions on the login page.
 *
 * @author hechenyi
 */
public class LoginPageController implements EventHandler<ActionEvent> {

    private SignInView signinView;
    private Model model;

    /**
     * Constructs a LoginPageController with a SignInView instance.
     *
     * @param signinView The SignInView associated with this controller.
     */
    public LoginPageController(SignInView signinView) {
        this.signinView = signinView;
        this.model = new Model();
    }

    /**
     * Handles button click events from the SignInView.
     *
     * @param event The ActionEvent representing the button click.
     */
    @Override
    public void handle(ActionEvent event) {
        // Handle sign up button click
        if (event.getSource() == signinView.signupButton) {
            SignUpView signupView = new SignUpView();
            signupView.show();
        }
        // Handle sign in button click
        if (event.getSource() == signinView.signinButton) {
            // Retrieve username and password from text fields
            TextField passwordText = signinView.getNewPasswordTextField();
            TextField usernameText = signinView.getNewUserTextField();
            String username = usernameText.getText();
            String password = passwordText.getText();

            // Attempt to log in using the model
            if (model.login(username, password)) {
                // If login successful, show the main page view
                MainPageView mainPageView = new MainPageView();
                mainPageView.show();
            }
        }
    }
}
