import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

enum SpritesAnimated {

    sprite_shark("shark.gif");

    private ImageIcon sprite;

    private SpritesAnimated(String path) {
        this.sprite = loadImage(path);
    }

    public ImageIcon getSprite() {
        //Convert bufferedImage to Image
        //int size = VueGrille.SCREEN_SIZE/Modele.getHeight();
        //return texture.getScaledInstance(size, size, Image.SCALE_DEFAULT);
        /*int size = VueGrille.SCREEN_SIZE/Modele.getHeight();
        return sprite.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);*/
        return sprite;
    }

    static ImageIcon loadImage(String path) {
        //ImageIcon image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        //ImageIcon image = new ImageIcon();
        ImageIcon image = image = new ImageIcon("res\\images\\" + path);
        /*try {
            image = new ImageIcon("res\\images\\" + path);
        } catch (IOException ie) {
            System.out.println("Erreur lors du chargement de l'image " + path);
            ie.printStackTrace();
            System.exit(1);
        }*/
        return image;
    }
}