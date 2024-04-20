package Main;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;

public class Main {
    
    public static void main(String[] args){

        JFrame Window = new JFrame();
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.setResizable(false);
        Window.setTitle("What game????");

         
        GamePanel gamePanel = new GamePanel();
        Window.add(gamePanel);
        
        Window.pack(); //sized to fit the preferred size and layouts of its components

        Window.setLocationRelativeTo(null);
        Window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
