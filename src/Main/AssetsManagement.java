package Main;

import Entity.NPCSage;

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
        gp.npc[0].worldX = gp.tileSize * 4;
        gp.npc[0].worldY = gp.tileSize * 46;

    }
}
