package Main;

import Entity.NPCSage;
import Entity.NPCSageE;

public class AssetsManagement {

    GamePanel gp;

    public AssetsManagement(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //setup object in world map


    }
    public void setNPC(){

        gp.npc[0] = new NPCSage(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 46;

        gp.npc[1] = new NPCSageE(gp);
        gp.npc[1].worldX = gp.tileSize * 100;
        gp.npc[1].worldY = gp.tileSize * 20;

    }
}
