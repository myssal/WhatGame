package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends ObjectTmp {

    GamePanel gp;
    public Obj_Door(GamePanel gp){

        this.gp = gp;
        name = "Door";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/door.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        };
    }
}
