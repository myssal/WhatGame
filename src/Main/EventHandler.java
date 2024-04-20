package Main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    //event checker
    public void checkEvent(){

        //template
        if (hit(27, 16, "right")){
            damagePit(gp.dialogueState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        //get plater and event collision coordinates
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){

                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Fool ass";
        gp.player.HP -= 1;
    }

    public void healing(int gameState){

        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Got a heal, huh?";
            gp.player.HP = gp.player.maxHP;
        }

        gp.keyH.enterPressed = false;
    }
}
