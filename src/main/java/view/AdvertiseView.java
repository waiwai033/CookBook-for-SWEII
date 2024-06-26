package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.nio.file.Paths;

public class AdvertiseView extends Stage {
    public Button skipButton;
    public MediaPlayer mediaPlayer;

    public AdvertiseView(){
        this.setTitle("");
        this.setResizable(false);
        this.setWidth(800);
        this.setHeight(600);
        init();
    }
    private void init(){
        String videoPath = Paths.get("src/video/advertisement.MP4").toUri().toString();
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setFitWidth(800);
        mediaView.setFitHeight(600);
        mediaView.setPreserveRatio(true);

        skipButton = new Button("Skip");
        skipButton.setPrefSize(70,70);
        // 使用AnchorPane将按钮放置在右上角
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(mediaView, skipButton);

        // 将按钮锚定在右上角
        AnchorPane.setTopAnchor(skipButton, 10.0);
        AnchorPane.setRightAnchor(skipButton, 10.0);

        Scene scene = new Scene(anchorPane, 800, 600);
        this.setScene(scene);
        mediaPlayer.setAutoPlay(true);
    }
    public void setOnEndOfMedia(Runnable handler) {
        if (mediaPlayer != null) {
            mediaPlayer.setOnEndOfMedia(handler);
        }
    }

    public void setOnSkipButton(EventHandler<ActionEvent> handler) {
        skipButton.setOnAction(handler);
    }
}


