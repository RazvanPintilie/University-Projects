package PaooGame.Utils;

import PaooGame.Game;

public class SoundManager {

    Game gp;

    public Sound music = new Sound("res/sound/music.wav");
    public Sound engine = new Sound("res/sound/engine.wav");

    public SoundManager(Game gp){
        this.gp = gp;
    }

    public void playMusic(){
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playEngine(){
        if(gp.sound % 2 == 0) {
            engine.loop();
        }
    }

    public void stopEngine(){
        engine.stop();
    }

    public void playPickUp(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/pickUp.wav").play();
        }
    }

    public void playGun(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/gun.wav").play();
        }
    }

    public void playEGun(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/eGun.wav").play();
        }
    }

    public void playHit(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/hit.wav").play();
        }
    }

    public void playLvlUp(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/lvlUp.wav").play();
        }
    }

    public void playWin(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/win.wav").play();
        }
    }

    public void playLose(){
        if(gp.sound % 2 == 0) {
            new Sound("res/sound/lose.wav").play();
        }
    }


}
