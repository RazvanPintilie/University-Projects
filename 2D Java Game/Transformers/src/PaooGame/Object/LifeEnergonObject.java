package PaooGame.Object;

import PaooGame.Graphics.Assets;

public class LifeEnergonObject extends ObjectManager{
    public LifeEnergonObject(int x, int y){
        super(Assets.lifeEnergon);
        worldX = x;
        worldY = y;
        collision = true;
        name = "lifeEnergon";
    }
}
