package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    //constructor
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //setting the collision area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = 32;
        solidArea.width = 32;


        setDefaultValue();
        getPlayerImage();
    }
    //set default Player position
    public void setDefaultValue(){

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 10;
        direction = "down";
    }
    public void getPlayerImage(){
        
        try {
            
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if(keyH.upPressed){
                direction = "up";

            }
            else if (keyH.downPressed) {
                direction = "down";

            }
            else if (keyH.leftPressed){
                direction = "left";

            }
            else if (keyH.rightPressed){
                direction = "right";

            }

            //collision check
            collisonOn = false;
            gp.collisionChecker.checkTile(this); //collision checker for entity, in this case player


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
        }

        
    }
    public void draw(Graphics2D graph2){

        //graph2.setColor(Color.white);
        //graph2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

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
        graph2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
