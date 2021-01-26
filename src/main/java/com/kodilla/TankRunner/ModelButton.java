package com.kodilla.TankRunner;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ModelButton extends Button {
    private final String pathToFont = "file:src/main/resources/kenvector_future.ttf";
    private final String pathToButton = "-fx-background-image: url('resources/green_button00.png')";
    private final String pathToPressedButton = "-fx-background-image: url('resources/green_button13.png')";



    public ModelButton(String text){
        setText(text);
        setButtonFont();
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(pathToButton);
        buttonListener();
    }

    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(pathToFont),23));
        }
        catch (FileNotFoundException e){
            setFont(Font.font("Tahoma",23));
        }
    }

    private void setButtonStyle(){
        setStyle(pathToButton);
        setPrefHeight(49);
        setLayoutY(getLayoutY());
    }

    private void setPressedButtonStyle(){
        setStyle(pathToPressedButton);
        setPrefHeight(45);
        setLayoutY(getLayoutY());
    }

    private void buttonListener(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setPressedButtonStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
