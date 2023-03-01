package PaooGame.State;

import PaooGame.Game;
import PaooGame.Utils.AccessDataBase;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class LoadState implements State {

    Font arialLost;
    Font arialName;

    BufferedImage background2;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager loadButton1;
    ButtonManager loadButton2;
    ButtonManager loadButton3;
    ButtonManager loadButton4;
    ButtonManager backButton;

    public AccessDataBase accessDataBase;

    boolean ok = true;

    public void handle(Context context) {
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        accessDataBase = new AccessDataBase(gp);
        DecimalFormat df = new DecimalFormat("#0.00");

        g.drawImage(background2,null,0,0);
        int x = Game.width / 2 - 128;
        int y = 210;
        int dy = 170;

        int loadNumber = -1;

        g.setFont(arialLost);
        g.setColor(Color.RED);
        g.drawString("Name", 163, y - 37 );
        g.setColor(Color.WHITE);
        g.drawString("Name", 160, y - 40);
        g.setColor(Color.RED);
        g.drawString("Level", 393, y - 37);
        g.setColor(Color.WHITE);
        g.drawString("Level", 390, y - 40);
        g.setColor(Color.RED);
        g.drawString("Time", 593, y - 37);
        g.setColor(Color.WHITE);
        g.drawString("Time", 590, y - 40);

        g.setColor(Color.RED);
        g.drawString("Load Game", 473, y - 137);
        g.setColor(Color.WHITE);
        g.drawString("Load Game", 470, y - 140);
        g.setFont(arialName);

        loadButton1.setX(900);
        loadButton1.setY(y - 40);
        loadButton1.checkPressed(gp);
        if(loadButton1.pressed) {
            loadNumber = 1;
            Thread.sleep(150);
        }
        loadButton1.checkReleased(gp);
        loadButton1.draw(g);

        loadButton2.setX(900);
        loadButton2.setY(y + 75);
        loadButton2.checkPressed(gp);
        if(loadButton2.pressed) {
            loadNumber = 2;
            Thread.sleep(150);
        }
        loadButton2.checkReleased(gp);
        loadButton2.draw(g);

        loadButton3.setX(900);
        loadButton3.setY(y + 190);
        loadButton3.checkPressed(gp);
        if(loadButton3.pressed) {
            loadNumber = 3;
            Thread.sleep(150);
        }
        loadButton3.checkReleased(gp);
        loadButton3.draw(g);

        loadButton4.setX(900);
        loadButton4.setY(y + 305);
        loadButton4.checkPressed(gp);
        if(loadButton4.pressed) {
            loadNumber = 4;
            Thread.sleep(150);
        }
        loadButton4.checkReleased(gp);
        loadButton4.draw(g);

        accessDataBase.getSave(g, loadNumber);

        y += 2 * dy + 85;
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
        background2 = LoadImage("/menu/Background2.jpg");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");

        loadButton1 = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Load1");
        loadButton2 = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Load2");
        loadButton3 = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Load3");
        loadButton4 = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Load4");
        backButton = new ButtonManager(gp,0,0,256,128,buttonIcon,buttonPressedIcon,"Back");

        arialLost = new Font("Arial", Font.BOLD, 60);
        arialName = new Font("Arial", Font.BOLD, 40);

    }

    public String toString() {
        return "Load GameState";
    }
}