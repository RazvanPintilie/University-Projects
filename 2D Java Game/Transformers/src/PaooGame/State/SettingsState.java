package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class SettingsState implements State{

    BufferedImage background;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    public ButtonManager musicButton;
    public ButtonManager soundButton;
    ButtonManager menuButton;
    ButtonManager backButton;

    public boolean ok = true;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        g.drawImage(background,null,0,0);
        int x = Game.width / 2 - 128;
        int y = 200;
        int dy = 170;

        musicButton.setX(x);
        musicButton.setY(y);
        musicButton.checkPressed(gp);
        if(musicButton.pressed) {
            gp.music++;
            if(gp.music % 2 == 1) {
                musicButton.setText("Music Off");
                gp.soundM.stopMusic();
            }
            else {
                musicButton.setText("Music On");
                gp.soundM.playMusic();
            }
            Thread.sleep(150);
        }
        musicButton.checkReleased(gp);
        musicButton.draw(g);

        y += dy;
        soundButton.setX(x);
        soundButton.setY(y);
        soundButton.checkPressed(gp);
        if(soundButton.pressed) {
            gp.sound++;
            if(gp.sound % 2 == 1) {
                soundButton.setText("Sound Off");
                gp.soundM.stopEngine();
            }
            else
                soundButton.setText("Sound On");
            Thread.sleep(150);
        }
        soundButton.checkReleased(gp);
        soundButton.draw(g);

        y += dy;
        if(gp.lastState == gp.titleState){
            menuButton.setX(x);
            menuButton.setY(y);
            menuButton.checkPressed(gp);
            if(menuButton.pressed) {
                gp.titleState.handle(gp.context);
                Thread.sleep(150);
            }
            menuButton.checkReleased(gp);
            menuButton.draw(g);
        }
        else{
            backButton.setX(x);
            backButton.setY(y);
            backButton.checkPressed(gp);
            if(backButton.pressed) {
                gp.pauseState.handle(gp.context);
                Thread.sleep(150);
            }
            backButton.checkReleased(gp);
            backButton.draw(g);
        }
    }

    public void buttonsInit(Game gp, Graphics2D g){
        background = LoadImage("/menu/Background.png");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");

        menuButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Menu");
        backButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Back");
        musicButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Music On");
        soundButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Sound On");

    }

    public String toString(){
        return "Settings GameState";
    }

}

