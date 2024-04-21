package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_AttackPotion extends Entity {
    public Obj_AttackPotion(GamePanel gp){

        super(gp);
        name = "AttackPotion";
        entityType = "Object";


        solidArea = new Rectangle(5, 5, 20, 20);
        collision = true;
    }
}
