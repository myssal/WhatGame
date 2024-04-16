package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    //for characters (main, npc, enemies, bosses...)

    public int worldX, worldY; //coordinates of entity relative to world map
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; //BufferedImage describes an images with an accessible buffer of image data.
    public String direction; //decide which direction entity will face when spawn in the screen

    public int spriteCounter = 0; //variable to create a "loop" animation
    public int spriteNum = 1; //decide which sprite to use in "loop" animation
    public Rectangle solidArea; //create collision area for entity
    public int solidAreaDefaultX, solidAreaDefaultY; //store default collision area coordinates
    public boolean collisonOn = false;
}
