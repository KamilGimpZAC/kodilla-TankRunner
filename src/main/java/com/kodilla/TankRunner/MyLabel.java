package com.kodilla.TankRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;


public class MyLabel extends Label {
    public final static String pathToFont = "kenvector_future.ttf";
    public final static String pathToBackground = "green_button13.png";

    public MyLabel(String text){
        setPrefWidth(380);
        setPrefHeight(49);
        setText(text);
        setWrapText(true);
        setAlignment(Pos.CENTER);
        setFont(Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream(pathToFont), 23));
        BackgroundImage image = new BackgroundImage(new Image(pathToBackground,380,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(image));
    }
}
