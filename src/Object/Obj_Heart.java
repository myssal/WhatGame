package Object;

import Entity.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Heart extends Entity {


    public Obj_Heart(GamePanel gp){

        super(gp);
        name = "Heart";
        image = setUp("object/heart_full");
        imageVar1 = setUp("object/heart_half");
        imageVar2 = setUp("object/heart_blank");

    }
}
