package PaooGame.State;

import PaooGame.Game;

import java.awt.*;

public interface State {

    void handle(Context context);

    public void drawStateScreen(Game gp, Graphics2D g) throws InterruptedException;

}
