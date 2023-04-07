package Modele;

import Modele.Modele;

public class ActionTimer2 extends Thread {
    private Modele m;
    private int time;
    /*private Modele.Personnage p;
    private int[] timeBar;
    private JProgressBar bar;*/
    private int numPerso;

    /**
     * @param m Modele.Modele du jeu
     * @param time Temps d'occupation du personnage en secondes
     */
    //public Modele.ActionTimer2(Modele.Modele m, int time, Modele.Personnage p) throws IllegalArgumentException {
    //public Modele.ActionTimer2(int time, int[] timeBar, JProgressBar bar) throws IllegalArgumentException {
    public ActionTimer2(Modele m, /*int time, */int numPerso) throws IllegalArgumentException {
        this.m = m;
        /*if (time <= 0){
            throw new IllegalArgumentException("Le temps donné doit être supérieur à zéro");
        }*/
        //this.time = time;
        System.out.println("num perso actiontimer2: " + numPerso);
        this.numPerso = numPerso;
    }

    public void run(int numAction, int x, int y){
        System.out.println(numPerso);
        Personnage p = m.getPersonnage(numPerso);
        int timer = m.getTimer(numAction)*60; // On convertit le temps en secondes
        p.setActive();
        m.deplacePersonnage(numPerso, x, y);
        while(this.time > 0){
            try {
                Thread.sleep(1000);
                this.time--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        m.useAction(numAction, numPerso);
        p.setInactive();
    }
}
