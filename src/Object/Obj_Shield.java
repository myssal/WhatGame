package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_Shield extends Entity {

    public Obj_Shield(GamePanel gp){

        super(gp);
        name = "Shield";
        entityType = "Object";
        try {
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/weapon/shield.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea = new Rectangle(5, 5, 20, 20);
        collision = true;
    }
}
