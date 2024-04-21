package Main;

import Entity.NPCSage;
import Entity.NPCSageE;
import Mob.Slime;
import Object.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.util.Scanner;

public class AssetsManagement {

    GamePanel gp;

    public AssetsManagement(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //setup object in world map
        //optimize object spawn
        try {

            int objNum;
            String pathName = "";
            Scanner objectInput = new Scanner(new File("res/maps/objectList.txt"));
            while (objectInput.hasNext()){
                objNum = objectInput.nextInt();
                gp.obj[objNum] = new Obj_Template(gp);
                gp.obj[objNum].name = objectInput.next();
                gp.obj[objNum].down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream(objectInput.next()));
                gp.obj[objNum].worldX = objectInput.nextInt() * gp.tileSize;
                gp.obj[objNum].worldY = objectInput.nextInt() * gp.tileSize;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setNPC(){

        gp.npc[0] = new NPCSage(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 46;

        gp.npc[1] = new NPCSageE(gp);
        gp.npc[1].worldX = gp.tileSize * 100;
        gp.npc[1].worldY = gp.tileSize * 20;

    }

    public void setMob(){
        gp.mob[0] = new Slime(gp);
        gp.mob[0].worldX = gp.tileSize * 10;
        gp.mob[0].worldY = gp.tileSize * 50;
    }
}
