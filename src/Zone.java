public class Zone {
    /**
     * Type énuméré qui indique le type de la zone
     */
    public enum TypeZone {
        Sol, Buisson, arbre, eau, Mer, Epave;
    }

    /** On stocke les coordonnées pour pouvoir les passer au modèle.**/
    private final int x, y;

    /**
     * l'attribut type represente le type de la zone, et permet de savoir s'il y a des ressources ou si la zone est praticable
     */
    private TypeZone type;

    /** Constructeur de la zone **/
    public Zone(int x, int y, TypeZone type){
        this.x = x;
        this.y = y;
        this.type = type;
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
    }
}
