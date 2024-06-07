package CookBookView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class signupView extends Stage {
    public TextField newUserTextField ,newPasswordTextField;

    public ImageView imageView;

    public Button createUserButton;

    public Button createVIPButton;
    public signupView() {
        this.setTitle("Signup");
        this.setWidth(800);
        this.setHeight(600);
        this.setResizable(false);
        init();
    }
    public void init() {
        AnchorPane background = new AnchorPane();
        background.setPrefSize(800, 600);
        setupImageBackground();


        AnchorPane frontground = new AnchorPane();
        frontground.setPrefSize(700, 500);
        frontground.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");

        Label userLabel = setUserTextField();
        Label passwordLabel = setPasswordField();
        setSignupButton();
        setVIPButton();

        frontground.getChildren().addAll(userLabel, passwordLabel, createUserButton, createVIPButton,newUserTextField,newPasswordTextField);
        background.getChildren().addAll(imageView,frontground);
        frontground.setLayoutY(50);
        frontground.setLayoutX(50);

        Scene scene = new Scene(background);
        this.setScene(scene);
    }

    private void setupImageBackground() {
        imageView = new ImageView(new Image("file:src/images/background/bg.png"));
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(false);
        imageView.setPickOnBounds(true);
    }

    private Label setPasswordField() {
        Label UsernameLabel = new Label("Username:");
        UsernameLabel.setLayoutX(100);
        UsernameLabel.setLayoutY(100);
        newUserTextField = new TextField();
        newUserTextField.setLayoutX(180);
        newUserTextField.setLayoutY(100);

        return UsernameLabel;
    }

    private Label setUserTextField() {
        Label PasswordLabel = new Label("Password:");
        PasswordLabel.setLayoutX(100);
        PasswordLabel.setLayoutY(300);

        newPasswordTextField = new PasswordField();
        newPasswordTextField.setLayoutX(180);
        newPasswordTextField.setLayoutY(300);

        return PasswordLabel;
    }
    public void setSignupButton() {
        createUserButton = new Button("Create");
        createUserButton.setLayoutX(150);
        createUserButton.setLayoutY(350);
        createUserButton.setPrefSize(100,40);
    }
    public void setVIPButton() {
        createVIPButton = new Button("Get our VIP");
        createVIPButton.setLayoutX(350);
        createVIPButton.setLayoutY(350);
        createVIPButton.setPrefSize(100,40);
    }
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

}

