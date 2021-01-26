package com.kodilla.TankRunner;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyLabel extends Label {
    public final static String pathToFont = "file:src/main/resources/kenvector_future.ttf";

    public MyLabel(String text){
        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40));
        setText(text);
        setWrapText(true);
        try {
            setFont(Font.loadFont(new FileInputStream(pathToFont),23));
        }catch (FileNotFoundException e){
            setFont(Font.loadFont("Tahoma",23));
        }
    }
}
