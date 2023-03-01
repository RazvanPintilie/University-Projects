package PaooGame.Entity;

import PaooGame.Game;

import java.awt.*;

public abstract class Entity {

    Game gp;

    public int worldX,worldY;   //position on the worldMap
    public int speed;
    public double dec = 0;
    public String direction, lastDirection;

    public int spriteCounter = 0;
    public int spriteNum = 0;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int shoot = 0;
    public int form = 1;
    public int lives = 3;
    public int damage = 1;

    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public Projectile projectile;

    public boolean alive = true;

    public Entity(Game gp){
        this.gp = gp;
    }

    public void setAction(){

    }

    public void update(){
        setAction();
        collisionOn = false;

        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkPlayer(this);
        if(!collisionOn && shoot == 0)
        {
            if(form == 1){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            else{
                switch (direction){
                    case "upLeft": worldX -= 2; break;
                    case "upRight": worldX += 2; break;
                    case "up": break;
                }
                if(worldY > 0)
                    worldY -= speed;
            }
        }
        spriteCounter++;
        if(spriteCounter > 6){
            if(spriteNum == 2 )
                spriteNum = 0;
            else
                spriteNum++;
            spriteCounter = 0;
        }

        if(invincible == true){
            invincibleCounter ++;
            if(invincibleCounter > 30){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public abstract void draw(Graphics2D g);

}
