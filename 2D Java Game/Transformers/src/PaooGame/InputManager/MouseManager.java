package PaooGame.InputManager;

import PaooGame.Game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    Game gp;
    public boolean leftPressed, rightPressed;
    private int mouseX, mouseY;

    public MouseManager(Game gp){
        this.gp = gp;
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        if(code == MouseEvent.BUTTON1){
            leftPressed = true;
        }
        else if(code == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if(code == MouseEvent.BUTTON1){
            leftPressed = false;
        }
        else if(code == MouseEvent.BUTTON3){
            rightPressed = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
