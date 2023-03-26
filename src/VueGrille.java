import java.awt.*;
import javax.swing.*;
import java.util.Iterator;

class VueGrille extends JPanel {

    private Modele modele;
    public Zone zone = new Zone(0, 0, TypeZone.Sol);
    public final static int SCREEN_SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public VueGrille(Modele modele) {
        this.modele = modele;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension((SCREEN_SIZE/Modele.getHeight()) * Modele.getWidth(), SCREEN_SIZE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int size = SCREEN_SIZE/Modele.getHeight();

        for (Zone zone : modele.zonesToRepaint) {
            g.drawImage(zone.getTexture().getScaledInstance(size, size, Image.SCALE_DEFAULT), zone.getX() * size, zone.getY() * size, null);
        } for(Personnage p : modele.getPersonnages()) {
            g.drawImage(p.getTexture().getScaledInstance(2 * size / 3, 2 * size / 3, Image.SCALE_DEFAULT), p.getX() * size, p.getY() * size, null);
            System.out.println("personnage en " + p.getX() * size + " " + p.getY() * size);
        }
    }
}