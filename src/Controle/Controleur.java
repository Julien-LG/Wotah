package Controle;

import Modele.Modele;
import Vue.CVue;
import Modele.Personnage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controleur implements KeyListener {
    private CVue vue;
    private Modele modele;
    private PauseInGame pauseInGame;

    public Controleur(Modele modele, CVue vue) {
        this.vue = vue;
        this.modele = modele;
        this.vue.addKeyListener(this);
        this.pauseInGame = new PauseInGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (shouldPauseGame(e)) {
            pauseGame();
        } else {
            deplacePersonnage(e);
        }
    }

    private void deplacePersonnage(KeyEvent e) {
        Personnage p = modele.getPersonnages().get(0);
        int x = p.getX();
        int y = p.getY();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                y -= 1;
                break;
            case KeyEvent.VK_DOWN:
                y += 1;
                break;
            case KeyEvent.VK_LEFT:
                x -= 1;
                break;
            case KeyEvent.VK_RIGHT:
                x += 1;
                break;
            default:
                break;
        }
        modele.deplacePersonnage(0, x, y);
        // vue.repaintVueGrille();
    }

    private boolean shouldPauseGame(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_P;
    }

    private void pauseGame() {
        pauseInGame.menu();
    }
}
