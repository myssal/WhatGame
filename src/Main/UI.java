package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.Obj_Key;

public class UI {

    GamePanel gp;
    Graphics2D graph2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = " ";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        graph2 = g2;

        graph2.setFont(arial_40);
        graph2.setColor(Color.white);

        //play state
        if (gp.gameState == gp.playState){
            
        }

        //dialogue state
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        //pause state
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

    }

    public void drawPauseScreen(){

        graph2.setFont(graph2.getFont().deriveFont(Font.BOLD, 80));
        String gameState = "PAUSE";
        int x = getXCenteredText(gameState, graph2);
        int y = gp.screenHeight / 2;

        graph2.drawString(gameState, x, y);
    }

    //Dialogue handler
    public void drawDialogueScreen(){

        //setting window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2 ;
        int width = gp.screenWidth - gp.tileSize / 4;
        int height = gp.tileSize * 4;
        //render dialogue
        drawSubWindow(x, y, width, height);
    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color color = new Color(0, 0, 0);
        graph2.setColor(color);
        graph2.fillRoundRect(x, y, width, height, 30, 30);
    }

    //QoL function
    public int getXCenteredText(String text, Graphics2D graph2){
        int length = (int)graph2.getFontMetrics().getStringBounds(text, graph2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
