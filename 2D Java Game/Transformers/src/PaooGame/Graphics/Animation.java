package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    public static void playerAni(Graphics2D g, String direction, String lastDirection, int shoot, int x, int y,int spriteNum,boolean invincible) {
        if(invincible){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        switch (direction){
            case "idle":
                if(shoot==1)
                {
                    BufferedImage img=Assets.pRightShoot;
                    switch (lastDirection){
                        case "right":
                            //img=Assets.pRightShoot;
                            break;
                        case "left":
                            img=Assets.pLeftShoot;
                            break;
                        case "up":
                            img=Assets.pUpShoot;
                            break;
                        case "down":
                            img=Assets.pDownShoot;
                            break;
                    }
                    g.drawImage(img, x,y,64,64,null);
                }
                else
                    g.drawImage(Assets.pIdle[spriteNum], x,y,64,64,null);

                break;
            case "up":
                if(shoot==1)
                    g.drawImage(Assets.pUpShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.pUp[spriteNum], x,y,64,64,null);

                break;
            case "down":
                if(shoot==1)
                    g.drawImage(Assets.pDownShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.pDown[spriteNum], x,y,64,64,null);

                break;
            case "left":
            case "upLeft":
            case "downLeft":
                if(shoot==1)
                    g.drawImage(Assets.pLeftShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.pLeft[spriteNum], x, y, 64, 64, null);
                break;
            case "right":
            case "upRight":
            case "downRight":
                if(shoot==1)
                    g.drawImage(Assets.pRightShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.pRight[spriteNum], x, y, 64, 64, null);
                break;
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public static void carAni(Graphics2D g, String lastDirection, int x, int y, boolean invincible, int lvl) {
        if(invincible){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        switch (lastDirection){
            case "up":
                g.drawImage(Assets.carUp, x, y, 64, 64, null);
                break;
            case "upRight":
                if(lvl % 2 == 1)
                    g.drawImage(Assets.carUpRight, x, y, 64, 64, null);
                else
                    g.drawImage(Assets.carUp, x, y, 64, 64, null);
                break;
            case "upLeft":
                if(lvl % 2 == 1)
                    g.drawImage(Assets.carUpLeft, x, y, 64, 64, null);
                else
                    g.drawImage(Assets.carUp, x, y, 64, 64, null);
                break;
            case "downRight":
                g.drawImage(Assets.carDownRight, x, y, 64, 64, null);
                break;
            case "downLeft":
                g.drawImage(Assets.carDownLeft, x, y, 64, 64, null);
                break;
            case "down":
                g.drawImage(Assets.carDown, x, y, 64, 64, null);
                break;
            case "left":
                g.drawImage(Assets.carLeft, x, y, 64, 64, null);
                break;
            case "right":
                g.drawImage(Assets.carRight, x, y, 64, 64, null);
                break;
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public static void carAni1(Graphics2D g, int x, int y) {
        g.drawImage(Assets.car1, x, y, 64, 64, null);
    }

    public static void carAni2(Graphics2D g, int x, int y) {
        g.drawImage(Assets.car2, x, y, 64, 64, null);
    }

    public static void carAni3(Graphics2D g, int x, int y) {
        g.drawImage(Assets.car3, x, y, 64, 64, null);
    }

    public static void enemyAni(Graphics2D g, String direction, String lastDirection, int shoot, int x, int y, int spriteNum,boolean invincible) {
        if(invincible){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        switch (direction){
            case "idle":
                if(shoot==1)
                {
                    BufferedImage img=Assets.eRightShoot;
                    switch (lastDirection){
                        case "right":
                            break;
                        case "left":
                            img=Assets.eLeftShoot;
                            break;
                        case "up":
                            img=Assets.eUpShoot;
                            break;
                        case "down":
                            img=Assets.eDownShoot;
                            break;
                    }
                    g.drawImage(img, x,y,64,64,null);
                }
                else
                    g.drawImage(Assets.eIdle[spriteNum], x,y,64,64,null);

                break;
            case "up":
                if(shoot==1)
                    g.drawImage(Assets.eUpShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.eUp[spriteNum], x,y,64,64,null);

                break;
            case "down":
                if(shoot==1)
                    g.drawImage(Assets.eDownShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.eDown[spriteNum], x,y,64,64,null);

                break;
            case "left":
            case "upLeft":
            case "downLeft":
                if(shoot==1)
                    g.drawImage(Assets.eLeftShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.eLeft[spriteNum], x, y, 64, 64, null);
                break;
            case "right":
            case "upRight":
            case "downRight":
                if(shoot==1)
                    g.drawImage(Assets.eRightShoot, x,y,64,64,null);
                else
                    g.drawImage(Assets.eRight[spriteNum], x, y, 64, 64, null);
                break;
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public static void bossAni(Graphics2D g, String direction, String lastDirection, int shoot, int x, int y, int spriteNum, boolean invincible) {
        if(invincible){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        switch (direction){
            case "idle":
                if(shoot==1)
                {
                    BufferedImage img=Assets.bRightShoot;
                    switch (lastDirection){
                        case "right":
                            break;
                        case "left":
                            img=Assets.bLeftShoot;
                            break;
                        case "up":
                            img=Assets.bUpShoot;
                            break;
                        case "down":
                            img=Assets.bDownShoot;
                            break;
                    }
                    g.drawImage(img, x,y,128,128,null);
                }
                else
                    g.drawImage(Assets.bIdle[spriteNum], x,y,128,128,null);

                break;
            case "up":
                if(shoot==1)
                    g.drawImage(Assets.bUpShoot, x,y,128,128,null);
                else
                    g.drawImage(Assets.bUp[spriteNum], x,y,128,128,null);

                break;
            case "down":
                if(shoot==1)
                    g.drawImage(Assets.bDownShoot, x,y,128,128,null);
                else
                    g.drawImage(Assets.bDown[spriteNum], x,y,128,128,null);

                break;
            case "left":
            case "upLeft":
            case "downLeft":
                if(shoot==1)
                    g.drawImage(Assets.bLeftShoot, x,y,128,128,null);
                else
                    g.drawImage(Assets.bLeft[spriteNum], x, y, 128, 128, null);
                break;
            case "right":
            case "upRight":
            case "downRight":
                if(shoot==1)
                    g.drawImage(Assets.bRightShoot, x,y,128,128,null);
                else
                    g.drawImage(Assets.bRight[spriteNum], x, y, 128, 128, null);
                break;
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public static void fireBallAni(Graphics2D g, String direction, int x, int y) {
        g.drawImage(Assets.fireBall, x,y,64,64,null);
    }
    public static void blackFireBallAni(Graphics2D g, String direction, int x, int y) {
        g.drawImage(Assets.blackFireBall, x,y,64,64,null);
    }

}
