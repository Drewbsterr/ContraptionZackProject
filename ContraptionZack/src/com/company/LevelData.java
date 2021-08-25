package com.company;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelData {
    Scanner scan ;
    public LevelData (){
    }
    public void openFile (String fileName, ArrayList <String> a ){
        try {
            scan = new Scanner ( new File(fileName)) ;
        }
        catch(Exception e) {
            System.out.println("file not found");
        }
        while (scan.hasNext() ){
            a.add(scan.next());
        }
    }

}