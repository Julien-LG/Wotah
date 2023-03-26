import java.awt.event.*;

class Controleur implements KeyListener {

    private CVue vue;
    private Modele modele;

    public Controleur(Modele modele, CVue vue) {
        this.vue = vue;
        this.modele = modele;
        this.vue.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
        Personnage p = modele.getPersonnages().get(0);
        int x = p.getX();
        int y = p.getY();
		switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: y -= 1; break;
            case KeyEvent.VK_DOWN: y += 1; break;
            case KeyEvent.VK_LEFT: x -= 1; break;
            case KeyEvent.VK_RIGHT: x += 1; break;
		}
        modele.deplacePersonnage(0, x, y);
        vue.repaintVueGrille();
	}
}