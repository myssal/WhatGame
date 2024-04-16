package Main;

import Object.Obj_Door;
import Object.Obj_Key;

public class AssetsManagement {

    GamePanel gp;

    public AssetsManagement(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //setup object in world map
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 30 * gp.tileSize;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = 8 * gp.tileSize;
        gp.obj[2].worldY = 28 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 22 * gp.tileSize;

    }
}
