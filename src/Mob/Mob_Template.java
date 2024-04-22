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
        String mobName[] = {"Slime", "Goblin", "Spider"};
        Random random = new Random();
        int mobId = random.nextInt(2) + 1;
        name = mobName[mobId];
        entityType = "Mob";
        solidArea = new Rectangle(3, 18, 42, 40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
        setStatus();
        getMobImage();

    }

    public void getMobImage(){
        switch (name){
            case "Slime": {
                up1 = down1 = right1 = left1 = setUp("entity/mob/slime/slimeIdle_1");
                up2 = down2 = right2 = left2 = setUp("entity/mob/slime/slimeIdle_2");
                break;
            }
            case "Goblin": {
                up1 = down1 = right1 = left1 = setUp("entity/mob/goblin/goblinIdle_1");
                up2 = down2 = right2 = left2 = setUp("entity/mob/goblin/goblinIdle_2");
                break;
            }
            case "Spider": {
                up1 = down1 = right1 = left1 = setUp("entity/mob/spider/spiderIdle_1");
                up2 = down2 = right2 = left2 = setUp("entity/mob/spider/spiderIdle_2");
                break;
            }
        }
    }

    public void setStatus(){
        switch (name){
            case "Slime": {
                speed = 1;
                maxHP = 4;
                HP = maxHP;
                break;
            }
            case "Goblin": {
                speed = 2;
                maxHP = 5;
                HP = maxHP;
                break;
            }
            case "Spider": {
                speed = 2;
                maxHP = 4;
                HP = maxHP;
                break;
            }
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
