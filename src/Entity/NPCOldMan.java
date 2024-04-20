package Entity;

import Main.GamePanel;
import Main.Utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPCOldMan extends Entity{
    //fix path later
    public NPCOldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getOldManImage();
    }

    public void getOldManImage(){

        up1 = setUp("player/boy_up_1");
        up2 = setUp("player/boy_up_2");
        down1 = setUp("player/boy_down_1");
        down2 = setUp("player/boy_down_2");
        right1 = setUp("player/boy_right_1");
        right2 = setUp("player/boy_right_2");
        left1 = setUp("player/boy_left_1");
        left2 = setUp("player/boy_left_2");

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
