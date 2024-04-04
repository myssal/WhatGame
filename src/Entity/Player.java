package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    //constructor
    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValue();
        getPlayerImage();
    }
    //set default Player position
    public void setDefaultValue(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }
    public void getPlayerImage(){
        
        try {
            
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/boy_left_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if(keyH.upPressed){
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed){
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed){
                direction = "right";
                x += speed;
            }
        }
        
    }
    public void draw(Graphics2D graph2){

        //graph2.setColor(Color.white);
        //graph2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            
            case "down":
                image = down1;
                break;
                
            case "left":
                image = left1;
                break;

            case "right":
                image = right1;
                break;    
            default:
                break;
        }
        graph2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
