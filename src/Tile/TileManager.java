package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public  TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[40];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage("res/maps/tileList.txt");
        loadMap("res/maps/completeMap.txt");
        /*try {
            for (int i = 0; i < tile.length; i++){
                tile[i] = new Tile(i, "tileMap/floor1.png", false);
                tile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(tile[i].tileName));
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/



    }

    public void getTileImage(String tileListPath) {

        try {

            int i = 10;
            Scanner TileIn = new Scanner(new File(tileListPath));
            while (TileIn.hasNext()){
                int tileOrder = TileIn.nextInt();
                String tileName = TileIn.next();
                boolean collision = TileIn.nextBoolean();
                //TileList.add(new Tile(TileOrder, TileName, Collision));
                tile[i] = new Tile(tileOrder, tileName, collision);
                i++;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        for (int i = 10; i < 34; i++){
                try {
                    //fix to 10 later
                    //if (tileIt.tileOrder >= 10){
                        //tile[tileIt.tileOrder] = new Tile(tileIt.tileOrder, tileIt.tileName, tileIt.collision);
                        tile[i].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(tile[i].tileName));
                        //tile[tileIt.tileOrder].image = util.scaleImage(tile[tileIt.tileOrder].image, gp.tileSize, gp.tileSize);
                    //}

                }catch (IOException e){
                    e.printStackTrace();
                }


        }
        //optimization idea: parse tile list as .json or .txt and read over it one by one. Done!

    }

    public void loadMap(String filePath){

        try{
            Scanner mapScanner = new Scanner(new File(filePath));
            int col = 0, row = 0;
            while (mapScanner.hasNext()){
                mapTileNum[col][row] = mapScanner.nextInt();
                col++;
                if (col == 100){
                    row ++;
                    col = 0;
                }
            }
            /*
            //import text map
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            //read text map
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();
                while (col < gp.maxWorldCol){

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){

                    col = 0;
                    row++;
                }
            }
            br.close();*/
        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2){
        //create map
        //implement camera
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX ;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            //limit which tiles to render
            if(worldX + gp.tileSize + 1 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize - 1 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize + 1 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize - 1 < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
