package Main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    //class to get required sprite from spritesheet
    public BufferedImage sprite;

    public SpriteSheet(BufferedImage sprite){
        this.sprite = sprite;

    }

    public BufferedImage getSprite(int xSprite, int ySprite, int widthSprite, int heightSprite){

        BufferedImage img = sprite.getSubimage((xSprite*16)-16, (ySprite*16)-16, widthSprite, heightSprite);
        return img;
    }
}
