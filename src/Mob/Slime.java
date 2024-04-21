package Mob;

import Main.GamePanel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Slime extends Entity {

    public Slime(GamePanel gp){
        super(gp);

        name = "Slime";
        speed = 1;
        maxHP = 3;
        HP = maxHP;
        solidArea = new Rectangle(3, 23, 48, 25);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
        entityType = "Mob";
        getSlimeImage();
    }

    public void getSlimeImage(){
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("entity/mob/slime/slimeIdle_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setAction(){

        actionLockCounter ++;

        if (actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "up";
            if (i > 25 && i <= 50) direction = "down";
            if (i > 50 && i <= 75) direction = "left";
            if (i > 75 && i <= 100) direction = "right";

            actionLockCounter = 0;
        }
    }
}
