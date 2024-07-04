package view;

import control.RecipeSelectController;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * This class represents the view for recipe selection.
 * @author Yan Yi
 */
public class RecipeSelectView extends Stage {
    /**
     * The search field for searching recipes.
     */
    public TextField searchField;
    /**
     * The search button for searching recipes.
     */
    public Button searchButton;
    /**
     * The VIP button for getting VIP
     */
    public Button vipButton;
    /**
     * The back button for going back to the main menu.
     */
    public Button backButton;
    /**
     * The next button for navigating to the next page of recipes.
     */
    public Button nextButton;
    /**
     * The previous button for navigating to the previous page of recipes.
     */
    public Button prevButton;
    /**
     * The list of recipe button images on the current page.
     */
    public LinkedHashMap<Integer, String> imageUrls = new LinkedHashMap<>();
    /**
     * The list of recipe button names on the current page.
     */
    public List<String> imageNames;
    /**
     * The list of recipe button on the current page.
     */
    public HashMap<Button, Integer> buttonMap = new HashMap<>();
    private Label titleLabel;
    private int currentPage = 0;
    private static final int ITEMS_PER_PAGE = 3;
    private List<Button> buttonList = new ArrayList<>();

    /**
     * Constructor for the RecipeSelectView class.
     *
     */
    public RecipeSelectView() {
        this.setWidth(800);
        this.setHeight(600);
        this.setResizable(false);
        init();
    }
    private void init(){
        // Load initial data
        RecipeSelectController controller = new RecipeSelectController(this);
        controller.initializeData();
        AnchorPane background = new AnchorPane();
        background.setPrefSize(800, 600);
        background.setStyle("-fx-background-color: #f6ef97;");
        Pane pane = new Pane();

        setRecipeButtons(pane, currentPage);
        setNextButton(pane);
        setPreButton(pane);
        setSearchField();
        setSearchButton();
        setBackButton();
        setVIPButton();
        setTitleLabel();
        background.getChildren().addAll(pane, nextButton, prevButton,searchField,searchButton,vipButton,backButton,titleLabel);
        Scene scene = new Scene(background);
        this.setScene(scene);
    }

    private void setTitleLabel() {
        titleLabel = new Label("Choose a recipe");
        titleLabel.setFont(new Font("Comic Sans MS", 50));
        titleLabel.setLayoutX(200);
        titleLabel.setLayoutY(10);

    }

    public void update(LinkedHashMap<Integer, String>  _imageUrls, ArrayList<String> _imageNames){
        imageUrls = _imageUrls;
        imageNames = _imageNames;
        AnchorPane background = new AnchorPane();
        background.setPrefSize(800, 600);
        background.setStyle("-fx-background-color: #f6ef97;");
        Pane pane = new Pane();

        setRecipeButtons(pane, currentPage);
        setNextButton(pane);
        setPreButton(pane);
        setSearchField();
        setSearchButton();
        setBackButton();
        setVIPButton();
        background.getChildren().addAll(pane, nextButton, prevButton,searchField,searchButton,vipButton,backButton);
        Scene scene = new Scene(background);
        this.setScene(scene);
    }

    private void setVIPButton() {
        vipButton = new Button("GetVIP!");
        vipButton.setOnAction(new RecipeSelectController(this));
        vipButton.setLayoutX(700);
        vipButton.setLayoutY(20);
    }

    private void setBackButton() {
        backButton = new Button("Back");
        backButton.setOnAction(new RecipeSelectController(this));
        backButton.setLayoutX(20);
        backButton.setLayoutY(500);
    }

    private void setSearchButton() {
        searchButton = new Button("->");
        searchButton.setOnAction(new RecipeSelectController(this));
        searchButton.setLayoutX(620);
        searchButton.setLayoutY(100);
        searchButton.setPrefSize(40,40);
    }

    private void setNextButton(Pane pane) {
        nextButton = new Button(">");
        nextButton.setLayoutX(500);
        nextButton.setLayoutY(450);
        nextButton.setOnAction(e -> {
            if ((currentPage + 1) * ITEMS_PER_PAGE < imageUrls.size()) {
                currentPage++;
                setRecipeButtons(pane, currentPage);
            }
        });
    }

    private void setPreButton(Pane pane) {
        prevButton = new Button("<");
        prevButton.setLayoutX(300);
        prevButton.setLayoutY(450);
        prevButton.setOnAction(e -> {
            if (currentPage > 0) {
                currentPage--;
                setRecipeButtons(pane, currentPage);
            }
        });
    }

    private void setRecipeButtons(Pane pane, int page) {
        pane.getChildren().clear();
        int start = page * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, imageUrls.size());
        for (int i = start; i < end; i++) {
            // 获取第 i 对的键值对
            HashMap.Entry< Integer,String> entry = new ArrayList<>(imageUrls.entrySet()).get(i);
            String url = entry.getValue(); // 获取 url
            String imageName = imageNames.get(i);
            File imageUrl = new File(url);
            if (!imageUrl.exists()) {
                System.out.println("not found " + url);
                continue;
            }

            Image recipeImage;
            try {
                recipeImage = new Image(new FileInputStream(imageUrl));
            } catch (FileNotFoundException e) {
                System.out.println("not found: " + e.getMessage());
                continue;
            }

            VBox recipeButton = createButtonWithImage(entry.getKey(), recipeImage, imageName, 50 + (i - start) * 250, 200);
            pane.getChildren().add(recipeButton);
        }
    }

    private VBox createButtonWithImage(Integer recipeNumber, Image recipeImage, String imageName, int x, int y) {
        Button recipeButton = new Button();
        recipeButton.setOnAction(new RecipeSelectController(this));
        buttonList.add(recipeButton);
        ImageView imageView = new ImageView(recipeImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        recipeButton.setGraphic(imageView);
        Label label = new Label(imageName);
        buttonMap.put(recipeButton, recipeNumber);
        VBox vbox = new VBox(recipeButton, label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setLayoutX(x);
        vbox.setLayoutY(y);
        vbox.setSpacing(10); // Add some spacing between button and label
        return vbox;
    }

    private void setSearchField() {
        searchField = new TextField();
        searchField.setPrefHeight(40.0);
        searchField.setPrefWidth(400.0);
        searchField.setText("Search");
        searchField.setLayoutX(200);
        searchField.setLayoutY(100);
        searchField.setEditable(true);

//        background.getChildren().add(stackPane);
    }
}
