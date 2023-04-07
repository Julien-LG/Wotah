import Controle.Controleur;
import Controle.ControleurDragAndDrop;
import Controle.PauseInGame;
import Modele.Modele;
import Vue.CVue;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Modele modele = new Modele();
            CVue vue = new CVue(modele);
            new Controleur(modele, vue);
            new ControleurDragAndDrop(modele, vue);
        });
        PauseInGame pauseInGame = new PauseInGame();



        /*new Thread() { // Timer de la tempete (la fin du jeu)
            public void run(){
                while(modele.getTimerTempete() > 0){
                    try {
                        Thread.sleep(1000);
                        //System.out.println("coucou");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/
    }
}