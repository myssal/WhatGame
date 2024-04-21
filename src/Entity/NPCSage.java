package Entity;

import Main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPCSage extends Entity{
    //fix path later
    public NPCSage(GamePanel gp){
        super(gp);

        solidArea = new Rectangle(5, 5, 38, 43);
        direction = "down";
        speed = 1;
        getSageBImage();
        setDialogue();
    }

    public void getSageBImage(){

        up1 = setUp("entity/npc/sageB/sageUp_1");
        up2 = setUp("entity/npc/sageB/sageUp_2");
        down1 = setUp("entity/npc/sageB/sageDown_1");
        down2 = setUp("entity/npc/sageB/sageDown_2");
        right1 = setUp("entity/npc/sageB/sageRight_1");
        right2 = setUp("entity/npc/sageB/sageRight_2");
        left1 = setUp("entity/npc/sageB/sageLeft_1");
        left2 = setUp("entity/npc/sageB/sageLeft_2");

    }
    //set dialogue for npc
    public void setDialogue(){

        dialogues[0] = "Hello and welcome to the island.";
        dialogues[1] = "It has been a long time since i saw a people here.";
        dialogues[2] = "Go to the west, the treasures await you.";
        dialogues[3] = "Good luck.";
    }

    //simple AI setup for npc
    public void update(){
        //let him stuck here

        collisonOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);

        spriteCounter++;
        if (spriteCounter>30) {
            if (spriteNum == 1) {

                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    };

    public void speak(){
        //for customization
        super.speak();
    }
}
