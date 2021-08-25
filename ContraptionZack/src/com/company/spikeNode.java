package com.company;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
public class spikeNode extends Pane {

    private int x,y,w,h;
    private  String image_url,color;
    private  boolean state;
    private static int roomNum;
    private static int pos;

    public spikeNode( int x, int y, int w, int h, String image_url, boolean state, String color,int roomNum,int pos) {
        this.w = w;
        this.h = h;
        this.image_url = image_url;
        this.pos = pos;
        Image img = new Image(image_url);
        ImagePattern imagePattern = new ImagePattern(img);
        Rectangle rect = new Rectangle(w,h);
        int xMod = 0;
        int yMod = 0;
        //bottom of block (quarter1)
        if(pos == 1){
            yMod = 45;
        }
        //2nd position on block (quarter2)
        if(pos == 2){
            yMod = 30;
        }
        //3nd position on block (quarter3)
        if (pos == 3){
            yMod = 15;
        }
        //top of block (quarter4)
        //also use for vertical spikes
        if (pos == 4){

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
    public void setX(int pos){
        this.x = pos;
    }
    public void setY(int pos){
        this.y = pos;
    }
    public String getColor ()   {
        return color;
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

}
