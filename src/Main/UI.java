package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import Entity.Entity;
import Object.Obj_Heart;

import javax.imageio.ImageIO;

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
    BufferedImage heartFull, heartHalf, heartBlank;
    public int commandNum = 0; //title screen choice
    public int titleScreenState = 0;//load different title screen

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("fonts/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }

        //hud object
        Entity heart = new Obj_Heart(gp);
        heartFull = heart.image;
        heartHalf = heart.imageVar1;
        heartBlank = heart.imageVar2;

    }

    public void draw(Graphics2D g2){
        graph2 = g2;

        graph2.setFont(maruMonica);
        graph2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graph2.setColor(Color.white);

        //title state
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //play state
        if (gp.gameState == gp.playState){
            drawPlayerHP();
        }

        //dialogue stata
        if (gp.gameState == gp.dialogueState){
            drawPlayerHP();
            drawDialogueWindow();
        }

        //pause state
        if (gp.gameState == gp.pauseState) {
            drawPlayerHP();
            drawPauseScreen();
        }

    }

    public void drawPauseScreen(){

        graph2.setFont(graph2.getFont().deriveFont(Font.BOLD, 50));
        Color pause = new Color(91, 91, 91, 229);
        graph2.setColor(pause);
        String option1 = "Pause";
        String option2 = "Return to title screen";
        String option3 = "Exit";
        //render text and shadow

        graph2.drawString(option1, getXCenteredText(option1, graph2), gp.tileSize * 5);
        graph2.drawString(option2, getXCenteredText(option2, graph2), gp.tileSize * 7 );
        graph2.drawString(option3, getXCenteredText(option3, graph2), gp.tileSize * 9);
        //change command
        if (commandNum == 0){
            graph2.drawString(">", getXCenteredText(option1, graph2) - gp.tileSize, gp.tileSize * 5 + 4);
        }
        if (commandNum == 1){
            graph2.drawString(">", getXCenteredText(option2, graph2) - gp.tileSize, gp.tileSize * 7 + 4);
        }
        if (commandNum == 2){
            graph2.drawString(">", getXCenteredText(option3, graph2) - gp.tileSize, gp.tileSize * 9 + 4);
        }



    }
    //title screen
    public void drawTitleScreen(){
        if (titleScreenState == 0){
            //title screen background, currently using placeholder
            try {

                BufferedImage titleImage;
                titleImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("title/start.png"));
                graph2.drawImage(titleImage, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);
            }catch (IOException e){
                e.printStackTrace();
            }

            //title name
            graph2.setFont(graph2.getFont().deriveFont(Font.BOLD, 80F));
            String gameTitle = "What Game?";
            int x = getXCenteredText(gameTitle, graph2);
            int y = gp.tileSize * 3;

            //shadow
            Color titleColorShadow = new Color(56, 56, 56, 255);
            graph2.setColor(titleColorShadow);
            graph2.drawString(gameTitle, x + 4, y + 4);
            //main title
            Color titleColor = new Color(255, 255, 255);
            graph2.setColor(titleColor);
            graph2.drawString(gameTitle, x, y);

            //menu
            graph2.setFont(graph2.getFont().deriveFont(Font.BOLD, 40F));
            String[] menuOption = new String[]{"NEW GAME", "LOAD GAME", "QUIT"};
            for (int menuIt = 0; menuIt < menuOption.length; menuIt++){

                x = getXCenteredText(menuOption[menuIt], graph2);
                y = gp.tileSize * (6 + menuIt * 2);
                graph2.setColor(titleColorShadow);
                graph2.drawString(menuOption[menuIt], x + 4, y + 4);
                graph2.setColor(titleColor);
                graph2.drawString(menuOption[menuIt], x, y);
                if (commandNum == menuIt){
                    graph2.setColor(titleColorShadow);
                    graph2.drawString(">", x - gp.tileSize + 4, y + 4);
                    graph2.setColor(titleColor);
                    graph2.drawString(">", x - gp.tileSize, y);
                }

            }
        } else if (titleScreenState == 1) {

            try {

                BufferedImage tutorialBGImage;
                tutorialBGImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("title/tutorial.png"));
                graph2.drawImage(tutorialBGImage, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);
            }catch (IOException e){
                e.printStackTrace();
            }
            //tutorial screen
            Color tutorialColorShadow = new Color(255, 255, 255, 255);
            graph2.setColor(tutorialColorShadow);
            graph2.setFont(graph2.getFont().deriveFont(Font.BOLD,40F));
            String mainText = "TUTORIAL";
            int xMainTitle = getXCenteredText(mainText, graph2);
            graph2.drawString(mainText, xMainTitle, gp.tileSize * 2);
            //keyboard tutorial
            graph2.setFont(graph2.getFont().deriveFont(Font.BOLD,30F));
            graph2.drawString("Move: WASD or arrow key", gp.tileSize * 5, gp.tileSize * 4);
            graph2.drawString("Attack: Enter     Pause: P", gp.tileSize * 5, gp.tileSize * 5);
            graph2.drawString("Trigger Dialogue: Enter", gp.tileSize * 5, gp.tileSize * 6);
            graph2.drawString("Pick up object: E", gp.tileSize * 5, gp.tileSize * 7);
            int xBack = getXCenteredText("Back to Title Screen", graph2);
            int xUp = getXCenteredText("To the Game", graph2);

            //choice
            graph2.drawString("Back to Title Screen", xBack, gp.tileSize * 9);
            if (commandNum == 0){
                graph2.drawString(">", xBack - gp.tileSize, gp.tileSize * 9 + 4);
            }
            graph2.drawString("To the Game", xUp, gp.tileSize * 10);
            if (commandNum == 1){
                graph2.drawString(">", xUp - gp.tileSize, gp.tileSize * 10 + 4);
            }

            
        }

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

    //Hp hud
    public void drawPlayerHP(){

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //max hp display
        while (i < gp.player.maxHP / 2){
            graph2.drawImage(heartBlank, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x+= gp.tileSize;
        }
        //reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.HP){
            graph2.drawImage(heartHalf, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            if (i < gp.player.HP){
                graph2.drawImage(heartFull, x, y, gp.tileSize, gp.tileSize, null);
            }
            i++;
            x+= gp.tileSize;
        }

    }

    //QoL function
    public int getXCenteredText(String text, Graphics2D graph2){
        int length = (int)graph2.getFontMetrics().getStringBounds(text, graph2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
