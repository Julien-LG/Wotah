import java.util.EventListener;

public class Controleur implements EventListener {

    private Modele m;
    private mainGameTimer timer;

    public Controleur(Modele modele){
        this.m = modele;

        this.timer = new mainGameTimer(m);
        timer.start();
    }
}