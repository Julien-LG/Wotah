import java.util.List;

/**
 * Type énuméré qui indique le type de la zone
 */
enum TypeZone {
    Sol, Buisson, Arbre, Eau, Mer, Epave;
}

public class Modele {
    /** Taille de la grille **/
    private static final int hauteur = 15;
    private static final int largeur = 15;

    /** Constantes **/
    public static final int ViePersonnage = 20;
    private final int nbPersonnage = 3;

    /** Eplacement de l'epave (et donc des joueurs) **/
    private static final int hauteurEpave = 5;
    private static final int largeurEpave = 5;

    /** La grille qui contient les zones **/
    private final Zone[][] zones;

    /** La liste des joueurs **/
    private List<Personnage> personnages;

    public Modele(){
        zones = new Zone[hauteur][largeur];
        for(int i = 0; i<largeur;i++){
            for(int j = 0; j<hauteur; j++){
                zones[i][j] = new Zone(i, j, TypeZone.Mer);
            }
        }

        zones[hauteurEpave-1][largeurEpave-1] = new Zone(hauteurEpave-1,largeurEpave-1,TypeZone.Epave);

        for (int i = 0; i < nbPersonnage; i++)
            personnages.add(new Personnage(i,hauteurEpave,largeurEpave));

    }
}