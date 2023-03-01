package PaooGame.Utils;

import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonManager {

    Game gp;

    private int x;
    private int y;
    private final int width;
    private final int height;

    public boolean enabled;
    public boolean pressed;

    private String text;
    private final BufferedImage icon1;
    private final BufferedImage icon2;

    Font arial = new Font("Arial",Font.BOLD,50);


    public ButtonManager(Game gp, int x, int y, int width, int height, BufferedImage icon1, BufferedImage icon2, String text){
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon1 = icon1;
        this.icon2 = icon2;
        this.text = text;
        enabled = true;
    }

    private boolean isOver(int x,int y){
        return x >= this.x && x <= this.x + width
                && y >= this.y && y <= this.y + height;
    }

    public boolean mouseOver(Game gp){
        return isOver(gp.mouseM.getMouseX(), gp.mouseM.getMouseY());
    }

    public void checkPressed(Game gp){
        if(mouseOver(gp) && gp.mouseM.leftPressed){
            pressed = true;
        }
    }

    public void checkReleased(Game gp) {
        if(pressed && enabled){
            if(!gp.mouseM.leftPressed)
                pressed = false;
        }
    }

    public void draw(Graphics2D g){
        g.setFont(arial);
        int length = (int)g.getFontMetrics().getStringBounds(text,g).getWidth();
        if(mouseOver(gp)){
            g.drawImage(icon2,x + 2,y - 2, width, height,null);
            g.setColor(new Color(150,200,255));
            g.drawString(text, x + (width - length)/2 + 3, y + 85);
            g.setColor(Color.BLUE);
            g.drawString(text, x + (width - length)/2, y + 82);
        }
        else{
            g.drawImage(icon1, x ,y , width, height,null);
            g.setColor(new Color(150,200,255));
            g.drawString(text, x + (width - length)/2 + 1, y + 87);
            g.setColor(new Color(20,50,200));
            g.drawString(text, x + (width - length)/2 - 2, y + 84);
        }
    }

    public void setText(String text){
        this.text = text;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
