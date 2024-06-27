package view;

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

public class recipeCreateView extends Stage {

    public Button submitButton,backButton,uploadButton,addButton,deleteButton,clearButton;
    public TextField recipeNameTextField,preparationTextField, cookingTimeTextField,servenumberTextField;
    public ImageView recipeImage;
    public String imageurl = "";
    public TableView<RecipeIngredient> tableView;
    public TextArea instructionTextArea = new TextArea();
    private Label cookingTimeLabel,preparationTimeLabel,serveNumberLabel;
    private Tab ingredientsTab, instructionTab;

    public recipeCreateView() {
        this.setTitle("");
        this.setResizable(false);
        this.setWidth(800);
        this.setHeight(600);
        init();
    }
    public void init() {
        AnchorPane background = new AnchorPane();
        background.setPrefSize(800,600);
        background.setStyle("-fx-background-color: #f6ef97;");

        setupImage(imageurl);
        Pane imagePane = new Pane();
        imagePane.setPrefSize(200,200);
        imagePane.setStyle("-fx-background-color: #f8f5f5;-fx-background-radius: 10;");
        imagePane.setLayoutX(20);
        imagePane.setLayoutY(250);
        imagePane.setEffect(new DropShadow());
        if (recipeImage != null) {
            imagePane.getChildren().add(recipeImage);
        }

        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(450,350);
        tabPane.setLayoutX(300);
        tabPane.setLayoutY(150);
        setIngredientTab();
        setInstructionTab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-background-color: transparent;");
        tabPane.getTabs().addAll(ingredientsTab,instructionTab);

        setRecipename();
        setCookingTime();
        setPrepartionTime();
        setServenumber();
        setUploadButton();
        setSubminButtton();
        setAddButton();
        setDeleteButton();
        setBackButton();
        setClearButton();



        background.getChildren().addAll(
                imagePane,
                tabPane,
                createHeader(),
                cookingTimeLabel,
                cookingTimeTextField,
                preparationTextField,
                preparationTimeLabel,
                serveNumberLabel,
//                servenumberTextField,
                submitButton,
                uploadButton,
                addButton,
                deleteButton,
                backButton,
                clearButton

        );

        Scene scene = new Scene(background);
        this.setScene(scene);

    }

    private void setClearButton() {
        clearButton = new Button("Remove All");
        clearButton.setLayoutX(320);
        clearButton.setLayoutY(510);
        clearButton.setPrefSize(200,40);

    }

    private void setBackButton() {
        backButton = new Button("Cancel");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
    }

    private void setDeleteButton() {
        deleteButton = new Button("-");
        deleteButton.setLayoutX(260);
        deleteButton.setLayoutY(245);
        deleteButton.setPrefSize(30,30);
    }

    private void setAddButton() {
        addButton = new Button("+");
        addButton.setLayoutX(260);
        addButton.setLayoutY(210);
        addButton.setPrefSize(30,30);
    }

    private void setSubminButtton() {
        submitButton = new Button("Save recipe");
        submitButton.setLayoutX(550);
        submitButton.setLayoutY(510);
        submitButton.setPrefSize(200,40);
    }

    private void setUploadButton() {
        uploadButton = new Button("Upload pictures");
        uploadButton.setLayoutX(20);
        uploadButton.setLayoutY(470);
        uploadButton.setPrefSize(200,40);
    }

    private void setServenumber() {
        serveNumberLabel = new Label("Serve number: 1");
        serveNumberLabel.setLayoutX(300);
        serveNumberLabel.setLayoutY(120);
        serveNumberLabel.setFont(Font.font("System Bold Italic", 15));

//        servenumberTextField.setLayoutX(380);
//        servenumberTextField.setLayoutY(100);
//        servenumberTextField.setPrefSize(60,20);
//        servenumberTextField.setAlignment(Pos.CENTER);
    }

    private void setPrepartionTime() {
        preparationTextField = new TextField();
        preparationTextField.setLayoutX(150);
        preparationTextField.setLayoutY(200);
        preparationTextField.setPrefSize(80,20);
        preparationTextField.setAlignment(Pos.CENTER);

        preparationTimeLabel = new Label("Prepare Time:");
        preparationTimeLabel.setFont(new Font("Times New Roman", 20));
        preparationTimeLabel.setLayoutX(20);
        preparationTimeLabel.setLayoutY(200);

    }

    private void setCookingTime() {
        cookingTimeTextField = new TextField();
        cookingTimeTextField.setLayoutX(150);
        cookingTimeTextField.setLayoutY(165);
        cookingTimeTextField.setPrefSize(80,20);
        cookingTimeTextField.setAlignment(Pos.CENTER);

        cookingTimeLabel = new Label("Cooking Time:");
        cookingTimeLabel.setFont(new Font("Times New Roman", 20));
        cookingTimeLabel.setLayoutX(20);
        cookingTimeLabel.setLayoutY(160);
    }

    private void setRecipename() {
        recipeNameTextField = new TextField("Input Recipe name");
        recipeNameTextField.setFont(new Font("Comic Sans MS", 50));
        recipeNameTextField.setStyle("-fx-text-fill: #333;");
        recipeNameTextField.setPrefSize(600,40);
        recipeNameTextField.setAlignment(Pos.CENTER);
    }

    private HBox createHeader(){
        HBox headerBox = new HBox();
        headerBox.setPrefWidth(800);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().add(recipeNameTextField);
        headerBox.setLayoutY(10);
        return headerBox;
    }

    private void setInstructionTab() {
        instructionTab = new Tab("Instructions");
        AnchorPane instructionPane = new AnchorPane();

        instructionTextArea.setEditable(true);
        instructionTextArea.setWrapText(true);
        instructionTextArea.setPrefSize(450,350);
        instructionTextArea.setLayoutX(0);
        instructionTextArea.setLayoutY(0);
        instructionPane.getChildren().add(instructionTextArea);

        instructionTab.setContent(instructionPane);
        instructionPane.setStyle("-fx-background-color: transparent;");
    }

    private void setIngredientTab() {
        ingredientsTab = new Tab("Ingredients");
        tableView = new TableView<>();
        tableView.setLayoutX(0);
        tableView.setLayoutY(0);
        tableView.setPrefSize(450,350);
        tableView.setEffect(new DropShadow());

        TableColumn<RecipeIngredient, String> nameColumn = new TableColumn<>("Ingredient name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<RecipeIngredient, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<RecipeIngredient, String> unitsColumn = new TableColumn<>("Units");
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

        TableColumn<RecipeIngredient, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll(nameColumn, quantityColumn, unitsColumn, descriptionColumn);

        AnchorPane ingredientsPane = new AnchorPane();
        ingredientsPane.setPrefSize(450,350);
        ingredientsPane.setStyle("-fx-background-color: transparent;");
        ingredientsPane.setLayoutX(0);
        ingredientsPane.setLayoutY(0);
        ingredientsPane.getChildren().add(tableView);
        ingredientsTab.setContent(ingredientsPane);
    }

    public void setupImage(String imageurl) {
        if(imageurl == null || imageurl.isEmpty()){
            return;
        }else {
            Image image = new Image(imageurl);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            recipeImage = imageView;
            recipeImage.setLayoutX(0);
            recipeImage.setLayoutY(0);
        }
    }




}



