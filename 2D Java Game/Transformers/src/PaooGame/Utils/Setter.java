package PaooGame.Utils;

import PaooGame.Entity.CarEnemy;
import PaooGame.Entity.Enemy;
import PaooGame.Entity.Player;
import PaooGame.Game;
import PaooGame.Object.DamageEnergonObject;
import PaooGame.Object.LifeEnergonObject;
import PaooGame.World.Map;

public class Setter {
    Game gp;

    public Setter(Game gp){
        this.gp = gp;
    }

    public void setObjectLvl1(){

        gp.obj.removeAll(gp.obj);
        gp.projectileList.removeAll(gp.projectileList);
        gp.obj.add(new LifeEnergonObject(5028,131));
        gp.obj.add(new LifeEnergonObject(140,2815));
        gp.obj.add(new DamageEnergonObject(120,120));
        
    }

    public void setObjectLvl2(){
        gp.obj.removeAll(gp.obj);
        gp.projectileList.removeAll(gp.projectileList);
    }

    public void setObjectLvl3(){

        gp.obj.removeAll(gp.obj);
        gp.projectileList.removeAll(gp.projectileList);
        gp.obj.add(new LifeEnergonObject(508,330));
        gp.obj.add(new DamageEnergonObject(2230,767));
        gp.obj.add(new LifeEnergonObject(2834,3361));
        gp.obj.add(new DamageEnergonObject(4612,272));

    }

    public void loadLvl1(int playerX, int playerY, int damage, int lives, int rERight){
        gp.soundM.playEngine();
        gp.soundM.stopEngine();

        gp.worldWidth = gp.lvl1Width;
        gp.worldHeight = gp.lvl1Height;
        gp.maxWorldCol = gp.maxLvl1Col;
        gp.maxWorldRow = gp.maxLvl1Row;

        gp.map = null;
        gp.map = new Map("res/maps/lvl1.txt",gp.maxLvl1Row,gp.maxLvl1Col);

        Player.Reset();
        gp.lvl = 1;
        gp.winning = -1;
        gp.lvlUpTime = 0;
        gp.player = Player.getInstance(gp, gp.wnd);
        gp.player.setPosition(playerX, playerY);;
        gp.player.setData(lives, damage);

        gp.obj.removeAll(gp.obj);
        gp.projectileList.removeAll(gp.projectileList);
        for(int i = 0; i < gp.enemies.length; ++i)
            if(gp.enemies[i] != null)
                gp.enemies[i] = null;

        if(rERight == 1) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 5093;
            gp.enemies[0].worldY = 2400;
        }

        if(rERight == 2) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 5093;
            gp.enemies[0].worldY = 2400;

            gp.enemies[1] = new Enemy(gp);
            gp.enemies[1].worldX = 4939;
            gp.enemies[1].worldY = 2782;
        }

        if(rERight == 3) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 5093;
            gp.enemies[0].worldY = 2400;

            gp.enemies[1] = new Enemy(gp);
            gp.enemies[1].worldX = 4939;
            gp.enemies[1].worldY = 2782;

            gp.enemies[2] = new Enemy(gp);
            gp.enemies[2].worldX = 4474;
            gp.enemies[2].worldY = 2899;
        }

        if(rERight == 4) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 5093;
            gp.enemies[0].worldY = 2400;

            gp.enemies[1] = new Enemy(gp);
            gp.enemies[1].worldX = 4939;
            gp.enemies[1].worldY = 2782;

            gp.enemies[2] = new Enemy(gp);
            gp.enemies[2].worldX = 4474;
            gp.enemies[2].worldY = 2899;

            gp.enemies[3] = new Enemy(gp);
            gp.enemies[3].worldX = 4152;
            gp.enemies[3].worldY = 2181;
        }

        if(rERight == 5) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 5093;
            gp.enemies[0].worldY = 2400;

            gp.enemies[1] = new Enemy(gp);
            gp.enemies[1].worldX = 4939;
            gp.enemies[1].worldY = 2782;

            gp.enemies[2] = new Enemy(gp);
            gp.enemies[2].worldX = 4474;
            gp.enemies[2].worldY = 2899;

            gp.enemies[3] = new Enemy(gp);
            gp.enemies[3].worldX = 4152;
            gp.enemies[3].worldY = 2181;

            gp.enemies[4] = new Enemy(gp);
            gp.enemies[4].worldX = 3840;
            gp.enemies[4].worldY = 2910;
        }


        gp.playState.handle(gp.context);

    }

    public void loadLvl2(int playerX, int playerY, int damage, int lives){
        gp.soundM.playEngine();
        gp.soundM.stopEngine();

        gp.worldWidth = gp.lvl2Width;
        gp.worldHeight = gp.lvl2Height;
        gp.maxWorldCol = gp.maxLvl2Col;
        gp.maxWorldRow = gp.maxLvl2Row;

        gp.map = null;
        gp.map = new Map("res/maps/lvl2.txt",gp.maxLvl2Row,gp.maxLvl2Col);

        Player.Reset();
        gp.lvl = 2;
        gp.winning = -1;
        gp.lvlUpTime = 0;
        gp.player = Player.getInstance(gp, gp.wnd);
        gp.player.setPosition(playerX, playerY);;
        gp.player.screenY = 650;
        gp.player.setData(lives, damage);

        gp.obj.removeAll(gp.obj);
        gp.projectileList.removeAll(gp.projectileList);
        setEnemyLvl2();
        gp.soundM.playEngine();

        gp.playState.handle(gp.context);
    }

    public void loadLvl3(int playerX, int playerY, int damage, int lives, int rELeft, int rERight) {

        gp.soundM.playEngine();
        gp.soundM.stopEngine();

        gp.worldWidth = gp.lvl3Width;
        gp.worldHeight = gp.lvl3Height;
        gp.maxWorldCol = gp.maxLvl3Col;
        gp.maxWorldRow = gp.maxLvl3Row;

        gp.map = null;
        gp.map = new Map("res/maps/lvl3.txt",gp.maxLvl3Row,gp.maxLvl3Col);

        Player.Reset();
        gp.lvl = 3;
        gp.winning = -1;
        gp.lvlUpTime = 0;
        gp.player = Player.getInstance(gp, gp.wnd);
        gp.player.setPosition(playerX, playerY);;
        gp.player.setData(lives, damage);

        gp.obj.removeAll(gp.obj);
        gp.projectileList.removeAll(gp.projectileList);
        for(int i = 0; i < gp.enemies.length; ++i)
            if(gp.enemies[i] != null)
                gp.enemies[i] = null;

        if(rERight == 1) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 7013;
            gp.enemies[0].worldY = 266;
        }

        if(rERight == 2) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 7013;
            gp.enemies[0].worldY = 266;

            gp.enemies[1] = new Enemy(gp);
            gp.enemies[1].worldX = 7493;
            gp.enemies[1].worldY = 869;
        }

        if(rERight == 3) {
            gp.enemies[0] = new Enemy(gp);
            gp.enemies[0].worldX = 7013;
            gp.enemies[0].worldY = 266;

            gp.enemies[1] = new Enemy(gp);
            gp.enemies[1].worldX = 7493;
            gp.enemies[1].worldY = 869;

            gp.enemies[5] = new Enemy(gp);
            gp.enemies[5].worldX = 7526;
            gp.enemies[5].worldY = 1688;
        }

        if(rELeft == 1) {
            gp.enemies[2] = new Enemy(gp);
            gp.enemies[2].worldX = 2507;
            gp.enemies[2].worldY = 3303;
        }

        if(rELeft == 2) {
            gp.enemies[2] = new Enemy(gp);
            gp.enemies[2].worldX = 2507;
            gp.enemies[2].worldY = 3303;

            gp.enemies[3] = new Enemy(gp);
            gp.enemies[3].worldX = 309;
            gp.enemies[3].worldY = 3608;
        }
        if(rELeft == 3) {
            gp.enemies[2] = new Enemy(gp);
            gp.enemies[2].worldX = 2507;
            gp.enemies[2].worldY = 3303;

            gp.enemies[3] = new Enemy(gp);
            gp.enemies[3].worldX = 309;
            gp.enemies[3].worldY = 3608;

            gp.enemies[4] = new Enemy(gp);
            gp.enemies[4].worldX = 276;
            gp.enemies[4].worldY = 2573;
        }
        for(int i = 0; i < gp.enemies.length; ++i){
            if(gp.enemies[i] != null) {
                gp.enemies[i].damage = 2;
                gp.enemies[i].lives = 5;
            }
        }
        gp.playState.handle(gp.context);
    }

    public void setEnemyLvl1(){

        for(int i = 0; i < gp.enemies.length; ++i)
            if(gp.enemies[i] != null)
                gp.enemies[i] = null;

        gp.enemies[0] = new Enemy(gp);
        gp.enemies[0].worldX = 5093;
        gp.enemies[0].worldY = 2400;

        gp.enemies[1] = new Enemy(gp);
        gp.enemies[1].worldX = 4939;
        gp.enemies[1].worldY = 2782;

        gp.enemies[2] = new Enemy(gp);
        gp.enemies[2].worldX = 4474;
        gp.enemies[2].worldY = 2899;

        gp.enemies[3] = new Enemy(gp);
        gp.enemies[3].worldX = 4152;
        gp.enemies[3].worldY = 2181;

        gp.enemies[4] = new Enemy(gp);
        gp.enemies[4].worldX = 3840;
        gp.enemies[4].worldY = 2910;

    }

    public void setEnemyLvl2() {

        for(int i = 0; i < gp.enemies.length; ++i)
            if(gp.enemies[i] != null)
                gp.enemies[i] = null;

        gp.enemies[0] = new CarEnemy(gp);
        gp.enemies[0].worldX = 600;
        gp.enemies[0].worldY = 19000;

        gp.enemies[1] = new CarEnemy(gp);
        gp.enemies[1].worldX = 710;
        gp.enemies[1].worldY = 18500;

        gp.enemies[2] = new CarEnemy(gp);
        gp.enemies[2].worldX = 550;
        gp.enemies[2].worldY = 18000;

        gp.enemies[3] = new CarEnemy(gp);
        gp.enemies[3].worldX = 710;
        gp.enemies[3].worldY = 17500;

        gp.enemies[4] = new CarEnemy(gp);
        gp.enemies[4].worldX = 690;
        gp.enemies[4].worldY = 17000;

        gp.enemies[5] = new CarEnemy(gp);
        gp.enemies[5].worldX = 510;
        gp.enemies[5].worldY = 16500;

        gp.enemies[6] = new CarEnemy(gp);
        gp.enemies[6].worldX = 700;
        gp.enemies[6].worldY = 15000;

        gp.enemies[7] = new CarEnemy(gp);
        gp.enemies[7].worldX = 650;
        gp.enemies[7].worldY = 14500;

        gp.enemies[8] = new CarEnemy(gp);
        gp.enemies[8].worldX = 750;
        gp.enemies[8].worldY = 13000;

        gp.enemies[9] = new CarEnemy(gp);
        gp.enemies[9].worldX = 510;
        gp.enemies[9].worldY = 12500;

        gp.enemies[10] = new CarEnemy(gp);
        gp.enemies[10].worldX = 510;
        gp.enemies[10].worldY = 11500;

        gp.enemies[11] = new CarEnemy(gp);
        gp.enemies[11].worldX = 510;
        gp.enemies[11].worldY = 10000;

        gp.enemies[12] = new CarEnemy(gp);
        gp.enemies[12].worldX = 710;
        gp.enemies[12].worldY = 9500;

        gp.enemies[13] = new CarEnemy(gp);
        gp.enemies[13].worldX = 610;
        gp.enemies[13].worldY = 9000;

        gp.enemies[14] = new CarEnemy(gp);
        gp.enemies[14].worldX = 510;
        gp.enemies[14].worldY = 8500;

        gp.enemies[15] = new CarEnemy(gp);
        gp.enemies[15].worldX = 710;
        gp.enemies[15].worldY = 8000;

        gp.enemies[16] = new CarEnemy(gp);
        gp.enemies[16].worldX = 510;
        gp.enemies[16].worldY = 7500;

        gp.enemies[17] = new CarEnemy(gp);
        gp.enemies[17].worldX = 710;
        gp.enemies[17].worldY = 6500;

    }

    public void setEnemyLvl3() {

        for(int i = 0; i < gp.enemies.length; ++i)
            if(gp.enemies[i] != null)
                gp.enemies[i] = null;

        gp.enemies[0] = new Enemy(gp);
        gp.enemies[0].worldX = 7013;
        gp.enemies[0].worldY = 266;

        gp.enemies[1] = new Enemy(gp);
        gp.enemies[1].worldX = 7493;
        gp.enemies[1].worldY = 869;

        gp.enemies[5] = new Enemy(gp);
        gp.enemies[5].worldX = 7526;
        gp.enemies[5].worldY = 1688;

        gp.enemies[2] = new Enemy(gp);
        gp.enemies[2].worldX = 2507;
        gp.enemies[2].worldY = 3303;

        gp.enemies[3] = new Enemy(gp);
        gp.enemies[3].worldX = 309;
        gp.enemies[3].worldY = 3608;

        gp.enemies[4] = new Enemy(gp);
        gp.enemies[4].worldX = 276;
        gp.enemies[4].worldY = 2573;

        for(int i = 0; i < gp.enemies.length; ++i){
            if(gp.enemies[i] != null) {
                gp.enemies[i].damage = 2;
                gp.enemies[i].lives = 5;
            }
        }

    }

}
