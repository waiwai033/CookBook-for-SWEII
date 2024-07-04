package view;
import control.VIPViewController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.function.Consumer;

/**
 * This view is for user to try VIP
 */
public class VIPView extends Stage {
    /**
     * Button for Become VIP
     */
    public Button button1;
    /**
     * Button for turning back.
     */
    public Button button2;
    /**
     *  Consumer for status of VIP
     */
    public Consumer<Void> onBecomeVIP;
    /**
     * Constructor for VIPView
     * @param onBecomeVIP
     */
    public VIPView(Consumer<Void> onBecomeVIP) {
        this.setTitle("TryVIP");
        this.setResizable(false);
        this.setHeight(650);
        this.setWidth(400);
        this.onBecomeVIP =onBecomeVIP;
        init();
    }
    private void init() {
        AnchorPane background = new AnchorPane();
        background.setPrefSize(650, 400);
        Label Joinus = setuplabel("Join us!",85,40,50);
        Label label1 = setuplabel("Our VIP can get:",30,120,35);
        Label label2 = setuplabel("1.Skip advertise",50,200,30);
        Label label3 = setuplabel("2.VIP Right2",50,280,30);
        Label label4 = setuplabel("3.VIP Right3",50,360,30);
        Label label5 = setuplabel("4.VIP Right4",50,440,30);
        setButton1();
        setButton2();

        background.getChildren().addAll(
                Joinus,
                label1,
                label2,
                label3,
                label4,
                label5,
                button1,
                button2);

        Scene scene = new Scene(background);
        this.setScene(scene);
    }
    private Label setuplabel(String context,int x,int y,int size) {
        Label label = new Label(context);
        label.setLayoutX(x);
        label.setLayoutY(y);
        Font font = Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC,size);
        label.setFont(font);
        return label;
    }
    private void setButton1() {
        button1 = new Button("Join right now");
        button1.setOnAction(new VIPViewController(this,onBecomeVIP));
        button1.setLayoutX(50);
        button1.setLayoutY(520);
        button1.setPrefSize(100,40);
    }
    private void setButton2() {
        button2 = new Button("Cancel");
        button2.setOnAction(new VIPViewController(this,onBecomeVIP));
        button2.setLayoutX(250);
        button2.setLayoutY(520);
        button2.setPrefSize(100,40);

    }
}
