package view;

import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import control.LoginPageController;

public class SignInView extends Stage {

    public Button signinButton;
    public Button signupButton;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public Label titleLabel;
    public SignInView() {
        this.setTitle("Sign In");
        this.setWidth(800);
        this.setHeight(600);
        this.setResizable(false);
        start();
    }


    public TextField getNewUserTextField() {
        return usernameTextField;
    }
    public TextField getNewPasswordTextField() {
        return passwordTextField;
    }
    private ImageView createImageView(String imagepath,double LayoutX,double LayoutY,double width,double height) {
        Image image = new Image(imagepath);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(LayoutX);
        imageView.setLayoutY(LayoutY);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(false);
        return imageView;
    }
    private void startAnimation(ImageView imageView, double toX, double toY) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(50), imageView);
        transition.setFromX(0);
        transition.setToX(toX);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }


    public void start() {
        AnchorPane backPane = new AnchorPane();
        backPane.setPrefSize(800, 600);
        double width = backPane.getPrefWidth() / 2;
        double height = backPane.getPrefHeight() / 2;
//        add image
        ImageView imageView1 = createImageView("file:src/images/dishes/001.png", -100, 0, width+50, height);
        ImageView imageView1a = createImageView("file:src/images/dishes/001.png", 2*width-100, 0, width+50, height);
        ImageView imageView2 = createImageView("file:src/images/dishes/002.png", width-100, 0, width+50, height);
        ImageView imageView3 = createImageView("file:src/images/dishes/003.png", 100, height, width+50, height);
        ImageView imageView4 = createImageView("file:src/images/dishes/004.png", width+100, height, width+50, height);
        ImageView imageView4a = createImageView("file:src/images/dishes/004.png",    50-width, height, width+50, height);

        backPane.getChildren().addAll(imageView1a,imageView1, imageView2, imageView3, imageView4,imageView4a);
        // start anime
        startAnimation(imageView1a, width/4, 0);
        startAnimation(imageView1, width/4, 0);
        startAnimation(imageView2, width/4, 0);
        startAnimation(imageView3, -width/4, 0);
        startAnimation(imageView4, -width/4, 0);
        startAnimation(imageView4a, -width/4, 0);



        AnchorPane frontPane = new AnchorPane();
        frontPane.setPrefSize(600, 400);
        frontPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");


        Label usernameLabel = setUsernameTextField();
        Label passwordLabel = setPasswordTextField();
        setTitleLabel();

        setSigninButton();
        setSignupButton();

        frontPane.getChildren().addAll(signinButton, signupButton, usernameLabel, passwordLabel,usernameTextField,passwordTextField,titleLabel);

        backPane.getChildren().add(frontPane);
        frontPane.setLayoutX(100);
        frontPane.setLayoutY(80);
        Scene scene = new Scene(backPane);
        this.setScene(scene);

    }

    private void setTitleLabel() {
        titleLabel = new Label("Please Login In");
        titleLabel.setFont(new Font("Comic Sans MS", 50));
        titleLabel.setLayoutX(115);
        titleLabel.setLayoutY(30);
    }

    public void setSigninButton() {
        signinButton = new Button("Sign In");
        signinButton.setOnAction(new LoginPageController(this));
        signinButton.setLayoutX(150);
        signinButton.setLayoutY(320);
        signinButton.setPrefSize(100,40);
    }

    public void setSignupButton() {
        signupButton = new Button("Sign up");
        signupButton.setOnAction(new LoginPageController(this));
        signupButton.setLayoutX(350);
        signupButton.setLayoutY(320);
        signupButton.setPrefSize(100,40);
    }

    public Label setUsernameTextField() {
        Label UsernameLabel = new Label("Username:");
        UsernameLabel.setFont(new Font("Times New Roman", 25));
        UsernameLabel.setLayoutX(120);
        UsernameLabel.setLayoutY(150);

        usernameTextField = new TextField();
        usernameTextField.setLayoutX(245);
        usernameTextField.setLayoutY(152);
        usernameTextField.setPrefSize(200,30);

        return UsernameLabel;
    }

    public Label setPasswordTextField() {
        Label PasswordLabel = new Label("Password:");
        PasswordLabel.setFont(new Font("Times New Roman", 25));
        PasswordLabel.setLayoutX(120);
        PasswordLabel.setLayoutY(240);

        passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(242);
        passwordTextField.setLayoutY(242);
        passwordTextField.setPrefSize(200,30);

        return PasswordLabel;
    }

}

