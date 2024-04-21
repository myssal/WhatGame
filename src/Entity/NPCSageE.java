package Entity;

import Main.GamePanel;

import java.util.Random;

public class NPCSageE extends Entity{

    public NPCSageE(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getSageEImage();
        setDialogue();
    }
    public void setDialogue(){

        dialogues[0] = "So you finally got here.";
        dialogues[1] = "May you find the wished treasures here.";
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

    }
    public void speak(){
        //for customization
        super.speak();
    }
}
