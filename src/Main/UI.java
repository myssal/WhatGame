package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import Object.Obj_Key;

public class UI {

    GamePanel gp;
    Graphics2D graph2;
    public boolean messageOn = false;
    public String message = " ";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");
    public String currentDialogue = "";
    Font maruMonica;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("fonts/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        graph2 = g2;

        graph2.setFont(maruMonica);
        graph2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graph2.setColor(Color.white);

        //play state
        if (gp.gameState == gp.playState){
            
        }

        //dialogue stata
        if (gp.gameState == gp.dialogueState){
            drawDialogueWindow();
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
    //dialogue draw
    public void drawDialogueWindow(){
        //window setting
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 3;
        drawSubWindow(x, y, width, height);

        //draw dialogue
        graph2.setFont(graph2.getFont().deriveFont(Font.PLAIN,28f));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line: currentDialogue.split("\n")){

            graph2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color color = new Color(14, 14, 14, 220);
        graph2.setColor(color);
        graph2.fillRoundRect(x, y, width, height, 20, 20);

        color = new Color(255, 255, 255);
        graph2.setColor(color);
        graph2.setStroke(new BasicStroke(5));
        graph2.drawRoundRect(x + 5,  y+ 5, width - 10, height - 10, 20, 20);
    }

    //QoL function
    public int getXCenteredText(String text, Graphics2D graph2){
        int length = (int)graph2.getFontMetrics().getStringBounds(text, graph2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
