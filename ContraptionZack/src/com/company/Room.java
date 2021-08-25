package com.company;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Room {
    //things that make up a room
    private ArrayList< Node > platforms;
    private ArrayList < Node > floors;
    private ArrayList <Node> arrows;
    private ArrayList <Node> downArrows;
    private ArrayList <Node>halfWalls ;
    private ArrayList <Node>inBetweenWalls ;
    private ArrayList<Node>QuarterWalls;
    private ArrayList<Node>Vertical_QuarterWalls;
    //button stuff
    private ArrayList<buttonNode>yellow_buttons;
    private ArrayList<buttonNode>purple_buttons;
    private ArrayList<buttonNode>blue_buttons;
    private ArrayList<buttonNode>orange_buttons;
    //spike stuff
    private ArrayList<spikeNode>yellow_spikes;
    private ArrayList<spikeNode>purple_spikes;
    private ArrayList<spikeNode>blue_spikes;
    private ArrayList<spikeNode>orange_spikes;

    //doors
    private ArrayList<doorNode>doors;
    private int arr [] = {0,0,0,1};

    //lightsaber
    private ArrayList<Node>lightsabers;

    //private ArrayList<buttonNode>downButtons;
    private Pane appRoot, objectPane,gameRoot,ground,upSprings,downSprings,downButtonPane,buttonPane,downSpikePane,spikePane,doorPane,lightsaberPane;
    private int vCount,count;
    private ArrayList <Node> downsprings;

    private StackPane menuStack;
    private VBox menuBox;

    private String direction ;
    private int roomNumber;

    public Room(ArrayList<String> level, int roomNum) {
        roomNumber = roomNum;
        count = 0;
        vCount = 0;
        this.platforms = new ArrayList < Node > ();
        this.floors = new ArrayList < Node > ();
        this.arrows = new ArrayList <Node> ();
        this.downArrows = new ArrayList <Node> ();
        this.halfWalls = new ArrayList<Node>();
        this.inBetweenWalls = new ArrayList<Node>();
        this.QuarterWalls = new ArrayList<Node>();
        this.downsprings = new ArrayList <Node> ();
        this.Vertical_QuarterWalls = new ArrayList<Node>();
        //doors
        this.doors = new ArrayList<doorNode>();

        //lightsaber
        this.lightsabers = new ArrayList<Node>();

        //button stuff
        this.yellow_buttons =new ArrayList<buttonNode>();
        this.purple_buttons =new ArrayList<buttonNode>();
        this.blue_buttons =new ArrayList<buttonNode>();
        this.orange_buttons =new ArrayList<buttonNode>();
        this.downButtonPane = new Pane();
        this.buttonPane = new Pane();
        //spike stuff
        this.yellow_spikes =new ArrayList<spikeNode>();
        this.purple_spikes =new ArrayList<spikeNode>();
        this.blue_spikes =new ArrayList<spikeNode>();
        this.orange_spikes =new ArrayList<spikeNode>();
        this.downSpikePane = new Pane();
        this.spikePane = new Pane();
        //Panes
        this.appRoot = new Pane();
        this.objectPane = new Pane();
        this.gameRoot = new Pane();//contains all barriers
        this.ground = new Pane();
        this.menuStack = new StackPane();
        this.menuBox = new VBox();
        this.upSprings = new Pane();
        this.downSprings = new Pane();
        this.doorPane = new Pane();
        this.lightsaberPane = new Pane();



        Rectangle backGround = new Rectangle(500, 600);
        for (int i = 0; i < level.size(); i++) {
            String line = level.get(i); //rows
            for (int j = 0; j < line.length(); j++) { //go through characters within the row
                switch (line.charAt(j)) {
                    case '0':
                        Node tile0 = createFloor(j * 60, i * 60, 60, 60, "/whiteTile.png",false,false);
                        this.floors.add(tile0);
                        break;
                    case '1':
                        Node boarder= createEntity(j * 60, i * 60, 60, 60, Color.BLACK,false," ");
                        this.platforms.add(boarder);
                        break;
                    case '2':
                        Node juke = creatJukeBox(j * 60, i * 60, 60,60);
                        this.platforms.add(juke);
                        break;
                    case '3':
                        Node difTile = createFloor(j * 60, i * 60, 60, 60,"/lvl0highwall.png",false,false);
                        this.platforms.add(difTile);
                        break;
                    case '4':
                        Node arrow = createArrow(j * 60, i * 60, 60, 60,"/room1Floor.png", "/gamearrow.png",0);
                        this.arrows.add(arrow);
                        break;
                    case '5':
                        Node tile5= createFloor(j * 60, i * 60, 60, 60,"/blackTile.png",false,false);
                        this.floors.add(tile5);
                        break;
                    case '6':
                        Node tile6 = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile6);
                        break ;
                    case'7':
                        Node arrow1 = createArrow(j * 60, i * 60, 60, 60,"/cobblestone.png", "/gamearrow.png",0);
                        this.arrows.add(arrow1);
                        break;
                    case'8':
                        Node tile8 = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                        this.floors.add(tile8);
                        break ;
                    case '9':
                        Node half_wall =createHalfWall(j * 60, i * 60, 60, 30,"/halfWall.png");
                        Node tile9 = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                        this.floors.add(tile9);
                        this.halfWalls.add(half_wall);
                        break;
                    //enter arrow room 1
                    case 'q':
                        Node enter_arrow_room_1 = createArrow(j * 60 , i * 60, 60, 60,"/room1Floor.png", "/gamearrow.png",1);
                        Node tile_q = createEntity(j * 60, i * 60, 30, 60,Color.BLACK,true,"LEFT");
                        Node tile_Q = createEntity(j * 60, i * 60, 90, 60,Color.BLACK,true,"RIGHT");
                        this.inBetweenWalls.add(tile_q);
                        this.inBetweenWalls.add(tile_Q);
                        this.downArrows.add(enter_arrow_room_1);
                        break;

                    //exit arrow room 1
                    case 'w':
                        Node exit_arrow_room_1 = createArrow(j * 60 , i * 60, 60, 60,"/room1Floor.png", "/gamearrow.png",1);
                        Node tile_w = createEntity(j * 60, i * 60, 30, 60,Color.BLACK,true,"LEFT");
                        Node tile_W = createEntity(j * 60, i * 60, 30, 60,Color.BLACK,true,"RIGHT");
                        this.inBetweenWalls.add(tile_w);
                        this.inBetweenWalls.add(tile_W);
                        this.arrows.add(exit_arrow_room_1);
                        break;
                    //room 1 middle floor before arrow
                    case'e':
                        Node tilee = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",true,false);
                        //make barriers around the middle block
                        Node tile_e = createEntity(j * 60, i * 60, 30, 60,Color.BLACK,true,"LEFT");
                        Node tile_E = createEntity(j * 60, i * 60, 90, 60,Color.BLACK,true,"RIGHT");
                        this.inBetweenWalls.add(tile_e);
                        this.inBetweenWalls.add(tile_E);
                        this.floors.add(tilee);
                        spikeNode e1 = new spikeNode(j*60,i*60,60,15,"/spikePurpleNew.png",true,"PURPLE",2,4);
                        spikeNode e2 = new spikeNode(j*60,i*60,60,15,"/spikeOrangeNew.png",true,"ORANGE",2,3);
                        spikeNode e3 = new spikeNode(j*60,i*60,60,15,"/spikeYelloNew.png",true,"YELLOW",2,2);
                        spikeNode e4 = new spikeNode(j*60,i*60,60,15,"/spikeBlueNew.png",true,"BLUE",2,1);
                        spikeNode e10 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"PURPLE",2,4);
                        spikeNode e20 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"ORANGE",2,3);
                        spikeNode e30 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"YELLOW",2,2);
                        spikeNode e40 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"BLUE",2,1);
                        //Node tile_e = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                        //this.floors.add(tile_e);
                        e1.setTranslateX(e1.getX()-30);
                        e1.setX(e1.getX()-30);
                        e1.setTranslateY(e1.getY());
                        e2.setTranslateX(e2.getX()-30);
                        e2.setX(e2.getX()-30);
                        e2.setTranslateY(e2.getY());
                        e3.setTranslateX(e3.getX()-30);
                        e3.setX(e3.getX()-30);
                        e3.setTranslateY(e3.getY());
                        e4.setTranslateX(e4.getX()-30);
                        e4.setX(e4.getX()-30);
                        e4.setTranslateY(e4.getY());
                        e10.setTranslateX(e10.getX()-30);
                        e10.setTranslateY(e10.getY());
                        e20.setTranslateX(e20.getX()-30);
                        e20.setTranslateY(e20.getY());
                        e30.setTranslateX(e30.getX()-30);
                        e30.setTranslateY(e30.getY());
                        e40.setTranslateX(e40.getX()-30);
                        e40.setTranslateY(e40.getY());
                        this.downSpikePane.getChildren().addAll(e10,e20,e30,e40);
                        this.spikePane.getChildren().addAll(e1,e2,e3,e4);
                        this.purple_spikes.add(e1);
                        this.orange_spikes.add(e2);
                        this.yellow_spikes.add(e3);
                        this.blue_spikes.add(e4);
                        break ;

                    //room 2 walls
                    //room 2 entrance arrow
                    //HORIZONTAL BOTTOM ORIENTED WALL
                    case'r':
                        Node tiler = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","RIGHT");
                        Node tile_r = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_r);
                        this.QuarterWalls.add(tiler);
                        break ;

                    //RIHGT T WALL
                    case't':
                        Node tilet = createQuarterWall_Vertical(j * 60, i * 60, 15, 60,"/room1Floor.png","T_T_RIGHT");
                        Node tile_T = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","T_LEFT");
                        Node tile_t = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.Vertical_QuarterWalls.add(tilet);
                        this.QuarterWalls.add(tile_T);
                        this.floors.add(tile_t);
                        break ;
                    // LEFT T WALL
                    case'y':
                        Node tiley = createQuarterWall_Vertical(j * 60, i * 60, 15, 60,"/room1Floor.png","T_T_LEFT");
                        Node tile_Y = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","T_LEFT");
                        Node tile_y = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.Vertical_QuarterWalls.add(tiley);
                        this.QuarterWalls.add(tile_Y);
                        this.floors.add(tile_y);
                        break;
                    //RIGHT block wall
                    //vertical wall will be the small block
                    //horizontal will fill the rest
                    case'u':
                        Node tileu = createQuarterWall_Vertical(j * 60, i * 60, 15, 15,"/room1Floor.png","R_BLOCK");
                        Node tile_U = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","R_BLOCK");
                        Node tile_u = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.Vertical_QuarterWalls.add(tileu);
                        this.QuarterWalls.add(tile_U);
                        this.floors.add(tile_u);
                        break ;
                    //LEFT block wall
                    case'i':
                        Node tilei = createQuarterWall_Vertical(j * 60, i * 60, 15, 15,"/room1Floor.png","L_BLOCK");
                        Node tile_I = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","L_BLOCK");
                        Node tile_i = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.Vertical_QuarterWalls.add(tilei);
                        this.QuarterWalls.add(tile_I);
                        this.floors.add(tile_i);
                        break ;
                    //WALL AFTER BLOCK WALL (L_BLOCK)
                    //LEFT
                    case'o':
                        Node tileo = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","L_BLOCK");
                        Node tile_o = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_o);
                        this.QuarterWalls.add(tileo);
                        break ;
                    //WALL AFTER BLOCK WALL (AR_BLOCK)
                    //RIGHT
                    case'p':
                        Node tilep = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","R_BLOCK");
                        Node tile_p = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_p);
                        this.QuarterWalls.add(tilep);
                        break ;
                    //RIGHT TOP L WALL
                    case'a':
                        Node tilea = createQuarterWall_Vertical(j * 60, i * 60, 15, 30,"/room1Floor.png","T_L_RIGHT");
                        Node tile_A = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","T_L_RIGHT");
                        Node tile_a = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.Vertical_QuarterWalls.add(tilea);
                        this.QuarterWalls.add(tile_A);
                        this.floors.add(tile_a);
                        break ;
                    // LEFT TOP L WALL
                    case's':
                        Node tiles = createQuarterWall_Vertical(j * 60, i * 60, 15, 30,"/room1Floor.png","T_L_LEFT");
                        Node tile_S = createQuarterWall_Horizontal(j * 60, i * 60, 60, 15,"/room1Floor.png","T_L_LEFT");
                        Node tile_s = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.Vertical_QuarterWalls.add(tiles);
                        this.QuarterWalls.add(tile_S);
                        this.floors.add(tile_s);
                        break;
                    //TOP ORIENTED LEFT INVERSE L WALL
                    case'd':
                        Node tiled = createQuarterWall_Horizontal(j * 60, i * 60, 30, 15,"/room1Floor.png","T_O_LEFT");
                        Node tileD = createQuarterWall_Vertical(j * 60, i * 60, 15, 30,"/room1Floor.png","T_O_LEFT");
                        Node tile_d = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_d);
                        this.QuarterWalls.add(tiled);
                        this.Vertical_QuarterWalls.add(tileD);
                        break;
                    //TOP ORIENTED RIGHT INVERSE L WALL
                    case'f':
                        Node tilef = createQuarterWall_Horizontal(j * 60, i * 60, 30, 15,"/room1Floor.png","T_O_RIGHT");
                        Node tileF = createQuarterWall_Vertical(j * 60, i * 60, 15, 30,"/room1Floor.png","T_O_RIGHT");
                        Node tile_f = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.QuarterWalls.add(tilef);
                        this.Vertical_QuarterWalls.add(tileF);
                        this.floors.add(tile_f);
                        break ;
                    //room 2 entrance arrow
                    case'h':
                        Node entrance_arrow_room2 =   createArrow(j * 60 , i * 60, 60, 60,"/room1Floor.png", "/gamearrowdown.png",2);
                        Node tile_h = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.downArrows.add(entrance_arrow_room2);
                        this.floors.add(tile_h);
                        break ;

                    //yellow button room 2
                    case'g':
                        if (roomNumber == 2) {
                            buttonNode g = new buttonNode(j*60,i*60,60,60,"/YELLOWUP.png",true,"YELLOW",2,0);
                            buttonNode gg = new buttonNode(j*60,i*60,60,60,"/YELLOWDOWN.png",false,"YELLOW",2,0);
                            Node tile_g = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_g);
                            gg.setTranslateX(gg.getX());
                            gg.setTranslateY(gg.getY());
                            g.setTranslateX(g.getX());
                            g.setTranslateY(g.getY());
                            this.downButtonPane.getChildren().add(gg);
                            this.buttonPane.getChildren().add(g);
                            this.yellow_buttons.add(g);
                        }
                        if (roomNumber == 3) {
                            buttonNode g = new buttonNode(j*60,i*60,60,60,"/yelloCircleOn.png",true,"YELLOW",2,0);
                            buttonNode gg = new buttonNode(j*60,i*60,60,60,"/yelloCircleOff.png",false,"YELLOW",2,0);
                            Node tile_g = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_g);
                            gg.setTranslateX(gg.getX());
                            gg.setTranslateY(gg.getY());
                            g.setTranslateX(g.getX());
                            g.setTranslateY(g.getY());
                            this.downButtonPane.getChildren().add(gg);
                            this.buttonPane.getChildren().add(g);
                            this.yellow_buttons.add(g);
                        }
                        break ;
                    case'G':
                        if (roomNumber == 2) {
                            buttonNode G = new buttonNode(j*60,i*60,60,60,"/YELLOWUP.png",true,"YELLOW",2,1);
                            buttonNode GG = new buttonNode(j*60,i*60,60,60,"/YELLOWDOWN.png",false,"YELLOW",2,1);
                            Node tile_G = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_G);
                            G.setTranslateX(G.getX());
                            G.setTranslateY(G.getY());
                            GG.setTranslateX(GG.getX());
                            GG.setTranslateY(GG.getY());
                            this.downButtonPane.getChildren().add(GG);
                            this.buttonPane.getChildren().add(G);
                            this.yellow_buttons.add(G);
                        }
                        if (roomNumber == 3) {
                            buttonNode G = new buttonNode(j*60,i*60,60,60,"/yelloCircleOn.png",true,"YELLOW",2,1);
                            buttonNode GG = new buttonNode(j*60,i*60,60,60,"/yelloCircleOff.png",false,"YELLOW",2,1);
                            Node tile_G = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_G);
                            G.setTranslateX(G.getX());
                            G.setTranslateY(G.getY());
                            GG.setTranslateX(GG.getX());
                            GG.setTranslateY(GG.getY());
                            this.downButtonPane.getChildren().add(GG);
                            this.buttonPane.getChildren().add(G);
                            this.yellow_buttons.add(G);
                        }
                        break ;
                    //purple button room 2
                    case'k':
                        if (roomNumber == 2) {
                            buttonNode k = new buttonNode(j*60,i*60,60,60,"/PURPLEUP.png",true,"PURPLE",2,0);
                            buttonNode kk = new buttonNode(j*60,i*60,60,60,"/PURPLEDOWN.png",false,"PURPLE",2,0);
                            Node tile_k = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_k);
                            k.setTranslateX(k.getX());
                            k.setTranslateY(k.getY());
                            kk.setTranslateX(kk.getX());
                            kk.setTranslateY(kk.getY());
                            this.downButtonPane.getChildren().add(kk);
                            this.buttonPane.getChildren().add(k);
                            this.purple_buttons.add(k);
                        }
                        if (roomNumber == 3) {
                            buttonNode k = new buttonNode(j*60,i*60,60,60,"/purpleCircleOn.png",true,"PURPLE",2,0);
                            buttonNode kk = new buttonNode(j*60,i*60,60,60,"/purpleCircleOff.png",false,"PURPLE",2,0);
                            Node tile_k = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_k);
                            k.setTranslateX(k.getX());
                            k.setTranslateY(k.getY());
                            kk.setTranslateX(kk.getX());
                            kk.setTranslateY(kk.getY());
                            this.downButtonPane.getChildren().add(kk);
                            this.buttonPane.getChildren().add(k);
                            this.purple_buttons.add(k);
                        }
                        break ;
                    case'K':
                        if (roomNumber == 2) {
                            buttonNode K = new buttonNode(j*60,i*60,60,60,"/PURPLEUP.png",true,"PURPLE",2,1);
                            buttonNode KK = new buttonNode(j*60,i*60,60,60,"/PURPLEDOWN.png",false,"PURPLE",2,1);
                            Node tile_K = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_K);
                            K.setTranslateX(K.getX());
                            K.setTranslateY(K.getY());
                            KK.setTranslateX(KK.getX());
                            KK.setTranslateY(KK.getY());
                            this.downButtonPane.getChildren().add(KK);
                            this.buttonPane.getChildren().add(K);
                            this.purple_buttons.add(K);
                        }
                        if (roomNumber == 3) {
                            buttonNode K = new buttonNode(j*60,i*60,60,60,"/purpleCircleOn.png",true,"PURPLE",2,1);
                            buttonNode KK = new buttonNode(j*60,i*60,60,60,"/purpleCircleOff.png",false,"PURPLE",2,1);
                            Node tile_K = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_K);
                            K.setTranslateX(K.getX());
                            K.setTranslateY(K.getY());
                            KK.setTranslateX(KK.getX());
                            KK.setTranslateY(KK.getY());
                            this.downButtonPane.getChildren().add(KK);
                            this.buttonPane.getChildren().add(K);
                            this.purple_buttons.add(K);
                        }
                        break ;
                    //blue button room 2
                    case'l':
                        if (roomNumber == 2) {
                            buttonNode l = new buttonNode(j*60,i*60,60,60,"/BLUEUP.png",true,"BLUE",2,2);
                            buttonNode ll = new buttonNode(j*60,i*60,60,60,"/BLUEDOWN.png",false,"BLUE",2,2);
                            Node tile_l = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_l);
                            l.setTranslateX(l.getX());
                            l.setTranslateY(l.getY());
                            ll.setTranslateX(ll.getX());
                            ll.setTranslateY(ll.getY());
                            this.buttonPane.getChildren().add(l);
                            this.downButtonPane.getChildren().add(ll);
                            this.blue_buttons.add(l);
                        }
                        if (roomNumber == 3) {
                            buttonNode l = new buttonNode(j*60,i*60,60,60,"/blueCircleOn.png",true,"BLUE",2,2);
                            buttonNode ll = new buttonNode(j*60,i*60,60,60,"/blueCircleOff.png",false,"BLUE",2,2);
                            Node tile_l = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_l);
                            l.setTranslateX(l.getX());
                            l.setTranslateY(l.getY());
                            ll.setTranslateX(ll.getX());
                            ll.setTranslateY(ll.getY());
                            this.buttonPane.getChildren().add(l);
                            this.downButtonPane.getChildren().add(ll);
                            this.blue_buttons.add(l);
                        }
                        break ;
                    case'L':
                        if (roomNumber == 2) {
                            buttonNode L = new buttonNode(j*60,i*60,60,60,"/BLUEUP.png",true,"BLUE",2,1);
                            buttonNode LL = new buttonNode(j*60,i*60,60,60,"/BLUEDOWN.png",false,"BLUE",2,1);
                            Node tile_L = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_L);
                            L.setTranslateX(L.getX());
                            L.setTranslateY(L.getY());
                            LL.setTranslateX(LL.getX());
                            LL.setTranslateY(LL.getY());
                            this.downButtonPane.getChildren().add(LL);
                            this.buttonPane.getChildren().add(L);
                            this.blue_buttons.add(L);
                        }
                        if (roomNumber == 3) {
                            buttonNode L = new buttonNode(j*60,i*60,60,60,"/blueCircleOn.png",true,"BLUE",2,1);
                            buttonNode LL = new buttonNode(j*60,i*60,60,60,"/blueCircleOff.png",false,"BLUE",2,1);
                            Node tile_L = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_L);
                            L.setTranslateX(L.getX());
                            L.setTranslateY(L.getY());
                            LL.setTranslateX(LL.getX());
                            LL.setTranslateY(LL.getY());
                            this.downButtonPane.getChildren().add(LL);
                            this.buttonPane.getChildren().add(L);
                            this.blue_buttons.add(L);
                        }
                        break ;
                    //orange button room 2
                    case'z':
                        if (roomNumber == 2) {
                            buttonNode z = new buttonNode(j*60,i*60,60,60,"/ORANGEUP.png",true,"ORANGE",2,2);
                            buttonNode zz = new buttonNode(j*60,i*60,60,60,"/ORANGEDOWN.png",false,"ORANGE",2,2);
                            Node tile_z = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_z);
                            z.setTranslateX(z.getX());
                            z.setTranslateY(z.getY());
                            zz.setTranslateX(zz.getX());
                            zz.setTranslateY(zz.getY());
                            this.downButtonPane.getChildren().add(zz);
                            this.buttonPane.getChildren().add(z);
                            this.orange_buttons.add(z);
                        }
                        if (roomNumber == 3) {
                            buttonNode z = new buttonNode(j*60,i*60,60,60,"/greenCircleOn.png",true,"GREEN",2,2);
                            buttonNode zz = new buttonNode(j*60,i*60,60,60,"/greenCircleOff.png",false,"GREEN",2,2);
                            Node tile_z = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_z);
                            z.setTranslateX(z.getX());
                            z.setTranslateY(z.getY());
                            zz.setTranslateX(zz.getX());
                            zz.setTranslateY(zz.getY());
                            this.downButtonPane.getChildren().add(zz);
                            this.buttonPane.getChildren().add(z);
                            this.orange_buttons.add(z);
                        }
                        break ;
                    case'Z':
                        if (roomNumber == 2) {
                            buttonNode Z = new buttonNode(j*60,i*60,60,60,"/ORANGEUP.png",true,"ORANGE",2,1);
                            buttonNode ZZ = new buttonNode(j*60,i*60,60,60,"/ORANGEDOWN.png",false,"ORANGE",2,1);
                            Node tile_Z = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_Z);
                            Z.setTranslateX(Z.getX());
                            Z.setTranslateY(Z.getY());
                            ZZ.setTranslateX(ZZ.getX());
                            ZZ.setTranslateY(ZZ.getY());
                            this.downButtonPane.getChildren().add(ZZ);
                            this.buttonPane.getChildren().add(Z);
                            this.orange_buttons.add(Z);
                        }
                        if (roomNumber == 3) {
                            buttonNode Z = new buttonNode(j*60,i*60,60,60,"/greenCircleOn.png",true,"GREEN",2,1);
                            buttonNode ZZ = new buttonNode(j*60,i*60,60,60,"/greenCircleOff.png",false,"GREEN",2,1);
                            Node tile_Z = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_Z);
                            Z.setTranslateX(Z.getX());
                            Z.setTranslateY(Z.getY());
                            ZZ.setTranslateX(ZZ.getX());
                            ZZ.setTranslateY(ZZ.getY());
                            this.downButtonPane.getChildren().add(ZZ);
                            this.buttonPane.getChildren().add(Z);
                            this.orange_buttons.add(Z);
                        }
                        break ;

                    case 'b':
                        Image img9 = new Image("/springDown.png");
                        Rectangle rekt9 = new Rectangle(60, 60);
                        rekt9.setTranslateX(j * 60);
                        rekt9.setTranslateY(i * 60);
                        ImagePattern imagePattern9 = new ImagePattern(img9);
                        rekt9.setFill(imagePattern9);
                        this.downSprings.getChildren().add(rekt9);
                        Node spring = rekt9;
                        this.downsprings.add(spring);
                        Image img10 = new Image("/springRight.png");
                        Rectangle rekt10 = new Rectangle(60, 60);
                        rekt10.setTranslateX(j * 60);
                        rekt10.setTranslateY(i * 60);
                        ImagePattern imagePattern10 = new ImagePattern( img10);
                        rekt10.setFill(imagePattern10);
                        this.upSprings.getChildren().add(rekt10);
                        Node highspring = rekt10;
                        this.platforms.add(highspring);
                        break;
                    //left spring
                    case 'B':
                        Image img90 = new Image("/springDown.png");
                        Rectangle rekt90 = new Rectangle(60, 60);
                        rekt90.setTranslateX(j * 60);
                        rekt90.setTranslateY(i * 60);
                        ImagePattern imagePattern90 = new ImagePattern(img90);
                        rekt90.setFill(imagePattern90);
                        this.downSprings.getChildren().add(rekt90);
                        Node spring0 = rekt90;
                        this.downsprings.add(spring0);
                        Image img100 = new Image("/springLeft.png");
                        Rectangle rekt100 = new Rectangle(60, 60);
                        rekt100.setTranslateX(j * 60);
                        rekt100.setTranslateY(i * 60);
                        ImagePattern imagePattern100 = new ImagePattern( img100);
                        rekt100.setFill(imagePattern100);
                        this.upSprings.getChildren().add(rekt100);
                        Node highspring0 = rekt100;
                        this.platforms.add(highspring0);
                        break;

                    //code for the spikes
                    //horizontal blue up
                    //room 3: blue horizontal up
                    case 'x':
                        if (roomNumber == 2) {
                            spikeNode x1 = new spikeNode(j*60,i*60,15,60,"/hori_spike_blue.png",true,"BLUE",2,4);
                            spikeNode x10 = new spikeNode(j*60,i*60,15,60,"/horiHoles.png",false,"BLUE",2,4);
                            Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_x);
                            x1.setTranslateX(x1.getX());
                            x1.setTranslateY(x1.getY());
                            x10.setTranslateX(x10.getX());
                            x10.setTranslateY(x10.getY());
                            this.downSpikePane.getChildren().add(x10);
                            this.spikePane.getChildren().add(x1);
                            this.blue_spikes.add(x1);
                        }
                        if (roomNumber == 3) {
                            spikeNode x1 = new spikeNode(j*60,i*60,15,60,"/hori_spike_blue.png",true,"BLUE",3,4);
                            spikeNode x10 = new spikeNode(j*60,i*60,15,60,"/horiHoles.png",false,"BLUE",3,4);
                            Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_x);
                            x1.setTranslateX(x1.getX()+15);
                            x1.setTranslateY(x1.getY()-30);
                            x1.setX(x1.getX()+15);
                            x1.setY(x1.getY()+30);
                            x10.setTranslateX(x10.getX()+15);
                            x10.setTranslateY(x10.getY()-30);
                            this.downSpikePane.getChildren().add(x10);
                            this.spikePane.getChildren().add(x1);
                            this.blue_spikes.add(x1);
                        }
                        break;
                    //orange and blue up
                    //room 3: green horizontal up
                    case 'X':
                        if (roomNumber == 2) {
                            spikeNode x1 = new spikeNode(j*60,i*60,60,15,"/spikeBlueNew.png",true,"BLUE",2,2);
                            spikeNode x2 = new spikeNode(j*60,i*60,60,15,"/spikeOrangeNew.png",true,"ORANGE",2,1);
                            spikeNode x10 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"BLUE",2,2);
                            spikeNode x20 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"ORAGNGE",2,1);
                            Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_x);
                            x1.setTranslateX(x1.getX());
                            x1.setTranslateY(x1.getY());
                            x2.setTranslateX(x2.getX());
                            x2.setTranslateY(x2.getY());
                            x10.setTranslateX(x10.getX());
                            x10.setTranslateY(x10.getY());
                            x20.setTranslateX(x20.getX());
                            x20.setTranslateY(x20.getY());

                            this.downSpikePane.getChildren().addAll(x10,x20);
                            this.spikePane.getChildren().addAll(x1,x2);
                            this.blue_spikes.add(x1);
                            this.orange_spikes.add(x2);
                        }
                        if (roomNumber == 3) {
                            spikeNode x1 = new spikeNode(j*60,i*60,15,60,"/horiSpikeGreen.png",true,"GREEN",3,4);
                            spikeNode x10 = new spikeNode(j*60,i*60,15,60,"/horiHoles.png",false,"GREEN",3,4);
                            Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_x);
                            x1.setTranslateX(x1.getX()+30);
                            x1.setX(x1.getX()+30);
                            x1.setTranslateY(x1.getY()-30);
                            x1.setY(x1.getY()-30);
                            x10.setTranslateX(x10.getX()+30);
                            x10.setTranslateY(x10.getY()-30);
                            this.downSpikePane.getChildren().add(x10);
                            this.spikePane.getChildren().add(x1);
                            this.orange_spikes.add(x1);
                        }
                        break;
                    //blue down orange up
                    //room 3: purple yellow up
                    case 'c':
                        if (roomNumber == 2) {
                            spikeNode x1 = new spikeNode(j*60,i*60,60,15,"/spikeBlueNew.png",false,"BLUE",2,2);
                            spikeNode x2 = new spikeNode(j*60,i*60,60,15,"/spikeOrangeNew.png",true,"ORANGE",2,1);
                            spikeNode x10 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"BLUE",2,2);
                            spikeNode x20 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"ORANGE",2,1);
                            Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                            this.floors.add(tile_x);
                            x1.setTranslateX(x1.getX());
                            x1.setTranslateY(x1.getY());
                            x2.setTranslateX(x2.getX());
                            x2.setTranslateY(x2.getY());
                            x10.setTranslateX(x10.getX());
                            x10.setTranslateY(x10.getY());
                            x20.setTranslateX(x20.getX());
                            x20.setTranslateY(x20.getY());

                            this.downSpikePane.getChildren().addAll(x10,x20);
                            this.spikePane.getChildren().addAll(x1,x2);
                            this.blue_spikes.add(x1);
                            this.orange_spikes.add(x2);
                        }
                        if (roomNumber == 3) {
                            spikeNode x1 = new spikeNode(j*60,i*60,60,15,"/spikePurpleNew.png",true,"PURPLE",3,3);
                            spikeNode x2 = new spikeNode(j*60,i*60,60,15,"/spikeYelloNew.png",true,"YELLOW",3,4);
                            spikeNode x10 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"PURPLE",3,3);
                            spikeNode x20 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"YELLOW",3,4);
                            Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                            this.floors.add(tile_x);
                            x1.setTranslateX(x1.getX()+30);
                            x1.setTranslateY(x1.getY()-30);
                            x1.setX(x1.getY()+30);
                            x1.setY(x1.getY()-30);
                            x2.setTranslateX(x2.getX()+30);
                            x2.setTranslateY(x2.getY()-30);
                            x2.setX(x1.getY()+30);
                            x2.setY(x1.getY()-30);
                            x10.setTranslateX(x10.getX()+30);
                            x10.setTranslateY(x10.getY()-30);
                            x20.setTranslateX(x20.getX()+30);
                            x20.setTranslateY(x20.getY()-30);

                            this.downSpikePane.getChildren().addAll(x10,x20);
                            this.spikePane.getChildren().addAll(x1,x2);
                            this.purple_spikes.add(x1);
                            this.yellow_spikes.add(x2);
                        }
                        break;
                    //yellow up
                    case 'C':
                        spikeNode x1 = new spikeNode(j*60,i*60,60,15,"/spikeYelloNew.png",true,"YELLOW",2,1);
                        spikeNode x10 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"YELLOW",2,1);
                        Node tile_x = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                        this.floors.add(tile_x);
                        x1.setTranslateX(x1.getX());
                        x1.setTranslateY(x1.getY());
                        x10.setTranslateX(x10.getX());
                        x10.setTranslateY(x10.getY());
                        this.downSpikePane.getChildren().add(x10);
                        this.spikePane.getChildren().add(x1);
                        this.yellow_spikes.add(x1);
                        break;
                    //yellow down
                    case 'v':
                        spikeNode v1 = new spikeNode(j*60,i*60,60,15,"/spikeYelloNew.png",false,"YELLOW",2,1);
                        spikeNode v10 = new spikeNode(j*60,i*60,60,15,"/emptySpikes.png",false,"YELLOW",2,1);
                        Node tile_v = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                        this.floors.add(tile_v);
                        v1.setTranslateX(v1.getX());
                        v1.setTranslateY(v1.getY());
                        v10.setTranslateX(v10.getX());
                        v10.setTranslateY(v10.getY());
                        this.downSpikePane.getChildren().add(v10);
                        this.spikePane.getChildren().add(v1);
                        this.yellow_spikes.add(v1);
                        break;
                    //horizontal blue down
                    case 'V':
                        spikeNode V1 = new spikeNode(j*60,i*60,15,60,"/hori_spike_blue.png",false,"BLUE",2,4);
                        spikeNode V10 = new spikeNode(j*60,i*60,15,60,"/horiHoles.png",false,"BLUE",2,4);
                        Node tile_V = createFloor(j * 60, i * 60, 60, 60,"/room1Floor.png",false,false);
                        this.floors.add(tile_V);
                        V1.setTranslateX(V1.getX());
                        V1.setTranslateY(V1.getY());
                        V10.setTranslateX(V10.getX());
                        V10.setTranslateY(V10.getY());
                        this.downSpikePane.getChildren().add(V10);
                        this.spikePane.getChildren().add(V1);
                        this.blue_spikes.add(V1);
                        break;
                        //2
                    case 'n':
                        doorNode d1 = new doorNode(j*60,i*60,15,80,  "/room1Floor.png",  0,2,false     ,2,1);
                        Node tile_d1 = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_d1);
                        d1.setDoorNum(1);
                        d1.setTranslateX(d1.getX());
                        d1.setTranslateY(d1.getY());
                        this.doors.add(d1);
                        this.doorPane.getChildren().add(d1);
                        break;
                        //3
                    case 'm':
                        doorNode d2 = new doorNode(j*60,i*60,15,80,  "/room1Floor.png",  0,3,true,2,2);
                        Node tile_d2 = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_d2);
                        d2.setDoorNum(2);
                        d2.setTranslateX(d2.getX());
                        d2.setTranslateY(d2.getY());
                        this.doors.add(d2);
                        this.doorPane.getChildren().add(d2);
                        break;
                    //upper left
                    //0
                    case ',':
                        doorNode d3 = new doorNode(j*60,i*60,15,85,  "/room1Floor.png",  0,0,false,2,3);
                        Node tile_d3 = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        this.floors.add(tile_d3);
                        d3.setDoorNum(3);
                        d3.setTranslateX(d3.getX());
                        d3.setTranslateY(d3.getY());
                        this.doors.add(d3);
                        this.doorPane.getChildren().add(d3);
                        break;
                        //upper right
                    //1
                    case '.':
                        doorNode d4 = new doorNode(j*60,i*60,15,85,  "/room1Floor.png",  0,1,false,2,4);
                        Node tile_d4 = createFloor(j * 60, i * 60, 60, 60,"/cobblestone.png",false,false);
                        d4.setDoorNum(4);
                        d4.setTranslateX(d4.getX());
                        d4.setTranslateY(d4.getY());
                        this.floors.add(d4);
                        this.doors.add(d4);
                        this.doorPane.getChildren().add(d4);
                        break;
                    case '?':
                        createLightsaber(j*60,i*60,80,15,"/lightsaber.png");
                        break;
                    case '$':
                        Node arrow_left = createArrow(j * 60, i * 60, 60, 60,"/room1Floor.png", "/gamearrowleft.png",0);
                        this.arrows.add(arrow_left);
                        break;
                    case '%':
                        Node arrow_up = createArrow(j * 60, i * 60, 60, 60,"/room1Floor.png", "/gamearrow.png",0);
                        this.arrows.add(arrow_up);
                        break;

                }


            }
        }
        //adds everything to the appRoot
        appRoot.getChildren().addAll(backGround, ground, gameRoot,downSprings, objectPane,downButtonPane,buttonPane,downSpikePane,spikePane,doorPane,lightsaberPane);
    }

    //getters
    public ArrayList<Node> getDownArrows() {
        return downArrows;
    }
    public ArrayList<Node> getQuarterWalls() {
        return QuarterWalls;
    }
    public ArrayList<Node> getPlatforms() {
        return platforms;
    }
    public ArrayList<Node> getArrows() {
        return arrows;
    }
    public ArrayList<Node>getHalfWalls() {
        return halfWalls;
    }
    public ArrayList<Node>getBetweenWalls() {
        return inBetweenWalls;
    }
    public ArrayList<Node> getSprings() {
        return downsprings;
    }
    public ArrayList<Node> getVertical_QuarterWalls(){
        return Vertical_QuarterWalls;
    }
    public ArrayList<buttonNode>getYellowButtons(){return yellow_buttons;}
    public ArrayList<buttonNode>getPurple_buttons(){return purple_buttons;}
    public ArrayList<buttonNode>getOrange_buttons(){return orange_buttons;}
    public ArrayList<buttonNode>getBlue_buttons(){return blue_buttons;}
    public ArrayList<spikeNode>getYellowSpikes(){return yellow_spikes;}
    public ArrayList<spikeNode>getPurpleSpikes(){return purple_spikes;}
    public ArrayList<spikeNode>getOrangeSpikes(){return orange_spikes;}
    public ArrayList<spikeNode>getBlueSpikes(){return blue_spikes;}
    public ArrayList<doorNode> getDoors() {return doors;}

    private void createLightsaber(int x,int y,int w,int h, String url) {
        Rectangle rekt = new Rectangle(60,60); //intial floor
        Rectangle lightsaber = new Rectangle(w,h);
        Image img= new Image(url);
        ImagePattern imagePattern = new ImagePattern(img);
        Image img2= new Image("/cobblestone.png");
        ImagePattern imagePattern2 = new ImagePattern(img2);
        lightsaber.setTranslateY(y+25);
        lightsaber.setTranslateX(x+30);
        lightsaber.setFill(imagePattern);
        rekt.setFill(imagePattern2);
        rekt.setTranslateX(x);
        rekt.setTranslateY(y);
        this.lightsaberPane.getChildren().addAll(rekt,lightsaber);
        Node saber = lightsaber;
        lightsabers.add(saber);
    }
    //create rm3 quarter walls
    private Node createQuarterWall_Horizontal ( int x, int y, int w, int h, String url,String position){
        Rectangle rekt = new Rectangle(w,h); //intial floor
        Image img= new Image(url);
        ImagePattern imagePattern = new ImagePattern(img);
        if(position.equals("LEFT")){
            rekt.setTranslateY(y+35);
            rekt.setTranslateX(x+45);
        }
        else if(position.equals("R_BLOCK")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x-15);
        }
        else if(position.equals("L_BLOCK")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x+15);
        }
        else if(position.equals("T_RIGHT")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x+15);
        }
        else if(position.equals("T_LEFT")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x);
        }
        else if(position.equals("T_L_LEFT")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x+15);
        }
        else if(position.equals("T_L_RIGHT")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x-15);
        }
        else if( position.equals("T_O_LEFT")){
            rekt.setTranslateY(y);
            rekt.setTranslateX(x+30);
        }
        else if( position.equals("T_O_RIGHT")){
            rekt.setTranslateY(y);
            rekt.setTranslateX(x);
        }
        else{
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x);
        }
        rekt.setFill(imagePattern);
        this.gameRoot.getChildren().add(rekt);
        return rekt;
    }
    private Node createQuarterWall_Vertical ( int x, int y, int w, int h, String url,String position){
        Rectangle rekt = new Rectangle(w,h); //intial floor
        Image img= new Image(url);
        ImagePattern imagePattern = new ImagePattern(img);
        rekt.setFill(imagePattern);
        if(position.equals("LEFT")){
            rekt.setTranslateY(y+25);
            rekt.setTranslateX(x);
        }
        else if(position.equals("R_BLOCK")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x+45);
        }
        else if(position.equals("L_BLOCK")){
            rekt.setTranslateY(y+45);
            rekt.setTranslateX(x);
        }
        else if(position.equals("T_RIGHT")){
            rekt.setTranslateY(y+30);
            rekt.setTranslateX(x);
        }
        else if(position.equals("T_LEFT")){
            rekt.setTranslateY(y+30);
            rekt.setTranslateX(x+45);
        }
        else if(position.equals("T_T_RIGHT")){
            rekt.setTranslateY(y+25);
            rekt.setTranslateX(x-15);
        }
        else if(position.equals("T_T_LEFT")){
            rekt.setTranslateY(y+25);
            rekt.setTranslateX(x+60);
        }
        else if(position.equals("T_L_LEFT")){
            rekt.setTranslateY(y+30);
            rekt.setTranslateX(x+75);
        }
        else if(position.equals("T_L_RIGHT")){
            rekt.setTranslateY(y+30);
            rekt.setTranslateX(x-30);
        }
        else if(position.equals("T_O_RIGHT")){
            rekt.setTranslateY(y);
            rekt.setTranslateX(x+30);
        }
        else if(position.equals("T_O_LEFT")){
            rekt.setTranslateY(y);
            rekt.setTranslateX(x+15);
        }
        else{
            rekt.setTranslateY(y+25);
            rekt.setTranslateX(x+45);

        }
        this.gameRoot.getChildren().add(rekt);
        return rekt;
    }
    private Node createHalfWall ( int x, int y, int w, int h, String url){
        Rectangle rekt = new Rectangle(w,h); //intial floor
        Image img= new Image(url);
        ImagePattern imagePattern = new ImagePattern(img);
        rekt.setTranslateY(y+30);
        rekt.setTranslateX(x);
        rekt.setFill(imagePattern);
        this.gameRoot.getChildren().add(rekt);
        return rekt;

    }
    private Node creatJukeBox(int x, int y, int w, int h) {
        Rectangle rekt = new Rectangle(w,h);
        Image img = new Image("/whiteTile.png");
        rekt.setTranslateX(x);
        rekt.setTranslateY(y);
        Main.jukee.setTranslateX(x);
        Main.jukee.setTranslateY(200);
        ImagePattern imagePattern = new ImagePattern(img);
        rekt.setFill(imagePattern);
        this.ground.getChildren().add(rekt);
        this.gameRoot.getChildren().add(Main.jukee);
        Main.jukee.animation.play();
        return Main.jukee;
    }
    //JukeBox Section ends here - *****************
    private Node createFloor(int x, int y, int w, int h, String url,boolean special,boolean next_to_middle ) {

        Image img = new Image(url);
        Rectangle rekt = new Rectangle(w, h);
        if(special == true ){
            rekt.setTranslateX(x-30);
            rekt.setTranslateY(y);
            ImagePattern imagePattern = new ImagePattern( img);
            rekt.setFill(imagePattern);
            this.gameRoot.getChildren().add(rekt);
        }
        else{
            rekt.setTranslateX(x);
            rekt.setTranslateY(y);
            ImagePattern imagePattern = new ImagePattern( img);
            rekt.setFill(imagePattern);
            this.ground.getChildren().add(rekt);
        }
        return rekt;
    }
    private Node createArrow(int x, int y, int w, int h, String url,String url2,int roomNum) {
        StackPane arrowStack = new StackPane();
        Image img = new Image(url);
        Image img2 = new Image(url2);
        ImageView i1 = new ImageView(img);
        ImageView i2 = new ImageView(img2);
        i1.setFitHeight(h);
        i1.setFitWidth(w);
        i2.setFitHeight(h);
        i2.setFitWidth(w);
        arrowStack.getChildren().addAll(i1,i2);
        if (roomNum == 1) {
            arrowStack.setTranslateX(x-30);
            arrowStack.setTranslateY(y);
        }
        else if (roomNum == 2) {
            arrowStack.setTranslateX(x+30);
            arrowStack.setTranslateY(y);
        }
        else{
            arrowStack.setTranslateX(x);
            arrowStack.setTranslateY(y);
        }
        this.gameRoot.getChildren().add(arrowStack);
        return arrowStack;

    }
    private Node createEntity(int x, int y, int w, int h, Color color,boolean next_to_middle, String position) {
        Rectangle entity = new Rectangle(w, h);
        if(next_to_middle == true && position.equals("LEFT")){
            entity.setTranslateX(x-60);
            entity.setTranslateY(y);
        }
        else if(next_to_middle == true && position.equals("RIGHT")){
            entity.setTranslateX(x+30);
            entity.setTranslateY(y);
        }
        else{
            entity.setTranslateX(x);
            entity.setTranslateY(y);
        }
        entity.setFill(color);
        this.gameRoot.getChildren().add(entity);
        return entity;
    }


    //gets the appRoot for making the scenes in main
    public Pane getappRoot() {
        return this.appRoot;
    }





    //move the player to a different room
    public void removePlayerRoom() {
        this.appRoot.getChildren().remove(Main.darth);
    }
    public void movePlayerRoom() {
        this.appRoot.getChildren().addAll(Main.darth);
    }

    //movement functions
    public void movePlayerX(int value) {
        onButton();
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform: getPlatforms()) {
                if (Main.darth.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() + 20 == platform.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == platform.getTranslateX() + 50) {
                            return;
                        }
                    }
                }
            }
            for(Node halfWall: this.getHalfWalls()) {
                if (Main.darth.getBoundsInParent().intersects(halfWall.getBoundsInParent())) {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() + 32 == halfWall.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == halfWall.getTranslateX() + 50) {
                            return;
                        }
                    }


                }
            }
            for(Node q_wall : this.getQuarterWalls()){
                if (Main.darth.getBoundsInParent().intersects(q_wall.getBoundsInParent())) {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == q_wall.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == q_wall.getTranslateX() + 50) {
                            return;
                        }
                    }
                }
            }
            for(Node b_wall : this.getBetweenWalls()){
                if (Main.darth.getBoundsInParent().intersects(b_wall.getBoundsInParent())) {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == b_wall.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == b_wall.getTranslateX() + 20) {
                            return;
                        }
                    }
                }
            }
            for(Node v_wall : this.getVertical_QuarterWalls()){
                if (Main.darth.getBoundsInParent().intersects(v_wall.getBoundsInParent())) {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == v_wall.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == v_wall.getTranslateX() + 5) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode yellow_spike : this.getYellowSpikes()){
                if((Main.darth.getBoundsInParent().intersects(yellow_spike.getBoundsInParent()) && (yellow_spike.getState()==true)))
                {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == yellow_spike.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == yellow_spike.getTranslateX() + 45) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode purple_spike : this.getPurpleSpikes()){
                if((Main.darth.getBoundsInParent().intersects(purple_spike.getBoundsInParent()) && (purple_spike.getState()==true)))
                {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == purple_spike.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == purple_spike.getTranslateX() + 45) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode orange_spike : this.getOrangeSpikes()){
                if((Main.darth.getBoundsInParent().intersects(orange_spike.getBoundsInParent()) && (orange_spike.getState()==true)))
                {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == orange_spike.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == orange_spike.getTranslateX() + 45) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode blue_spike : this.getBlueSpikes()){
                if((Main.darth.getBoundsInParent().intersects(blue_spike.getBoundsInParent()) && (blue_spike.getState()==true)))
                {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == blue_spike.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == blue_spike.getTranslateX() + 15) {
                            return;
                        }
                    }
                }
            }
            for(doorNode d : this.getDoors()) {
                if ((Main.darth.getBoundsInParent().intersects(d.getBoundsInParent())) && d.getState() == false) {
                    if (movingRight) {
                        direction = "right";
                        if (Main.darth.getTranslateX() +20 == d.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        direction = "left";
                        if (Main.darth.getTranslateX() == d.getTranslateX() + 15) {
                            return;
                        }
                    }
                }
            }
            if(movingRight){
                direction = "right";
                Main.darth.setTranslateX(Main.darth.getTranslateX() + 1 );
            }
            else{
                direction = "left";
                Main.darth.setTranslateX(Main.darth.getTranslateX()-1);
            }
        }
    }
    public void movePlayerY(int value) {
        onButton();
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform: getPlatforms()) {
                if (Main.darth.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == platform.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == platform.getTranslateY() + 50) {
                            return;
                        }
                    }
                }
            }
            for(Node halfWall: this.getHalfWalls()) {
                if (Main.darth.getBoundsInParent().intersects(halfWall.getBoundsInParent())) {
                    if (Main.darth.getBoundsInParent().intersects(halfWall.getBoundsInParent())) {
                        if (movingDown) {
                            direction = "down";
                            if (Main.darth.getTranslateY() + 40 == halfWall.getTranslateY()) {
                                Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                                return;
                            }
                        }
                        else {
                            direction = "up";
                            if (Main.darth.getTranslateY() == halfWall.getTranslateY() + 30) {
                                return;
                            }
                        }
                    }
                }
            }

            for(Node q_wall : this.getQuarterWalls()){
                if (Main.darth.getBoundsInParent().intersects(q_wall.getBoundsInParent())) {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == q_wall.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == q_wall.getTranslateY() + 15) {
                            return;
                        }
                    }
                }
            }
            for(Node b_wall : this.getBetweenWalls()){
                if (Main.darth.getBoundsInParent().intersects(b_wall.getBoundsInParent())) {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 32 == b_wall.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == b_wall.getTranslateY() + 50) {
                            return;
                        }
                    }
                }
            }
            for(Node v_wall : this.getVertical_QuarterWalls()){
                if (Main.darth.getBoundsInParent().intersects(v_wall.getBoundsInParent())) {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 32 == v_wall.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == v_wall.getTranslateY() + 50) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode yellow_spike : this.getYellowSpikes()){
                if((Main.darth.getBoundsInParent().intersects(yellow_spike.getBoundsInParent()) && (yellow_spike.getState()==true)))
                {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == yellow_spike.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == yellow_spike.getTranslateY() + 15) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode purple_spike : this.getPurpleSpikes()){
                if((Main.darth.getBoundsInParent().intersects(purple_spike.getBoundsInParent()) && (purple_spike.getState()==true)))
                {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == purple_spike.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == purple_spike.getTranslateY() + 15) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode orange_spike : this.getOrangeSpikes()){
                if((Main.darth.getBoundsInParent().intersects(orange_spike.getBoundsInParent()) && (orange_spike.getState()==true)))
                {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == orange_spike.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() ==orange_spike.getTranslateY() + 15) {
                            return;
                        }
                    }
                }
            }
            for(spikeNode blue_spike : this.getBlueSpikes()){
                if((Main.darth.getBoundsInParent().intersects(blue_spike.getBoundsInParent()) && (blue_spike.getState()==true)))
                {
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == blue_spike.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == blue_spike.getTranslateY() + 15) {
                            return;
                        }
                    }
                }
            }
            for(doorNode d : this.getDoors()){
                if((Main.darth.getBoundsInParent().intersects(d.getBoundsInParent()))&&d.getState()==false){
                    if (movingDown) {
                        direction = "down";
                        if (Main.darth.getTranslateY() + 40 == d.getTranslateY()) {
                            Main.darth.setTranslateY(Main.darth.getTranslateY() - 1);
                            return;
                        }
                    }
                    else {
                        direction = "up";
                        if (Main.darth.getTranslateY() == d.getTranslateY() + 15) {
                            return;
                        }
                    }
                }
            }
            if(movingDown){
                direction = "down";
                Main.darth.setTranslateY(Main.darth.getTranslateY() + 1 );
            }
            else{
                direction = "up";
                Main.darth.setTranslateY(Main.darth.getTranslateY()-1);
            }
        }
    }
    public boolean onArrow(Room one,Room two,Room three){
        boolean toReturn= false ;
        //going up a level
        for(Node uparrow: this.getArrows()) {
            if (Main.darth.getBoundsInParent().intersects(uparrow.getBoundsInParent())) {
                vCount++;
                if(vCount == 60 ){
                    this.vCount = 0;
                    if(Main.darth.getRoom() == 1) {
                        this.fadeOut(Main.room1,Main.room2,0,2);
                    }
                    else if (Main.darth.getRoom() == 2) {
                        this.fadeOut(Main.room2,Main.room3,0,3);
                    }
                    toReturn = true;
                }
            }
        }
        //going down a level
        for(Node downarrow: this.getDownArrows()) {
            if (Main.darth.getBoundsInParent().intersects(downarrow.getBoundsInParent())) {
                vCount++;
                if(vCount == 60 ){
                    this.vCount = 0;
                    if(Main.darth.getRoom() == 2) {
                        this.fadeOut(Main.room2,Main.room1,1,1);
                    }
                    else if (Main.darth.getRoom() == 3) {
                        this.fadeOut(Main.room3,Main.room2,1,2);
                    }
                    toReturn = true;
                }
            }
        }
        return toReturn;
    }

    //fade transition
    public void fadeOut(Room remove, Room move, int pos, int toNum) {
        Rectangle rect = new Rectangle(700,700);
        FadeTransition fadetransition = new FadeTransition();
        fadetransition.setDuration(Duration.millis(400));
        this.appRoot.getChildren().add(rect);
        fadetransition.setNode(this.appRoot);
        fadetransition.setFromValue(0);
        fadetransition.setToValue(1);
        fadetransition.setOnFinished((ActionEvent event) -> {
            Main.darth.changeRoom(toNum, pos);
            Main.darth.changeRoomNum(toNum);
            remove.removePlayerRoom();
            move.movePlayerRoom();
            this.appRoot.getChildren().remove(rect);
        });
        fadetransition.play();
    }

    //animating the character getting pushed
    TranslateTransition transition = new TranslateTransition();

    //collision for springs
    public boolean onSpring(char direction) {
        boolean toReturn= false ;
        for(Node downsprings: this.getSprings()) {
            if (Main.darth.getBoundsInParent().intersects(downsprings.getBoundsInParent())) {
                switch(direction) {
                    //moves the player left
                    case 'A':
                        Main.darth.setTranslateY(480);
                        transition.setDuration(Duration.seconds(0.5));
                        transition.setToX(Main.darth.getTranslateX() - 120);
                        //add the up spring in the appRoot over the down spring to act as a wall
                        transition.setNode(Main.darth);
                        transition.play();
                        appRoot.getChildren().remove(downSprings);
                        gameRoot.getChildren().addAll(upSprings);
                        break;
                    //moves the player right
                    case 'D':
                        Main.darth.setTranslateY(480);
                        transition.setDuration(Duration.seconds(0.5));
                        transition.setToX(Main.darth.getTranslateX() + 120);
                        //add the up spring in the appRoot over the down spring to act as a wall
                        transition.setNode(Main.darth);
                        transition.play();
                        appRoot.getChildren().remove(downSprings);
                        gameRoot.getChildren().addAll(upSprings);
                        break;
                }
            }
        }
        return toReturn;
    }
    private int doornum = 3;
    public void doorTimer() {

        boolean opened = false;



        for(doorNode doors : this.getDoors()) {
           //door 1
            if (doors.getDoorNum() == 0 && doors.getState()==false && doornum ==3) {
               doors.setState(true);
               doors.setOpacity(0);

               doornum = 0;
               break;
            }
            else if (doors.getDoorNum() == 1  && doors.getState()==false && doornum==0) {
                doors.setState(true);
                doors.setOpacity(0);
                doornum =1;
                break;
            }
            else if (doors.getDoorNum() == 2  && doors.getState()==false&& doornum==1) {
                doors.setState(true);
                doors.setOpacity(0);
               doornum=2;
               break;


            }
            else if (doors.getDoorNum() == 3  && doors.getState()==false&& doornum==2) {
                doors.setState(true);
                doors.setOpacity(0);
                doornum=3;
                break;
            }

        }
        //change states to false
        for(doorNode dd : this.getDoors()){
           if(dd.getDoorNum()==3 && doornum == 0){
               dd.setState(false);
               dd.setOpacity(1);
               break;
           }
           else if(dd.getDoorNum()==0 && doornum == 1){
                dd.setState(false);
               dd.setOpacity(1);
                break;
            }
           else  if(dd.getDoorNum()==1 && doornum == 2){
                dd.setState(false);
               dd.setOpacity(1);
                break;
            }
            else if(dd.getDoorNum()==2 && doornum == 3){
                dd.setState(false);
               dd.setOpacity(1);
                break;
            }

        }

    }
    //check if on lightsaber
    public void onLightsaber() {
        for (Node lightsaber: this.lightsabers) {
            if (Main.darth.getBoundsInParent().intersects(lightsaber.getBoundsInParent())) {
                lightsaberPane.getChildren().remove(lightsaber);
            }
        }
    }


    //checks to see if the player collides with button
    public boolean onButton (){
        boolean toReturn= false ;
        for(buttonNode buttonsy : this.getYellowButtons()){
            if (Main.darth.getBoundsInParent().intersects(buttonsy.getBoundsInParent())) {
                if(buttonsy.getState()==true&&buttonsy.getColor().equals("YELLOW")){
                    buttonsy.setState(false);
                    for (spikeNode ySpike : this.getYellowSpikes()) {
                        if (ySpike.getState() == false) {
                            ySpike.setState(true);
                        }
                        else if (ySpike.getState() == true) {
                            ySpike.setState(false);
                        }
                    }
                    buttonPane.getChildren().remove(buttonsy);
                    return true;
                }
            }
        }
        for(buttonNode buttonsp : this.getPurple_buttons()){
            if (Main.darth.getBoundsInParent().intersects(buttonsp.getBoundsInParent())) {
                if (buttonsp.getState() == true && buttonsp.getColor().equals("PURPLE")) {
                    buttonsp.setState(false);
                    for (spikeNode pSpike : this.getPurpleSpikes()) {
                        if (pSpike.getState() == false) {
                            pSpike.setState(true);
                        }
                        else if (pSpike.getState() == true) {
                            pSpike.setState(false);
                        }
                    }
                    buttonPane.getChildren().remove(buttonsp);
                    return true;

                }
            }
        }
        for(buttonNode buttonso : this.getOrange_buttons()){
            if (Main.darth.getBoundsInParent().intersects(buttonso.getBoundsInParent())) {
                if(buttonso.getState()==true){
                    buttonso.setState(false);
                    for (spikeNode oSpike : this.getOrangeSpikes()) {
                        if (oSpike.getState() == false) {
                            oSpike.setState(true);
                        }
                        else if (oSpike.getState() == true) {
                            oSpike.setState(false);
                        }
                    }
                    buttonPane.getChildren().remove(buttonso);
                    return true;
                }
            }
        }
        for(buttonNode buttonsb : this.getBlue_buttons()){
            if (Main.darth.getBoundsInParent().intersects(buttonsb.getBoundsInParent())) {
                if(buttonsb.getState()==true&&buttonsb.getColor().equals("BLUE")){
                    buttonsb.setState(false);
                    for (spikeNode bSpike : this.getBlueSpikes()) {
                        if (bSpike.getState() == false) {
                            bSpike.setState(true);
                        }
                        else if (bSpike.getState() == true) {
                            bSpike.setState(false);
                        }
                    }
                    buttonPane.getChildren().remove(buttonsb);
                    return true;
                }
            }
        }
        return toReturn;
    }

    //check the states of the spikes and change the opacity accordingly
    public void spikeState() {
        for (spikeNode ySpike : this.getYellowSpikes()) {
            if (ySpike.getState() == false) {
                ySpike.setOpacity(0);
            }
            else {
                ySpike.setOpacity(1);
            }
        }
        for (spikeNode bSpike : this.getBlueSpikes()) {
            if (bSpike.getState() == false) {
                bSpike.setOpacity(0);
            }
            else {
                bSpike.setOpacity(1);
            }
        }
        for (spikeNode pSpike : this.getPurpleSpikes()) {
            if (pSpike.getState() == false) {
                pSpike.setOpacity(0);
            }
            else {
                pSpike.setOpacity(1);
            }
        }
        for (spikeNode oSpike : this.getOrangeSpikes()) {
            if (oSpike.getState() == false) {
                oSpike.setOpacity(0);
            }
            else {
                oSpike.setOpacity(1);
            }
        }
    }


    //creates the in-game menu
    public void inGameMenu() {
        if (count == 0) {
            count ++;
            //StackPane menuStack = new StackPane();
            Rectangle menuRect = new Rectangle(180, 240);
            menuRect.setFill(Color.DARKGREY);
            //
            Button about = new Button("About...");
            about.setOnAction(e -> System.out.println("This game was brought to you by Connor, Alex, and Ricky!"));
            //
            Button restart = new Button("Restart...");
            restart.setOnAction(e -> Main.darth.changeRoom(1, 0));
            //
            Button save = new Button("Save Game...");
            //
            Button load = new Button("Load Game...");
            //
            Button clipboard = new Button("Clipboard...");
            //
            Button sound = new Button("Sound...");
            //
            Button quit = new Button("Quit...");
            quit.setOnAction(e -> System.exit(0));

            menuBox.getChildren().addAll(about, restart, save, load, clipboard, sound, quit);
            menuStack.getChildren().addAll(menuRect, menuBox);
            menuStack.setTranslateX(60);
            menuStack.setTranslateY(60);
        }
    }
    public void makeMenu(){
        this.appRoot.getChildren().add(menuStack);
    }
    public void removeMenu() {

        this.appRoot.getChildren().remove(menuStack);
    }




}