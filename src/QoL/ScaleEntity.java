package QoL;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScaleEntity {

    //method to improve render performance by pre-scaled sprite before load into draw method
    public BufferedImage scaleImage(BufferedImage original, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(scaledImage, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
