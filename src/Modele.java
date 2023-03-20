import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Type énuméré qui indique le type de la zone
 */
enum TypeZone {
    Sol, Buisson, Arbre, Eau, Mer, Sable, Epave, PousseArbre, PousseBuisson;
}

public class Modele {
    /** Nombre de ressources récoltés**/
    private int nbRecolte = 20;

    /** Taille de la grille **/
    public static final int hauteur = 10;
    private static final int largeur = 15;

    /** Constantes **/
    /** Vie maximal pour un personnage**/
    public static final int ViePersonnage = 20;
    private final int nbPersonnage = 3;

    /** Eplacement de l'epave (et donc des joueurs) **/
    private static final int hauteurEpave = 0;
    private static final int largeurEpave = 4;

    /** La grille qui contient les zones **/
    private final Zone[][] zones;

    /** La liste des joueurs **/
    private ArrayList<Personnage> personnages;

    /**
     * Attributs de stockage
     */
    private int stockageEau = 0;
    private int stockageBois = 0;
    private int stockageNourriture = 0;

    private int stockageGraineArbre = 0;
    private int stockageGraineBuisson = 0;

    /** Condition de victoire et de défaite **/
    private int nbRessourcesVictoire = 100;
    private int timerTempete = 30;

    /** Temps des timer **/
    private int timerRecolte = 3;
    private int timerPlantation = 3;

    private int timerPousse = 5;

    /**
     * Constructeur du Modele
     */
    public Modele(){
        zones = new Zone[largeur][hauteur];
        for(int i = 0; i<largeur;i++){
            for(int j = 0; j<hauteur; j++){
                if (i == 0 || (i == 1 && (j == 0 || j == 1 || j == 2 || j == hauteur-1 || j == hauteur-2 || j == hauteur-3)) || (i == 2 && (j == 0 || j == hauteur-1)))
                    zones[i][j] = new Zone(i, j, TypeZone.Mer);
                else
                    zones[i][j] = new Zone(i, j, TypeZone.Sol);
            }
        }

        for(int i = 0; i<largeur; i++){
            for(int j = 0; j<hauteur; j++){
                if (zones[i][j].getType() == TypeZone.Sol && ((j < hauteur-1 && zones[i][j+1].getType() == TypeZone.Mer) || (j > 0 && zones[i][j-1].getType() == TypeZone.Mer) ||
                        (i > 0 && zones[i-1][j].getType() == TypeZone.Mer) || (i > 0 && j < hauteur-1 && zones[i-1][j+1].getType() == TypeZone.Mer) || (i > 0 && j > 0 && zones[i-1][j-1].getType() == TypeZone.Mer)))
                    zones[i][j] = new Zone(i, j, TypeZone.Sable);
            }
        }

        zones[hauteurEpave][largeurEpave] = new Zone(hauteurEpave-1, largeurEpave-1, TypeZone.Epave);

        personnages = new ArrayList<Personnage>();
        for (int i = 0; i < nbPersonnage; i++)
            personnages.add(new Personnage(i, hauteurEpave, largeurEpave));

    }

    /**
     * Génére l'ile avec ses différentes Zones
     */
    /*public void genereIle(){
        for(int i = largeurEpave; i<largeur;i++){
            for(int j = hauteurEpave; j<hauteur; j++){
                zones[i][j] = new Zone(i, j, TypeZone.Sol);
            }
        }
    }*/

    /**
     * Renvoie la liste des personnages
     * @return, la liste
     */
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    @Override
    public String toString() {
        return "Modele{" +
                "nbPersonnage=" + nbPersonnage +
                ", personnages=" + personnages +
                ", stockageEau=" + stockageEau +
                ", stockageBois=" + stockageBois +
                ", stockageNourriture=" + stockageNourriture +
                '}';
    }

    public int getNbPersonnage() {
        return nbPersonnage;
    }

    /**
     * Déplace le joueur s'il peut y aller
     */
    public void deplacePersonnage(int numeroPersonnage, int x, int y){
        System.out.println("Deplacement du personnage");
        if ((x > 0 && x < largeur)&&(y > 0 && y < hauteur)){
            System.out.println("valeur vérifié ; " + x + y);
            Zone z = zones[x][y];
            System.out.println("type de la zone " + z.getType());
            switch (z.getType()) {
                case Mer -> {
                }
                default -> {personnages.get(numeroPersonnage).deplace(x, y);}
            }
            System.out.println("je check le perso : " + personnages.get(numeroPersonnage).toString());
        }
    }

    /**
     * Recolte une ressource
     * @param numeroPersonnage, pour savoir son emplacement
     */
    public void recolteRessource(int numeroPersonnage){
        /*for (Personnage p :personnages) {
            Zone z = zones[p.getX()][p.getY()];
            switch (z){
                Case
            }s
        }*/
        int chanceGraine = (int)(Math.random() * 100);
        Zone z = zones[personnages.get(numeroPersonnage).getX()][personnages.get(numeroPersonnage).getY()];
        switch (z.getType()) {
            case Eau -> stockageEau += nbRecolte;
            case Arbre -> {
                stockageBois += nbRecolte;
                if (chanceGraine < 90) stockageGraineBuisson+=1;
                else stockageGraineBuisson+=2;
            }
            case Buisson -> {
                stockageNourriture += nbRecolte;
                if (chanceGraine < 80) stockageGraineBuisson+=1;
                else stockageGraineBuisson+=2;
            }
            default -> {}
        }
    }

    /**
     * Vérifie la présence d'une certaine graine
     * @param typeGraine
     * @return, true s'il en existe au moins une
     */
    public boolean possedeGraine(TypeZone typeGraine){
        if (typeGraine == TypeZone.Arbre){
            return stockageGraineArbre > 0;
        }
        else if(typeGraine == TypeZone.Buisson) {
            return stockageGraineBuisson > 0;
        }
        else {
            return false;
        }
    }

    /**
     * Retire une graine voulu de l'inventaire
     * @param typeGraine
     */
    public void planteGraine(TypeZone typeGraine){
        if (possedeGraine(typeGraine)){
            switch (typeGraine){
                case Arbre -> stockageGraineArbre -= 1;
                case Buisson -> stockageGraineBuisson -= 1;
                default -> {}
            }
        }
    }

    /**
     * Génére les différentes actions possibles d'un personnage sur une case
     * @param numeroPersonnage, le numéro du personnage
     * @param x, la direction en x
     * @param y, la direction en y
     * @return, la liste des actions possibles
     */
    public List<String> actionsPossiblesPerso(int numeroPersonnage, int x, int y){
        Zone z = zones[x][y];
        List<String> result = null;
        switch (z.getType()){
            case Eau -> result.add("Récolter eau");
            case Arbre -> result.add("Récolter bois");
            case Buisson -> result.add("Récolter nourriture");
            default -> {}
        }

        if (z.getType() == TypeZone.Sol){
            if (possedeGraine(TypeZone.Arbre)) result.add("Planter arbre");
            if (possedeGraine(TypeZone.Buisson)) result.add("Planter buisson");
        }

        if (z.getType() != TypeZone.Mer){
            result.add("Se déplacer");
        }

        if (z.getType() == TypeZone.Epave) result.add("Tenter de fuir");

        return result;
    }

    /**
     * Fonction de fuite de l'ile, si les ressources sont suffiasantes
     * @param numPersonnage, le numéro du personnage
     */
    public void quitterIle(int numPersonnage){
        if (stockageEau == nbRessourcesVictoire && stockageBois == nbRessourcesVictoire && stockageNourriture == nbRessourcesVictoire) {
            int nbPersos = 0;
            for (Personnage p : personnages){
                if (p.getX() == largeurEpave && p.getY() == hauteurEpave) nbPersos++;
            }

            if (nbPersos == 1) { // Partir seul...
                //Appel de l'affichage de Victoire solo

            }
            else if (nbPersos == 0) { //erreur
                System.out.println("Erreur aucun joueur présent sur l'épave");
            }
            else if (nbPersos == personnages.size()) { // Partir avec tout le monde
                //Appel de l'affichage de Victoire avec tout le monde

            }
            else { // Abandonner quelqu'un
                //Appel de l'affichage de Victoire avec une partie des naufragés seulement

            }
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

    public static int getHeight(){
        return hauteur;
    }

    public static int getWidth(){
        return largeur;
    }
}