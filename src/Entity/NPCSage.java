package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPCSage extends Entity{

    public NPCSage(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 2;
        getSageImage();
    }

    public void getSageImage(){

        up1 = setUp("entity/npc/sage/sageUp_1.png");
        up2 = setUp("entity/npc/sage/sageUp_2.png");
        down1 = setUp("entity/npc/sage/sageDown_1.png");
        down2 = setUp("entity/npc/sage/sageDown_2.png");
        right1 = setUp("entity/npc/sage/sageRight_1.png");
        right2 = setUp("entity/npc/sage/sageRight_2.png");
        left1 = setUp("entity/npc/sage/sageLeft_1.png");
        left2 = setUp("entity/npc/sage/sageLeft_2.png");

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
