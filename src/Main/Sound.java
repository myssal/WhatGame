package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        try {
            Scanner soundInput = new Scanner(new File("res/maps/soundList.txt"));
            int sfxOrder;
            while (soundInput.hasNext()){
                sfxOrder = soundInput.nextInt();
                soundURL[sfxOrder] = getClass().getClassLoader().getResource(soundInput.next());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setFile(int i){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }

    }

    public void play(){

        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }


}
