package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Graphics.Animation;

import java.awt.*;
import java.util.Random;

import static PaooGame.Graphics.Assets.purpleLifeIcon;

public class Boss extends Enemy{

    public Boss(Game gp) {
        super(gp);
        type = 2;
        damage = 3;
        lives = 6;
        solidArea = new Rectangle(-32,-32,128,128);
    }

    public void setAction(){
        int dx = this.worldX - gp.player.worldX;
        int dy = this.worldY - gp.player.worldY;
        if(java.lang.Math.abs(dx) <= 384 && java.lang.Math.abs(dy) <= 384){
            if(dx <= 0){
                if(dy > 0) {
                    if(dx * (-1) > dy)
                        direction = "right";
                    else
                        direction = "up";
                }
                else{
                    if(dx * (-1) > dy * (-1))
                        direction = "right";
                    else
                        direction = "down";
                }
            }
            else {
                if(dy > 0) {
                    if(dx > dy)
                        direction = "left";
                    else
                        direction = "up";
                }
                else{
                    if(dx > dy * (-1))
                        direction = "left";
                    else
                        direction = "down";
                }
            }
            lastDirection = direction;
            if(!projectile.alive){
                shoot = 1;
                gp.soundM.playEGun();
                projectile.set(worldX + 32,worldY + 32,lastDirection,true,this);
                gp.projectileList.add(projectile);
            }
        }
        else
            shoot = 0;

        if(shoot == 0)
            actionLockCounter++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(125) + 1;

            if (i <= 25) {
                direction = "up";
                lastDirection = direction;
            }
            if (i > 25 && i <= 50) {
                direction = "down";
                lastDirection = direction;
            }
            if (i > 50 && i <= 75) {
                direction = "left";
                lastDirection = direction;
            }
            if (i > 75 && i <= 100) {
                direction = "right";
                lastDirection = direction;
            }
            if (i > 100) {
                direction = "idle";
            }
            actionLockCounter = 0;
        }
    }


    public void draw(Graphics2D g){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        int rightOffset = Game.width - screenX;
        int downOffset = Game.height - screenY;
        int x = screenX;
        int y = screenY;

        if(screenX > worldX)
            x = worldX;
        if(screenY > worldY)
            y = worldY;
        if(rightOffset > gp.worldWidth - worldX)
            x = Game.width - (gp.worldWidth - worldX);
        if(downOffset > gp.worldHeight - worldY)
            y = Game.height - (gp.worldHeight - worldY);

        Font arialLives = new Font("Arial",Font.BOLD,15);
        g.setFont(arialLives);
        g.setColor(new Color(120,0,200));
        if(worldX + 64 > gp.player.worldX - gp.player.screenX &&
                worldX - 64 < gp.player.worldX + gp.player.screenX &&
                worldY + 64 > gp.player.worldY - gp.player.screenY &&
                worldY - 2*64 < gp.player.worldY + gp.player.screenY) {
            g.drawImage(purpleLifeIcon, x + 35, y - 30, 25, 25, null);
            g.drawString("x " + lives, x + 62, y - 12);
            Animation.bossAni(g, direction, lastDirection, shoot, x, y, spriteNum, invincible);
        }
        else if(gp.player.screenX > gp.player.worldX ||
                gp.player.screenY > gp.player.worldY ||
                rightOffset > gp.worldWidth - gp.player.worldX ||
                downOffset > gp.worldHeight - gp.player.worldY) {
            g.drawImage(purpleLifeIcon, x + 3, y - 30, 25, 25, null);
            g.drawString("x " + lives, x + 30, y - 12);
            Animation.bossAni(g, direction, lastDirection, shoot, x, y, spriteNum, invincible);
        }
    }

}
