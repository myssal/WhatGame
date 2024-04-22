package Main;

import Entity.NPCSage;
import Entity.NPCSageE;
import Object.*;
import Mob.*;
import javax.imageio.ImageIO;
import java.io.File;
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
        gp.npc[1].worldX = gp.tileSize * 103;
        gp.npc[1].worldY = gp.tileSize * 28;

    }

    public void setMob(){
        //test spawn
        gp.mob[0] = new Mob_Template(gp);
        gp.mob[0].name = "Slime";
        gp.mob[0].worldX = 12;
        gp.mob[0].worldY = 50;
        /*try {

            int mobNum;
            Scanner mobInput = new Scanner(new File("res/maps/mobList.txt"));
            while (mobInput.hasNext()){
                mobNum = mobInput.nextInt();
                gp.mob[mobNum] = new Mob_Template(gp);
                gp.mob[mobNum].name = mobInput.next();
                gp.mob[mobNum].worldX = mobInput.nextInt() * gp.tileSize;
                gp.mob[mobNum].worldY = mobInput.nextInt() * gp.tileSize;
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
