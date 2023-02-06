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
    /** Vie maximal pour un personnage**/
    public static final int ViePersonnage = 20;
    private final int nbPersonnage = 3;

    /** Eplacement de l'epave (et donc des joueurs) **/
    private static final int hauteurEpave = 5;
    private static final int largeurEpave = 5;

    /** La grille qui contient les zones **/
    private final Zone[][] zones;

    /** La liste des joueurs **/
    private List<Personnage> personnages;

    /**
     * Attributs de stockage
     */
    private int stockageEau = 0;
    private int stockageBois = 0;
    private int stockageNourriture = 0;

    /**
     * Constructeur du Modele
     */
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

    /**
     * Renvoie la liste des personnages
     * @return, la liste
     */
    public List<Personnage> getPersonnages() {
        return personnages;
    }

    /**
     * Déplace le joueur s'il peut y aller
     */
    public void deplacePersonnage(int numeroPersonnage, int x, int y){
        if ((x > 0 && x < largeur)&&(y > 0 && y < hauteur)){
            Zone z = zones[personnages.get(numeroPersonnage).getX()][personnages.get(numeroPersonnage).getY()];
            switch (z.getType()) {
                case Mer -> {
                }
                default -> {personnages.get(numeroPersonnage).deplace(x, y);}
            }
        }
    }

    public void recolteRessource(int numeroPersonnage){
        /*for (Personnage p :personnages) {
            Zone z = zones[p.getX()][p.getY()];
            switch (z){
                Case
            }
        }*/
        Zone z = zones[personnages.get(numeroPersonnage).getX()][personnages.get(numeroPersonnage).getY()];
        switch (z.getType()) {
            case Arbre -> stockageBois += 10;
            case Buisson -> stockageNourriture += 10;
            case Eau -> stockageEau += 10;
            default -> {}
        }
    }

    /**
     * Renvoie la zone qui se trouve aux coordonnées fournis
     * @param x, l'axe des X
     * @param y, l'axe des Y
     * @return la zone souhaitée
     */
    public Zone getZone(int x, int y){
        return zones[x][y];
    }
}