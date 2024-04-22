package Mob;

import Main.GamePanel;
import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Mob_Template extends Entity {

    public Mob_Template(GamePanel gp){
        super(gp);
        HP = maxHP;
        solidArea = new Rectangle(3, 40, 42, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
        entityType = "Mob";
        setStatus();
        getMobImage();
        setAction();

    }

    public void getMobImage(){
        if (name.contentEquals("Slime")){
            up1 = down1 = right1 = left1 = setUp("entity/mob/slime/slimeIdle_1");
            up2 = down2 = right2 = left2 = setUp("entity/mob/slime/slimeIdle_2");
        } else if (name.contentEquals("Goblin")) {
            up1 = down1 = right1 = left1 = setUp("entity/mob/goblin/goblinIdle_1");
            up2 = down2 = right2 = left2 = setUp("entity/mob/goblin/goblinIdle_2");
        } else if (name.contentEquals("Spider")) {
            up1 = down1 = right1 = left1 = setUp("entity/mob/spider/spiderIdle_1");
            up2 = down2 = right2 = left2 = setUp("entity/mob/spider/spiderIdle_2");
        }

    }

    public void setStatus(){
        if (name.contentEquals("Slime")){
            speed = 1;
            maxHP = 4;
        } else if (name.contentEquals("Goblin")) {
            speed = 2;
            maxHP = 5;
        } else if (name.contentEquals("Spider")) {
            speed = 2;
            maxHP = 4;
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
