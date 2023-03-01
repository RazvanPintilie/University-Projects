package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile
{
    public boolean collision;
    private static final int NR_TILES = 50;
    public static Tile[] tiles = new Tile[NR_TILES];       /*!< Vector de referinte de tipuri de dale.*/

    public static Tile sand1Tile = new Tile(19, Assets.sand1, false);
    public static Tile sand2Tile = new Tile(26, Assets.sand2, false);
    public static Tile waterTile = new Tile(12, Assets.water, true);
    public static Tile rockTile = new Tile(31, Assets.rock, true);
    public static Tile borderLeftTile = new Tile(21, Assets.borderLeft, false);
    public static Tile borderRightTile = new Tile(20, Assets.borderRight, false);
    public static Tile borderUpTile = new Tile(27, Assets.borderUp, false);
    public static Tile borderDownTile = new Tile(28, Assets.borderDown, false);
    public static Tile cornerLUTile = new Tile(6, Assets.cornerLU, false);
    public static Tile cornerLDTile = new Tile(13, Assets.cornerLD, false);
    public static Tile cornerRDTile = new Tile(14, Assets.cornerRD, false);
    public static Tile cornerRUTile = new Tile(7, Assets.cornerRU, false);
    public static Tile scornerLUTile = new Tile(36, Assets.scornerLU, false);
    public static Tile scornerLDTile = new Tile(43, Assets.scornerLD, false);
    public static Tile scornerRDTile = new Tile(44, Assets.scornerRD, false);
    public static Tile scornerRUTile = new Tile(37, Assets.scornerRU, false);
    public static Tile roadTile = new Tile(5, Assets.road, false);
    public static Tile upRoadLineTile = new Tile(1, Assets.upRoadLine, false);
    public static Tile downRoadLineTile = new Tile(8, Assets.downRoadLine, false);
    public static Tile leftRoadLineTile = new Tile(2, Assets.leftRoadLine, false);
    public static Tile rightRoadLineTile = new Tile(3, Assets.rightRoadLine, false);
    public static Tile rightZebraTile = new Tile(10, Assets.rightZebra, false);
    public static Tile leftZebraTile = new Tile(9, Assets.leftZebra, false);
    public static Tile upZebraTile = new Tile(4, Assets.upZebra, false);
    public static Tile downZebraTile = new Tile(11, Assets.downZebra, false);
    public static Tile redHouseLDTile = new Tile(24, Assets.redHouseLD, true);
    public static Tile redHouseLUTile = new Tile(17, Assets.redHouseLU, true);
    public static Tile redHouseRUTile = new Tile(18, Assets.redHouseRU, true);
    public static Tile redHouseRDTile = new Tile(25, Assets.redHouseRD, true);
    public static Tile greenHouseLDTile = new Tile(41, Assets.greenHouseLD, true);
    public static Tile greenHouseLUTile = new Tile(34, Assets.greenHouseLU, true);
    public static Tile greenHouseRUTile = new Tile(35, Assets.greenHouseRU, true);
    public static Tile greenHouseRDTile = new Tile(42, Assets.greenHouseRD, true);
    public static Tile greyHouseLDTile = new Tile(39, Assets.greyHouseLD, true);
    public static Tile greyHouseLUTile = new Tile(32, Assets.greyHouseLU, true);
    public static Tile greyHouseRUTile = new Tile(33, Assets.greyHouseRU, true);
    public static Tile greyHouseRDTile = new Tile(40, Assets.greyHouseRD, true);
    public static Tile treeRDTile = new Tile(23, Assets.treeRD, true);
    public static Tile treeRUTile = new Tile(16, Assets.treeRU, true);
    public static Tile treeLUTile = new Tile(15, Assets.treeLU, false);
    public static Tile treeLDTile = new Tile(22, Assets.treeLD, false);
    public static Tile bushTile = new Tile(30, Assets.bush, true);
    public static Tile cactusTile = new Tile(29, Assets.cactus, true);


    public static final int TILE_WIDTH  = 64;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 64;                       /*!< Inaltimea unei dale.*/

    public BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    public Tile(int id, BufferedImage img, boolean collision)
    {
        this.img = img;
        this.id = id;
        this.collision = collision;
        tiles[id-1] = this;
    }

    public void Draw(Graphics2D g, int x, int y) {
        /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
}
