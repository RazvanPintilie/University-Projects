package PaooGame.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class ImageLoader {
    public static BufferedImage LoadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch(IOException e) {
            /// Afiseaza informatiile necesare depanarii.
            System.out.println("Imaginea nu a putut fi incarcata");
            e.printStackTrace();
        }
        return null;
    }
}

