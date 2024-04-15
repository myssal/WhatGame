package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //SETTING SCREEN
    final int originalTileSize = 16;//object size
    final int scale = 3; //scale size to fit screen width

    final public int tileSize = originalTileSize * scale;//scale size
    //WORLD SETTINGS
    final public int maxWorldCol = 50;
    final public int maxWorldRow = 50;
    final public int worldWidth = maxWorldCol * tileSize;
    final public int worldHeight = maxWorldRow * tileSize;

    //FPS
    final int FPS = 60;
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth  = tileSize * maxScreenCol;
    final public int screenHeight  = tileSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);
    Thread gameThread;    
    public Player player = new Player(this,keyH);

    //set default position


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

        tileM.draw(graph2);
        player.draw(graph2);

        graph2.dispose();
    }
}
