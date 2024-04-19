package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.Obj_Key;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = " ";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
        Obj_Key key = new Obj_Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

        if (gameFinished){
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x,y;

            text = "You found the treasure";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            text = "You have played" + dFormat.format(playTime);
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.white);

        }else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey,72,60);
            //note that drawString y display the base line, not the top of the object like drawImage

            //time
            playTime +=(double) 1/60;
            g2.drawString("Time: "+dFormat.format(playTime),gp.tileSize*11, gp.tileSize);

            //display message
            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(30));
                g2.drawString(message,gp.tileSize/2,gp.tileSize*5);

                //message timer
                messageCounter++;
                if (messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
