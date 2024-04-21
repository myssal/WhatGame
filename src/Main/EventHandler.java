package Main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];
    int previousEventX, previousEventY;
    boolean canTouchEvent;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0, row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol){
                row ++;
                col = 0;
            }
        }

    }

    //event checker
    public void checkEvent(){
        //check if player is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize){
            canTouchEvent = true;
        }

        if (canTouchEvent){
            //template
            if (hit(11, 48, "any")){
                damagePit(11, 48, gp.dialogueState);
            }
        }

    }

    public boolean hit(int col, int row, String reqDirection){

        boolean hit = false;

        //get player and event collision coordinates
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){

                hit = true;
                //get player coordinates
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState){

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Fool ass";
        gp.player.HP -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healing(int col, int row, int gameState){

        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Got a heal, huh?";
            gp.player.HP = gp.player.maxHP;
        }

        gp.keyH.enterPressed = false;
    }
}
