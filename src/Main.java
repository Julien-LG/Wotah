import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Modele modele = new Modele();
        modele.genereIle();
        //Partie graphique
        //CVue vue = new CVue(modele);

        System.out.println(modele.toString());
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

        /*Scanner coordX = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrer le X");

        Scanner coordY = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entrer le Y");

        System.out.println("le x est : " + coordX.nextInt() + " le y est : " + coordY.nextInt());  // Output user input
        modele.deplacePersonnage(0, coordX.nextInt(),coordY.nextInt());

        System.out.println(modele.toString());*/
    }
}

