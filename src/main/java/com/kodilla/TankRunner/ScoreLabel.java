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

public class ScoreLabel extends Label {
    public final static String pathToFont = "kenvector_future.ttf";
    public final static String pathToBackground = "green_button13.png";

    public ScoreLabel(String text){
        setPrefWidth(160);
        setPrefHeight(50);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(pathToBackground,160,50,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10));
        setText(text);
        setScoreFont();
    }
    private void setScoreFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(pathToFont),23));
        }
        catch (FileNotFoundException e){
            setFont(Font.font("Tahoma",23));
        }
    }
}
