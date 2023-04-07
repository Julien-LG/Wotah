package Modele;

import Modele.Modele;

import java.awt.*;

public class Personnage {
    /**
     * Attributs du personnage
     */
    private final int numero;
    private int x, y;
    private int vie;
    private boolean vivant = true;
    private Image texture;
    private boolean isActive;
    
    /**
     * Constructeur du personnage
     * @param numero, le numero du personnage
     * @param x, y, sa position
     */
    public Personnage(int numero, int x, int y){
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.vie = Modele.getViePersonnage();
        this.texture = Textures.texture_perso.getTexture();
        this.isActive = false;
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
            this.x = x;
            this.y = y;
    }

    @Override
    public String toString() {
        return  "Joueur " + numero+ " {" + "x=" + x + ", y=" + y + ", vie=" + vie + "}";
    }

    public Boolean getVivant() {
        return vivant;
    }

    public Image getTexture() {
        return texture;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(){
        this.isActive = true;
    }

    public void setInactive(){
        this.isActive = false;
    }
}
