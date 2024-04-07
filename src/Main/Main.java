package Main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args){

        JFrame Window = new JFrame();
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.setResizable(false);
        Window.setTitle("2D Adventure");
        
        //set game icon

         
        GamePanel gamePanel = new GamePanel();
        Window.add(gamePanel);
        
        Window.pack(); //sized to fit the preferred size and layouts of its components

        Window.setLocationRelativeTo(null);
        Window.setVisible(true);

        gamePanel.startGameThread();
    }
}
