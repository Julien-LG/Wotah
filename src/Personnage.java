public class Personnage {
    /**
     * Attributs du personnage
     */
    private final int numero;
    private int x, y;
    private int vie;
    private boolean vivant;

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
            this.x = x;
            this.y = y;
    }

    @Override
    public String toString() {
        return  "Joueur " + numero+ " {" + "x=" + x + ", y=" + y + ", vie=" + vie + "}";
    }
}
