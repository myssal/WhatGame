package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends SuperObject{

    public Obj_Key(){

        name = "Key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("object/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        };
    }
}
