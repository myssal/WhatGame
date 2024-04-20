package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPCSageE extends Entity{

    public NPCSageE(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getSageEImage();
    }

    public void getSageEImage(){

        up1 = setUp("entity/npc/sageE/sageUp_1");
        up2 = setUp("entity/npc/sageE/sageUp_2");
        down1 = setUp("entity/npc/sageE/sageDown_1");
        down2 = setUp("entity/npc/sageE/sageDown_2");
        right1 = setUp("entity/npc/sageE/sageRight_1");
        right2 = setUp("entity/npc/sageE/sageRight_2");
        left1 = setUp("entity/npc/sageE/sageLeft_1");
        left2 = setUp("entity/npc/sageE/sageLeft_2");

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
