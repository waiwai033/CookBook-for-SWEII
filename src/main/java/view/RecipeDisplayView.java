package view;

import control.RecipeDisplayController;
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
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import model.Model;

/**
 * This view is used to display a recipe.
 *
 * @author Yan Yi
 */
public class RecipeDisplayView extends Stage {
    /**
     *  Button to edit recipe
     */
    public Button editRecipeButton,/**
     *  Button to delete recipe
     **/
    deleteRecipeButton,/**
     *  Button to turn back
     **/
    backButton;
    /**
     *  List to display ingredients
     */
    public ObservableList<RecipeIngredient> selectedIngredients = FXCollections.observableArrayList();
    /**
     *  Label to display recipe name
     */
    public Label recipeNameLabel=new Label("");
    /**
     *  Label to display cooking time
     */
    public Label cookingTimeLabel = new Label();
    /**
     *  Label to display preparation time
     */
    public Label preparationTimeLabel = new Label();
    /**
     *  TextField to change serving number
     */
    public TextField serveNumberTextField = new TextField("dsd");
    /**
     *  String to store imageurl
     */
    public String imageurl = "";
    /**
     *  TextArea to display instructions
     */
    public TextArea instructionsTextArea = new TextArea();;
    /**
     *  Integer to store selected recipe number
     */
    public Integer selectedRecipeNumber;
    private Tab ingredientsTab, instructionsTab;
    private ImageView recipeImage;
    private Label serveNumberLabel;

    /**
     *  Constructor of RecipeDisplayView
     * @param recipeNumber
     */
    public RecipeDisplayView(Integer recipeNumber) {
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
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-background-color: transparent;");
        tabPane.getTabs().addAll(ingredientsTab, instructionsTab);

        setRecipename();
        setCookingTime();
        setPreparationTime();
        setSeverNumber();
        setBackButton();
        setEditButton();
        setDeleteButton();


        background.getChildren().addAll(
                imagePane,
                tabPane,
                createHeader(),
                cookingTimeLabel,
                preparationTimeLabel,
                serveNumberLabel,
                serveNumberTextField,
                backButton,
                editRecipeButton,
                deleteRecipeButton
        );


        Scene scene = new Scene(background);
        this.setScene(scene);
    }

    private void setDeleteButton() {
        deleteRecipeButton = new Button("Delete Recipe");
        deleteRecipeButton.setLayoutX(520);
        deleteRecipeButton.setLayoutY(510);
        deleteRecipeButton.setPrefSize(200,40);
        deleteRecipeButton.setOnAction(new RecipeDisplayController(this));
    }

    private void setEditButton() {
        editRecipeButton = new Button("Edit Recipe");
        editRecipeButton.setLayoutX(280);
        editRecipeButton.setLayoutY(510);
        editRecipeButton.setPrefSize(200,40);
        editRecipeButton.setOnAction(new RecipeDisplayController(this));
    }

    private void setBackButton() {
        backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(500);
        backButton.setPrefSize(60,40);
        backButton.setOnAction(new RecipeDisplayController(this));
    }

    private void setSeverNumber() {
        serveNumberLabel = new Label("Serve number:");
        serveNumberLabel.setLayoutX(260);
        serveNumberLabel.setLayoutY(100);
        serveNumberLabel.setFont(Font.font("System Bold Italic", 15));
        serveNumberTextField.setTextFormatter(Model.textFieldFormatter(3));
        serveNumberTextField.setLayoutX(380);
        serveNumberTextField.setLayoutY(100);
        serveNumberTextField.setPrefSize(60, 20);
        serveNumberTextField.setAlignment(Pos.CENTER);
    }

    private void setPreparationTime() {
        preparationTimeLabel.setFont(new Font("Times New Roman", 20));
        preparationTimeLabel.setLayoutX(20);
        preparationTimeLabel.setLayoutY(200);
    }

    private void setCookingTime() {

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
        TableView<RecipeIngredient> tableView = new TableView<>();
        tableView.setLayoutX(0);
        tableView.setLayoutY(0);
        tableView.setPrefSize(500,350);
        tableView.setEffect(new DropShadow());



        TableColumn<RecipeIngredient, String> nameColumn = new TableColumn<>("Ingredient name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<RecipeIngredient, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<RecipeIngredient, String> unitsColumn = new TableColumn<>("Units");
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

        TableColumn<RecipeIngredient, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll( nameColumn, quantityColumn, unitsColumn, descriptionColumn);


        tableView.setItems(selectedIngredients);

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

