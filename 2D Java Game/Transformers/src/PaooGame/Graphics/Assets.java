package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage[] pIdle = new BufferedImage[3];
    public static BufferedImage pUpShoot;
    public static BufferedImage[] pUp = new BufferedImage[3];
    public static BufferedImage pLeftShoot;
    public static BufferedImage[] pLeft = new BufferedImage[3];
    public static BufferedImage pRightShoot;
    public static BufferedImage[] pRight = new BufferedImage[3];
    public static BufferedImage pDownShoot;
    public static BufferedImage[] pDown = new BufferedImage[3];

    public static BufferedImage car1;
    public static BufferedImage car2;
    public static BufferedImage car3;
    public static BufferedImage carUp;
    public static BufferedImage carDown;
    public static BufferedImage carLeft;
    public static BufferedImage carRight;
    public static BufferedImage carUpRight;
    public static BufferedImage carUpLeft;
    public static BufferedImage carDownLeft;
    public static BufferedImage carDownRight;

    public static BufferedImage[] eIdle = new BufferedImage[3];
    public static BufferedImage eUpShoot;
    public static BufferedImage[] eUp = new BufferedImage[3];
    public static BufferedImage eLeftShoot;
    public static BufferedImage[] eLeft = new BufferedImage[3];
    public static BufferedImage eRightShoot;
    public static BufferedImage[] eRight = new BufferedImage[3];
    public static BufferedImage eDownShoot;
    public static BufferedImage[] eDown = new BufferedImage[3];

    public static BufferedImage[] bIdle = new BufferedImage[3];
    public static BufferedImage bUpShoot;
    public static BufferedImage[] bUp = new BufferedImage[3];
    public static BufferedImage bLeftShoot;
    public static BufferedImage[] bLeft = new BufferedImage[3];
    public static BufferedImage bRightShoot;
    public static BufferedImage[] bRight = new BufferedImage[3];
    public static BufferedImage bDownShoot;
    public static BufferedImage[] bDown = new BufferedImage[3];

    public static BufferedImage sand1,sand2,water,rock;
    public static BufferedImage treeLD,treeLU,treeRU,treeRD, bush, cactus;
    public static BufferedImage cornerLU,cornerLD,cornerRU,cornerRD;
    public static BufferedImage scornerLU,scornerLD,scornerRU,scornerRD;
    public static BufferedImage borderUp,borderDown,borderLeft,borderRight;
    public static BufferedImage road,upRoadLine,downRoadLine,leftRoadLine,rightRoadLine;
    public static BufferedImage leftZebra,rightZebra,downZebra,upZebra;
    public static BufferedImage redHouseLD,redHouseLU,redHouseRU,redHouseRD;
    public static BufferedImage greyHouseLD,greyHouseLU,greyHouseRU,greyHouseRD;
    public static BufferedImage greenHouseLD,greenHouseLU,greenHouseRU,greenHouseRD;

    public static BufferedImage damageEnergon,lifeEnergon;
    public static BufferedImage lifeIcon, purpleLifeIcon, damageIcon;
    public static BufferedImage fireBall;
    public static BufferedImage blackFireBall;

    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/OptimusSpriteSheet.png"));
        SpriteSheet carSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/OptimusCarSpriteSheet.png"));
        SpriteSheet enemySheet = new SpriteSheet(ImageLoader.LoadImage("/textures/SmallDecepticonSpriteSheet.png"));
        SpriteSheet bossSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/BigDecepticonSpriteSheet.png"));
        SpriteSheet worldSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/WorldTiles.png"));
        SpriteSheet energonSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Energon.png"));
        SpriteSheet uiIconSheet = new SpriteSheet(ImageLoader.LoadImage("/ui/UI.png"));
        SpriteSheet fireBallSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Projectile.png"));
        SpriteSheet carsSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/CarsSpriteSheet.png"));


        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        pIdle[0] = playerSheet.crop(2,0, 64, 64);
        pIdle[1] = playerSheet.crop(3,0, 64, 64);
        pIdle[2] = playerSheet.crop(3,0, 64, 64);
        pRight[0] = playerSheet.crop(0, 1, 64, 64);
        pRight[1] = playerSheet.crop(1, 1, 64, 64);
        pRight[2] = playerSheet.crop(2, 1, 64, 64);
        pRightShoot = playerSheet.crop(3, 1, 64, 64);
        pLeft[0] = playerSheet.crop(0, 2, 64, 64);
        pLeft[1] = playerSheet.crop(1, 2, 64, 64);
        pLeft[2] = playerSheet.crop(2, 2, 64, 64);
        pLeftShoot = playerSheet.crop(3, 2, 64, 64);
        pUp[0] = playerSheet.crop(0,3, 64, 64);
        pUp[1] = playerSheet.crop(1,3, 64, 64);
        pUp[2] = playerSheet.crop(2,3, 64, 64);
        pUpShoot = playerSheet.crop(3,3, 64, 64);
        pDown[0] = playerSheet.crop(0,4, 64, 64);
        pDown[1] = playerSheet.crop(1,4, 64, 64);
        pDown[2] = playerSheet.crop(2,4, 64, 64);
        pDownShoot = playerSheet.crop(3,4, 64, 64);

        eIdle[0] = enemySheet.crop(2,0, 64, 64);
        eIdle[1] = enemySheet.crop(3,0, 64, 64);
        eIdle[2] = enemySheet.crop(3,0, 64, 64);
        eRight[0] = enemySheet.crop(0, 1, 64, 64);
        eRight[1] = enemySheet.crop(1, 1, 64, 64);
        eRight[2] = enemySheet.crop(2, 1, 64, 64);
        eRightShoot = enemySheet.crop(3, 1, 64, 64);
        eLeft[0] = enemySheet.crop(0, 2, 64, 64);
        eLeft[1] = enemySheet.crop(1, 2, 64, 64);
        eLeft[2] = enemySheet.crop(2, 2, 64, 64);
        eLeftShoot = enemySheet.crop(3, 2, 64, 64);
        eUp[0] = enemySheet.crop(0,3, 64, 64);
        eUp[1] = enemySheet.crop(1,3, 64, 64);
        eUp[2] = enemySheet.crop(2,3, 64, 64);
        eUpShoot = enemySheet.crop(3,3, 64, 64);
        eDown[0] = enemySheet.crop(0,4, 64, 64);
        eDown[1] = enemySheet.crop(1,4, 64, 64);
        eDown[2] = enemySheet.crop(2,4, 64, 64);
        eDownShoot = enemySheet.crop(3,4, 64, 64);

        bIdle[0] = bossSheet.crop(2,0, 128, 128);
        bIdle[1] = bossSheet.crop(3,0, 128, 128);
        bIdle[2] = bossSheet.crop(3,0, 128, 128);
        bRight[0] = bossSheet.crop(0, 1, 128, 128);
        bRight[1] = bossSheet.crop(1, 1, 128, 128);
        bRight[2] = bossSheet.crop(2, 1, 128, 128);
        bRightShoot = bossSheet.crop(3, 1, 128, 128);
        bLeft[0] = bossSheet.crop(0, 2, 128, 128);
        bLeft[1] = bossSheet.crop(1, 2, 128, 128);
        bLeft[2] = bossSheet.crop(2, 2, 128, 128);
        bLeftShoot = bossSheet.crop(3, 2, 128, 128);
        bUp[0] = bossSheet.crop(0,3, 128, 128);
        bUp[1] = bossSheet.crop(1,3, 128, 128);
        bUp[2] = bossSheet.crop(2,3, 128, 128);
        bUpShoot = bossSheet.crop(3,3, 128, 128);
        bDown[0] = bossSheet.crop(0,4, 128, 128);
        bDown[1] = bossSheet.crop(1,4, 128, 128);
        bDown[2] = bossSheet.crop(2,4, 128, 128);
        bDownShoot = bossSheet.crop(3,4, 128, 128);

        car1 = carsSpriteSheet.crop(0,0, 64, 64);
        car2 = carsSpriteSheet.crop(1,0, 64, 64);
        car3 = carsSpriteSheet.crop(0,1, 64, 64);
        carUp = carSheet.crop(0,0, 64, 64);
        carDown = carSheet.crop(1,0, 64, 64);
        carLeft = carSheet.crop(2,0, 64, 64);
        carRight = carSheet.crop(3,0, 64, 64);
        carUpRight = carSheet.crop(0,1, 64, 64);
        carUpLeft = carSheet.crop(1,1, 64, 64);
        carDownLeft = carSheet.crop(3,1, 64, 64);
        carDownRight = carSheet.crop(2,1, 64, 64);

        sand1 = worldSheet.crop(4,2, 64, 64);
        sand2 = worldSheet.crop(4,3, 64, 64);
        water = worldSheet.crop(4,1, 64, 64);
        rock = worldSheet.crop(2,4, 64, 64);

        borderLeft = worldSheet.crop(6,2, 64, 64);
        borderRight = worldSheet.crop(5,2, 64, 64);
        borderUp = worldSheet.crop(5,3, 64, 64);
        borderDown = worldSheet.crop(6,3, 64, 64);

        cornerLU = worldSheet.crop(5,0, 64, 64);
        cornerLD = worldSheet.crop(5,1, 64, 64);
        cornerRU = worldSheet.crop(6,0, 64, 64);
        cornerRD = worldSheet.crop(6,1, 64, 64);
        scornerLU = worldSheet.crop(0,5, 64, 64);
        scornerLD = worldSheet.crop(0,6, 64, 64);
        scornerRU = worldSheet.crop(1,5, 64, 64);
        scornerRD = worldSheet.crop(1,6, 64, 64);

        road = worldSheet.crop(4,0, 64, 64);
        upRoadLine = worldSheet.crop(0,0, 64, 64);
        downRoadLine = worldSheet.crop(0,1, 64, 64);
        leftRoadLine = worldSheet.crop(1,0, 64, 64);
        rightRoadLine = worldSheet.crop(2,0, 64, 64);
        leftZebra = worldSheet.crop(1,1, 64, 64);
        rightZebra = worldSheet.crop(2,1, 64, 64);
        upZebra = worldSheet.crop(3,0, 64, 64);
        downZebra = worldSheet.crop(3,1, 64, 64);

        redHouseLD = worldSheet.crop(2,3, 64, 64);
        redHouseLU = worldSheet.crop(2,2, 64, 64);
        redHouseRU = worldSheet.crop(3,2, 64, 64);
        redHouseRD = worldSheet.crop(3,3, 64, 64);

        greyHouseLD = worldSheet.crop(3,5, 64, 64);
        greyHouseLU = worldSheet.crop(3,4, 64, 64);
        greyHouseRU = worldSheet.crop(4,4, 64, 64);
        greyHouseRD = worldSheet.crop(4,5, 64, 64);

        greenHouseLD = worldSheet.crop(5,5, 64, 64);
        greenHouseLU = worldSheet.crop(5,4, 64, 64);
        greenHouseRU = worldSheet.crop(6,4, 64, 64);
        greenHouseRD = worldSheet.crop(6,5, 64, 64);

        treeLD = worldSheet.crop(0,3, 64, 64);
        treeLU = worldSheet.crop(0,2, 64, 64);
        treeRU = worldSheet.crop(1,2, 64, 64);
        treeRD = worldSheet.crop(1,3, 64, 64);
        bush = worldSheet.crop(1,4, 64, 64);
        cactus = worldSheet.crop(0,4, 64, 64);

        damageEnergon = energonSheet.crop(1,0, 64, 64);
        lifeEnergon = energonSheet.crop(0,0, 64, 64);
        lifeIcon = uiIconSheet.crop(0,0, 64, 64);
        purpleLifeIcon = uiIconSheet.crop(2,0, 64, 64);
        damageIcon = uiIconSheet.crop(1,0, 64, 64);
        fireBall = fireBallSheet.crop(0,0, 64, 64);
        blackFireBall = fireBallSheet.crop(1,0, 64, 64);
    }
}
