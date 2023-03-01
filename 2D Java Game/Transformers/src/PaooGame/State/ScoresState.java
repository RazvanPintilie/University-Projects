package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.AccessDataBase;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class ScoresState implements State{

    Font arialLost;
    Font arialName;

    BufferedImage background2;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager menuButton;

    public AccessDataBase accessDataBase;

    boolean ok = true;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        accessDataBase = new AccessDataBase(gp);

        g.drawImage(background2,null,0,0);
        int x = Game.width / 2 - 128;
        int y = 200;

        g.setFont(arialLost);
        g.setColor(Color.RED);
        g.drawString("Hall of Fame", 473, y - 67);
        g.setColor(Color.WHITE);
        g.drawString("Hall of Fame", 470, y - 70);
        g.setColor(Color.RED);
        g.drawString("Name", 253, y + 53);
        g.setColor(Color.WHITE);
        g.drawString("Name", 250, y + 50);
        g.setColor(Color.RED);
        g.drawString("Time", 883, y + 53);
        g.setColor(Color.WHITE);
        g.drawString("Time", 880, y + 50);
        g.setFont(arialName);
        accessDataBase.getScores(g);

        y += 390;
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
        background2 = LoadImage("/menu/Background2.jpg");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");

        menuButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Menu");

        arialLost = new Font("Arial", Font.BOLD, 60);
        arialName = new Font("Arial", Font.BOLD, 40);

    }

    public String toString(){
        return "ScoresState GameState";
    }

}
