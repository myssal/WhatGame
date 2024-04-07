package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public  TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("maps/worldMap.txt");
    }

    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/earth.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/sand.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){

        try{
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
            br.close();
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
            if(worldX + gp.tileSize * 2 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize * 2 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 2 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxScreenCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
