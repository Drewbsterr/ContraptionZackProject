package com.company;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Character extends Pane {

    ImageView imageView;
    int count = 4;
    int columns = 4;
    int offsetX = 0;
    int offsetY = 0;
    int width = 32;
    int height = 50;
    int currentRoom;

    SpriteAnimation animation;

    public Character(ImageView imageView){
        currentRoom = 1;
        this.imageView = imageView;
        this.imageView.setViewport( new Rectangle2D(offsetX,offsetY,width,height));
        animation = new SpriteAnimation(imageView, Duration.millis(200),count, columns, offsetX,offsetY,width,height);
        getChildren().addAll(imageView);
        setTranslateX(200);
        setTranslateY(350);
    }

    public void changeRoomNum(int num) {
        this.currentRoom = num;
    }

    //changes the room and position of the player
    public void changeRoom(int num, int pos) {
        //need to see what room we are currently in to see which door to put the player in the next room
        if (num == 1) {
            if (pos == 0) {
                Main.window.setScene(Main.room1Scene);
                setTranslateX(240);
                setTranslateY(360);

            }
            if (pos == 1) {
                Main.window.setScene(Main.room1Scene);
                setTranslateX(310);
                setTranslateY(120);

            }
        }
        if (num == 2) {
            if (pos == 0) {
                Main.window.setScene(Main.room2Scene);
                setTranslateX(285);
                setTranslateY(525);

            }
            if (pos == 1) {
                Main.window.setScene(Main.room2Scene);
                setTranslateX(280);
                setTranslateY(60);

            }
        }
        if (num == 3) {
            if (pos == 0) {
                Main.window.setScene(Main.room3Scene);
                setTranslateX(280);
                setTranslateY(475);

            }
            if (pos == 1) {
                Main.window.setScene(Main.room3Scene);
                setTranslateX(190);
                setTranslateY(115);
               
            }
        }
    }

    //returns the room number as an int
    public int getRoom() {
        return currentRoom;
    }

    //getting player coordinates
    public int getCharX() {
        int charX = (int) this.getTranslateX();
        return charX;
    }
    public int getCharY() {
        int charY = (int) this.getTranslateY();
        return charY;
    }
}