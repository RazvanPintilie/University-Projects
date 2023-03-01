package PaooGame.Utils;

import PaooGame.Entity.Entity;
import PaooGame.Game;
import PaooGame.Graphics.Tile;

public class CollisionChecker {

    Game gp;

    public CollisionChecker(Game gp){
        this.gp = gp;
    }

    public int checkTile(Entity entity){
        int speedType = 7;  //on sand
        int roadSpeed = 10;

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityDownWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / 64;
        int entityRightCol = entityRightWorldX / 64;
        int entityTopRow = entityTopWorldY / 64;
        int entityDownRow = entityDownWorldY / 64;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gp.map.mapTile[entityTopRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "down":
                entityDownRow = (entityDownWorldY + entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityDownRow][entityLeftCol];
                tileNum2 = gp.map.mapTile[entityDownRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gp.map.mapTile[entityDownRow][entityLeftCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityRightCol];
                tileNum2 = gp.map.mapTile[entityDownRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "upLeft":
                entityTopRow = (entityTopWorldY - entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityLeftCol];
                entityLeftCol = (entityLeftWorldX - entity.speed) / 64;
                tileNum2 = gp.map.mapTile[entityTopRow][entityLeftCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "upRight":
                entityTopRow = (entityTopWorldY - entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityRightCol];
                entityRightCol = (entityRightWorldX + entity.speed) / 64;
                tileNum2 = gp.map.mapTile[entityTopRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "downLeft":
                entityDownRow = (entityDownWorldY + entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityDownRow][entityLeftCol];
                entityLeftCol = (entityLeftWorldX - entity.speed) / 64;
                tileNum2 = gp.map.mapTile[entityDownRow][entityLeftCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road
                break;
            case "downRight":
                entityDownRow = (entityDownWorldY + entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityDownRow][entityRightCol];
                entityRightCol = (entityLeftWorldX + entity.speed) / 64;
                tileNum2 = gp.map.mapTile[entityDownRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                else if ((tileNum1 == 4 || tileNum1 == 0 || tileNum1 == 7 || tileNum1 == 1 || tileNum1 == 2 || tileNum1 == 9 || tileNum1 == 3 || tileNum1 == 8 || tileNum1 == 10)
                        || (tileNum2 == 4 || tileNum2 == 0 || tileNum2 == 7 || tileNum2 == 1 || tileNum2 == 2 || tileNum2 == 9 || tileNum2 == 3 || tileNum2 == 8 || tileNum2 == 10))
                    speedType = roadSpeed;  // is on the road

                break;
            default:
                entityTopRow = (entityTopWorldY - entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gp.map.mapTile[entityTopRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                entityDownRow = (entityDownWorldY + entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityDownRow][entityLeftCol];
                tileNum2 = gp.map.mapTile[entityDownRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                entityRightCol = (entityRightWorldX + entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityRightCol];
                tileNum2 = gp.map.mapTile[entityDownRow][entityRightCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;
                entityLeftCol = (entityLeftWorldX - entity.speed) / 64;
                tileNum1 = gp.map.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gp.map.mapTile[entityDownRow][entityLeftCol];
                if (Tile.tiles[tileNum1].collision || Tile.tiles[tileNum2].collision)
                    entity.collisionOn = true;

        }
        return speedType;
    }

    public int checkObject(Entity entity, boolean player){
        int index = -1;

        for(int i = 0; i < gp.obj.size(); ++i)
        {
            if(gp.obj.get(i) !=null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                gp.obj.get(i).solidArea.x = gp.obj.get(i).worldX + gp.obj.get(i).solidArea.x;
                gp.obj.get(i).solidArea.y = gp.obj.get(i).worldY + gp.obj.get(i).solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    case "upLeft":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    case "upRight":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    case "downLeft":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    case "downRight":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                            if(gp.obj.get(i).collision)
                                entity.collisionOn = true;
                                if(player)
                                    index = i;
                        }
                        break;
                    default:
                        //pentru idle
                        switch (entity.lastDirection){
                            case "up":
                                entity.solidArea.y -= entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "down":
                                entity.solidArea.y += entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "left":
                                entity.solidArea.x -= entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "right":
                                entity.solidArea.x += entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "upLeft":
                                entity.solidArea.y -= entity.speed;
                                entity.solidArea.x -= entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "upRight":
                                entity.solidArea.y -= entity.speed;
                                entity.solidArea.x += entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "downLeft":
                                entity.solidArea.y += entity.speed;
                                entity.solidArea.x -= entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                            index = i;
                                }
                                break;
                            case "downRight":
                                entity.solidArea.y += entity.speed;
                                entity.solidArea.x += entity.speed;
                                if(entity.solidArea.intersects(gp.obj.get(i).solidArea)){
                                    if(gp.obj.get(i).collision)
                                        entity.collisionOn = true;
                                        if(player)
                                         index = i;
                                }
                                break;
                        }

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj.get(i).solidArea.x = gp.obj.get(i).solidAreaDefaultX;
                gp.obj.get(i).solidArea.y = gp.obj.get(i).solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target){
        int index = -1;
        for(int i = 0; i < target.length; ++i)
        {
            if(target[i] !=null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "upLeft":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "upRight":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "downLeft":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "downRight":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    default:
                        //pentru idle
                        switch (entity.lastDirection){
                            case "up":
                                entity.solidArea.y -= entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "down":
                                entity.solidArea.y += entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "left":
                                entity.solidArea.x -= entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "right":
                                entity.solidArea.x += entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "upLeft":
                                entity.solidArea.y -= entity.speed;
                                entity.solidArea.x -= entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "upRight":
                                entity.solidArea.y -= entity.speed;
                                entity.solidArea.x += entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "downLeft":
                                entity.solidArea.y += entity.speed;
                                entity.solidArea.x -= entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                            case "downRight":
                                entity.solidArea.y += entity.speed;
                                entity.solidArea.x += entity.speed;
                                if(entity.solidArea.intersects(target[i].solidArea)){
                                    entity.collisionOn = true;
                                    index = i;
                                }
                                break;
                        }

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        gp.player.solidArea.x =  gp.player.worldX +  gp.player.solidArea.x;
        gp.player.solidArea.y =  gp.player.worldY +  gp.player.solidArea.y;

        switch (entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "upLeft":
                entity.solidArea.y -= entity.speed;
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "upRight":
                entity.solidArea.y -= entity.speed;
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "downLeft":
                entity.solidArea.y += entity.speed;
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            case "downRight":
                entity.solidArea.y += entity.speed;
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects( gp.player.solidArea)){
                    entity.collisionOn = true;
                }
                break;
            default:
                //pentru idle
                switch (entity.lastDirection){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "upLeft":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "upRight":
                        entity.solidArea.y -= entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "downLeft":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                    case "downRight":
                        entity.solidArea.y += entity.speed;
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects( gp.player.solidArea)){
                            entity.collisionOn = true;
                        }
                        break;
                }
        }
         entity.solidArea.x = entity.solidAreaDefaultX;
         entity.solidArea.y = entity.solidAreaDefaultY;
         gp.player.solidArea.x =  gp.player.solidAreaDefaultX;
         gp.player.solidArea.y =  gp.player.solidAreaDefaultY;
         return entity.collisionOn;
    }
}