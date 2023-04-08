package Modele;

import Modele.Modele;

public class Recolte extends Thread {

    public void run(Modele modele, int p) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } modele.recolteRessource(p);        
    }
}
