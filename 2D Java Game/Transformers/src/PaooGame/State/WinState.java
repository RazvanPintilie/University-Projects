package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class WinState implements State{

    Font arialLost;

    BufferedImage background;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager menuButton;
    ButtonManager scoresButton;

    boolean ok = true;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        g.drawImage(background, null, 0, 0);
        g.setFont(arialLost);
        g.setColor(Color.WHITE);
        g.drawString("You won!", 503, 253);
        g.setColor(Color.RED);
        g.drawString("You won!", 500, 250);
        g.setColor(Color.BLACK);
        g.drawString("Your time: " + df.format(gp.playTime), 403, 363);
        g.setColor(Color.WHITE);
        g.drawString("Your time: " + df.format(gp.playTime), 400, 360);

        scoresButton.setX(500);
        scoresButton.setY(420);
        scoresButton.checkPressed(gp);
        if (scoresButton.pressed) {
            gp.scoresState.handle(gp.context);
            Thread.sleep(150);
        }
        scoresButton.checkReleased(gp);
        scoresButton.draw(g);

        menuButton.setX(500);
        menuButton.setY(560);
        menuButton.checkPressed(gp);
        if (menuButton.pressed) {
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
        scoresButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Scores");


        arialLost = new Font("Arial", Font.BOLD, 60);

    }

    public String toString(){
        return "Win GameState";
    }
}
