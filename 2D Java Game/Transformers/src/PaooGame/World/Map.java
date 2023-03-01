package PaooGame.World;

import PaooGame.Game;
import PaooGame.Graphics.Tile;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Map {
    public int[][] mapTile;

    public Map(String path, int width, int height){
        mapTile = new int[width][height];
        LoadMap(path,width,height);
    }

    public void LoadMap(String path,int width,int height){

        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            for(int i = 0; i < width; ++i) {
                String line = sc.nextLine();
                for (int j = 0; j < height; ++j) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[j]);
                    mapTile[i][j] = num;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Exception occurred: " + e);
        }
    }

    public void Draw(Graphics2D g,int row,int col,int worldWidth,int worldHeight, Game gp){


        for (int i = 0; i < row; ++i)
            for (int j = 0; j < col; ++j)
            {
                int worldX = j * 64;
                int worldY = i * 64;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                int rightOffset = Game.width - gp.player.screenX;
                int downOffset = Game.height - gp.player.screenY;
                if(gp.player.screenX > gp.player.worldX)
                    screenX = worldX;
                if(gp.player.screenY > gp.player.worldY)
                    screenY = worldY;
                if(rightOffset > worldWidth - gp.player.worldX)
                     screenX = Game.width - (worldWidth - worldX);
                if(downOffset > worldHeight - gp.player.worldY)
                    screenY = Game.height - (worldHeight - worldY);

                if(worldX + 64 > gp.player.worldX - gp.player.screenX &&
                    worldX - 64 < gp.player.worldX + gp.player.screenX &&
                    worldY + 64 > gp.player.worldY - gp.player.screenY &&
                    worldY - 2*64 < gp.player.worldY + gp.player.screenY) {
                    Tile.tiles[mapTile[i][j]].Draw(g, screenX, screenY);
                }
                else if(gp.player.screenX > gp.player.worldX ||
                        gp.player.screenY > gp.player.worldY ||
                        rightOffset > worldWidth - gp.player.worldX ||
                        downOffset > worldHeight - gp.player.worldY) {
                    Tile.tiles[mapTile[i][j]].Draw(g, screenX, screenY);
                }
            }
        }
}




