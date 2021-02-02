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
    private static final String pathToFont = "kenvector_future.ttf";
    private static final String pathToButton = "-fx-background-image: url('green_button00.png')";
    private static final String pathToPressedButton = "-fx-background-image: url('green_button13.png')";



    public ModelButton(String text){
        setText(text);
        setFont(Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream(pathToFont), 23));
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(pathToButton);
        buttonListener();
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
