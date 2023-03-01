package PaooGame.State;

import PaooGame.Game;

import java.awt.*;

public class LvlUpState implements State{

    Font arialUp;

    boolean ok = true;

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {
        if (ok) {
            buttonsInit(gp, g);
            ok = false;
        }
        if (gp.lvlUpTime > 0) {
            if (gp.lvlUpTime <= 30) {
                float alpha = 1f;
                AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g.setComposite(transparency);
                g.setFont(arialUp);
                g.setColor(Color.WHITE);
                g.drawString("Level Up!", 473, 473);
                g.setColor(Color.RED);
                g.drawString("Level Up!", 470, 470);
            }
            if (gp.lvlUpTime > 30 && gp.lvlUpTime <= 60) {
                float alpha = 0.5f;
                AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g.setComposite(transparency);
                g.setFont(arialUp);
                g.setColor(Color.WHITE);
                g.drawString("Level Up!", 473, 473);
                g.setColor(Color.RED);
                g.drawString("Level Up!", 470, 470);
            }
            if (gp.lvlUpTime > 60 && gp.lvlUpTime <= 90) {
                float alpha = 1f;
                AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g.setComposite(transparency);
                g.setFont(arialUp);
                g.setColor(Color.WHITE);
                g.drawString("Level Up!", 473, 473);
                g.setColor(Color.RED);
                g.drawString("Level Up!", 470, 470);
            }
            if (gp.lvlUpTime > 90 && gp.lvlUpTime <= 120) {
                float alpha = 0.5f;
                AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g.setComposite(transparency);
                g.setFont(arialUp);
                g.setColor(Color.WHITE);
                g.drawString("Level Up!", 473, 473);
                g.setColor(Color.RED);
                g.drawString("Level Up!", 470, 470);
            }
            if (gp.lvlUpTime > 120 && gp.lvlUpTime <= 150) {
                float alpha = 1f;
                AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
                g.setComposite(transparency);
                g.setFont(arialUp);
                g.setColor(Color.WHITE);
                g.drawString("Level Up!", 473, 473);
                g.setColor(Color.RED);
                g.drawString("Level Up!", 470, 470);
            }
        } else {
            gp.context.setState(gp.playState);
            gp.lvl++;
            if (gp.lvl == 2) {
                gp.soundM.playEngine();
                gp.setupLvl2();
            } else
                gp.setupLvl3();
        }
    }

    public void buttonsInit(Game gp, Graphics2D g){
        arialUp = new Font("Arial", Font.BOLD, 80);
    }

    public String toString(){
        return "LvlUp GameState";
    }
}
