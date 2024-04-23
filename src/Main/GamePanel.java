package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    //tile size setting
    final int originalTileSize = 16;//object size
    final int scale = 3; //scale size to fit screen width
    final public int tileSize = originalTileSize * scale;//scale size

    //world setting
    final public int maxWorldCol = 115;
    final public int maxWorldRow = 59;
    final public int worldWidth = maxWorldCol * tileSize;
    final public int worldHeight = maxWorldRow * tileSize;

    //fps
    final int FPS = 60;

    //screen setting
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth  = tileSize * maxScreenCol;
    final public int screenHeight  = tileSize * maxScreenRow;
    public KeyHandler keyH = new KeyHandler(this);
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    Thread gameThread;

    //entity and object setting
    public Player player = new Player(this,keyH);
    public Entity npc[] = new Entity[5];
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Entity obj[] = new Entity[30]; //create an object arrays to store the object that'll appear in game
    public Entity mob[] = new Entity[35];
    ArrayList<Entity> entityList = new ArrayList<>(); //store all entities
    //sort the order to decide the render order with the one has lowest worldY comes to 0


    public AssetsManagement aManagement = new AssetsManagement(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    //set default position

    //game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int victoryState = 4;
    public final int failState = 5;

    //initialize constructor
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); //focus gamePanel to take input
    }

    public void setUpGame(){
        //set up object in the map
        aManagement.setObject();
        aManagement.setNPC();
        aManagement.setMob();
        playMusic(0);
        gameState = titleState;

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
    //render game screen
    public void update(){

        if (gameState == playState){
            //call player update
            player.update();
            //npc
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
            //mob
            for (int i = 0; i < mob.length; i++){
                if (mob[i] != null){
                    mob[i].update();
                }
            }
        }

        if (gameState == pauseState){
            //do nothing;
        }

    }

    public void paintComponent(Graphics graph){

        super.paintComponent(graph);

        Graphics2D graph2 = (Graphics2D)graph;

        //debug, find out rendering time
        long drawStart = 0;
        if (keyH.debugPressed){
            drawStart = System.nanoTime();
        }

        //title screen
        if (gameState == titleState){

            ui.draw(graph2);

        }//others
        else {
            //tile
            tileM.draw(graph2);
            //add entity
            entityList.add(player);
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    entityList.add(npc[i]);
                }

            }
            for (int i = 0; i < obj.length; i++){
                if (obj[i] != null){
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < mob.length; i++){
                if (mob[i] != null){
                    entityList.add(mob[i]);
                }
            }
            //sort entityList
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });
            //draw entity
            for (int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(graph2);
            }
            //empty entityList
            entityList.clear();

            //ui
            ui.draw(graph2);
        }

        if (keyH.debugPressed) {
            long drawEnd;
            drawEnd = System.nanoTime();
            long timeRender = drawEnd - drawStart;
            graph2.setColor(Color.white);
            graph2.drawString("Time render: " + timeRender, 30, 30);
            System.out.println("Time render: " + timeRender);
        }
        //end debug

        graph2.dispose();
    }

    //function to handle bgm
    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    //function to handle sfx
    public void playSoundEffect(int i){

        soundEffect.setFile(i);
        soundEffect.play();
    }
}
