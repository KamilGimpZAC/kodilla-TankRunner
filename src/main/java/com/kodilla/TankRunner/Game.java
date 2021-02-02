package com.kodilla.TankRunner;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;

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

    private static String mineOn = "tanks_mineOn.png";
    private static String minePressed = "tanks_minePressed.png";

    private ImageView[] imageMineOn;
    //private ImageView[] imageMinePressed;
    Random randomPosition = new Random();

    private ImageView fuel;
    private ScoreLabel scoreLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;
    private final static String pathToFuel = "tanks_barrelGrey.png";

    private final static int fuelRadius = 25;
    private final static int mineRadius = 16;
    private final static int tankRadius = 27;

    private PrintWriter savePoints;


    public Game(){
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane,1024,1024);
        gameStage = new Stage();
        setGameBackground();
        gameStage.setScene(gameScene);
        gameListener();
    }

    private void createElements(Tanks choosenTank){
        playerLife = 2;
        fuel = new ImageView(pathToFuel);
        setElementPosition(fuel);
        gamePane.getChildren().add(fuel);
        scoreLabel = new ScoreLabel("POINTS: 00");
        scoreLabel.setLayoutX(800);
        scoreLabel.setLayoutY(20);
        gamePane.getChildren().add(scoreLabel);
        playerLifes = new ImageView[3];
        for (int i = 0; i<playerLifes.length; i++){
            playerLifes[i] = new ImageView(choosenTank.getLife());
            playerLifes[i].setLayoutX(30 + (i * 120));
            playerLifes[i].setLayoutY(20);
            gamePane.getChildren().add(playerLifes[i]);
        }
        imageMineOn = new ImageView[8];
        for (int i = 0; i<imageMineOn.length; i++){
            imageMineOn[i] = new ImageView(mineOn);
            setElementPosition(imageMineOn[i]);
            gamePane.getChildren().add(imageMineOn[i]);
        }

    }

    private void setElementPosition(ImageView image){
        image.setLayoutX(randomPosition.nextInt(3200)+600);
        image.setLayoutY(640 + randomPosition.nextInt(940 + 1 - 640));
    }

    private void moveElements(){
        fuel.setLayoutX(fuel.getLayoutX() - 5);
        for (int i = 0; i<imageMineOn.length; i++){
            imageMineOn[i].setLayoutX(imageMineOn[i].getLayoutX()-7);
        }
    }

    private void relocateElements(){
        if (fuel.getLayoutX() < 0){
            setElementPosition(fuel);
        }
        for (int i = 0; i<imageMineOn.length;i++){
            if (imageMineOn[i].getLayoutX() < 0){
                setElementPosition(imageMineOn[i]);
            }
        }
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
        createElements(choosenTank);
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
                moveElements();
                relocateElements();
                checkCollision();
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
            if (tank.getLayoutY() > 600){
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

    private void setGameBackground(){
            Image backgroundImage = new Image("backgroundColorGrass.png",1024,1024,false,true);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
            gamePane.setBackground(new Background(background));
    }

    private void checkCollision(){
        if (tankRadius + fuelRadius > distance(tank.getLayoutX() + 49,fuel.getLayoutX()+15,tank.getLayoutY()+37,fuel.getLayoutY()+15)){
            setElementPosition(fuel);
            points++;
            String textToSet = "POINTS: ";
            if (points < 10){
                textToSet = textToSet + "0";
            }
            scoreLabel.setText(textToSet + points);
        }
        for (int i =0; i<imageMineOn.length; i++){
            if (tankRadius + mineRadius > distance(tank.getLayoutX()+49,imageMineOn[i].getLayoutX()+20,tank.getLayoutY()+37,imageMineOn[i].getLayoutY()+20)){
                removeLife();
                setElementPosition(imageMineOn[i]);
            }
        }
    }

    private void removeLife(){
        gamePane.getChildren().remove(playerLifes[playerLife]);
        playerLife--;
        if (playerLife < 0){
            createSave();
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double distance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
    }

    private void createSave(){
        try {
            savePoints = new PrintWriter("G:/Development/Projects/kodilla-TankRunner/src/main/resources/points.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File error: " + e);
        }
        savePoints.write("Points: " + points);
        savePoints.close();
    }
}
