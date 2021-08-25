package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.scene.layout. * ;

public class Main extends Application {

    public static Stage window;
    public static Scene theMenu,room1Scene,room2Scene,room3Scene;
    public static int readySpring = 0;
    //player image
    public static Image vader = new Image("/darthvader.png");
    public static ImageView imv = new ImageView(vader);
    public static Character darth = new Character(imv);


    //
    public static Image image = new Image("/rainbowJuke.png");
    public static ImageView imageView = new ImageView(image);
    public static jukebox jukee = new jukebox(imageView);

    private static boolean open = false;
    public int count =0;

    //room objects
    public static Room room1, room2, room3;

    //maps physical keys to a key within java
    public static HashMap < KeyCode, Boolean > keys = new HashMap < KeyCode, Boolean > ();


    //game engine
    private void update() {
        //move left


        if (darth.getRoom() == 1) {
            if (isPressed(KeyCode.LEFT)) {
                darth.animation.play();
                darth.animation.setOffsetY(48);
                room1.movePlayerX(-2);
            }
            //move right
            else if (isPressed(KeyCode.RIGHT)) {
                darth.animation.play();
                darth.animation.setOffsetY(96);
                room1.movePlayerX(2);
            }
            //up
            else if (isPressed(KeyCode.UP)) {
                darth.animation.play();
                darth.animation.setOffsetY(144);
                room1.movePlayerY(-2);
                room1.onArrow(room1,room2,room3);
            }
            //down
            else if (isPressed(KeyCode.DOWN)) {
                darth.animation.play();
                darth.animation.setOffsetY(0);
                room1.movePlayerY(2);
                room1.onArrow(room1,room2,room3);
            }
            else{
                darth.animation.stop();
            }
            if (isPressed(KeyCode.ESCAPE)) {
                try {
                    room1.makeMenu();
                }
                catch (IllegalArgumentException iae) {
                    room1.removeMenu();
                }
            }
        }
        if (darth.getRoom() == 2) {
            room2.spikeState();
            if (isPressed(KeyCode.LEFT)) {
                darth.animation.play();
                darth.animation.setOffsetY(48);
                room2.movePlayerX(-2);
                if(readySpring == 0) {
                    readySpring++;
                    room2.onSpring('A');
                }
            }
            //move right
            else if (isPressed(KeyCode.RIGHT)) {
                darth.animation.play();
                darth.animation.setOffsetY(96);
                room2.movePlayerX(2);
                if(readySpring == 0) {
                    readySpring++;
                    room2.onSpring('D');
                }
            }
            //up
            else if (isPressed(KeyCode.UP)) {
                darth.animation.play();
                darth.animation.setOffsetY(144);
                room2.movePlayerY(-2);
                room2.onArrow(room1,room2,room3);
            }
            //down
            else if (isPressed(KeyCode.DOWN)) {
                darth.animation.play();
                darth.animation.setOffsetY(0);
                room2.movePlayerY(2);
                room2.onArrow(room1,room2,room3);
            }
            else{
                darth.animation.stop();
            }

            if (isPressed(KeyCode.ESCAPE)) {
                try {
                    room2.makeMenu();
                }
                catch (IllegalArgumentException iae) {
                    room2.removeMenu();
                }
            }
        }
        if (darth.getRoom() == 3) {
            room3.spikeState();
            room3.onLightsaber();
            if (isPressed(KeyCode.LEFT)) {
                darth.animation.play();
                darth.animation.setOffsetY(48);
                room3.movePlayerX(-2);
            }
            //move right
            else if (isPressed(KeyCode.RIGHT)) {
                darth.animation.play();
                darth.animation.setOffsetY(96);
                room3.movePlayerX(2);
            }
            //up
            else if (isPressed(KeyCode.UP)) {
                darth.animation.play();
                darth.animation.setOffsetY(144);
                room3.movePlayerY(-2);
                room3.onArrow(room1,room2,room3);
            }
            //down
            else if (isPressed(KeyCode.DOWN)) {
                darth.animation.play();
                darth.animation.setOffsetY(0);
                room3.movePlayerY(2);
                room3.onArrow(room1,room2,room3);
            }
            else{
                darth.animation.stop();
            }
            if (isPressed(KeyCode.ESCAPE)) {
                try {
                    room3.makeMenu();
                }
                catch (IllegalArgumentException iae) {
                    room3.removeMenu();
                }
            }
        }
        jukee.animation.play();
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }


    //start
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        ArrayList <String> LEVEL1 = new ArrayList<>();
        ArrayList <String> LEVEL2 = new ArrayList<>();
        ArrayList <String> LEVEL3 = new ArrayList<>();

        LevelData r0 = new LevelData();
        LevelData r1 = new LevelData();
        LevelData r2 = new LevelData();

        r0.openFile("Files/room0.txt",LEVEL1);
        r1.openFile("Files/room1.txt",LEVEL2);
        r2.openFile("Files/room2.txt",LEVEL3);

        //make the rooms
        room1 = new Room(LEVEL1,1);
        room2 = new Room(LEVEL2,2);
        room3 = new Room(LEVEL3,3);
        //position player for room 1
        darth.setTranslateX(200);
        darth.setTranslateY(350);
        //making the menus
        room1.inGameMenu();
        room2.inGameMenu();
        room3.inGameMenu();

        //start button
        Button btnStart = new Button("Start");
        btnStart.setOnAction(e -> darth.changeRoom(1, 0));

        //load button
        Button btnLoad = new Button("Load");

        //quit button
        Button btnQuit = new Button("Quit");
        btnQuit.setOnAction(e -> System.exit(0));

        //Menu layout
        Pane root = new Pane();
        root.setPrefSize(1280,720);
        //grab image
        Image pic = new Image ("cz.jpg");
        ImageView img = new ImageView(pic);
        img.setFitWidth(1280);
        img.setFitHeight(750);
        root.getChildren().add(img);
        //vbox to store buttons
        VBox menuLayout = new VBox();
        menuLayout.setTranslateX(600);
        menuLayout.setTranslateY(520);
        menuLayout.setSpacing(20);
        Text t = new Text("Contraption Zack");
        t.setFont(new Font(72));
        t.setTranslateX(370);
        t.setTranslateY(500);
        menuLayout.getChildren().addAll(btnStart,btnLoad,btnQuit);
        root.getChildren().addAll(t,menuLayout);
        theMenu = new Scene(root, 1280, 720);
        theMenu.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(),true));
        theMenu.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(),false));

        //other room stuff
        room1.getappRoot().getChildren().addAll(darth);
        room1Scene = new Scene(room1.getappRoot(),660,600);
        room2Scene = new Scene(room2.getappRoot(),600,660);
        room3Scene = new Scene(room3.getappRoot(),600,600);
        room1Scene.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(),true));
        room1Scene.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(),false));
        room2Scene.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(),true));
        room2Scene.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(),false));
        room3Scene.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(),true));
        room3Scene.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(),false));

        //animation timer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                update();
                long seconds = TimeUnit.NANOSECONDS.toSeconds(now);
                if(seconds%5==0 && count < 1){
                    open = true;
                    count ++;
                    room3.doorTimer();

                }
                else if(seconds%5!=0  ){
                    count = 0;

                }

            }
        };

        timer.start();

        //window details
        window.setScene(theMenu);
        window.setTitle("Contraption Zack");
        window.show();
    }

    public static void main(String[] args) {
        // write your code here
        launch(args);
    }
}