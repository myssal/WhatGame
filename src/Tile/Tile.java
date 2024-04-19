package Tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;
    public int tileOrder;
    public String tileName;

    public Tile(int tileOrder, String tileName, boolean collision){
        this.tileOrder = tileOrder;
        this.tileName = tileName;
        this.collision = collision;
    }
}
