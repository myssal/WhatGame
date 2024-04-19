package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boost extends Object {

    GamePanel gp;
    public Obj_Boost(GamePanel gp){

        this.gp = gp;
        name = "Boost";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/potion_red.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        };
    }
}
