package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.AccessDataBase;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class PauseState implements State{

    BufferedImage background;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager menuButton;
    ButtonManager infoButton;
    ButtonManager loadButton;
    ButtonManager saveButton;
    ButtonManager settingsButton;
    ButtonManager resumeButton;

    boolean ok = true;

    public AccessDataBase accessDataBase;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        accessDataBase = new AccessDataBase(gp);
        float alpha = 0.9f;
        AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g.setComposite(transparency);
        g.drawImage(background,null,0,0);
        int x = 240;
        int dx = (Game.width - x)/2;
        int y = 200;
        int dy = 170;

        resumeButton.setX(x);
        resumeButton.setY(y);
        resumeButton.checkPressed(gp);
        if(resumeButton.pressed){
            gp.playState.handle(gp.context);
            Thread.sleep(150);
        }
        resumeButton.checkReleased(gp);
        resumeButton.draw(g);

        y += dy;
        loadButton.setX(x);
        loadButton.setY(y);
        loadButton.checkPressed(gp);
        if(loadButton.pressed){
            gp.loadState.handle(gp.context);
            gp.lastState = gp.pauseState;
            Thread.sleep(150);

        }
        loadButton.checkReleased(gp);
        loadButton.draw(g);

        y += dy;
        saveButton.setX(x);
        saveButton.setY(y);
        saveButton.checkPressed(gp);
        if(saveButton.pressed){
            int rELeft = 0;
            int rERight = 0;
            for(int i = 0; i < gp.enemies.length; ++i) {
                if(gp.enemies[i] != null){
                    if (gp.enemies[i].worldX > gp.worldHeight / 2)
                        rERight ++;
                    else
                        rELeft ++;
                }
            }
            accessDataBase.insertSave(gp.lvl, gp.name, gp.playTime, gp.player.worldX, gp.player.worldY, gp.player.damage, gp.player.lives, rELeft, rERight);
            Thread.sleep(150);
        }
        saveButton.checkReleased(gp);
        saveButton.draw(g);

        x += dx;
        y -= 2*dy;
        infoButton.setX(x);
        infoButton.setY(y);
        infoButton.checkPressed(gp);
        if(infoButton.pressed){
            gp.infoState.handle(gp.context);
            gp.lastState = gp.pauseState;
            Thread.sleep(150);
        }
        infoButton.checkReleased(gp);
        infoButton.draw(g);

        y += dy;
        settingsButton.setX(x);
        settingsButton.setY(y);
        settingsButton.checkPressed(gp);
        if(settingsButton.pressed){
            gp.settingsState.handle(gp.context);
            gp.lastState = gp.pauseState;
            Thread.sleep(150);
        }
        settingsButton.checkReleased(gp);
        settingsButton.draw(g);

        y += dy;
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

    public void buttonsInit(Game gp, Graphics2D g){

        background = LoadImage("/menu/Background.png");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");

        menuButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Menu");
        infoButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Info");
        loadButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Load");
        saveButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Save");
        settingsButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Settings");
        resumeButton = new ButtonManager(gp,0,0,256,128, buttonIcon, buttonPressedIcon, "Resume");

    }

    public String toString(){
        return "Pause GameState";
    }

}
