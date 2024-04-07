package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    //for characters (main, npc, enemies, bosses...)

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; //BufferedImage describes an images with an accessible buffer of image data.
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
