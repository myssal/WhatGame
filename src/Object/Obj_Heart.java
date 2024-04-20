package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Heart extends ObjectTmp {
    GamePanel gp;

    public Obj_Heart(GamePanel gp){

        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/heart_full.png"));
            imageVar1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/heart_half.png"));
            imageVar2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/heart_blank.png"));
        }catch (IOException e){
            e.printStackTrace();
        };
        collision = true;
    }
}
