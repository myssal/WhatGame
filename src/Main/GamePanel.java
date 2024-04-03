package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;

public class GamePanel extends JPanel implements Runnable{

    //SETTING SCREEN
    final int originalTileSize = 16;//object size
    final int scale = 3; //scale size to fit screen width

    final int FPS = 60;
    final public int tileSize = originalTileSize*scale;//scale size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth  = tileSize * maxScreenCol;
    final int screenHeight  = tileSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;    
    Player player = new Player(this,keyH);

    //set default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //initialize constructor
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); //focus gamePanel to take input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    //create game loop, core of the game
    public void run(){

        while (gameThread != null) {

            //restrict time to update
            double drawInterval = 1000000000/FPS;
            double nextDrawTime = drawInterval + System.nanoTime();

            //1.Update: update information
            update();

            //2.Draw: draw the screen with the updated information
            repaint();

            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    //Java coordination: upper left corner = (0,0)
    //x increases to the right, y increase when go down
    public void update(){
        //call player update
        player.update();
    }

    public void paintComponent(Graphics graph){

        super.paintComponent(graph);

        Graphics2D graph2 = (Graphics2D)graph;
        player.draw(graph2);

        graph2.dispose();
    }
}
