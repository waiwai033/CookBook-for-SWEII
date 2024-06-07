package CookBookView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class signinView extends Stage {

    public Button signinButton;
    public Button signupButton;
    public TextField usernameTextField;
    public TextField passwordTextField;

    public signinView() {
        this.setTitle("Sign In");
        this.setWidth(800);
        this.setHeight(600);
        this.setResizable(false);
        start();
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
        frontPane.setPrefSize(700, 500);
        frontPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");


        Label usernameLabel = setUsernameTextField();
        Label passwordLabel = setPasswordTextField();

        setSigninButton();
        setSignupButton();

        frontPane.getChildren().addAll(signinButton, signupButton, usernameLabel, passwordLabel,usernameTextField,passwordTextField);

        backPane.getChildren().add(frontPane);
        frontPane.setLayoutX(50);
        frontPane.setLayoutY(50);
        Scene scene = new Scene(backPane);
        this.setScene(scene);

    }

    public void setSigninButton() {
        signinButton = new Button("Sign In");
        signinButton.setLayoutX(150);
        signinButton.setLayoutY(350);
        signinButton.setPrefSize(100,40);
    }

    public void setSignupButton() {
        signupButton = new Button("Sign Up");
        signupButton.setLayoutX(350);
        signupButton.setLayoutY(350);
        signupButton.setPrefSize(100,40);
    }

    public Label setUsernameTextField() {
        Label UsernameLabel = new Label("Username:");
        UsernameLabel.setLayoutX(100);
        UsernameLabel.setLayoutY(100);

        usernameTextField = new TextField();
        usernameTextField.setLayoutX(180);
        usernameTextField.setLayoutY(100);

        return UsernameLabel;
    }

    public Label setPasswordTextField() {
        Label PasswordLabel = new Label("Password:");
        PasswordLabel.setLayoutX(100);
        PasswordLabel.setLayoutY(300);

        passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(180);
        passwordTextField.setLayoutY(300);

        return PasswordLabel;
    }
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}

