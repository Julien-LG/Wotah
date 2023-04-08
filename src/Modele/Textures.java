package Modele;

import Vue.CVue;
import Vue.VueGrille;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public enum Textures {

    texture_sea("sea_sprite.png"),
    texture_rock("rocks.png"),
    texture_sand("sand.png"), texture_grass("grass.png"),
    texture_water("clear_water.png"), texture_shipwreck("epave.png"),
    texture_tree("palms.png"), texture_tree_reversed(texture_tree), texture_bushs("bushs.png"), texture_bushs_reversed(texture_bushs),
    texture_perso("pion\\Diver_Adventurer_Icon_give@2x.png"),
    sprite_sheet("sheet.png");

    private BufferedImage texture;

    private Textures(String path) {
        this.texture = loadImage(path);
    }

    private Textures(Textures texture) {
        this.texture = reverseImage(texture.texture);
    }

    private final static int size = CVue.size;

    public Image getTexture() {
        //Convert bufferedImage to Image
        Image texture = Toolkit.getDefaultToolkit().createImage(this.texture.getSource());
        return texture.getScaledInstance(size, size, Image.SCALE_DEFAULT);
    }

    public Image getTextureNoScale() {
        //Convert bufferedImage to Image
        Image texture = Toolkit.getDefaultToolkit().createImage(this.texture.getSource());
        return texture;
    }

    static BufferedImage loadImage(String path) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(new File("res\\images\\" + path));
        } catch (IOException ie) {
            System.out.println("Erreur lors du chargement de l'image " + path);
            ie.printStackTrace();
            System.exit(1);
        } return image;
    }

    static BufferedImage reverseImage(BufferedImage image) {
        BufferedImage reversed = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        reversed.createGraphics().drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
        return reversed;
    }

    static ImageIcon loadIcon(String path) {
        return new ImageIcon(loadImage(path));
    }
}

