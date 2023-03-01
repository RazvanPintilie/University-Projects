package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class InfoState implements State{


    BufferedImage info;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager backButton;

    boolean ok = true;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        g.drawImage(info,null,0,0);
        int x = Game.width / 2 - 128;
        int y = 200;
        int dy = 170;

        y += 2*dy;
        backButton.setX(x);
        backButton.setY(y);
        backButton.checkPressed(gp);
        if(backButton.pressed) {
            gp.lastState.handle(gp.context);
            Thread.sleep(150);
        }
        backButton.checkReleased(gp);
        backButton.draw(g);
    }

    public void buttonsInit(Game gp, Graphics2D g){
        info = LoadImage("/menu/info.png");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");

        backButton = new ButtonManager(gp,0,0,256,128,buttonIcon,buttonPressedIcon,"Back");

    }

    public String toString(){
        return "Info GameState";
    }
}
