package view;

import control.RecipeDisplayController;
import control.RecipeSelectController;
import dao.mappers.Recipe;
import dao.mappers.RecipeIngredient;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class recipeDisplayView extends Stage {
    public Button editRecipeButton,deleteRecipeButton,backButton,VIPbutton;

    public TextField serveNumberTextField;

    public Label recipeNameLabel=new Label("");
    public Label cookingTimeLabel,preparationTimeLabel,serveNumberLabel;

    public ImageView recipeImage;
    public String imageurl = "";

    private Tab ingredientsTab, instructionsTab;

    public TableView<RecipeIngredient> tableView;
    public TextArea instructionsTextArea;

    public Integer selectedRecipeNumber;

    public recipeDisplayView(Integer recipeNumber) {
//        this.selectedButton = selectedButton;

        System.out.print(recipeNumber);
        this.setTitle("");
        this.setResizable(false);
        this.setWidth(800);
        this.setHeight(600);
        this.selectedRecipeNumber = recipeNumber;
        init();
    }

    private void init() {
        AnchorPane background = new AnchorPane();
        background.setPrefSize(800,600);
        background.setStyle("-fx-background-color: #f6ef97;");

        RecipeDisplayController controller = new RecipeDisplayController(this);
        controller.initializeData();
//        String imageurl = "file:src/images/background/bg.png";
        setupImageView(imageurl);
        Pane imagePane = new Pane();
        imagePane.setStyle("-fx-background-color: #f8f5f5;-fx-background-radius: 10;");
        imagePane.setPrefSize(200,200);
        imagePane.setLayoutX(20);
        imagePane.setLayoutY(250);
        imagePane.setEffect(new DropShadow());
        imagePane.getChildren().add(recipeImage);

        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(500,350);
        tabPane.setLayoutX(260);
        tabPane.setLayoutY(150);
        setIngredientsTab();
        setInstructionTab();
        tabPane.setStyle("-fx-background-color: transparent;");
        tabPane.getTabs().addAll(ingredientsTab, instructionsTab);

        setRecipename();
        setCookingTime();
        setPreparationTime();
        setSeverNumber();


        background.getChildren().addAll(
                imagePane,
                tabPane,
                createHeader(),
                cookingTimeLabel,
                preparationTimeLabel,
                serveNumberLabel,
                serveNumberTextField);


        Scene scene = new Scene(background);
        this.setScene(scene);
    }

    private void setSeverNumber() {
        serveNumberLabel = new Label("Serve number:");
        serveNumberLabel.setLayoutX(260);
        serveNumberLabel.setLayoutY(100);
        serveNumberLabel.setFont(Font.font("System Bold Italic", 15));
        serveNumberTextField = new TextField();
        serveNumberTextField.setLayoutX(380);
        serveNumberTextField.setLayoutY(100);
        serveNumberTextField.setPrefSize(60, 20);
        serveNumberTextField.setAlignment(Pos.CENTER);
    }

    private void setPreparationTime() {
        preparationTimeLabel = new Label("Preparation Time: ");
        preparationTimeLabel.setFont(new Font("Times New Roman", 20));
        preparationTimeLabel.setLayoutX(20);
        preparationTimeLabel.setLayoutY(200);
    }

    private void setCookingTime() {
        cookingTimeLabel = new Label("Cooking Time:");
        cookingTimeLabel.setFont(new Font("Times New Roman", 20));
        cookingTimeLabel.setLayoutX(20);
        cookingTimeLabel.setLayoutY(160);
    }

    private void setRecipename() {
        recipeNameLabel.setFont(new Font("Comic Sans MS", 50));
        recipeNameLabel.setStyle("-fx-text-fill: #333;");
    }

    private HBox createHeader() {
        HBox headerBox = new HBox();
        headerBox.setPrefWidth(800);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().add(recipeNameLabel);
        headerBox.setLayoutY(10); // Adjust the Y position to your preference
        return headerBox;
    }

    private void setInstructionTab() {
        instructionsTab = new Tab("Instructions");
        AnchorPane instructionsPane = new AnchorPane();
        instructionsTextArea = new TextArea();
        instructionsTextArea.setEditable(true);
        instructionsTextArea.setWrapText(true);
        instructionsTextArea.setPrefSize(500,350);
        instructionsTextArea.setLayoutX(0);
        instructionsTextArea.setLayoutY(0);
        instructionsPane.getChildren().add(instructionsTextArea);
        instructionsTab.setContent(instructionsPane);
        instructionsPane.setStyle("-fx-background-color: transparent;");
    }

    private void setIngredientsTab() {
        ingredientsTab = new Tab("Ingredients");
        tableView = new TableView<>();
        tableView.setLayoutX(0);
        tableView.setLayoutY(0);
        tableView.setPrefSize(500,350);
        tableView.setEffect(new DropShadow());

        TableColumn<RecipeIngredient, String> ingredientsColumn = new TableColumn<>("Ingredient name");
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<RecipeIngredient,String> quantityColumn = new TableColumn<>("quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<RecipeIngredient,String> unitsColumn = new TableColumn<>("units");
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("units"));
        TableColumn<RecipeIngredient,String> descriptionColumn = new TableColumn<>("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableView.getColumns().addAll(ingredientsColumn,quantityColumn,unitsColumn,descriptionColumn);

        AnchorPane ingredientsPane = new AnchorPane();
        ingredientsPane.setStyle("-fx-background-color: transparent;");
        ingredientsPane.setPrefSize(500,350);
        ingredientsPane.setLayoutX(0);
        ingredientsPane.setLayoutY(0);
        ingredientsPane.getChildren().add(tableView);

        ingredientsTab.setContent(ingredientsPane);
    }


    private void setupImageView(String url) {
        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        recipeImage = imageView;
        recipeImage.setLayoutX(0);
        recipeImage.setLayoutY(0);
    }


}

