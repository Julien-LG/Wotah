import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class ControleurDragAndDrop implements MouseListener, MouseMotionListener {

    public static boolean activeMenu = false;
    public int selectedPlayerNumber = 0;

    private List<Personnage> personnageList = new ArrayList<Personnage>();

    private Modele modele;
    private CVue vue;
    int size = VueGrille.SCREEN_SIZE/Modele.getHeight();

    public ControleurDragAndDrop(Modele modele, CVue vue){
        this.modele = modele;
        this.vue = vue;

        this.vue.addMouseListener(this);
        this.vue.addMouseMotionListener(this);
    }

    private void doPop(MouseEvent e) {
        PopUpMenu menu = new PopUpMenu(vue.getGame());
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Personnage p: modele.getPersonnages()) {
            //if (mouseX >= p.x && mouseX <= p.x + 50 && mouseY >= p.y && mouseY <= p.y + 50) {
            //if (mouseX >= p.getX() && mouseX <= p.getX() + 40 && mouseY >= p.getY() && mouseY <= p.getY() + 40) {
            System.out.println("clique en " + mouseX + " " + mouseY);
            if (mouseX >= (p.getX()*size) && mouseX/3 <= (p.getX()*size + (2 * size / 3)) && mouseY/3 >= (p.getY()*size) && mouseY <= (p.getY()*size + (2 * size / 3))) {
                //g.drawImage(p.getTexture().getScaledInstance(2 * size / 3, 2 * size / 3, Image.SCALE_DEFAULT), p.getX() * size, p.getY() * size, null);
                selectedPlayerNumber = p.getNumero()+1;
                /*j.setX(mouseX - 25);
                j.setY(mouseY - 25);
                repaint();*/
                System.out.println("detect player");
            }
        }

        if (activeMenu){
            // On efface le menu si on clique à côté
            //repaint();
            activeMenu = false;
            // On renvoi le personnage à son emplacement de base d'avant le drag and drop
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedPlayerNumber != 0){
            // On affiche le menu et on le définit comme ouvert
            doPop(e);
            activeMenu = true;
        }

        //doPop(e);
        selectedPlayerNumber = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (Personnage p: modele.getPersonnages()) {
            //if (mouseX >= p.x && mouseX <= p.x + 50 && mouseY >= p.y && mouseY <= p.y + 50) {
            //if (p.getNumero() == (selectedPlayerNumber-1) && mouseX >= p.getX() && mouseX <= p.getX() + 40 && mouseY >= p.getY() && mouseY <= p.getY() + 40) {
            if (p.getNumero() == (selectedPlayerNumber-1) && mouseX >= (p.getX()*size) && mouseX <= (p.getX()*size + 40) && mouseY >= (p.getY()*size) && mouseY <= (p.getY()*size + 40)) {
                p.setX(mouseX - 25);
                p.setY(mouseY - 25);
                //paintComponent(this.getGraphics(),p3);
                //vue.repaintVueGrille();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
