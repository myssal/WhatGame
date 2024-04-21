package Main;

import Entity.Entity;
import Tile.Tile;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        /*
        Random rn = new Random(4);
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                System.out.print(rn.nextInt(4)+" ");
            }
            System.out.println(" ");
        }
        String test = "hello";
        System.out.println("WHat "+test+"????");
        ArrayList<Tile> TileList = new ArrayList<Tile>();
        try {

            Scanner TileIn = new Scanner(new File("res/maps/tileList.txt"));
            while (TileIn.hasNext()){
                int TileOrder = TileIn.nextInt();
                String TileName = TileIn.next();
                TileList.add(new Tile.TileOriginal(TileOrder,TileName));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        for (Tile.TileOriginal i : TileList){
            System.out.println(i.tileOrder+i.tileName);
        }

*/      int ObjectNum, ObjectX, ObjectY;
        String ObjectName;
        Entity[] obj = new Entity[30];
        String test;
        try {
            Scanner ObjectInput = new Scanner(new File("res/maps/objectList.txt"));
            while (ObjectInput.hasNext()){
                ObjectNum = ObjectInput.nextInt();
                Class classTypeObj = Class.forName(ObjectInput.next());
                System.out.println();
                ObjectX = ObjectInput.nextInt();
                ObjectY = ObjectInput.nextInt();

            }
        }catch (Exception e){}

    }
}
