package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Graphics.Animation;

import java.awt.*;
import java.util.Random;


public class CarEnemy extends Entity{

    public CarEnemy(Game gp) {
        super(gp);
        setDefaultValues();
    }

    public int type;

    public void setDefaultValues(){
        lives = 1;
        form = 2;
        Random random = new Random();
        speed = random.nextInt(6) + 2;
        type = random.nextInt(3) + 1;
        direction = "up";
        lastDirection = direction;
        solidArea = new Rectangle(16,0,32,64);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 30){
            Random random = new Random();
            int i = random.nextInt(150) + 1;

            if (i <= 25) {
                direction = "upRight";
                lastDirection = direction;
            }
            if (i > 25 && i <= 50) {
                direction = "upLeft";
                lastDirection = direction;
            }
            if (i > 50) {
                direction = "up";
                lastDirection = direction;
            }
            actionLockCounter = 0;
        }
    }

    public void draw(Graphics2D g) {
        if (worldY > 200) {
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            int rightOffset = Game.width - screenX;
            int downOffset = Game.height - screenY;
            int x = screenX;
            int y = screenY;

            if (screenX > worldX)
                x = worldX;
            if (screenY > worldY)
                y = worldY;
            if (rightOffset > gp.worldWidth - worldX)
                x = Game.width - (gp.worldWidth - worldX);
            if (downOffset > gp.worldHeight - worldY)
                y = Game.height - (gp.worldHeight - worldY);

            if (worldX + 64 > gp.player.worldX - gp.player.screenX &&
                    worldX - 64 < gp.player.worldX + gp.player.screenX &&
                    worldY + 64 > gp.player.worldY - gp.player.screenY &&
                    worldY - 2 * 64 < gp.player.worldY + gp.player.screenY) {
                switch (type) {
                    case 1:
                        Animation.carAni1(g, x, y);
                        break;
                    case 2:
                        Animation.carAni2(g, x, y);
                        break;
                    case 3:
                        Animation.carAni3(g, x, y);
                        break;
                }

            } else if (gp.player.screenX > gp.player.worldX ||
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX ||
                    downOffset > gp.worldHeight - gp.player.worldY) {
                switch (type) {
                    case 1:
                        Animation.carAni1(g, x, y);
                        break;
                    case 2:
                        Animation.carAni2(g, x, y);
                        break;
                    case 3:
                        Animation.carAni3(g, x, y);
                        break;
                }
            }
            /*
            g.setColor(Color.RED);
            int dfx = solidArea.x;
            int dfy = solidArea.y;
            solidArea.x += x;
            solidArea.y += y;
            g.draw(solidArea);
            solidArea.x = dfx;
            solidArea.y = dfy;
             */
        }
    }
}
