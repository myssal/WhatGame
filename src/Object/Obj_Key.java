package Object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends Object {

    GamePanel gp;

    public Obj_Key(GamePanel gp){

        this.gp = gp;
        name = "Key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/key.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        };
        collision = true;
    }
}
