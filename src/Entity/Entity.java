package Entity;

import Main.GamePanel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    //for characters (main, npc, enemies, bosses...)
    GamePanel gp;
    public int worldX, worldY; //coordinates of entity relative to world map
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; //BufferedImage describes an images with an accessible buffer of image data.
    public String direction; //decide which direction entity will face when spawn in the screen

    public int spriteCounter = 0; //variable to create a "loop" animation
    public int spriteNum = 1; //decide which sprite to use in "loop" animation
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //create collision area for entity
    public int solidAreaDefaultX, solidAreaDefaultY; //store default collision area coordinates
    public boolean collisonOn = false;
    public int actionLockCounter = 0;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    //render image
    public BufferedImage setUp(String imagePath){

        Utility util = new Utility();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath+".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void setAction(){};
    public void update(){

        setAction();

        collisonOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);

        //if false, player can move
        if(collisonOn == false){

            switch (direction){
                case"up":{
                    worldY -= speed;
                    break;
                }
                case"down":{
                    worldY += speed;
                    break;
                }
                case"right":{
                    worldX += speed;
                    break;
                }
                case"left":{
                    worldX -= speed;
                    break;
                }
            }

        }

        spriteCounter++;
        if (spriteCounter>10) {
            if (spriteNum == 1) {

                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    };
    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX ;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        //limit which tiles to render
        if(worldX + gp.tileSize + 1 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize - 1 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize + 1 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize - 1 < gp.player.worldY + gp.player.screenY){

            switch (direction) {
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    break;

                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    break;

                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;

                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    break;
                default:
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }


}
