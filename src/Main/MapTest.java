package Main;

import Tile.Tile;

import java.io.*;
import java.util.Scanner;

public class MapTest {
    //qol to check out map rightness
    public static void main(String[] args) throws IOException {

        Tile[] tile = new Tile[40];
        int i = 10;
        int tileOrderH;
        String tileNameH;
        Scanner tileMapin = new Scanner(new File("res/maps/tileListCheck.txt"));
        while (tileMapin.hasNext()){
            tileOrderH = tileMapin.nextInt();
            tileNameH = tileMapin.next();
            tile[i] = new Tile(tileOrderH, tileNameH, true);
            i++;
        }
        FileWriter mapCreateIn = new FileWriter("res/maps/completeMapCheck.txt", true);
        Scanner mapRecreate = new Scanner(new File("res/maps/completeMap.txt"));
        int j = 0;
        int tileNumber = 0;
        boolean match = false;
        while (mapRecreate.hasNext()){
            tileNumber = mapRecreate.nextInt();
            for (int it = 10; it < tile.length; it++){
                if (tileNumber == tile[it].tileOrder){
                    mapCreateIn.append(tile[it].tileName+" ");
                    match = true;
                    j++;
                }
                if (match) break;
            }
            if (j == 114){
                mapCreateIn.append("/");
                j = 0;
            }
        }
    }
}
