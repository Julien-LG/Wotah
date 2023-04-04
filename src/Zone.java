import java.awt.*;
import java.awt.image.BufferedImage;

public class Zone {
    /** On stocke les coordonnées pour pouvoir les passer au modèle.**/
    private final int x, y;

    /**
     * l'attribut type represente le type de la zone, et permet de savoir s'il y a des ressources ou si la zone est praticable
     */
    private TypeZone type;

    private Image texture;

    /** Constructeur de la zone **/
    public Zone(int x, int y, TypeZone type){
        this.x = x;
        this.y = y;
        this.type = type;

        switchTexture(type);
    }

    /**
     * Affiche sous forme d'une chaine de caractere la zone
     * @return
     */
    public String toString(){
        return "Zone{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }

    /**
     * Renvoie le type de la zone
     * @return le type de la zone
     */
    public TypeZone getType() {
        return type;
    }

    /**
     * Remplace le type de la zone actuelle par un sol classiqeu
     */
    public void setTypeVide() {
        if (type != TypeZone.Mer)
            this.type = TypeZone.Sol;
    }

    /**
     * Remplace le type de la zone
     * @param type le nouveau type
     */
    public void setType(TypeZone type){
        this.type = type;
        switchTexture(type);
    }

    public void switchTexture(TypeZone type) {
        switch (type) {
            case Sol -> this.texture = Textures.texture_grass.getTexture();
            case Sable -> this.texture = Textures.texture_sand.getTexture();
            case Rocher -> this.texture = Textures.texture_rock.getTexture();
            case Epave -> this.texture = Textures.texture_shipwreck.getTexture();
            case Arbre -> this.texture = (Math.random() < 0.5) ? Textures.texture_tree.getTexture() : Textures.texture_tree_reversed.getTexture();
            case Buisson -> this.texture = (Math.random() < 0.5) ? Textures.texture_bushs.getTexture() : Textures.texture_bushs_reversed.getTexture();
            case Eau -> this.texture = Textures.texture_water.getTexture();
            default -> this.texture = Textures.texture_shipwreck.getTexture();
        };
    }

    public Image getTexture() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
