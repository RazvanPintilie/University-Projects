package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class TitleState implements State{

    BufferedImage background;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager newButton;
    ButtonManager quitButton;
    ButtonManager scoresButton;
    ButtonManager menuButton;
    ButtonManager infoButton;
    ButtonManager loadButton;
    ButtonManager saveButton;
    ButtonManager settingsButton;
    ButtonManager resumeButton;

    boolean ok = true;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        gp.soundM.playEngine();
        gp.soundM.stopEngine();
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        g.drawImage(background,null,0,0);
        int x = 240;
        int dx = (Game.width - x)/2;
        int y = 200;
        int dy = 170;

        newButton.setX(x);
        newButton.setY(y);
        newButton.checkPressed(gp);
        if(newButton.pressed) {
            gp.InitGame();
            gp.setupLvl1();
            gp.insertNameState.handle(gp.context);
            Thread.sleep(150);
        }
        newButton.checkReleased(gp);
        newButton.draw(g);

        x+=dx;
        infoButton.setX(x);
        infoButton.setY(y);
        infoButton.checkPressed(gp);
        if(infoButton.pressed){
            gp.infoState.handle(gp.context);
            gp.lastState = gp.titleState;
            Thread.sleep(150);
        }
        infoButton.checkReleased(gp);
        infoButton.draw(g);

        x-=dx;
        y+=dy;
        loadButton.setX(x);
        loadButton.setY(y);
        loadButton.checkPressed(gp);
        if(loadButton.pressed){
            gp.loadState.handle(gp.context);
            gp.lastState = gp.titleState;
            Thread.sleep(150);
        }
        loadButton.checkReleased(gp);
        loadButton.draw(g);

        y +=dy;
        scoresButton.setX(x);
        scoresButton.setY(y);
        scoresButton.checkPressed(gp);
        if(scoresButton.pressed){
            gp.scoresState.handle(gp.context);
            Thread.sleep(150);
        }
        scoresButton.checkReleased(gp);
        scoresButton.draw(g);

        y -= dy;
        x += dx;
        settingsButton.setX(x);
        settingsButton.setY(y);
        settingsButton.checkPressed(gp);
        if(settingsButton.pressed){
            gp.settingsState.handle(gp.context);
            gp.lastState = gp.titleState;
            Thread.sleep(150);
        }
        settingsButton.checkReleased(gp);
        settingsButton.draw(g);

        y += dy;
        quitButton.setX(x);
        quitButton.setY(y);
        quitButton.checkPressed(gp);
        if(quitButton.pressed) {
            Thread.sleep(150);
            System.exit(0);
            gp.StopGame();
        }
        quitButton.checkReleased(gp);
        quitButton.draw(g);
    }

    public void buttonsInit(Game gp, Graphics2D g){

        background = LoadImage("/menu/Background.png");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");

        newButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "New");
        quitButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Quit");
        scoresButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Scores");
        menuButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Menu");
        infoButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Info");
        loadButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Load");
        saveButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Save");
        settingsButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Settings");
        resumeButton = new ButtonManager(gp,0,0,256,128, buttonIcon, buttonPressedIcon, "Resume");

    }

    public String toString(){
        return "Title GameState";
    }
}
