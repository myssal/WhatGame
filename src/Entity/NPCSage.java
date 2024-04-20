package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPCSage extends Entity{
    //fix path later
    public NPCSage(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getSageBImage();
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

    //simple AI setup for npc
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
