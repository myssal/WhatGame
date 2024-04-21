package Mob;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class Mob_Temp extends Entity {

    Mob_Temp(GamePanel gp){
        super(gp);
        entityType = "Mob";
        solidArea = new Rectangle(5, 5, 40, 40);
        collision = true;
    }
}
