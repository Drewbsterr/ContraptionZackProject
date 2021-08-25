package com.company;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class jukebox extends Pane{

    ImageView imageView;
    int count = 12;
    int columns = 12;
    int offsetX = 0;
    int offsetY = 0;
    int width = 60;
    int height = 60;


    Rectangle removeRect = null;
    SpriteAnimation animation;

    public jukebox(ImageView imageView){
        this.imageView = imageView;
        this.imageView.setViewport( new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(1000),count, columns, offsetX,offsetY,width,height);
        getChildren().addAll(imageView);
    }
}