package PaooGame.Object;

import PaooGame.Entity.Projectile;
import PaooGame.Game;
import PaooGame.Graphics.Animation;
import PaooGame.Graphics.Assets;

import java.awt.*;

public class BlackFireBall extends Projectile {

    Game gp;

    public BlackFireBall(Game gp){
        super(gp);
        this.gp = gp;
        setDefaultValues();
    }

    public void setDefaultValues(){
        solidArea.x = 25;
        solidArea.y = 25;
        solidArea.width = 14;
        solidArea.height = 14;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        speed = 6;
        maxDuration = 120;
        alive = false;
    }

    public void draw(Graphics2D g) {
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

        if(worldX + 64 > gp.player.worldX - gp.player.screenX &&
                worldX - 64 < gp.player.worldX + gp.player.screenX &&
                worldY + 64 > gp.player.worldY - gp.player.screenY &&
                worldY - 2*64 < gp.player.worldY + gp.player.screenY) {
            Animation.blackFireBallAni(g,direction,x,y);
        }
        else if(gp.player.screenX > gp.player.worldX ||
                gp.player.screenY > gp.player.worldY ||
                rightOffset > gp.worldWidth - gp.player.worldX ||
                downOffset > gp.worldHeight - gp.player.worldY) {
            Animation.blackFireBallAni(g,direction,x,y);
        }
        /*g.setColor(Color.RED);
        int dfx = solidArea.x;
        int dfy = solidArea.y;
        solidArea.x += screenX;
        solidArea.y += screenY;
        g.draw(solidArea);
        solidArea.x = dfx;
        solidArea.y = dfy;*/
    }
}
