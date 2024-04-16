package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boost extends SuperObject{

    public Obj_Boost(){

        name = "Boost";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/potion_red.png"));
        }catch (IOException e){
            e.printStackTrace();
        };
    }
}
