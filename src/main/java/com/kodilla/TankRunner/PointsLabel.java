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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PointsLabel extends Label {
    public final static String pathToFont = "kenvector_future.ttf";
    public final static String pathToBackground = "green_button13.png";

    public PointsLabel(String text){
        setPrefWidth(600);
        setPrefHeight(400);
        //BackgroundImage backgroundImage = new BackgroundImage(new Image(pathToBackground,600,400,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        //setBackground(new Background(backgroundImage));
        setAlignment(Pos.TOP_LEFT);
        setPadding(new Insets(10));
        setText(text);
        setFont(Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream(pathToFont), 23));
    }
}
