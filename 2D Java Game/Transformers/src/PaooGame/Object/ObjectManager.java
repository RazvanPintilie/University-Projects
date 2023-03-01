package PaooGame.Object;

import PaooGame.Game;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectManager {
    public boolean collision;

    public static final int OBJ_WIDTH  = 64;
    public static final int OBJ_HEIGHT = 64;

    public int worldX, worldY;
    public BufferedImage img;
    public String name;

    public Rectangle solidArea = new Rectangle(0,24,64,40);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;


    public ObjectManager(BufferedImage img)
    {
        this.img = img;
        this.collision = false;
    }

    public void draw(Graphics2D g,Game gp) {

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

        if(worldX + OBJ_WIDTH > gp.player.worldX - gp.player.screenX &&
                worldX - OBJ_WIDTH < gp.player.worldX + gp.player.screenX &&
                worldY + OBJ_HEIGHT > gp.player.worldY - gp.player.screenY &&
                worldY - 2*OBJ_HEIGHT < gp.player.worldY + gp.player.screenY)
            g.drawImage(img,x,y,null);
        else if(gp.player.screenX > gp.player.worldX ||
                gp.player.screenY > gp.player.worldY ||
                rightOffset > gp.worldWidth - gp.player.worldX ||
                downOffset > gp.worldHeight - gp.player.worldY)
            g.drawImage(img,x,y,null);
    }
}
