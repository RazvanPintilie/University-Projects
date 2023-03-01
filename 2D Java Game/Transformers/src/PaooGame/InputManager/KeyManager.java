package PaooGame.InputManager;

import PaooGame.Game;
import java.awt.event.*;

public class KeyManager implements KeyListener {

    Game gp;

    public boolean up, down, left, right;
    public boolean space;
    public boolean robot, car;
    public boolean esc;

    public static String buffer = "";

    public KeyManager(Game gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        char keyChar = e.getKeyChar();

        if(gp.context.getState() == gp.playState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                up = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                down = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                space = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.context.setState(gp.pauseState);
            }
            if (code == KeyEvent.VK_R) {
                robot = true;
            }
            if (code == KeyEvent.VK_C) {
                car = true;
            }
        }
        else if(gp.context.getState() == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.context.setState(gp.playState);
            }
        }
        else if(gp.context.getState() == gp.insertNameState) {
            if((keyChar >= 'A' && keyChar <= 'Z') || (keyChar >= 'a' && keyChar <= 'z')) {
                if(buffer.length() < 10)
                    buffer += (Character.toString(keyChar));
            }
            if(keyChar == KeyEvent.VK_BACK_SPACE && buffer.length() > 0)
                buffer = buffer.substring(0, buffer.length() -1);
            //System.out.println(buffer);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code=e.getKeyCode();

        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
            up = false;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
            down = false;
        }
        if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
            left = false;
        }
        if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
            right = false;
        }
        if(code==KeyEvent.VK_SPACE) {
            space = false;
        }
        if(code==KeyEvent.VK_R) {
            robot = false;
        }
        if(code==KeyEvent.VK_C) {
            car = false;
        }
    }
}


