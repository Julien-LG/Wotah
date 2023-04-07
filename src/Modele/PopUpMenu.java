package Modele;

import Controle.ControleurDragAndDrop;
import Modele.Modele;
import Vue.CVue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PopUpMenu extends JPopupMenu implements ActionListener {
    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;
    JMenuItem item5;
    JMenuItem item6;
    JMenuItem item7;
    JMenuItem item8;

    private JFrame frame;
    private JPanel panel;
    private Modele modele;
    private int numeroPersonnage;
    private ControleurDragAndDrop c;
    private CVue vue;

    private int persoX;
    private int persoY;

    //public Modele.PopUpMenu(final JFrame frame) {
    public PopUpMenu(final JPanel panel, Modele modele, int numeroPersonnage, ControleurDragAndDrop c, CVue vue) {
        //this.frame = frame;
        this.panel = panel;
        this.c = c;
        this.vue = vue;

        item1 = new JMenuItem("Récolter eau");
        item2 = new JMenuItem("Récolter bois");
        item3 = new JMenuItem("Récolter nourriture");
        item4 = new JMenuItem("Planter arbre");
        item5 = new JMenuItem("Planter buisson");
        item6 = new JMenuItem("Se déplacer");
        item7 = new JMenuItem("Tenter de fuir");
        item8 = new JMenuItem("Annuler");

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);
        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);
        item8.addActionListener(this);

        Personnage p = modele.getPersonnage(numeroPersonnage);
        persoX = p.getX();
        persoY = p.getY();

        List<Integer> actions = modele.actionsPossiblesPerso(numeroPersonnage, persoX, persoY);
        if (!actions.contains(1)) item1.setEnabled(false);
        if (!actions.contains(2)) item2.setEnabled(false);
        if (!actions.contains(3)) item3.setEnabled(false);
        if (!actions.contains(4)) item4.setEnabled(false);
        if (!actions.contains(5)) item5.setEnabled(false);
        if (!actions.contains(6)) item6.setEnabled(false);
        if (!actions.contains(7)) item7.setEnabled(false);

        /*if (actions.contains(1)) add(item1);
        if (actions.contains(2)) add(item2);
        if (actions.contains(3)) add(item3);
        if (actions.contains(4)) add(item4);
        if (actions.contains(5)) add(item5);
        if (actions.contains(6)) add(item6);
        if (actions.contains(7)) add(item7);
        add(item8);*/

        add(item1);
        add(item2);
        add(item3);
        add(item4);
        add(item5);
        add(item6);
        add(item7);
        add(item8);

        this.modele = modele;
        this.numeroPersonnage = numeroPersonnage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        Personnage p = modele.getPersonnages().get(numeroPersonnage);
        int x = p.getX();
        int y = p.getY();

        if (source.getText().equals("Récolter eau" ) || source.getText().equals("Récolter bois") || source.getText().equals("Récolter nourriture")){
            System.out.println("Récolte");
            System.out.println("perso dans popup " + numeroPersonnage);
            vue.newProgressBar(x* c.size,y* c.size);
            new ActionTimer2(modele, numeroPersonnage).run(1, x, y);
            modele.recolteRessource(numeroPersonnage);

        } else if (source.getText().equals("Planter arbre")) {
            System.out.println("Plante arbre");
            //modele.planteGraine(numeroPersonnage, 0);
        } else if (source.getText().equals("Planter buisson")){
            System.out.println("Plante buisson");
            //modele.planteGraine(numeroPersonnage, 1);
        } else if (source.getText().equals("Se déplacer")){
            System.out.println("Déplace");
            //modele.deplacePersonnage(numeroPersonnage, 0, 0);
            vue.newProgressBar(x* c.size,y* c.size);
        } else if (source.getText().equals("Annuler")){
            System.out.println("Annuler");
        }
        System.out.println("hello");
        c.goToOldPosition();

        /*
        "Tenter de fuir"
        */

        //frame.repaint();

        //DragAndDropTexture2.activeMenu = false;
        //repaint();
    }
}