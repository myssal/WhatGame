package Main;

import Entity.NPColdMan;
import Object.Obj_Door;
import Object.Obj_Key;
import Object.Obj_Boost;

public class AssetsManagement {

    GamePanel gp;

    public AssetsManagement(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //setup object in world map


    }
    public void setNPC(){

        gp.npc[0] = new NPColdMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

    }
}
