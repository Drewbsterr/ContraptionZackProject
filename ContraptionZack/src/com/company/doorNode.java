package com.company;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class doorNode extends Pane {
    private int x,y,w,h, count;
    private  String image_url;
    private  boolean state;
    private static int roomNum;
    private static int pos;
    private int doorNum;
    public doorNode(int x, int y, int w, int h, String image_url,  int count,int doorNum, boolean state,int roomNum,int pos ){
        this.w = w;
        this.h = h;
        this.doorNum = doorNum;
        this.image_url = image_url;
        this.pos = pos;
        this.count = count ;
        Image img = new Image(image_url);
        ImagePattern imagePattern = new ImagePattern(img);
        Rectangle rect = new Rectangle(w,h);
        int xMod = 0;
        int yMod = 0;
        //bottom of block (quarter1)
        if(pos == 1){
            yMod = -35;
        }
        //2nd position on block (quarter2)
        if(pos == 2){
            yMod = -35;
            xMod = 45;
        }
        //3nd position on block (quarter3)
        if (pos == 3){
            yMod = -60;
        }
        //top of block (quarter4)
        //also use for vertical spikes
        if (pos == 4){
            yMod = -60;
             xMod = 45;
        }

        this.x = x + xMod;
        this.y = y+yMod;
        rect.setFill(imagePattern);
        this.state = state;
        this.roomNum = roomNum;
        getChildren().addAll(rect);




    }
    public void incrementCount(){count++;  }
    public int getCount () { return count; }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setDoorNum(int num){num=doorNum;}
    public int getDoorNum(){return doorNum;}
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
