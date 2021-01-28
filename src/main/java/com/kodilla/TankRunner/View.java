package com.kodilla.TankRunner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class View {
    private static final int HEIGHT = 680;
    private static final int WIDTH = 1024;
    private static final int xButton = 110;
    private static final int yButton = 200;
    private AnchorPane anchorPane;
    private Scene scene;
    private Stage stage;

    private GameSubScenes rules = new GameSubScenes();
    private GameSubScenes score = new GameSubScenes();
    private GameSubScenes play = new GameSubScenes();
    private GameSubScenes tankChooser = new GameSubScenes();

    private GameSubScenes sceneToHide = new GameSubScenes();


    List<ModelButton> menuButtons;
    List<TankPicker> tankList;
    private Tanks choosenTank;

    public View() {
        menuButtons = new ArrayList<>();
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane,WIDTH,HEIGHT);
        stage = new Stage();
        stage.setScene(scene);
        iniSubScenes();
        createButtons();
        setMenuBackground();
        setLogo();
    }

    private void hideSubScene(GameSubScenes subScenes){
        if (sceneToHide != null){
            sceneToHide.move();
        }
        subScenes.move();
        sceneToHide = subScenes;
    }

    private void iniSubScenes(){
        anchorPane.getChildren().add(rules);
        anchorPane.getChildren().add(score);
        anchorPane.getChildren().add(tankChooser);
        createTankPickerSubScene();

    }

    private void createTankPickerSubScene(){
        anchorPane.getChildren().add(play);
        MyLabel mylabel = new MyLabel("CHOOSE YOUR TANK");
        mylabel.setLayoutX(110);
        mylabel.setLayoutY(25);
        play.getPane().getChildren().add(mylabel);
        play.getPane().getChildren().add(createTanksToChoose());
        play.getPane().getChildren().add(createPlayButton());
    }

    private HBox createTanksToChoose(){
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        tankList = new ArrayList<>();
        for(Tanks tanks: Tanks.values()){
            TankPicker tankToPick = new TankPicker(tanks);
            tankList.add(tankToPick);
            hbox.getChildren().add(tankToPick);
            tankToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for(TankPicker tank: tankList){
                        tank.setCircle(false);
                    }
                    tankToPick.setCircle(true);
                    choosenTank = tankToPick.getTank();
                }
            });
        }
        hbox.setLayoutX(130);
        hbox.setLayoutY(100);
        return hbox;
    }

    private ModelButton createPlayButton(){
        ModelButton startButtton = new ModelButton("START");
        startButtton.setLayoutX(200);
        startButtton.setLayoutY(300);
        startButtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (choosenTank != null){
                    Game game = new Game();
                    game.createGame(stage,choosenTank);
                }
            }
        });
        return startButtton;
    }

    public Stage getStage() {
        return stage;
    }

    private void addMenuButton(ModelButton button){
        button.setLayoutY(yButton + menuButtons.size() * 100);
        button.setLayoutX(xButton);
        menuButtons.add(button);
        anchorPane.getChildren().add(button);
    }

    private void createButtons(){
        createStartButton();
        createScoreButton();
        createRulesButton();
        createExitButton();
    }

    private void createStartButton(){
        ModelButton button1 = new ModelButton("PLAY");
        addMenuButton(button1);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideSubScene(play);
            }
        });
    }

    private void createScoreButton(){
        ModelButton button2 = new ModelButton("SCORE");
        addMenuButton(button2);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideSubScene(score);
            }
        });
    }

    private void createRulesButton(){
        ModelButton button3 = new ModelButton("RULES");
        addMenuButton(button3);
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideSubScene(rules);
            }
        });
    }

    private void createExitButton(){
        ModelButton button4 = new ModelButton("EXIT");
        addMenuButton(button4);
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
    }

    private void setMenuBackground(){
        Image backgroundImage = new Image("backgroundColorGrass.png",1024,1024,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        anchorPane.setBackground(new Background(background));
    }

    private void setLogo(){
        ImageView logo = new ImageView("logo.png");
        logo.setLayoutX(255);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new Glow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });
        anchorPane.getChildren().add(logo);
    }
}
