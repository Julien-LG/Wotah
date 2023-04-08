package Modele;

import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Modele {
    /** Taille de la grille **/
    private static final int hauteur = 10;
    private static final int largeur = 15;

    /** Eplacement de l'epave (et donc des joueurs) **/
    private static final int hauteurEpave = 5;
    private static final int largeurEpave = 1;

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

    /** Temps des timer en minutes **/
    private final int timerRecolte = 3;
    private final int timerPlantation = 3;
    private final int timerPousse = 5;

    /** Temps en secondes entre chaque déplacement de zone **/
    private final int vitesseDeplacement = 5;

    /** Pourcentage de chances de n'avoir qu'une graine **/
    private final int dropGraineBuisson = 80;
    private final int dropGraineArbre = 90;

    public List<Zone> zonesToRepaint = new ArrayList<Zone>();

    /**
     * Constructeur du Modele.Modele
     */
    public Modele(){
        Random rand = new Random();
        zones = new Zone[largeur][hauteur];

        for(int i = 0; i < largeur; i++){
            for(int j = 0; j < hauteur; j++){
                TypeZone type;
                Boolean isSea = i == 0 || (i == 1 && (j == 0 || j == 1 || j == 2 || j == hauteur-1 || j == hauteur-2 || j == hauteur-3)) || (i == 2 && (j == 0 || j == hauteur-1));
                //boolean isShipwreck = !isSea && i == hauteurEpave && j == largeurEpave;
                boolean isShipwreck = !isSea && i == largeurEpave && j == hauteurEpave;
                Boolean isSand = !isSea && !isShipwreck && (i == 1 && (j > 2 && j < hauteur - 2) || (i == 2 && ((j > 0 && j < 4) || (j > hauteur - 5 && j < hauteur - 1))) || (i == 3 && ((j == 0 || j == 1 || j == hauteur - 1 || j == hauteur - 2))));

                if (isSea) type = TypeZone.Mer;
                else if (isSand) type = TypeZone.Sable;
                else if (isShipwreck) type = TypeZone.Epave;
                else {
                    switch (rand.nextInt(20)) {
                        case 0, 1 -> type = TypeZone.Buisson;
                        case 2, 3 -> type = TypeZone.Arbre;
                        case 4, 5 -> type = TypeZone.Eau;
                        case 6 -> type = TypeZone.Rocher;
                        default -> type = TypeZone.Sol;
                    }
                } this.zones[i][j] = new Zone(i, j, type);
                if (!isSea) zonesToRepaint.add(zones[i][j]);
            }
        }
        /// Gère le cas ou un rocher se trouve sur l'emplacement de spawn des personnages
        if (zones[largeurEpave][hauteurEpave].getType() == TypeZone.Rocher) {
            zones[largeurEpave+1][hauteurEpave].setType(TypeZone.Sol);
            zonesToRepaint.add(zones[hauteurEpave][largeurEpave]);
        }
        for (int i = 0; i < nbPersonnage; i++)
            this.personnages.add(new Personnage(i,  largeurEpave+ 1, hauteurEpave));
    }

    /**
     * Renvoie la liste des personnages
     * @return, la liste
     */
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public Personnage getPersonnage(int numeroPersonnage) {
        return personnages.get(numeroPersonnage);
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
            if (zones[x][y].getType() != TypeZone.Mer && zones[x][y].getType() != TypeZone.Rocher) {
                personnages.get(numeroPersonnage).deplace(x, y);
            }
        /*if (zones[x][y].getType() == TypeZone.Eau || zones[x][y].getType() == TypeZone.Arbre || zones[x][y].getType() == TypeZone.Buisson) {
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
        }*/
    }

    public void deplacePersonnageUnPas(int numeroPersonnage, int x, int y){
        if ((x >= 0 && x <= largeur) && (y >= 0 && y <= hauteur))
            if (zones[x][y].getType() != TypeZone.Mer) {
                if (x > personnages.get(numeroPersonnage).getX())
                    personnages.get(numeroPersonnage).deplace(x+1, y);
                else if (x < personnages.get(numeroPersonnage).getX())
                    personnages.get(numeroPersonnage).deplace(x-1, y);
                else if (y > personnages.get(numeroPersonnage).getY())
                    personnages.get(numeroPersonnage).deplace(x, y+1);
                else if (y < personnages.get(numeroPersonnage).getY())
                    personnages.get(numeroPersonnage).deplace(x, y-1);
            }
    }

    /**
     * Modele.Recolte une ressource
     * @param numeroPersonnage, pour savoir son emplacement
     */
    public void recolteRessource(int numeroPersonnage){
        int chanceGraine = (int)(Math.random() * 100);
        Zone z = zones[personnages.get(numeroPersonnage).getX()][personnages.get(numeroPersonnage).getY()];
        System.out.println(z.getType());
        switch (z.getType()) {
            case Eau -> stockageEau += nbRecolte;
            case Arbre -> {
                stockageBois += nbRecolte;
                if (chanceGraine < dropGraineArbre) stockageGraineArbre+=1;
                else stockageGraineArbre+=2;
            }
            case Buisson -> {
                stockageNourriture += nbRecolte;
                if (chanceGraine < dropGraineBuisson) stockageGraineBuisson+=1;
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
                case Arbre -> stockageGraineArbre -= 1;//j'ai supprimé le TypeZone.Arbre et TypeZone.Buisson pour le besoin du package
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
     * @return, la liste des numéros des actions possibles
     */
    public List<Integer> actionsPossiblesPerso(int numeroPersonnage, int x, int y){
        Zone z = zones[x][y];
        List<Integer> result = new ArrayList<>();
        switch (z.getType()){
            case Eau -> result.add(1);
            case Arbre -> result.add(2);
            case Buisson -> result.add(3);
            default -> {}
        }

        if (z.getType() == TypeZone.Sol){
            if (possedeGraine(TypeZone.Arbre)) result.add(4);
            if (possedeGraine(TypeZone.Buisson)) result.add(5);
        }

        if (z.getType() != TypeZone.Mer && z.getType() != TypeZone.Rocher){
            result.add(6);
        }

        if (z.getType() == TypeZone.Epave) result.add(7);

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
                System.out.println("Vous avez quitter l'île seul en abondonnant les autres naufragés à leur sort...");
            }
            else if (nbPersos == 0) { //erreur
                System.out.println("Erreur aucun joueur présent sur l'épave");
            }
            else if (nbPersos == personnages.size()) { // Partir avec tout le monde
                //Appel de l'affichage de Victoire avec tout le monde
                System.out.println("Vous avez réussi à quitter l'île avec tout le monde !");
            }
            else { // Abandonner quelqu'un
                //Appel de l'affichage de Victoire avec une partie des naufragés seulement
                System.out.println("Vous etes vivant mais vous avez abandonné " + (personnages.size() - nbPersos) + " personnes sur cette île");
            }
        }
    }

    public void useAction(int numAction, int numPersonnage){
        System.out.println("numero perso " + numPersonnage);
        Personnage p = personnages.get(numPersonnage);
        switch (numAction){
            case 1 -> recolteRessource(numPersonnage);
            case 2 -> recolteRessource(numPersonnage);
            case 3 -> recolteRessource(numPersonnage);
            case 4 -> planteGraine(TypeZone.Arbre);
            case 5 -> planteGraine(TypeZone.Buisson);
            case 7 -> quitterIle(numPersonnage);
            default -> {}
        }
        zonesToRepaint.add(zones[p.getX()][p.getY()]);
    }

    public int getTimer(int numAction){
        switch (numAction){
            case 1,2,3 -> {return timerRecolte;}
            case 4,5 -> {return timerPlantation;}
            default -> {}
        }
        return -1;
    }

    public int getStockageBois() {
        return stockageBois;
    }

    public int getStockageEau() {
        return stockageEau;
    }

    public int getStockageNourriture() {
        return stockageNourriture;
    }

    public int getVitesseDeplacement() {
        return vitesseDeplacement;
    }
}