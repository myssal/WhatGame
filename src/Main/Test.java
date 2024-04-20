package Main;

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
*/      int[][] mapTileNum = new int[100][50];
        Scanner mapScanner = new Scanner(new File("res/maps/completeMap.txt"));
        int col = 0, row = 0;
        while (mapScanner.hasNext()){
            mapTileNum[col][row] = mapScanner.nextInt();
            col++;
            if (col == 100){
                row ++;
                col = 0;
            }
        }
        System.out.println(mapTileNum[0][49]);

    }
}
