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
        solidArea = new Rectangle(8, 0, 32, 35);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea = new Rectangle(36, 36);


        setDefaultValue();
        getPlayerImage();
        getPlayerAttackImage();
    }
    //set default Player position
    public void setDefaultValue(){
        //8,48: default spawn location
        worldX = gp.tileSize*96;
        worldY = gp.tileSize*12;
        speed = 4;
        direction = "right";

        //status
        maxHP = 10;
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

    public void getPlayerAttackImage(){
        //placeholder image, change later
        attackUp1 = setUp("entity/player/mainAttackUp_1");
        attackUp2 = setUp("entity/player/mainAttackUp_2");
        attackDown1 = setUp("entity/player/mainAttackDown_1");
        attackDown2 = setUp("entity/player/mainAttackDown_2");
        attackRight1 = setUp("entity/player/mainAttackRight_1");
        attackRight2 = setUp("entity/player/mainAttackRight_2");
        attackLeft1 = setUp("entity/player/mainAttackLeft_1");
        attackLeft2 = setUp("entity/player/mainAttackLeft_2");

    }
    public void update(){
        if (attacking){
            attackingP();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){


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

            //check npc collision
            int npcChecker = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcChecker);

            //check monster collision
            int mobChecker = gp.collisionChecker.checkEntity(this, gp.mob);
            contactMonster(mobChecker);

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

        if (chestCount >= 6){
            gp.gameState = gp.victoryState;
        }
        if (HP <= 0){
            gp.gameState = gp.failState;
        }
        //set time invicible
        if (invicible){
            invicibleCounter++;
            if (invicibleCounter > 60){
                invicible = false;
                invicibleCounter = 0;
            }
        }
    }

    public void reset(){
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * 48;
        direction = "right";
        HP = maxHP;
        invicible = false;
    }
    public void pickUpObject(int index){
        //set up interaction with object
        if (gp.keyH.pickUpPressed){
            if (index != 999){
                //handle each type of object.
                switch (gp.obj[index].name){
                    case "Chest":{
                        if (gp.keyH.pickUpPressed){
                            gp.ui.currentDialogue = "You got a treasure chest!";
                            gp.gameState = gp.dialogueState;
                            gp.ui.drawDialogueWindow();
                            chestCount++;
                            gp.obj[index] = null;

                        }
                        break;
                    }
                    case "Sword":{
                        if (gp.keyH.pickUpPressed){
                            gp.ui.currentDialogue = "You got a sword! Now you can attack the monster.";
                            gp.gameState = gp.dialogueState;
                            gp.ui.drawDialogueWindow();
                            if (gp.keyH.enterPressed){
                                gp.gameState = gp.playState;
                            }
                            gp.obj[index] = null;

                        }
                        break;
                    }
                    case "Shield":{
                        if (gp.keyH.pickUpPressed){
                            gp.ui.currentDialogue = "A shield to help increase your defense";
                            //actually haven't implemented yet lol
                            gp.gameState = gp.dialogueState;
                            gp.ui.drawDialogueWindow();
                            if (gp.keyH.enterPressed){
                                gp.gameState = gp.playState;
                            }
                            gp.obj[index] = null;

                        }
                        break;
                    }
                    case "HpPotion":{
                        if (gp.keyH.pickUpPressed){
                            gp.ui.currentDialogue = "You healed 1 HP!";
                            HP += 1;
                            gp.gameState = gp.dialogueState;
                            gp.ui.drawDialogueWindow();
                            if (gp.keyH.enterPressed){
                                gp.gameState = gp.playState;
                            }
                            gp.obj[index] = null;

                        }
                        break;
                    }
                    case "AttackPotion":{
                        if (gp.keyH.pickUpPressed){
                            gp.ui.currentDialogue = "A small boost to your attack";
                            //one more thing that haven't got implemented yet lol
                            gp.gameState = gp.dialogueState;
                            gp.ui.drawDialogueWindow();
                            if (gp.keyH.enterPressed){
                                gp.gameState = gp.playState;
                            }
                            gp.obj[index] = null;

                        }
                        break;
                    }
                    case "TeleportPole":{
                        if (gp.keyH.pickUpPressed){
                            gp.obj[index] = null;
                        }
                        break;
                    }
                }

            }
        }

    }

    public void interactNPC(int index){
        //show dialogue when hit enter
        if (gp.keyH.enterPressed){
            if (index != 999){

                if (gp.keyH.enterPressed == true){
                    gp.gameState = gp.dialogueState;
                    gp.npc[index].speak();
                }
            }
            else {
                if (gp.keyH.enterPressed) attacking = true;
            }
        }

    }

    public void contactMonster(int mobIndex){

        if (mobIndex != 999){

            if (invicible == false){
                HP -= 1;
                invicible = true;
            }

        }
    }

    public void attackingP(){
        //create attack animation
        spriteCounter++;
        if (spriteCounter <= 10) spriteNum = 1;
        if (spriteCounter > 10 && spriteCounter <= 20) {
            spriteNum = 2;

            //save player current coordinates
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust attack area
            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }//attack area update
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //check mob collision
            int mobIndex = gp.collisionChecker.checkEntity(this, gp.mob);
            damageMonster(mobIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 20){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    void damageMonster(int mobIndex){
        if (mobIndex != 999){
            if (!gp.mob[mobIndex].invicible){
                gp.mob[mobIndex].HP -= 1;
                gp.mob[mobIndex].invicible = true;

                if (gp.mob[mobIndex].HP < 0){
                    //mob die
                    mobDefeated ++;
                    gp.mob[mobIndex] = null;
                }
            }
        }
    }

    public void draw(Graphics2D graph2){
        //need to use another temp coordinates to display correctly position for bigger sprite

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                if (attacking){
                    if (spriteNum == 1) image = attackUp1;
                    if (spriteNum == 2) image = attackUp2;
                }
                break;

            case "down":
                if (!attacking) {
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                if (attacking){
                    if (spriteNum == 1) image = attackDown1;
                    if (spriteNum == 2) image = attackDown2;
                }

                break;
                
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                if (attacking){
                    if (spriteNum == 1) image = attackLeft1;
                    if (spriteNum == 2) image = attackLeft2;
                }
                break;

            case "right":
                if (!attacking) {
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                }
                if (attacking){
                    if (spriteNum == 1) image = attackRight1;
                    if (spriteNum == 2) image = attackRight2;
                }
                break;    
            default:
                break;
        }

        if (invicible){
            //effect invicible
            //change opacity of player sprite
            graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        }

        //optimize later
        if (image == attackDown1 || image == attackDown2 || image == attackUp1 || image == attackUp2){
            graph2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize, null);
        } else if (image == attackLeft1 || image == attackLeft2 || image == attackRight1 || image == attackRight2) {
            graph2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize * 2, null);
        }else {
            graph2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

        //reset opacity
        graph2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
