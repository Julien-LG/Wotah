package Modele;

import Modele.Modele;
import Vue.CVue;

public class MainGameTimer extends Thread {

    private Modele model;
    private CVue vue;

    public MainGameTimer(Modele m, CVue vue){
        this.model = m;
        this.vue = vue;
    }

    @Override
    public void run(){
        int timer = model.getTimerTempete();

        /*while(timer > 0){
            try {
                Thread.sleep(1000);
                timer--;
                System.out.println(timer);
                model.setTimerTempete(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        while(model.getTimerTempete() > 0) {
            try {
                Thread.sleep((model.getTimerTempete()/60 * 1000L)/100);
                vue.addTempeteTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}