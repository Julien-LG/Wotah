import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Personnage {
    /**
     * Attributs du personnage
     */
    private final int numero;
    private int x, y;
    private int vie;
    private boolean vivant;
    public BufferedImage texture;

    {
        try {
            texture = ImageIO.read(new File("res\\images\\pion\\Diver_Adventurer_Icon@2x.png"));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Constructeur du personnage
     * @param numero, le numero du personnage
     * @param x, y, sa position
     */
    public Personnage(int numero, int x, int y){
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.vie = Modele.ViePersonnage;
        this.vivant = true;
    }

    /**
     * Sa position en x
     */
    public int getX() {
        return x;
    }

    /**
     * Sa position en y
     */
    public int getY() {
        return y;
    }

    /**
     * Le numero du joueur
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Déplace le personnage, dans un endroit prealablement testé
     * @param x, represente l'axe des x
     * @param y, represente l'axe des y
     */
    public void deplace(int x, int y){
        System.out.println("x :" + x + "y :" + y);
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return  "Joueur " + numero+ " {" + "x=" + x + ", y=" + y + ", vie=" + vie + "}";
    }
}
