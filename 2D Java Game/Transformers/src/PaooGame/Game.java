package PaooGame;

import PaooGame.Entity.Entity;
import PaooGame.Entity.Player;
import PaooGame.Graphics.Assets;
import PaooGame.InputManager.KeyManager;
import PaooGame.InputManager.MouseManager;
import PaooGame.Object.ObjectManager;
import PaooGame.Utils.*;
import PaooGame.World.Map;
import PaooGame.State.*;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class Game extends JPanel implements Runnable{
    public static int width;
    public static int height;

    public double playTime = 0;
    public int music = 0;
    public int sound = 0;

    private Thread gameThread;
    public final Window wnd;
    private boolean running;
    private BufferStrategy bs;
    private KeyManager keyM = new KeyManager(this);
    public MouseManager mouseM = new MouseManager(this);
    public SoundManager soundM = new SoundManager(this);

    public int lvl;

    public int lvl1WorldX = 2920;
    public int lvl1WorldY = 1750;
    public int lvl2WorldX = 1280 /2 + 32;
    //public int lvl2WorldY = 210;
    public int lvl2WorldY = 19000;
    public int lvl3WorldX = 6761;
    public int lvl3WorldY = 3176;

    public final int maxLvl1Col = 82;
    public final int maxLvl1Row = 48;
    public final int maxLvl2Col = 20;
    public final int maxLvl2Row = 300;
    public final int maxLvl3Col = 120;
    public final int maxLvl3Row = 60;

    public final int lvl1Width = maxLvl1Col * 64;
    public final int lvl1Height = maxLvl1Row * 64;
    public final int lvl2Width = maxLvl2Col * 64;
    public final int lvl2Height = maxLvl2Row * 64;
    public final int lvl3Width = maxLvl3Col * 64;
    public final int lvl3Height = maxLvl3Row * 64;

    public int worldWidth;
    public int worldHeight;
    public int  maxWorldCol;
    public int  maxWorldRow;

    public Map map;

    public Player player;
    public String name;
    public CollisionChecker cChecker;
    public Setter setter = new Setter(this);
    public UI ui;
    public ArrayList<ObjectManager> obj = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public Entity[] enemies = new Entity[18];

    public State lastState;
    public Context context = new Context();
    public TitleState titleState = new TitleState();
    public SettingsState settingsState = new SettingsState();
    public InfoState infoState = new InfoState();
    public LoadState loadState = new LoadState();
    public InsertNameState insertNameState = new InsertNameState();
    public PlayState playState = new PlayState();
    public PauseState pauseState = new PauseState();
    public LvlUpState lvlUpState = new LvlUpState();
    public LoseState loseState = new LoseState();
    public WinState winState = new WinState();
    public ScoresState scoresState = new ScoresState();

    public int lvlUpTime;
    public int winning;

    public Graphics2D g;

    public Game(String title, int width,int height){
        Game.width = width;
        Game.height = height;
        running = false;
        wnd = new Window(title, width, height,keyM,mouseM);
        wnd.BuildGameWindow();
        this.setDoubleBuffered(true);
    }

    public void setupGame() {
        name = "";
        playTime = 0;
        music = 0;
        sound = 0;
        if(!settingsState.ok) {
            settingsState.musicButton.setText("Music On");
            settingsState.soundButton.setText("Sound On");
        }
        titleState.handle(context);
        soundM.playMusic();
    }

    public void setupLvl1(){
        map = null;
        map = new Map("res/maps/lvl1.txt", maxLvl1Row, maxLvl1Col);

        setter.setObjectLvl1();
        setter.setEnemyLvl1();

        worldWidth = lvl1Width;
        worldHeight = lvl1Height;
        maxWorldCol = maxLvl1Col;
        maxWorldRow = maxLvl1Row;

        Player.Reset();
        lvl = 1;
        winning = -1;
        lvlUpTime = 0;
        player = Player.getInstance(this,wnd);
        player.setPosition(lvl1WorldX,lvl1WorldY);
    }

    public void setupLvl2(){
        map = null;
        map = new Map("res/maps/lvl2.txt", maxLvl2Row, maxLvl2Col);
        worldWidth = lvl2Width;
        worldHeight = lvl2Height;
        maxWorldCol = maxLvl2Col;
        maxWorldRow = maxLvl2Row;

        setter.setObjectLvl2();
        setter.setEnemyLvl2();

        Player.Reset();
        player = Player.getInstance(this,wnd);
        player.setPosition(lvl2WorldX,lvl2WorldY);
        player.screenY = 650;
    }

    public void setupLvl3(){
        map = null;
        map = new Map("res/maps/lvl3.txt",maxLvl3Row,maxLvl3Col);
        worldWidth = lvl3Width;
        worldHeight = lvl3Height;
        maxWorldCol = maxLvl3Col;
        maxWorldRow = maxLvl3Row;

        setter.setObjectLvl3();
        setter.setEnemyLvl3();

        Player.Reset();
        player = Player.getInstance(this,wnd);
        player.setPosition(lvl3WorldX,lvl3WorldY);
        player.screenY = 768/2-50;
    }

    public void InitGame(){
        cChecker = new CollisionChecker(this);
        Assets.Init();
        setupGame();
        try {
            ui = new UI(this);
        }catch (InterruptedException e){
            System.out.println("Exception occurred: " + e);
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int showFps;

    @Override
    public void run(){
        InitGame();
        long oldTime = System.nanoTime();
        long currentTime;
        double delta = 0;
        int frameCount = 0;
        long timer = 0;

        final int FPS = 60;
        final double timeFrame = 1000000000. / FPS;

        while (running)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - oldTime)/timeFrame;
            timer += (currentTime - oldTime);
            oldTime=currentTime;
            if(delta >= 1)
            {
                Update();
                try {
                    Draw();
                } catch (InterruptedException e) {
                    System.out.println("Exception occurred: " + e);
                }
                delta--;
                frameCount++;
            }

            //numaram fps-urile
            if(timer >= 1000000000)
            {
                showFps = frameCount;
                timer = 0;
                frameCount = 0;
            }
        }
    }

    public synchronized void StartGame() {
        if(!running){
            running = true;
            startGameThread();
        }
        else {
            System.out.println("Thread pornit");
            return;
        }
    }

    public synchronized void StopGame() {
        if(running) {
            running = false;
            try {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
                wnd.CloseWnd();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Thread oprit");
            return;
        }
    }

    private void Update(){
        if(context.getState() == playState) {
            player.update();
            boolean empty = true;
            for(int i = 0; i < enemies.length; ++i) {
                if (enemies[i] != null) {
                    empty = false;
                    enemies[i].update();
                }
            }
            if(!empty){
                for(int i = 0; i < projectileList.size(); ++i)
                    if(projectileList.get(i) != null)
                        projectileList.get(i).update();
                if(player.lives <= 0) {
                    player.alive = false;
                    soundM.playEngine();
                    soundM.stopEngine();
                    soundM.playLose();
                    context.setState(loseState);
                }
            }
            else{
                if(lvl == 3){
                    if(winning == -1)
                    winning = 120;
                }
                else{
                    if(lvl % 2 == 1) {
                        context.setState(lvlUpState);
                        soundM.playLvlUp();
                    }
                }
            }
            if(lvl % 2 == 0 && player.worldY <= 200) {
                context.setState(lvlUpState);
                soundM.playLvlUp();
                soundM.stopEngine();
            }
            if(winning > 0) {
                winning--;
            }
            if(winning == 1){
                AccessDataBase.insertScore(name, playTime);
            }
            if(winning == 0) {
                soundM.playWin();
                context.setState(winState);
            }
        }
        if(context.getState() == lvlUpState){
            if(lvlUpTime <= 150)
                lvlUpTime++;
            else
                lvlUpTime = 0;
        }
    }

    private void Draw() throws InterruptedException{
        bs = wnd.GetCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        /// operatie de desenare


        //Map

        if (context.getState() == playState || context.getState() == pauseState || context.getState() == lvlUpState)
            map.Draw(g, maxWorldRow, maxWorldCol, worldWidth, worldHeight, this);

        //Objects
        for(int i=0; i<obj.size(); ++i)
            if(obj.get(i)!=null)
                obj.get(i).draw(g,this);

        //Player
        if (context.getState() == playState || context.getState() == pauseState || context.getState() == lvlUpState)
            player.draw(g);

        //Enemies
        for (int i = 0; i < enemies.length; ++i)
            if (enemies[i] != null)
                enemies[i].draw(g);

        //Projectiles

        for (int i = 0; i < projectileList.size(); ++i) {
            if (projectileList.get(i) != null)
                projectileList.get(i).draw(g);
            if (!projectileList.get(i).alive)
                projectileList.remove(i);
        }

        //Fps
        ui.draw(g);


        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();
        g.dispose();
    }


}

