package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Object.DamageEnergonObject;
import PaooGame.Object.LifeEnergonObject;

import java.awt.*;

import java.util.Random;

public class Projectile extends  Entity{

    Entity user;

    protected int maxDuration;
    boolean bossOn = false;

    public Projectile(Game gp){
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.user = user;
        this.damage = 1;
        this.worldX = worldX;
        this.worldY = worldY;
        this.alive = alive;
        this.direction = direction;
        this.lives = maxDuration;
    }

    public void update(){
        if(user == gp.player){
            int enemyIndex = gp.cChecker.checkEntity(this,gp.enemies);
            if(enemyIndex != -1){
                alive = false;
                if(!gp.enemies[enemyIndex].invincible) {
                    gp.soundM.playHit();
                    gp.enemies[enemyIndex].lives -= gp.player.damage;
                    gp.enemies[enemyIndex].invincible = true;
                }
                if(gp.enemies[enemyIndex].lives <= 0) {
                    gp.enemies[enemyIndex].alive = false;
                    Random random = new Random();
                    int i = random.nextInt(75) + 1;
                    if(i <= 25)
                        gp.obj.add(new LifeEnergonObject(gp.enemies[enemyIndex].worldX,gp.enemies[enemyIndex].worldY));
                    else if(i <= 50)
                        gp.obj.add(new DamageEnergonObject(gp.enemies[enemyIndex].worldX,gp.enemies[enemyIndex].worldY));
                    gp.enemies[enemyIndex] = null;
                }
                boolean empty = true;
                for(int i = 0; i < gp.enemies.length; ++i)
                    if(gp.enemies[i] != null){
                        empty = false;
                        break;
                    }
                if(empty && !bossOn && gp.lvl == 3){
                    gp.enemies[0] = new Boss(gp);
                    if(gp.player.worldX > gp.lvl3WorldX / 2){
                        gp.enemies[0].worldX = 6297;
                        gp.enemies[0].worldY = 851;
                    }
                    else{
                        gp.enemies[0].worldX = 580;
                        gp.enemies[0].worldY = 3164;
                    }
                    gp.player.invincible = true;
                    bossOn = true;
                }
            }
        }
        else{
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(!gp.player.invincible && contactPlayer){
                gp.soundM.playHit();
                gp.player.lives -= user.damage;
                alive = false;
                gp.player.invincible = true;
            }
        }

        gp.cChecker.checkTile(this);

        if(!collisionOn)
            switch (direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        else{
            alive = false;
            collisionOn = false;
        }

        lives --;
        if(lives <= 0)
            alive = false;

    }

    public void draw(Graphics2D g) {

    }
}
