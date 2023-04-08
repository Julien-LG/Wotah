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

            // Timer de la tempete (la fin du jeu)
            new Thread(() -> {
                while(modele.getTimerTempete() > 0) {
                    try {
                        Thread.sleep((modele.getTimerTempete()/60 * 1000L)/100);
                        vue.addTempeteTime();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
        PauseInGame pauseInGame = new PauseInGame();
    }
}