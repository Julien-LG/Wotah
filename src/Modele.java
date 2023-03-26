import java.util.*;
import java.util.ArrayList;

public class Modele {
    /** Taille de la grille **/
    private static final int hauteur = 10;
    private static final int largeur = 15;

    /** Eplacement de l'epave (et donc des joueurs) **/
    private static final int hauteurEpave = 1;
    private static final int largeurEpave = 5;

    /** Vie maximal pour un personnage **/
    private final int nbPersonnage = 1;

    /** Nombre de personnage présent **/
    private static final int ViePersonnage = 20;

    /** La grille qui contient les zones **/
    private final Zone[][] zones;

    /** La liste des joueurs **/
    private ArrayList<Personnage> personnages = new ArrayList<Personnage>();

    /** Nombre de ressources récoltés**/
    private int nbRecolte = 20;

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

    public List<Zone> zonesToRepaint = new ArrayList<Zone>();

    /**
     * Constructeur du Modele
     */
    public Modele(){
        Random rand = new Random();
        zones = new Zone[largeur][hauteur];

        for(int i = 0; i < largeur; i++){
            for(int j = 0; j < hauteur; j++){
                TypeZone type;
                Boolean isSea = i == 0 || (i == 1 && (j == 0 || j == 1 || j == 2 || j == hauteur-1 || j == hauteur-2 || j == hauteur-3)) || (i == 2 && (j == 0 || j == hauteur-1));
                boolean isShipwreck = (isSea) ? false : i == hauteurEpave && j == largeurEpave;
                Boolean isSand = (isSea || isShipwreck) ? false : i == 1 && (j > 2 && j < hauteur-2) || (i == 2 && ((j > 0 && j < 4) || (j > hauteur-5 && j < hauteur-1))) || (i == 3 && ((j == 0 || j == 1 || j == hauteur-1 || j == hauteur-2)));

                if (isSea) type = TypeZone.Mer;
                else if (isSand) type = TypeZone.Sable;
                else if (isShipwreck) type = TypeZone.Epave;
                else {
                    switch (rand.nextInt(10)) {
                        case 0 -> type = TypeZone.Buisson;
                        case 1 -> type = TypeZone.Arbre;
                        case 2 -> type = TypeZone.Eau;
                        default -> type = TypeZone.Sol;
                    }
                } this.zones[i][j] = new Zone(i, j, type);
                if (!isSea) zonesToRepaint.add(zones[i][j]);
            }
        }

        for (int i = 0; i < nbPersonnage; i++)
            this.personnages.add(new Personnage(i, hauteurEpave + 1, largeurEpave));
    }

    /**
     * Renvoie la liste des personnages
     * @return, la liste
     */
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public static int getHeight(){
        return hauteur;
    }

    public static int getWidth(){
        return largeur;
    }

    public static int getViePersonnage() {
        return ViePersonnage;
    }

    public int getNbPersonnage() {
        return nbPersonnage;
    }

    public int getTimerTempete() {
        return timerTempete;
    }

    public void setTimerTempete(int timerTempete) {
        this.timerTempete = timerTempete;
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
    
    public Zone[][] getZones() {
        return zones;
    }

    /**
     * Déplace le joueur s'il peut y aller
     */
    public void deplacePersonnage(int numeroPersonnage, int x, int y){
        if ((x >= 0 && x <= largeur) && (y >= 0 && y <= hauteur))
            if (zones[x][y].getType() != TypeZone.Mer) {
                personnages.get(numeroPersonnage).deplace(x, y);
            }
        if (zones[x][y].getType() == TypeZone.Eau || zones[x][y].getType() == TypeZone.Arbre || zones[x][y].getType() == TypeZone.Buisson) {
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } recolteRessource(numeroPersonnage);
                    System.out.println("Ressource recoltée");
                    zones[x][y].setType(TypeZone.Sol);
                    zonesToRepaint.add(zones[x][y]);
                }
            }.start();
        }
    }

    /**
     * Recolte une ressource
     * @param numeroPersonnage, pour savoir son emplacement
     */
    public void recolteRessource(int numeroPersonnage){
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
}