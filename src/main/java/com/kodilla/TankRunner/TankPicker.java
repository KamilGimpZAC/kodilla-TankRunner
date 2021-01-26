package com.kodilla.TankRunner;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TankPicker extends VBox {
    private ImageView tankImage;
    private String pathToCircle = "file:src/main/resources/green_boxTick.png";
    private String pathToCircleNotChosen = "file:src/main/resources/grey_circle.png";
    private ImageView circleImage = new ImageView(pathToCircleNotChosen);
    private Tanks tank;
    private boolean isCircleChosen;

    public TankPicker(Tanks tank){
        tankImage = new ImageView(tank.getTank());
        this.tank = tank;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(tankImage);
    }

    public Tanks getTank() {
        return tank;
    }

    public boolean isCircleChosen() {
        return isCircleChosen;
    }

    public void setCircle(boolean isCircleChosen){
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? pathToCircle : pathToCircleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }
}
