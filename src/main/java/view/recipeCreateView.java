package view;

import control.recipeCreateController;
import dao.mappers.RecipeIngredient;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

public class recipeCreateView extends Stage {

    public boolean isEdited = false;
    public Integer editedRecipeId = 0;
    public Button submitButton,backButton,uploadButton,addButton,deleteButton,clearButton;
    public TextField recipeNameTextField,preparationTextField, cookingTimeTextField,servenumberTextField;
    public ImageView recipeImage;
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

        setupImage();
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
        clearButton.setOnAction(new recipeCreateController(this));

    }

    private void setBackButton() {
        backButton = new Button("Back");
        backButton.setOnAction(new recipeCreateController(this));
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
    }

    private void setDeleteButton() {
        deleteButton = new Button("-");
        deleteButton.setLayoutX(260);
        deleteButton.setLayoutY(245);
        deleteButton.setPrefSize(30,30);
        deleteButton.setOnAction(new recipeCreateController(this));
    }

    private void setAddButton() {
        addButton = new Button("+");
        addButton.setLayoutX(260);
        addButton.setLayoutY(210);
        addButton.setPrefSize(30,30);
        addButton.setOnAction(new recipeCreateController(this));
    }

    private void setSubminButtton() {
        submitButton = new Button("Save recipe");
        submitButton.setOnAction(new recipeCreateController(this));
        submitButton.setLayoutX(550);
        submitButton.setLayoutY(510);
        submitButton.setPrefSize(200,40);
    }

    private void setUploadButton() {
        uploadButton = new Button("Upload pictures");
        uploadButton.setLayoutX(20);
        uploadButton.setLayoutY(470);
        uploadButton.setPrefSize(200,40);
        uploadButton.setOnAction(new recipeCreateController(this));
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
        recipeNameTextField = new TextField("");
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
        tableView.setEditable(true);


        TableColumn<RecipeIngredient, String> nameColumn = new TableColumn<>("Ingredient name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event ->{
            RecipeIngredient ingredient = event.getRowValue();
            ingredient.setName(event.getNewValue());
        });

        TableColumn<RecipeIngredient, Float> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.setCellFactory(column -> {
            TextFieldTableCell<RecipeIngredient, Float> cell = new TextFieldTableCell<>(new FloatStringConverter());

            cell.setConverter(new FloatStringConverter() {
                @Override
                public Float fromString(String value) {
                    if (value.matches("-?\\d*(\\.\\d+)?")) {
                        return super.fromString(value);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warn");
                        alert.setHeaderText(null);
                        alert.setContentText("Please input number");
                        alert.showAndWait();
                        return null; // 返回 null 表示无效输入
                    }
                }
            });
            return cell;
        });
        quantityColumn.setOnEditCommit(event ->{
            RecipeIngredient ingredient = event.getRowValue();
            System.out.println(event.getNewValue());
            ingredient.setQuantity(event.getNewValue());
        });

        TableColumn<RecipeIngredient, String> unitsColumn = new TableColumn<>("Units");
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        unitsColumn.setOnEditCommit(event ->{
            RecipeIngredient ingredient = event.getRowValue();
            ingredient.setUnit(event.getNewValue());
        });

        TableColumn<RecipeIngredient, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event ->{
            RecipeIngredient ingredient = event.getRowValue();
            ingredient.setDescription(event.getNewValue());
        });

        tableView.getColumns().addAll(nameColumn, quantityColumn, unitsColumn, descriptionColumn);

        AnchorPane ingredientsPane = new AnchorPane();
        ingredientsPane.setPrefSize(450,350);
        ingredientsPane.setStyle("-fx-background-color: transparent;");
        ingredientsPane.setLayoutX(0);
        ingredientsPane.setLayoutY(0);
        ingredientsPane.getChildren().add(tableView);
        ingredientsTab.setContent(ingredientsPane);
    }

    public void setupImage() {
            recipeImage = new ImageView();
            recipeImage.setFitWidth(200);
            recipeImage.setFitHeight(200);
            recipeImage.setLayoutX(0);
            recipeImage.setLayoutY(0);
        }



    public void updateImage(String temp) {
        recipeImage.setImage(new Image("file:"+ temp));
    }
}



