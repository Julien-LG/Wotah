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
    int size = VueGrille.SCREEN_SIZE/Modele.getHeight();

    /// Stockage des valeurs x y du personnage avant le drag and drop
    private int oldNumPlayer;
    private int oldPositionX;
    private int oldPositionY;

    public ControleurDragAndDrop(Modele modele, CVue vue){
        this.modele = modele;
        this.vue = vue;

        /*this.vue.addMouseListener(this);
        this.vue.addMouseMotionListener(this);*/
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

        /*Point personnageLocation = new Point(modele.getPersonnages().get(0).getX() * size, modele.getPersonnages().get(0).getY() * size);
        Point positionEcranPersonnage = new Point(personnageLocation.x + ecranLocation.x, personnageLocation.y + ecranLocation.y);

        int x = positionEcranPersonnage.x;
        int y = positionEcranPersonnage.y;*/

        ///////////////////////////////////

        for (Personnage p: modele.getPersonnages()) {
            Point personnageLocation = new Point(p.getX() * size, p.getY() * size);
            Point positionEcranPersonnage = new Point(personnageLocation.x + ecranLocation.x, personnageLocation.y + ecranLocation.y);

            //if (mouseX >= p.x && mouseX <= p.x + 50 && mouseY >= p.y && mouseY <= p.y + 50) {
            //if (mouseX >= p.getX() && mouseX <= p.getX() + 40 && mouseY >= p.getY() && mouseY <= p.getY() + 40) {
            //if (((mouseX/3) >= (p.getX()*size)- (2 * size / 3) && (mouseX/3) <= ((p.getX()*size) + (2 * size / 3) )) && ((mouseY) >= (p.getY()*size) && (mouseY) <= ((p.getY()*size) + (2 * size / 3)))) {
            /*System.out.println(mouseX >= positionEcranPersonnage.x && mouseX <= positionEcranPersonnage.x + 40);
            System.out.println(mouseX >= positionEcranPersonnage.x);
            System.out.println(mouseX <= positionEcranPersonnage.x + 40);
            System.out.println(mouseX);
            System.out.println(positionEcranPersonnage.x+ 40);*/
            //System.out.println(mouseY >= positionEcranPersonnage.y && mouseY <= positionEcranPersonnage.y + 40);
            if (!p.getIsActive() && mouseX-(3*size) >= positionEcranPersonnage.x && mouseX-(3*size) <= positionEcranPersonnage.x + 40 && mouseY >= positionEcranPersonnage.y && mouseY <= positionEcranPersonnage.y + 40) {
                System.out.println("Personnage " + p.getNumero() + " sélectionné");
                selectedPlayerNumber = p.getNumero();
                oldPositionX = p.getX();
                oldPositionY = p.getY();
                /*j.setX(mouseX - 25);
                j.setY(mouseY - 25);
                repaint();*/
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

        //doPop(e);
        oldNumPlayer = selectedPlayerNumber;
        selectedPlayerNumber = -1;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        Component parent = SwingUtilities.getRoot(vue.getGame());
        Point panelLocation = vue.getGame().getLocationOnScreen();
        Point ecranLocation = new Point(panelLocation.x - parent.getLocationOnScreen().x, panelLocation.y - parent.getLocationOnScreen().y);

        for (Personnage p: modele.getPersonnages()) {

            Point personnageLocation = new Point(p.getX() * size, p.getY() * size);
            Point positionEcranPersonnage = new Point(personnageLocation.x + ecranLocation.x, personnageLocation.y + ecranLocation.y);

            if (p.getNumero() == (selectedPlayerNumber)) {
                p.setX(((mouseX)/(size))-3);
                p.setY(mouseY/size);

                vue.repaintVueGrille();
                //vue.affichePersonnages(vue.getGame().getGraphics());
                //vue.getGame().paint(vue.getGame().getGraphics());
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        /*Point panelLocation = vue.getGame().getLocationOnScreen();
        //System.out.println("clique en " + e.getX()/size + " " + e.getY());
        //System.out.println("clique en " + (e.getXOnScreen() - panelLocation.x)/size + " " + (e.getYOnScreen() - panelLocation.y)/size);
        System.out.println("clique en " + (e.getXOnScreen() - panelLocation.x) + " " + (e.getYOnScreen() - panelLocation.y));
        System.out.println("panel en " + (panelLocation.x) + " " + (panelLocation.y));*/
        /////////////////////////////////////////////////////////////////////////////////////////
        /*Component parent = SwingUtilities.getRoot(vue.getGame());
        Point panelLocation = vue.getGame().getLocationOnScreen();
        Point personnageLocation = new Point(modele.getPersonnages().get(0).getX() * size, modele.getPersonnages().get(0).getY() * size);
        Point ecranLocation = new Point(panelLocation.x - parent.getLocationOnScreen().x, panelLocation.y - parent.getLocationOnScreen().y);
        Point positionEcranPersonnage = new Point(personnageLocation.x + ecranLocation.x, personnageLocation.y + ecranLocation.y);

        int x = positionEcranPersonnage.x;
        int y = positionEcranPersonnage.y;

        //System.out.println("Position du personnage sur l'écran : " + x + ", " + y);

        //System.out.println("souris");
        int mouseX = e.getX();
        int mouseY = e.getY();
        //System.out.println((mouseX - ecranLocation.x)-(3*size) + " " + (mouseY - ecranLocation.y));
        if (mouseX-(3*size) >= positionEcranPersonnage.x && mouseX-(3*size) <= positionEcranPersonnage.x + 40 && mouseY >= positionEcranPersonnage.y && mouseY <= positionEcranPersonnage.y + 40) {
        {
            System.out.println("detection du personnage");
        }

            //if (p.getNumero() == (selectedPlayerNumber-1) && mouseX >= positionEcranPersonnage.x && mouseX <= positionEcranPersonnage.x + 40 && mouseY >= positionEcranPersonnage.y && mouseY <= positionEcranPersonnage.y + 40) {
        }*/
    }
}
