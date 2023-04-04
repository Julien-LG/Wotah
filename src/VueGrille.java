import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

class VueGrille extends JPanel {

    private Modele modele;
    public Zone zone = new Zone(0, 0, TypeZone.Sol);
    public final static int SCREEN_SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    JLabel labelNourriture;
    JLabel labelBois;
    JLabel labelEau;

    JProgressBar progressBarTempete;

    private int hauteurProgressBarTempete = 30;

    public VueGrille(Modele modele) {
        this.modele = modele;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension((SCREEN_SIZE/Modele.getHeight()) * Modele.getWidth(), SCREEN_SIZE));

        labelNourriture = new JLabel("0");
        labelNourriture.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/80, 100, 100);
        labelBois = new JLabel("0");
        labelBois.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/15, 100, 100);
        labelEau = new JLabel("0");
        labelEau.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/8, 100, 100);

        progressBarTempete = new JProgressBar();
        progressBarTempete.setValue(0);
        progressBarTempete.setStringPainted(true);
        progressBarTempete.setBounds(0,0,500,500);
        //progressBarTempete.setLocation(0, 0);

        this.add(labelNourriture);
        this.add(labelBois);
        this.add(labelEau);

        this.add(progressBarTempete);
    }



    public void paintTest(Graphics g){
        int size = SCREEN_SIZE/Modele.getHeight();
        //JLabel labelPion = new JLabel(new ImageIcon(modele.getPersonnages().get(0).getTexture().getScaledInstance(2 * size / 3, 2 * size / 3, Image.SCALE_DEFAULT)));
        JLabel labelPion = new JLabel(new ImageIcon(modele.getPersonnages().get(0).getTexture().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        labelPion.setLocation(100,100);
        this.add(labelPion);
        this.add(new Pion(modele.getPersonnages().get(0)));
        labelPion.update(g);
    }

    public void paintPersonnages(Graphics g){
        int size = SCREEN_SIZE/Modele.getHeight();
        for(Personnage p : modele.getPersonnages()) {
            g.drawImage(p.getTexture().getScaledInstance(2 * size / 3, 2 * size / 3, Image.SCALE_DEFAULT), p.getX() * size, p.getY() * size, null);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = SCREEN_SIZE/Modele.getHeight();

        for (Zone zone : modele.zonesToRepaint) {
            //g.drawImage(zone.getTexture().getScaledInstance(size, size, Image.SCALE_DEFAULT), zone.getX() * size, zone.getY() * size, null);
            g.drawImage(zone.getTexture(), zone.getX() * size, zone.getY() * size, null);
        }
        /*for(Personnage p : modele.getPersonnages()) {
            g.drawImage(p.getTexture().getScaledInstance(2 * size / 3, 2 * size / 3, Image.SCALE_DEFAULT), p.getX() * size, p.getY() * size, null);
            //System.out.println("personnage en " + p.getX() * size + " " + p.getY() * size);
            //g.
        }*/
        afficheRessources(g);
        paintPersonnages(g);
        progressBar(g);
        //paintTest(g);


        /*JLabel label1 = new JLabel("Test");
        //label1.setBounds(0, 0, 100, 100);

        label1.setLocation(0, 0);
        g.add(label1);
        g.*/

        /*JLabel labelNourriture = new JLabel("Test");
        labelNourriture.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/80, 100, 100);*/
        //labelNourriture.update(g);
    }

    //List progressBars = new List();

    /*public void newProgressBar(Graphics g, int x, int y){
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(50);
        progressBar.setStringPainted(true);
        progressBar.setLocation(x, y);
        //progressBar.setBounds(200, 200, 100, 100);
        this.add(progressBar);
        progressBar.update(g);
    }*/

    /*public void newProgressBar(Graphics g, int x, int y){
        final int[] progress = {0};

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(progress[0]);
        this.add(progressBar);

        progressBar.setStringPainted(true);
        progressBar.setBounds(x, y, 100, 10);

        Thread thread = new Thread() {
            public void run() {
                while (progress[0] < 100) {
                    try {
                        Thread.sleep(1000); // temps de pause entre chaque mise à jour de la barre de chargement
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progress[0]++;
                    progressBar.setValue(progress[0]);
                    progressBar.setBounds(x, y, 100, 10);
                }
            }
        };
        thread.start();
    }*/

    public void progressBar(Graphics g){
        //JProgressBar progressBar = new JProgressBar();
        /*progressBar.setValue(0);
        progressBar.setStringPainted(true);*/
        //progressBarTempete.setLocation(200, 200);
        //progressBar.setBounds(200, 200, 100, 100);
        //this.add(progressBar);
        //progressBarTempete.update(g);
        progressBarTempete.setBounds(0,SCREEN_SIZE-(hauteurProgressBarTempete*2),250,hauteurProgressBarTempete);

        /// TODO : l'avancement de la progressBar tempete ne doit pas s faire ici
        progressBarTempete.setValue(progressBarTempete.getValue()+1);

    }

    public void afficheRessources(Graphics g) {
        g.drawImage(Textures.sprite_sheet.getTextureNoScale().getScaledInstance(SCREEN_SIZE/4, SCREEN_SIZE/4, Image.SCALE_DEFAULT), 0, 0, null);

        labelNourriture.setText(String.valueOf(modele.getStockageNourriture()));
        labelBois.setText(String.valueOf(modele.getStockageBois()));
        labelEau.setText(String.valueOf(modele.getStockageEau()));
        /*labelNourriture.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/80);
        labelBois.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/15);
        labelEau.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/8);*/

        labelNourriture.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/18);
        labelBois.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/9);
        labelEau.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/6);

        //labelBois.update(g);

        /*labelNourriture = new JLabel(String.valueOf(modele.getStockageNourriture()));
        labelNourriture.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/80, 100, 100);
        labelBois = new JLabel(String.valueOf(modele.getStockageBois()));
        labelBois.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/15, 100, 100);
        labelEau = new JLabel(String.valueOf(modele.getStockageEau()));
        labelEau.setBounds(SCREEN_SIZE/8, SCREEN_SIZE/8, 100, 100);
        //vueGrille.add(label1);
        this.add(labelNourriture);
        this.add(labelBois);
        this.add(labelEau);*/


    }
}