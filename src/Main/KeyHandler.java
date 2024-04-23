package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean soundStatus = true;
    public boolean debugPressed = false; //debug mode add
    public boolean pickUpPressed = false; // pick up object
    public boolean attackPressed = false; //attack mob

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    //input keyboard represent 2 set of typical keyboard movement: wasd or arrow key.
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        //title state
        if (gp.gameState == gp.titleState){
            if (gp.ui.titleScreenState == 0){

                //switch between option
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0){
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2){
                        gp.ui.commandNum = 0;
                    }
                }
                //execute option
                if (code == KeyEvent.VK_ENTER){
                    if (gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                        gp.ui.commandNum = 0;
                    }
                    if (gp.ui.commandNum == 1){
                        if (soundStatus){
                            gp.stopMusic();
                            soundStatus = false;
                        }else {
                            gp.playMusic(0);
                            soundStatus = true;
                        }

                    }
                    if (gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1){

                //switch between option
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0){
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1){
                        gp.ui.commandNum = 0;
                    }
                }
                //execute option
                if (code == KeyEvent.VK_ENTER){
                    if (gp.ui.commandNum == 0){
                        //move to previous title screen
                        gp.ui.titleScreenState = 0;
                    }
                    if (gp.ui.commandNum == 1){
                        //move to game
                        gp.gameState = gp.playState;
                        gp.ui.commandNum = 0;
                    }
                }
            }

        }
        //play state
        else if (gp.gameState == gp.playState){

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if (code == KeyEvent.VK_E){
                pickUpPressed = true;
            }
            if (code == KeyEvent.VK_SPACE){
                attackPressed = true;
            }
            //debug handler
            if (code == KeyEvent.VK_1) {
                if (debugPressed == true) debugPressed = false;
                else if (debugPressed == false) debugPressed = true;
            }
        }
        //switch pause/play state
        else if (gp.gameState == gp.pauseState){

            //switch between option
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            //execute option
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
                    //resume
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1){
                    //return to title screen
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum = 0;
                    gp.ui.titleScreenState = 0;
                }
                if (gp.ui.commandNum == 2){
                    //exit game
                    System.exit(0);
                }
            }
        }

        //dialogue state
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

        //victory and game over state
        else if (gp.gameState == gp.victoryState || gp.gameState == gp.failState){
            //switch between option
            if (gp.gameState == gp.victoryState) gp.playSoundEffect(8);
            else if (gp.gameState == gp.failState) gp.playSoundEffect(3);
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;

                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
                    gp.player.reset();
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                }
                if (gp.ui.commandNum == 1) System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }        
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }        
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;            
        }
    }
    
}

//Improve rendering performance: preset scale size before put them into gp