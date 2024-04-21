package Main;

import Entity.NPCSage;
import Entity.NPCSageE;
import Object.Obj_HpPotion;

public class AssetsManagement {

    GamePanel gp;

    public AssetsManagement(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //setup object in world map
        gp.obj[0] = new Obj_HpPotion(gp);
        gp.obj[0].worldX = gp.tileSize * 29;
        gp.obj[0].worldY = gp.tileSize * 30;

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
