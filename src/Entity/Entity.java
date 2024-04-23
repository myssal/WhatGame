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
    public String entityType = "Character";
    public boolean invicible = false;
    public int invicibleCounter = 0;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; //BufferedImage describes an images with an accessible buffer of image data.
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down"; //decide which direction entity will face when spawn in the screen
    public int spriteCounter = 0; //variable to create a "loop" animation
    public int spriteNum = 1; //decide which sprite to use in "loop" animation
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //create collision area for entity
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY; //store default collision area coordinates
    public boolean collisonOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    public int chestCount = 0;
    public int mobDefeated = 0;
    int dialogueIndex = 0;
    boolean attacking = false;

    //character status
    public int maxHP;
    public int HP;

    //move from ObjectTmp
    public BufferedImage image, imageVar1, imageVar2;
    public String name = "";
    public boolean collision = true;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    //render image
    public BufferedImage setUp(String imagePath){
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
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.mob);

        //check if this is monster or not to deal dmg to player
        if (this.entityType.contentEquals("Mob") && contactPlayer == true){
            if(gp.player.invicible == false){
                gp.player.HP -= 1;
                gp.player.invicible = true;
            }
        }

        //if false, can move
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

        //set time invicible
        if (invicible){
            invicibleCounter++;
            if (invicibleCounter > 20){
                invicible = false;
                invicibleCounter = 0;
            }
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
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    break;

                case "down":
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    break;

                case "left":
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    break;

                case "right":
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    break;
                default:
                    break;
            }
            if (invicible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  0.6f));
            }

            if (entityType.contentEquals("Object")){
                g2.drawImage(image, screenX + 5, screenY + 5, 30, 30, null);
            }else {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }

    public void speak(){
        //read through dialogue
        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        //make npc face player
        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;

            case "down":
                direction = "up";
                break;

            case "right":
                direction = "left";
                break;

            case "left":
                direction = "right";
                break;
        }
    }


}
