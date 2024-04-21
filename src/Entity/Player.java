package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.Utility;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    //constructor
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //setting the collision area
        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 32;
        solidArea.width = 32;


        setDefaultValue();
        getPlayerImage();
    }
    //set default Player position
    public void setDefaultValue(){

        worldX = gp.tileSize*9;
        worldY = gp.tileSize*48;
        speed = 7;
        direction = "right";

        //status
        maxHP = 6;
        HP = maxHP ;

    }
    public void getPlayerImage(){
        //enhanced player image
        up1 = setUp("entity/player/mainUp_1");
        up2 = setUp("entity/player/mainUp_2");
        down1 = setUp("entity/player/mainDown_1");
        down2 = setUp("entity/player/mainDown_2");
        right1 = setUp("entity/player/mainRight_1");
        right2 = setUp("entity/player/mainRight_2");
        left1 = setUp("entity/player/mainLeft_1");
        left2 = setUp("entity/player/mainLeft_2");

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

            //tile collision check
            collisonOn = false;
            gp.collisionChecker.checkTile(this); //collision checker for entity, in this case player

            //object collision check
            int objIndex = gp.collisionChecker.checkObject(this,true);
            pickUpObject(objIndex); //call method handling objects

            //check object collision
            int npcChecker = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcChecker);

            //check event collision
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed = false;

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

    public void pickUpObject(int index){
        //set up interaction with object
        if (index != 999){


        }
    }

    public void interactNPC(int index){
        //show dialogue when hit enter
        if (index != 999){

            if (gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }
    }

    public void draw(Graphics2D graph2){

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
