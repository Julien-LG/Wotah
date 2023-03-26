import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele();
        //Partie graphique
        CVue vue = new CVue(modele);

        //System.out.println(modele.toString());


        /*new Thread() {
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



        //for (int i = 0; i< modele.getNbPersonnage(); i++){
        /*for (int i = 0; i< 1; i++){

            Scanner coordX = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Entrer le X");

            Scanner coordY = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Entrer le Y");

            //String userName = coordX.nextInt();  // Read user input
            System.out.println("le x est : " + coordX.nextInt() + " le y est : " + coordY.nextInt());  // Output user input

            modele.deplacePersonnage(i, coordX.nextInt(),coordY.nextInt());
        }*/

        /*System.out.println("Taille : " + modele.getPersonnages().size());
        Scanner coordX = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrer le X");

        Scanner coordY = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrer le Y");

        System.out.println("le x est : " + coordX.nextInt() + " le y est : " + coordY.nextInt());  // Output user input
        modele.deplacePersonnage(0, coordX.nextInt(),coordY.nextInt());

        System.out.println(modele.toString());
        System.out.println("Taille : " + modele.getPersonnages().size());*/

        /*Scanner coordX = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrer le X");*/
    }
}

