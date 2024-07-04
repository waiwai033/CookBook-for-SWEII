package view;

import control.SignPageController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This view is for users to sign up.
 * @author Yan Yi
 */
public class SignUpView extends Stage {
    /**
     * TextField for input username.
     */
    public TextField newUserTextField,/**
     * TextField for input password.
     **/
    newPasswordTextField;
    /**
     * Button for create a new user.
     */
    public Button createUserButton;
    /**
     * Button for back to sign in page.
     */
    public Button createBackButton;

    private Label titleLabel;
    private ImageView imageView;
    /**
     * Constructor for SignUpView.
     */
    public SignUpView() {
        this.setTitle("Signup");
        this.setWidth(800);
        this.setHeight(600);
        this.setResizable(false);
        init();
    }

    /**
     * This method is to get username.
     * @return username
     */
    public TextField getNewUserTextField() {
        return newUserTextField;
    }

    /**
     * This method is to get password
     * @return password
     */
    public TextField getNewPasswordTextField() {
        return newPasswordTextField;
    }

    /**
     * This method is to get the stage.
     * @return Stage
     */
    public Stage getStage() {
        return (Stage) createUserButton.getScene().getWindow();
    }
    private void init() {
        AnchorPane background = new AnchorPane();
        background.setPrefSize(800, 600);
        setupImageBackground();


        AnchorPane frontground = new AnchorPane();
        frontground.setPrefSize(640, 400);
        frontground.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");

        Label userLabel = setUserTextField();
        Label passwordLabel = setPasswordField();
        setSignupButton();
        setBackButton();
        setTitleLabel();

        frontground.getChildren().addAll(userLabel, passwordLabel,titleLabel,createUserButton,createBackButton,newUserTextField,newPasswordTextField);
        background.getChildren().addAll(imageView,frontground);
        frontground.setLayoutY(100);
        frontground.setLayoutX(80);

        Scene scene = new Scene(background);
        this.setScene(scene);
    }

    private void setTitleLabel() {
        titleLabel = new Label("Create Your Account !");
        titleLabel.setFont(new Font("Comic Sans MS", 50));
        titleLabel.setLayoutX(60);
        titleLabel.setLayoutY(30);
    }

    private void setupImageBackground() {
        imageView = new ImageView(new Image("file:src/images/background/bg.png"));
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(false);
        imageView.setPickOnBounds(true);
    }

    private Label setPasswordField() {
        Label PasswordLabel = new Label("Password:");
        PasswordLabel.setFont(new Font("Times New Roman", 25));
        PasswordLabel.setLayoutX(120);
        PasswordLabel.setLayoutY(240);

        newPasswordTextField = new PasswordField();
        newPasswordTextField.setLayoutX(242);
        newPasswordTextField.setLayoutY(242);
        newPasswordTextField.setPrefSize(200,30);

        return PasswordLabel;
    }

    private Label setUserTextField() {
        Label UsernameLabel = new Label("Username:");
        UsernameLabel.setFont(new Font("Times New Roman", 25));
        UsernameLabel.setLayoutX(120);
        UsernameLabel.setLayoutY(150);

        newUserTextField = new TextField();
        newUserTextField.setLayoutX(245);
        newUserTextField.setLayoutY(152);
        newUserTextField.setPrefSize(200,30);

        return UsernameLabel;

    }
    private void setSignupButton() {
        createUserButton = new Button("Create");
        createUserButton.setOnAction(new SignPageController(this));
        createUserButton.setLayoutX(150);
        createUserButton.setLayoutY(320);
        createUserButton.setPrefSize(100,40);
    }
    private void setBackButton() {
        createBackButton = new Button("Already have account");
        createBackButton.setLayoutX(350);
        createBackButton.setLayoutY(320);
        createBackButton.setPrefSize(200,40);
        createBackButton.setOnAction(new SignPageController(this));
    }


}

