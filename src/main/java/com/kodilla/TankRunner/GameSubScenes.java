package com.kodilla.TankRunner;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class GameSubScenes extends SubScene {
    private final String pathToFont = "file:src/main/resources/kenvector_future.ttf";
    private final String pathToBackground = "file:src/main/resources/green_panel.png";
    private boolean isHidden;
    public GameSubScenes() {
        super(new AnchorPane(), 600, 400);
        prefHeight(400);
        prefWidth(600);
        BackgroundImage image = new BackgroundImage(new Image(pathToBackground,600,400,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));
        isHidden = true;
        setLayoutY(200);
        setLayoutX(1300);
    }

    public void move(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(this);
        if (isHidden) {
            transition.setToX(-900);
            isHidden = false;
        } else {
            transition.setToX(900);
            isHidden = true;
        }
        transition.play();
    }

    //public AnchorPane getPane(){
     //   return (AnchorPane) this.getRoot();
    //}
}
