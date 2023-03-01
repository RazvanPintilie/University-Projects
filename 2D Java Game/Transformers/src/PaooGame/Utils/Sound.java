package PaooGame.Utils;

import javax.sound.sampled.*;
import java.io.File;


public class Sound {

    Clip clip;

    public Sound(String path){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
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
