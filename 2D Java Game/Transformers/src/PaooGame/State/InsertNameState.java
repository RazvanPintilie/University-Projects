package PaooGame.State;

import PaooGame.Game;
import PaooGame.InputManager.KeyManager;
import PaooGame.Utils.AccessDataBase;
import PaooGame.Utils.ButtonManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.ImageLoader.LoadImage;

public class InsertNameState implements State{

    Font arialName;

    BufferedImage background;
    BufferedImage buttonIcon;
    BufferedImage buttonPressedIcon;

    ButtonManager menuButton;
    ButtonManager continueButton;

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
        g.drawImage(background, null, 0, 0);
        g.setFont(arialName);
        g.setColor(Color.BLACK);
        g.drawString("Enter your name!", 483, 233);
        g.setColor(Color.YELLOW);
        g.drawString("Enter your name!", 480, 230);

        int x = Game.width / 2 - 128;
        int y = 230;
        int dy = 170;

        g.setColor(Color.WHITE);
        g.drawRect(x, 286, 256, 64);
        g.setColor(Color.BLACK);
        g.fillRect(x + 1, 287, 254, 62);

        g.setColor(Color.WHITE);
        int length = (int) g.getFontMetrics().getStringBounds(KeyManager.buffer, g).getWidth();
        g.drawString(KeyManager.buffer + "_ ", x + (254 - length) / 2 - 10, y + 102);

        y += dy;
        continueButton.setX(x);
        continueButton.setY(y);
        continueButton.checkPressed(gp);
        if (continueButton.pressed) {
            gp.InitGame();
            gp.setupLvl1();
            gp.name = KeyManager.buffer;
            KeyManager.buffer = "";
            gp.playState.handle(gp.context);
            Thread.sleep(150);
        }
        continueButton.checkReleased(gp);
        continueButton.draw(g);

        y += dy;
        menuButton.setX(x);
        menuButton.setY(y);
        menuButton.checkPressed(gp);
        if (menuButton.pressed) {
            gp.titleState.handle(gp.context);
            Thread.sleep(150);
        }
        menuButton.checkReleased(gp);
        menuButton.draw(g);

    }

    public void buttonsInit(Game gp, Graphics2D g){
        background = LoadImage("/menu/Background2.jpg");
        buttonIcon = LoadImage("/menu/Button.png");
        buttonPressedIcon = LoadImage("/menu/ButtonPressed.png");
        menuButton = new ButtonManager(gp, 0,0,256,128, buttonIcon, buttonPressedIcon, "Menu");
        continueButton = new ButtonManager(gp,0,0,256,128,buttonIcon,buttonPressedIcon,"Continue");

        arialName = new Font("Arial", Font.BOLD, 40);
    }

    public String toString() {
        return "InsertName GameState";
    }
}
