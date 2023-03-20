import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

class VueGrille extends JPanel implements Observer {

    private Modele modele;

    private final static int TAILLE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/Modele.getHeight();

    public VueGrille(Modele modele) {
        this.modele = modele;

        // modele.addObserver(this);

        Dimension dim = new Dimension(TAILLE * Modele.getWidth(), TAILLE * Modele.getHeight());
        this.setPreferredSize(dim);

        setOpaque(false);
    }

    public void update() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.repaint();

        for (int i = 0; i < Modele.getWidth(); i++) {
            for (int j = 0; j < Modele.getHeight(); j++) {
                if (modele.getZone(i, j).getType() != TypeZone.Mer)
                    paint(g, modele.getZone(i, j), i * TAILLE, j * TAILLE);
            }
        }
    }

    private void paint(Graphics g, Zone z, int x, int y) {

        if (z.getType() == TypeZone.Sable)
            g.drawImage(z.texture.getScaledInstance(TAILLE, TAILLE, Image.SCALE_DEFAULT), x, y, null);
        else if (z.getType() == TypeZone.Sol)
            g.drawImage(z.texture2.getScaledInstance(TAILLE, TAILLE, Image.SCALE_DEFAULT), x, y, null);

        // if (z.artifact != null) {
        //     g.drawImage(z.artifact.texture.getScaledInstance(TAILLE / 2, TAILLE / 2, Image.SCALE_DEFAULT),
        //             x + 2 * TAILLE / 5, y + 2 * TAILLE / 5, null);
        // }
        
        /*for(Personnage p : modele.getPersonnages())
            if(p.getX() == z.x && p.getY() == z.y)
                g.drawImage(p.texture.getScaledInstance(2 * TAILLE / 3, 2 * TAILLE / 3, Image.SCALE_DEFAULT), x, y, null);

        /*

        if (!z.players.isEmpty()) {
            BufferedImage texture = z.players.get(0).id == modele.currentPlayer ? z.players.get(0).pawn.selected_texture
                    : z.players.get(0).pawn.texture;
            g.drawImage(texture.getScaledInstance(2 * TAILLE / 3, 2 * TAILLE / 3, Image.SCALE_DEFAULT), x, y, null);
        }
        */
    }
}