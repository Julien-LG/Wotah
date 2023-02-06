enum TypeZone {
    Sol, Buisson, arbre, eau, Mer, Epave;
}

public class Modele {
    /**
     * Type énuméré qui indique le type de la zone
     */


    /*Taille de la grille*/
    private static final int hauteur = 15;
    private static final int largeur = 15;
    public static final int ViePersonnage = 20;
    private final Zone[][] zones;

    public Modele(){
        zones = new Zone[hauteur][largeur];
        for(int i = 0; i<largeur;i++){
            for(int j = 0; j<hauteur; j++){
                zones[i][j] = new Zone(i, j);
            }
        }
    }
}