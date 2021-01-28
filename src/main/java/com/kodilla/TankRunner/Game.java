package com.kodilla.TankRunner;

import com.sun.scenario.animation.AnimationPulse;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Game {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;
    private ImageView tank;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    public Game(){
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,1024,1024);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameListener();
    }

    private void gameListener(){
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP){
                    isUpKeyPressed = true;
                }else if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP){
                    isUpKeyPressed = false;
                }else if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = false;
                }
            }
        });
    }

    public void createGame(Stage menuStage, Tanks choosenTank){
        this.menuStage = menuStage;
        this.menuStage.hide();
        createTank(choosenTank);
        createAnimation();
        gameStage.show();
    }

    private void createTank(Tanks choosenTank){
        tank = new ImageView(choosenTank.getTank());
        tank.setLayoutX(30);
        tank.setLayoutY(900);
        gamePane.getChildren().add(tank);
    }

    private void createAnimation(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveTank();
            }
        };
        gameTimer.start();
    }

    private void moveTank(){
        if (isUpKeyPressed && !isDownKeyPressed){
            if (angle > -30){
                angle -= 5;
            }
            tank.setRotate(angle);
            if (tank.getLayoutY() > 700){
                tank.setLayoutY(tank.getLayoutY() -3);
            }
        }
        if (!isUpKeyPressed && isDownKeyPressed){
            if (angle < 30){
                angle += 5;
            }
            tank.setRotate(angle);
            if (tank.getLayoutY() < 940){
                tank.setLayoutY(tank.getLayoutY() +3);
            }
        }
        if (isUpKeyPressed && isDownKeyPressed){
            if(angle < 0){
                angle += 5;
            }else if (angle > 0){
                angle -= 5;
            }
            tank.setRotate(angle);
        }
        if (!isUpKeyPressed && !isDownKeyPressed){
            if(angle < 0){
                angle += 5;
            }else if (angle > 0){
                angle -= 5;
            }
            tank.setRotate(angle);
        }
    }
}
