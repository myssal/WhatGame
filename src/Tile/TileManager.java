package Tile;

import Main.GamePanel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public  TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("maps/worldMap.txt");
    }

    public void getTileImage(){

            //optimization idea: parse tile list as .json or .txt and read over it one by one.
            setUp(0, "grass", false);
            setUp(1, "wall", true);
            setUp(2, "water", true);
            setUp(3, "earth", false);
            setUp(4, "tree", true);
            setUp(5, "sand", false);
    }

    //class to set up image
    public void setUp(int index, String imagePath, boolean collision){

        //enhanced image size
        Utility util = new Utility();
        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/" + imagePath + ".png"));
            tile[index].image = util.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
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
            if(worldX + gp.tileSize + 1 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize - 1 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize + 1 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize - 1 < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }

            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
