package com.company;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
public class buttonNode extends Pane {

    private  int x;
    private  int y;
    private  int w;
    private  int h;
    private  String image_url;
    private  boolean state;
    private  String color ;

    private static int roomNum;
    private static int p;
    public buttonNode( int x, int y, int w, int h, String image_url, boolean state, String color,int roomNum,int p){
        this.w = w;
        this.h = h;
        this.image_url = image_url;
        this.p = p;
        Image img = new Image(image_url);
        ImagePattern imagePattern = new ImagePattern(img);
        Rectangle rect = new Rectangle(w,h);
        int xMod = 0;
        int yMod = 0;
        if(p == 0){
            xMod = -30;
        }
        if(p == 1){
            yMod = 30;
        }
        if (p == 2){
            xMod = -30;
            yMod = 30;
        }
        this.x = x + xMod;
        this.y = y+yMod;
        rect.setFill(imagePattern);
        this.state = state;
        this.color = color;
        this.roomNum = roomNum;
        getChildren().addAll(rect);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String getColor ()   {
        return color;
    }
    public void  setColor (String set_Color)   {
        color = set_Color ;
    }
    public void setImage_url(String img){
        image_url = img;
    }
    public void setState (Boolean set_state){
        state = set_state;
    }
    public Boolean getState (){
        return state;
    }
    public int getRoomNum (){
        return roomNum;
    }
    public void createCopy(){

    }
}
