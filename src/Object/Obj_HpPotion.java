package Object;

import Entity.Entity;
import Main.GamePanel;

import java.awt.*;

public class Obj_HpPotion extends Entity {

    public Obj_HpPotion(GamePanel gp){

        super(gp);
        name = "Hp Potion";
        down1 = setUp("object/potion/0.5health");
        solidArea = new Rectangle(5, 5, 20, 20);
        collision = true;
    }
}
