import javax.swing.*;

public class ActionTimer extends Thread {
    private Modele m;
    private int time;
    private Personnage p;
    private int[] timeBar;
    private JProgressBar bar;

    /**
     * @param m Modele du jeu
     * @param time Temps d'occupation du personnage en secondes
     */
    //public ActionTimer(Modele m, int time, Personnage p) throws IllegalArgumentException {
    public ActionTimer(int time, int[] timeBar, JProgressBar bar) throws IllegalArgumentException {
        //this.m = m;
        if (time <= 0){
            throw new IllegalArgumentException("Le temps donné doit être supérieur à zéro");
        }
        this.time = time;
        //this.p = p;
        this.timeBar = timeBar;
        this.bar = bar;
    }

    public void run(int x, int y, Personnage p){
        p.setActive();
        while(this.time > 0){
            try {
                Thread.sleep(1000);
                this.time--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeBar[0]++;
            bar.setValue(timeBar[0]);
            bar.setBounds(x, y, 100, 10);
        }
        p.setInactive();
    }
}
