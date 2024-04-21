package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_HpPotion extends Entity {

    public Obj_HpPotion(GamePanel gp){

        super(gp);
        name = "Hp Potion";
        entityType = "Object";
        try {
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/potion/0.5health.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea = new Rectangle(5, 5, 20, 20);
        collision = true;
    }
}
