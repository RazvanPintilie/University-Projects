package PaooGame.Utils;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Objects;


public class UI {

    Game gp;
    Graphics2D g;

    Font verdanaFps;
    Font verdanaMessage;
    Font arialLives;
    Font arialTime;
    Font arialLost;
    Font arialUp;
    Font arialName;

    BufferedImage lifeIcon;
    BufferedImage damageIcon;
    BufferedImage carIcon;
    BufferedImage botIcon;
    BufferedImage form;

    public boolean messageOn = false;
    public String message = "";

    int messageTimer = 0;
    int lifeIconX;


    DecimalFormat df = new DecimalFormat("#0.00");
    public AccessDataBase accessDataBase;

    public UI(Game gp) throws InterruptedException {
        this.gp = gp;
        accessDataBase = new AccessDataBase(gp);
        verdanaFps = new Font("Verdana", Font.BOLD, 20);
        verdanaMessage = new Font("Verdana", Font.ITALIC, 20);
        arialLives = new Font("Arial", Font.ITALIC, 25);
        arialTime = new Font("Arial", Font.BOLD, 20);
        arialLost = new Font("Arial", Font.BOLD, 60);
        arialUp = new Font("Arial", Font.BOLD, 80);
        arialName = new Font("Arial", Font.BOLD, 40);
        buttonsInit();
        lifeIconX = 20;
    }

    public void draw(Graphics2D g) throws InterruptedException {

        this.g = g;

        if (gp.context.getState() == gp.playState) {

            //lives
            for (int i = 0; i < gp.player.lives; ++i) {
                g.drawImage(lifeIcon, lifeIconX, 715, 40, 40, null);
                lifeIconX += 40;
            }
            lifeIconX = 20;

            //damage
            if (gp.lvl != 2) {
                g.setFont(arialLives);
                g.setColor(Color.BLUE);
                g.drawImage(damageIcon, 27, 668, 50, 50, null);
                g.drawString("x " + gp.player.damage, 68, 710);
            }

            //timer
            gp.playTime += (double) 1 / 60;
            g.setFont(arialTime);
            g.setColor(Color.WHITE);
            g.drawString("Time: " + df.format(gp.playTime), 1155, 35);

            //current level
            g.drawOval(Game.width / 2 - 43, 9, 102, 52);
            g.setColor(new Color(120, 70, 200));
            g.fillOval(Game.width / 2 - 42, 10, 100, 50);
            g.setColor(new Color(90, 0, 144));
            g.drawString("Lvl: " + gp.lvl, Game.width / 2 - 15, 45);
            g.setColor(Color.WHITE);
            g.drawString("Lvl: " + gp.lvl, Game.width / 2 - 17, 43);

            //cooldown
            if (gp.lvl != 2) {
                if (gp.player.form == 1) {
                    form = carIcon;
                    g.setFont(verdanaFps);
                    g.setColor(Color.BLUE);
                    g.drawImage(form, 1145, 690, 40, 40, null);
                    if (gp.player.timeToCar > 0)
                        g.drawString(": " + df.format(gp.player.timeToCar), 1187, 725);
                    else
                        g.drawString(": Ready!", 1180, 725);
                    gp.player.timeToCar -= (double) 1 / 60;
                } else {
                    form = botIcon;
                    g.setFont(verdanaFps);
                    g.setColor(Color.RED);
                    g.drawImage(form, 1145, 690, 40, 40, null);
                    g.drawString(": " + df.format(gp.player.timeToBot), 1187, 725);
                    gp.player.timeToBot -= (double) 1 / 60;
                }
            }

            //messages
            if (messageOn) {
                g.setFont(verdanaMessage);
                if (Objects.equals(message, "life")) {
                    g.setColor(Color.BLACK);
                    g.drawString("+ 1 x ", 602, 432);
                    g.setColor(Color.WHITE);
                    g.drawString("+ 1 x ", 600, 430);
                    g.drawImage(lifeIcon, 657, 402, 40, 40, null);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawString(message, 549, 432);
                    g.setColor(Color.WHITE);
                    g.drawString(message, 547, 430);
                }

                messageTimer++;
                if (messageTimer > 100) {
                    messageTimer = 0;
                    messageOn = false;
                }
            }
        }
        else{
            gp.context.getState().drawStateScreen(gp, g);
        }

        //fps
        g.setFont(verdanaFps);
        g.setColor(Color.GREEN);
        g.drawString(String.valueOf(gp.showFps), 20, 35);
    }

    public void displayMessage(String msg){
        message = msg;
        messageOn = true;
    }

    public void buttonsInit(){
        lifeIcon = Assets.lifeIcon;
        damageIcon = Assets.damageIcon;
        botIcon = Assets.pIdle[0];
        carIcon = Assets.carUp;
    }
}
