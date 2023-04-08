package Controle;


import Vue.CVue;
import Vue.VueGrille;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class ControleurDragAndDrop implements MouseListener, MouseMotionListener {

    public static boolean activeMenu = false;
    public int selectedPlayerNumber = -1;

    private List<Personnage> personnageList = new ArrayList<Personnage>();

    private Modele modele;
    private CVue vue;
    private final static int size = CVue.size;

    /// Stockage des valeurs x y du personnage avant le drag and drop
    private int oldNumPlayer;
    private int oldPositionX;
    private int oldPositionY;

    public ControleurDragAndDrop(Modele modele, CVue vue){
        this.modele = modele;
        this.vue = vue;

        this.vue.getGame().addMouseListener(this);
        this.vue.getGame().addMouseMotionListener(this);
    }

    private void doPop(MouseEvent e) {
        PopUpMenu menu = new PopUpMenu(vue.getGame(), modele, selectedPlayerNumber, this, vue);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        ////////////////////////////////////

        Component parent = SwingUtilities.getRoot(vue.getGame());
        Point panelLocation = vue.getGame().getLocationOnScreen();
        Point ecranLocation = new Point(panelLocation.x - parent.getLocationOnScreen().x, panelLocation.y - parent.getLocationOnScreen().y);

        ///////////////////////////////////

        for (Personnage p: modele.getPersonnages()) {
            Point personnageLocation = new Point(p.getX() * size, p.getY() * size);
            Point positionEcranPersonnage = new Point(personnageLocation.x + ecranLocation.x, personnageLocation.y + ecranLocation.y);

            if (!p.getIsActive() && mouseX-(3*size) >= positionEcranPersonnage.x && mouseX-(3*size) <= positionEcranPersonnage.x + 40 && mouseY >= positionEcranPersonnage.y && mouseY <= positionEcranPersonnage.y + 40) {
                System.out.println("Modele.Personnage " + p.getNumero() + " sélectionné");
                selectedPlayerNumber = p.getNumero();
                oldPositionX = p.getX();
                oldPositionY = p.getY();
            }
        }

        if (activeMenu){
            // On efface le menu si on clique à côté
            //repaint();
            //vue.repaintVueGrille();
            activeMenu = false;
            // On renvoi le personnage à son emplacement de base d'avant le drag and drop
            goToOldPosition();
        }
    }

    public void goToOldPosition(){
        if (oldNumPlayer != -1) {
            Personnage p = modele.getPersonnages().get(oldNumPlayer);
            p.setX(oldPositionX);
            p.setY(oldPositionY);
            vue.repaintVueGrille();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedPlayerNumber != -1){
            // On affiche le menu et on le définit comme ouvert
            doPop(e);
            activeMenu = true;
        }

        oldNumPlayer = selectedPlayerNumber;
        selectedPlayerNumber = -1;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Personnage p: modele.getPersonnages()) {
            if (p.getNumero() == (selectedPlayerNumber)) {
                p.setX(((mouseX)/(size))-3);
                p.setY(mouseY/size);

                vue.repaintVueGrille();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
