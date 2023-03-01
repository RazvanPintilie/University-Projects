package PaooGame.Entity;

import PaooGame.Game;
import PaooGame.Graphics.Animation;
import PaooGame.InputManager.MouseManager;
import PaooGame.Object.FireBallObject;
import PaooGame.Window;
import PaooGame.InputManager.KeyManager;

import java.awt.*;
import java.util.Objects;


public class Player extends Entity{
    KeyManager keyM;
    MouseManager mouseM;

    public final int screenX;
    public int screenY;
    public double timeToCar = 10;
    public double timeToBot = 7;

    private static Player instance = null;

    //SingleTon

    public static Player getInstance(Game gp,Window wnd) {
        if(instance == null)
            instance = new Player(gp,wnd);
        return instance;
    }

    public static void Reset() {
        instance = null;
    }

    private Player(Game gp,Window wnd){
        super (gp);
        this.keyM = wnd.keyM;
        this.mouseM = wnd.mouseM;
        screenX = 1280/2-25;
        screenY = 768/2-50;
        solidArea = new Rectangle(16,14,32,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
    }

    int enemyIndex;
    int objIndex;

    public void setDefaultValues()  {
        speed = 3;
        lives = 3;
        damage = 1;
        projectile = new FireBallObject(gp);
        direction = "idle";
        lastDirection = "right";
    }

    public void setPosition(int x, int y){
        worldX = x;
        worldY = y;
    }

    public void setData(int lives, int damage){
        this.damage = damage;
        this.lives = lives;
    }

    public void input(){
        if(shoot==0 && form ==1)        //robot
        {
            if(keyM.up){
                if(!keyM.right && !keyM.left){
                    direction = "up";
                    lastDirection = direction;
                }
                if(keyM.right)
                {
                    direction = "upRight";
                    lastDirection = "right";
                }
                if(keyM.left)
                {
                    direction = "upLeft";
                    lastDirection = "left";
                }
            }
            if(keyM.down){
                if(!keyM.right && !keyM.left) {
                    direction = "down";
                    lastDirection = direction;
                }
                if(keyM.right)
                {
                    direction = "downRight";
                    lastDirection = "right";
                }
                if(keyM.left)
                {
                    direction = "downLeft";
                    lastDirection = "left";
                }
            }
            if(keyM.right){
                if(!keyM.up && !keyM.down) {
                    direction = "right";
                    lastDirection = direction;
                }
            }
            if(keyM.left){
                if(!keyM.up && !keyM.down) {
                    direction = "left";
                    lastDirection = direction;
                }
            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            objIndex = gp.cChecker.checkObject(this, true);
            enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            pickUpObject(objIndex);

            if(!collisionOn)
            {
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "upLeft": worldY -= speed/Math.sqrt(2); worldX -= speed/Math.sqrt(2); break;
                    case "upRight": worldX += speed/Math.sqrt(2); worldY -= speed/Math.sqrt(2); break;
                    case "down": worldY += speed; break;
                    case "downLeft": worldX -= speed/Math.sqrt(2); worldY += speed/Math.sqrt(2);break;
                    case "downRight":worldX += speed/Math.sqrt(2); worldY += speed/Math.sqrt(2); break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
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
            if(!(keyM.left || keyM.right || keyM.up || keyM.down))
                direction = "idle";
            if(keyM.car && timeToCar <= 0)
            {
                gp.soundM.playEngine();
                form = 2;
                speed = 10;
                solidArea.x = 17;
                solidArea.y = 10;
                solidArea.height = 44;
                solidArea.width = 30;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
                direction=lastDirection;
                timeToBot = 7;
            }
        }
        if(keyM.space && form == 1 && !projectile.alive) {
            shoot = 1;
            gp.soundM.playGun();
            projectile.set(worldX,worldY,lastDirection,true,this);
            gp.projectileList.add(projectile);
            //gp.soundM.stopGun();
        }
        else if(!keyM.space)
            shoot = 0;

        if(form == 2)       //car
        {
            if(keyM.up){
                dec=speed;
                if(!keyM.right && !keyM.left){
                    direction = "up";
                    lastDirection = direction;
                }
                if(keyM.right)
                {
                    direction = "upRight";
                    lastDirection = direction;
                }
                if(keyM.left)
                {
                    direction = "upLeft";
                    lastDirection = direction;
                }
                solidArea.x = 17;
                solidArea.y = 10;
                solidArea.height = 44;
                solidArea.width = 30;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
            }
            if(keyM.down){
                dec=speed;
                if(!keyM.right && !keyM.left) {
                    direction = "down";
                    lastDirection = direction;
                }
                if(keyM.right)
                {
                    direction = "downRight";
                    lastDirection = direction;
                }
                if(keyM.left)
                {
                    direction = "downLeft";
                    lastDirection = direction;
                }
                solidArea.x = 17;
                solidArea.y = 10;
                solidArea.height = 44;
                solidArea.width = 30;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
            }
            if(keyM.right){
                dec=speed;
                if(!keyM.up && !keyM.down) {
                    direction = "right";
                    lastDirection = direction;
                }
                solidArea.x = 10;
                solidArea.y = 17;
                solidArea.height = 30;
                solidArea.width = 44;

            }
            if(keyM.left){
                dec=speed;
                if(!keyM.up && !keyM.down) {
                    direction = "left";
                    lastDirection = direction;
                }
                solidArea.x = 10;
                solidArea.y = 17;
                solidArea.height = 30;
                solidArea.width = 44;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
            }
            if(!(keyM.left || keyM.right || keyM.up || keyM.down)) {
                direction = "idle";
            }

            //check tile collision
            collisionOn = false;
            speed = gp.cChecker.checkTile(this);
            objIndex = gp.cChecker.checkObject(this, true);
            enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            pickUpObject(objIndex);

            if(!collisionOn)
            {
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "upLeft": worldY -= speed/Math.sqrt(2); worldX -= speed/Math.sqrt(2); break;
                    case "upRight": worldX += speed/Math.sqrt(2); worldY -= speed/Math.sqrt(2); break;
                    case "down": worldY += speed; break;
                    case "downLeft": worldX -= speed/Math.sqrt(2); worldY += speed/Math.sqrt(2);break;
                    case "downRight":worldX += speed/Math.sqrt(2); worldY += speed/Math.sqrt(2); break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                    case "idle":
                        //System.out.println("CarIdle");
                        switch (lastDirection) {
                            case "up":
                                if((int)dec>0) {
                                    worldY -= (int)dec;
                                    dec-=dec*0.08;
                                }
                                break;
                            case "upLeft":
                                if((int)dec>0) {
                                    worldY -= dec/Math.sqrt(2);
                                    worldX -= dec/Math.sqrt(2);
                                    dec-=dec*0.08;
                                }
                                break;
                            case "upRight":
                                if((int)dec>0) {
                                    worldY -= dec/Math.sqrt(2);
                                    worldX += dec/Math.sqrt(2);
                                    dec-=dec*0.08;
                                }
                                break;
                            case "down":
                                if((int)dec>0) {
                                    worldY += (int)dec;
                                    dec-=dec*0.08;
                                }
                                break;
                            case "downLeft":
                                if((int)dec>0) {
                                    worldY += dec/Math.sqrt(2);
                                    worldX -= dec/Math.sqrt(2);
                                    dec-=dec*0.08;
                                }
                                break;
                            case "downRight":
                                if((int)dec>0) {
                                    worldY += dec/Math.sqrt(2);
                                    worldX += dec/Math.sqrt(2);
                                    dec-=dec*0.08;
                                }
                                break;
                            case "left":
                                if((int)dec>0) {
                                    worldX -= dec;
                                    dec-=dec*0.08;
                                }
                                break;
                            case "right":
                                if((int)dec>0) {
                                    worldX += dec;
                                    dec-=dec*0.08;
                                }
                                break;
                        }
                        break;
                }
            }
            if(keyM.robot || timeToBot <= 0) {
                gp.soundM.stopEngine();
                form = 1;
                speed = 3;
                solidArea.width = 32;
                solidArea.height = 40;
                solidArea.y = 14;
                solidArea.x = 16;
                solidAreaDefaultX = solidArea.x;
                solidAreaDefaultY = solidArea.y;
                timeToCar = 15;
            }
        }
    }

    public void update(){
        if(gp.lvl % 2 == 1)
            input();
        else{
            form = 2;
            direction = "up";
            lastDirection = direction;
            speed = 10;
            solidArea.x = 17;
            solidArea.y = 10;
            solidArea.height = 44;
            solidArea.width = 30;
            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;

            if(keyM.right) {
                direction = "upRight";
                lastDirection = direction;
            }
            if(keyM.left) {
                direction = "upLeft";
                lastDirection = direction;
            }
            if(keyM.down) {
                speed = 5;
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            enemyIndex = gp.cChecker.checkEntity(this, gp.enemies);
            interactEnemy(enemyIndex);
            if(!collisionOn) {
                if(Objects.equals(direction, "upLeft"))
                    worldX -= 4;
                if(Objects.equals(direction, "upRight"))
                    worldX += 4;
            }
            if(!invincible)
                worldY -= speed;
        }
        if(invincible){
            invincibleCounter ++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        //System.out.println(worldX + ", " + worldY);
    }

    public void pickUpObject(int i){
        if(i != -1){
            gp.soundM.playPickUp();
            String objectName = gp.obj.get(i).name;
            switch (objectName){
                case "lifeEnergon":
                    lives++;
                    gp.obj.set(i,null);
                    gp.ui.displayMessage("life");
                    break;
                case "damageEnergon":
                    damage++;
                    gp.obj.set(i,null);
                    gp.ui.displayMessage("Damage increased!");
                    break;
            }
        }
    }
    void interactEnemy(int i){
        if(i != -1){
            gp.soundM.playHit();
            if(!gp.enemies[i].invincible) {
                gp.enemies[i].lives--;
                gp.enemies[i].invincible = true;
            }
            if(gp.enemies[i].lives == 0) {
                gp.enemies[i].alive = false;
                gp.enemies[i] = null;
            }
            if(!invincible){
                lives --;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g){

        int x = screenX;
        int y = screenY;

        int rightOffset = Game.width - screenX;
        int downOffset = Game.height - screenY;
        if (screenX > worldX)
            x = worldX;
        if (screenY > worldY)
            y = worldY;
        if (rightOffset > gp.worldWidth - worldX)
            x = Game.width - (gp.worldWidth - worldX);
        if (downOffset > gp.worldHeight - worldY)
            y = Game.height - (gp.worldHeight - worldY);

        if (form == 1)
            Animation.playerAni(g, direction, lastDirection, shoot, x, y, spriteNum, invincible);
        if (form == 2)
            Animation.carAni(g, lastDirection, x, y, invincible, gp.lvl);
    /*
    g.setColor(Color.RED);
    int dfx = solidArea.x;
    int dfy = solidArea.y;
    solidArea.x += screenX;
    solidArea.y += screenY;
    g.draw(solidArea);
    solidArea.x = dfx;
    solidArea.y = dfy;
    */
    }
}
