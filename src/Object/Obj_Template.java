package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Obj_Template extends Entity {

    public Obj_Template(GamePanel gp){
        super(gp);
        entityType = "Object";
        solidArea = new Rectangle(5, 5, 20, 20);
        collision = true;
    }
}
