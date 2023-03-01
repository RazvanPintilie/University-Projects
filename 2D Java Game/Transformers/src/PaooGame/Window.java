package PaooGame;

import PaooGame.InputManager.KeyManager;
import PaooGame.InputManager.MouseManager;

import javax.swing.*;
import java.awt.*;

public class Window
{
    JFrame wndFrame;
    private final String wndTitle;
    private final int wndWidth;
    private final int wndHeight;
    public KeyManager keyM;
    public MouseManager mouseM;

    private Canvas canvas;         /*!< "panza/tablou" in care se poate desena*/

    public Window(String title, int width, int height,KeyManager keyM, MouseManager mouseM) {
        wndTitle = title;
        wndWidth = width;
        wndHeight = height;
        wndFrame = null;
        this.keyM = keyM;
        this.mouseM = mouseM;
    }

    public void BuildGameWindow() {
        /// Daca fereastra a mai fost construita intr-un apel anterior
        /// se renunta la apel
        if(wndFrame != null) {
            return;
        }

        wndFrame = new JFrame(wndTitle);
        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(true);
        wndFrame.setLocation(200,20);
        wndFrame.setVisible(true);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        canvas.setBackground(Color.black);

        canvas.addKeyListener(keyM);
        canvas.addMouseListener(mouseM);
        canvas.addMouseMotionListener(mouseM);
        canvas.setFocusable(true);

        try {
            Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ui/TransformersIcon.png"));
            wndFrame.setIconImage(img);
        }catch (Exception e){
            System.out.println("Can't load Window Icon; image not found!");
        }
        wndFrame.add(canvas);
        wndFrame.pack();

    }

    public void CloseWnd(){
        wndFrame.dispose();
    }

    public int GetWndWidth() {
        return wndWidth;
    }

    public int GetWndHeight() {
        return wndHeight;
    }

    public Canvas GetCanvas() {
        return canvas;
    }
}
