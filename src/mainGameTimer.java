public class mainGameTimer extends Thread {

    private Modele model;

    public mainGameTimer(Modele m){
        this.model = m;
    }

    @Override
    public void run(){
        int timer = model.getTimerTempete();

        while(timer > 0){
            try {
                Thread.sleep(1000);
                timer--;
                System.out.println(timer);
                model.setTimerTempete(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}