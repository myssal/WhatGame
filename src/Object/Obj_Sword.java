package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_Sword extends Entity {
    public Obj_Sword(GamePanel gp){

        super(gp);
        name = "Sword";
        entityType = "Object";
        try {
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/weapon/sword.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea = new Rectangle(5, 5, 20, 20);
        collision = true;
    }
}
