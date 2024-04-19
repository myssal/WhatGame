package Main;

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
        gp.obj[0] = new Obj_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Obj_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 30 * gp.tileSize;

        gp.obj[2] = new Obj_Door(gp);
        gp.obj[2].worldX = 8 * gp.tileSize;
        gp.obj[2].worldY = 28 * gp.tileSize;

        gp.obj[3] = new Obj_Door(gp);
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 22 * gp.tileSize;

        gp.obj[4] = new Obj_Boost(gp);
        gp.obj[4].worldX = 37 * gp.tileSize;
        gp.obj[4].worldY = 42 * gp.tileSize;

    }
}
