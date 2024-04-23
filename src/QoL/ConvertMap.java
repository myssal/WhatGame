package QoL;

import Main.GamePanel;
import Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class ConvertMap {
    //QoL class to convert a map into txt map
    Tile tileTemplate[] = new Tile[40];
    public ConvertMap(){}
    public void getTileImage(String tileListPath) {

        try {


            int i = 10;
            Scanner TileIn = new Scanner(new File(tileListPath));
            while (TileIn.hasNext()){
                int tileOrder = TileIn.nextInt();
                String tileName = TileIn.next();
                boolean collision = TileIn.nextBoolean();
                //TileList.add(new Tile(TileOrder, TileName, Collision));
                tileTemplate[i] = new Tile(tileOrder, tileName, collision);
                i++;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        for (int i = 10; i < tileTemplate.length; i++){
            try {

                tileTemplate[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(tileTemplate[i].tileName));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //optimization idea: parse tile list as .json or .txt and read over it one by one. Done!

    }
    boolean compareImage(BufferedImage img1, BufferedImage img2){
        boolean equal = false;
        int width1 = img1.getWidth();
        int width2 = img2.getWidth();
        int height1 = img1.getHeight();
        int height2 = img2.getHeight();
        if ((width1 != width2) || (height1 != height2)){
            return false;
        }
        else {
            long difference = 0;
            for (int col = 0; col < width1; col ++){
                for (int row = 0; row < height1; row ++){
                    int rgbA = img1.getRGB(col, row);
                    int rgbB = img2.getRGB(col, row);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA)&0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB)&0xff;

                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }
            if (difference == 0) return true;
        }
        return false;
    }
    public void readMap(){
        try {
            
            BufferedImage map = ImageIO.read(getClass().getClassLoader().getResourceAsStream("maps/testSpawn.png"));
            //create a subImage to convert each tiles
            BufferedImage tileSub;
            int tileNumSub;
            BufferedWriter mapWriter = new BufferedWriter(new FileWriter("res/maps/testMap.txt"));
            for (int row = 0; row < 4; row ++){
                for (int col = 0; col < 4; col ++){
                    tileSub = map.getSubimage(col * 16,row * 16,16,16);
                    for (int i = 10; i < 35; i++){
                        if (compareImage(tileSub, tileTemplate[i].image)){
                            tileNumSub = tileTemplate[i].tileOrder;
                            System.out.println(tileNumSub);
                            mapWriter.write(tileNumSub + " ");
                        }

                    }

                }
                mapWriter.append("\n");
            }
            mapWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        /*test compare
        try {
            BufferedImage test = ImageIO.read(ConvertMap.class.getClassLoader().getResourceAsStream("tileMap/bfloor1.png"));
            BufferedImage testMap = ImageIO.read(ConvertMap.class.getClassLoader().getResourceAsStream("maps/testSpawn.png"));
            BufferedImage test2 = testMap.getSubimage(16, 16, 16, 16);
            boolean Rtrue = cMap.compareImage(test, test2);
            System.out.println(Rtrue);
        }catch (IOException e){
            e.printStackTrace();
        }
         */
        ConvertMap cMap = new ConvertMap();
        cMap.getTileImage("res/maps/tileList.txt");
        cMap.readMap();

    }
}
