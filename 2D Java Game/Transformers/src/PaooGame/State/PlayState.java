package PaooGame.State;

import PaooGame.Game;

import java.awt.*;

public class PlayState implements State{

    public void handle(Context context){
        context.setState(this);
    }

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException {

    }

    public String toString(){
        return "Play GameState";
    }

}
