package PaooGame.Object;

import PaooGame.Graphics.Assets;

public class DamageEnergonObject extends ObjectManager{
    public DamageEnergonObject(int x, int y){
        super(Assets.damageEnergon);
        worldX = x;
        worldY = y;
        collision = true;
        name = "damageEnergon";
    }
}
