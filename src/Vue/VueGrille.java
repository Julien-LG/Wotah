package Vue;
import Modele.*;

import java.awt.*;
import javax.swing.*;


public class VueGrille extends JPanel {

    private Modele modele;
    public Zone zone = new Zone(0, 0, TypeZone.Sol);
    private final static int SCREEN_SIZE = CVue.SCREEN_SIZE;
    private final static int size = CVue.size;

    JLabel labelNourriture;
    JLabel labelBois;
    JLabel labelEau;

    JProgressBar progressBarTempete;

    private int hauteurProgressBarTempete = 30;

    public VueGrille(Modele modele) {
        this.modele = modele;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension((SCREEN_SIZE/ Modele.getHeight()) * Modele.getWidth(), SCREEN_SIZE));

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

        this.add(labelNourriture);
        this.add(labelBois);
        this.add(labelEau);

        this.add(progressBarTempete);
    }

    public void paintPersonnages(Graphics g){
        for(Personnage p : modele.getPersonnages()) {
            g.drawImage(p.getTexture().getScaledInstance(2 * size / 3, 2 * size / 3, Image.SCALE_DEFAULT), p.getX() * size, p.getY() * size, null);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Zone zone : modele.zonesToRepaint) {
            g.drawImage(zone.getTexture(), zone.getX() * size, zone.getY() * size, null);
        }
        afficheRessources(g);
        paintPersonnages(g);
        //progressBar(g);
    }



    public void progressBar(){
        progressBarTempete.setBounds(0,SCREEN_SIZE-(hauteurProgressBarTempete*2),250,hauteurProgressBarTempete);

        /// TODO : l'avancement de la progressBar tempete ne doit pas s faire ici
        progressBarTempete.setValue(progressBarTempete.getValue()+1);

    }

    public void afficheRessources(Graphics g) {
        g.drawImage(Textures.sprite_sheet.getTextureNoScale().getScaledInstance(SCREEN_SIZE/4, SCREEN_SIZE/4, Image.SCALE_DEFAULT), 0, 0, null);

        labelNourriture.setText(String.valueOf(modele.getStockageNourriture()));
        labelBois.setText(String.valueOf(modele.getStockageBois()));
        labelEau.setText(String.valueOf(modele.getStockageEau()));

        labelNourriture.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/18);
        labelBois.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/9);
        labelEau.setLocation(SCREEN_SIZE/8, SCREEN_SIZE/6);
    }
}