package Object;

import Main.GamePanel;
import Main.Utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectTmp {

    public BufferedImage image, imageVar1, imageVar2;
    public String name;
    public boolean collision = true;
    public int worldX, worldY;
    //size of a whole tide, change in each objects if necessary
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public Utility util = new Utility();

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX ;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        //limit which tiles to render
        if(worldX + gp.tileSize + 1 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize - 1 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize + 1 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize - 1 < gp.player.worldY + gp.player.screenY){

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
